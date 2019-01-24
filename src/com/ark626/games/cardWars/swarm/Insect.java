package com.ark626.games.cardWars.swarm;

import java.sql.Timestamp;
import org.lwjgl.Sys;
import com.ark626.games.cardWars.base.engine.drawing.Sprite;
import com.ark626.games.cardWars.base.game.Delay;
import com.ark626.games.cardWars.base.game.Time;
import com.ark626.games.cardWars.base.gameobject.GameObjectTypes;
import com.ark626.games.cardWars.base.gameobject.StatObject;
import hyperneat.HyperNeat;
import neat.Genome;

public class Insect extends StatObject {

    private Delay attackDelay;
    private Delay healthDrainDelay;
    private Genome ki;
    private long started;
    private long killed;
    private double[] inputs;
    private long reward;
    private HyperNeat hyper;

    public Insect(float x, float y,int inventorySize, int exp, boolean levelable, Genome ki, float r, float g, float b,int inputs,int outputs,int hidden) {
        super(inventorySize, exp, levelable);
        init(x, y, r, g, b, 10, 10, game, GameObjectTypes.Insect,null);
        attackDelay = new Delay(10);
        attackDelay.start();
        healthDrainDelay = new Delay(300);
        healthDrainDelay.start();
        started = Time.getTime();
        this.sprite.init();
        this.inputs = new double[inputs];
        this.ki = ki;
        this.ki.generateNetwork();
//        hyper = new HyperNeat(inputs, outputs, hidden, 3   , 10, ki.getParent());
//        hyper.generateweigths(ki);
        this.reward = 0;

    }
    
    private void checkIfOutOfBounds() {
        if(x>810||y>610||y<-10||x<-10) {
            this.reward -=9999;
            this.Death();
        }
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

    public void feed(int amount) {

        if(this.attackDelay.over()) {
        this.getStats()
                .damage(-amount);
        attackDelay.start();
        reward+=100;
        }
    }

    public void update() {
        checkIfOutOfBounds();
        
        healthDrain();
        //System.out.println("Health: "+this.getStats().getCurrentHealth());
        if(getStats().getCurrentHealth()<0) {
            Death();
        }
        //getActions(null);
        // TODO: Implement view + implement Actions 
    }


    public void Death() {
       this.killed = Time.getTime();
       //reward += ((killed-started)/10000000);
       this.ki.setFitness((int)reward,true,true);
       this.remove();
    }
    
    public void getActions(double[] inputs) {
        double[] result = ki.step(inputs);//evaluateNetwork(inputs);
        this.move((int)result[0], (int)result[1]);
        
    }

    private void healthDrain() {
        if(this.healthDrainDelay.over()) {
            this.getStats().damage(1);
            this.healthDrainDelay.start();
        }
    }

}
