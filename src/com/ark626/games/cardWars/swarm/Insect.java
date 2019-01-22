package com.ark626.games.cardWars.swarm;

import java.sql.Timestamp;
import com.ark626.games.cardWars.base.engine.drawing.Sprite;
import com.ark626.games.cardWars.base.game.Delay;
import com.ark626.games.cardWars.base.game.Time;
import com.ark626.games.cardWars.base.gameobject.StatObject;
import hyper.Genome;

public class Insect extends StatObject {

    private Delay attackDelay;
    private Delay healthDrainDelay;
    private Genome ki;
    private long started;
    private long killed;

    public Insect(int inventorySize, int exp, boolean levelable, Genome ki, float r, float g, float b) {
        super(inventorySize, exp, levelable);
        attackDelay = new Delay(5);
        attackDelay.end();
        healthDrainDelay = new Delay(5);
        healthDrainDelay.start();
        started = Time.getTime();
        this.setSprite(new Sprite(r, g, b, 32, 32, null));
        this.sprite.init();
        this.ki.generateNetwork();

    }

    private void move(int magX, int magY) {
        float speedX = getStats().getSpeed() * magX;
        float speedY = getStats().getSpeed() * magY;

        if ((speedX >= 0.1 || speedX <= -0.1) && (speedY >= 0.1 || speedY <= -0.1)) {
            speedX = speedX * 0.75f;
            speedY = speedY * 0.75f;
        }

        x += speedX * Time.getDelta();
        y += speedY * Time.getDelta();

    }

    public void setAttackDelay(int time) {
        attackDelay = new Delay(time);
        attackDelay.end();
    }

    public void restartAttackDelay() {
        attackDelay.start();
    }

    public boolean canFeed() {
        if (this.attackDelay.over()) {

            return true;
        }
        return false;
    }

    public void feed() {


        this.getStats()
                .damage(-1);
        restartAttackDelay();
    }

    public void update() {
        healthDrain();
        getActions(null);
        // TODO: Implement view + implement Actions 
    }


    public void Death() {
       this.killed = Time.getTime();
       this.ki.setFitness((int)(killed-started));
       this.remove();
    }
    
    public void getActions(double[] inputs) {
        double[] result = ki.evaluateNetwork(inputs);
        this.move((int)result[0], (int)result[1]);
        
    }

    private void healthDrain() {
        if(this.healthDrainDelay.over()) {
            this.getStats().damage(1);
            this.healthDrainDelay.start();
        }
    }

}
