package com.ark626.games.cardWars.base.game;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import com.ark626.games.cardWars.swarm.Color;
import com.ark626.games.cardWars.swarm.Food;
import com.ark626.games.cardWars.swarm.Insect;
import hyper.Genome;
import hyper.Pool;
import hyper.Species;

public class Level {
    
    public static final int MAXFOOD = 3;
    public static final int MAXINSECTS = 30;
    
    private Game game;
    public Pool pool;
    public ArrayList<Insect> insects;
    public ArrayList<Food> foods;
    public ArrayList<Color> colors;
    public Random random;
    
    public void init() {
        Pool pool = new Pool((MAXFOOD+MAXINSECTS)*3,2);
        
    }
    
    public void update() {
        //TODO: Spawn food here spawn Insects here
        checkIfEnoughFood();
        checkIfAllInsects();
        
    }
    
    private void checkIfAllInsects() {
        
        int insectsLiving = countInsectsLiving();
        while(!pool.checkIfNextGenomeWouldGenerateNextStation()&&insectsLiving<MAXINSECTS) {
            
            pool.nextGenome();
            Genome genome = pool.currentGenome();
            genome.generateNetwork();
            Species species = pool.Species.get(pool.currentSpecies-1);
            Color color = getColor(species.hashCode());
            spawnInsect(genome, color);
        }
    }
    
    private Color getColor(int hashCode) {
        
        Optional<Color> optionalColor = checkIfSpeciesHasColor(hashCode);
        if(optionalColor.isPresent()) {
            return optionalColor.get();
        }
        Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), hashCode);
        this.colors.add(color);
        return color;
    }
    
    private Optional<Color> checkIfSpeciesHasColor(int hashCode) {

        for(Color color:colors) {
            if(color.getHash()==hashCode()) {
                return Optional.of(color);
            }
        }
        return Optional.ofNullable(null);
    }
    
    private int countInsectsLiving() {
        
        int count = 0;
        for(Insect insect:insects) {
            if(!insect.isRemoved()) {
                count++;
            }
        }
        return count;
        
    }
    
    private void checkIfEnoughFood() {
        
        int count = 0;
        for(Food food:foods) {
            if(!food.isRemoved()) {
                count++;
            }
        }
        if(count<MAXFOOD) {
            spawnFood();
        }
        
    }
    
    private void spawnFood() {
        Food food = new Food(random.nextInt(800),random.nextInt(600));
        
        foods.add(food);
        game.addGameObject(food);
    }
    
    private void spawnInsect(Genome genome,Color color) {
        Insect insect = new Insect(random.nextInt(800),random.nextInt(600),1,10,false,genome,color.getR(),color.getG(),color.getB(),(MAXFOOD+MAXINSECTS)*3);
        insects.add(insect);
        game.addGameObject(insect);
    }

}
