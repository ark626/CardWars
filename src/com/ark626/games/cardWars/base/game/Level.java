package com.ark626.games.cardWars.base.game;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import com.ark626.games.cardWars.base.gameobject.GameObject;
import com.ark626.games.cardWars.swarm.Color;
import com.ark626.games.cardWars.swarm.Food;
import com.ark626.games.cardWars.swarm.Insect;
import neat.Genome;
import neat.Pool;
import neat.Species;

public class Level {

    public static final int MAXFOOD = 3;
    public static final int MAXINSECTS = 30;

    private Game game;
    public Pool pool;
    public ArrayList<Insect> insects;
    public ArrayList<Food> foods;
    public ArrayList<Color> colors;
    public Random random;

    public void init(Game game) {
        this.pool = new Pool((MAXFOOD + MAXINSECTS) * 3 + 3, 2);
        insects = new ArrayList<>();
        foods = new ArrayList<>();
        colors = new ArrayList<>();
        random = new Random();
        this.game = game;

    }

    public void update() {
        // TODO: Spawn food here spawn Insects here
        checkIfEnoughFood();
        checkIfAllInsects();
        getInputsForEveryInsect();


    }

    private void getInputsForEveryInsect() {
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.addAll(insects);
        gameObjects.addAll(foods);
        insects.forEach(insect -> {
            insect.getActions(getInputsForInsect(insect, gameObjects));
        });
    }

    private double[] getInputsForInsect(Insect insect, ArrayList<GameObject> gameObjects) {

        double[] input = new double[(MAXFOOD + MAXINSECTS) * 3 + 3];
        input[0] = insect.getX();
        input[1] = insect.getY();
        input[2] = insect.getType()
                .ordinal();

        int i = 3;
        for (GameObject gameObject : gameObjects) {
            if (gameObject != insect) {
                input[i++] = gameObject.getX();
                input[i++] = gameObject.getY();
                input[i++] = gameObject.getType()
                        .ordinal();
            }
        }

        return input;
    }

    private void checkIfAllInsects() {

        int insectsLiving = countInsectsLiving();

        Genome genome = pool.currentGenome();
        
        while (insectsLiving < MAXINSECTS && !pool.checkIfNextGenomeWouldGenerateNextStation()) {
            insectsLiving = countInsectsLiving();
            while((genome == null || genome.getFitness() != 0)) {
            
            if(pool.checkIfNextGenomeWouldGenerateNextStation()) {
                return;
            }
            else {
            pool.nextGenome();
            genome = pool.currentGenome();
            }
            
            }
            if(genome != null&&genome.getFitness()==0) {
            genome.generateNetwork();
            Species species = pool.Species.get(pool.currentSpecies - 1);
            Color color = getColor(species.hashCode());
            spawnInsect(genome, color);
            genome.setFitness(-9, true, false);
            }
        }
        
        if (insectsLiving == 0&&pool.checkIfNextGenomeWouldGenerateNextStation()) {

            System.out.println(
                    "BestFit:" + pool.getbest()
                            .getFitness() + " Generation: " + pool.generation + " PoolMaxFit " + pool.maxFitness);
            pool.newGeneration();
            pool.currentGenome = 1;
            pool.currentSpecies = 1;
        }

    }

    private Color getColor(int hashCode) {

        Optional<Color> optionalColor = checkIfSpeciesHasColor(hashCode);
        if (optionalColor.isPresent()) {
            return optionalColor.get();
        }
        Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), hashCode);
        this.colors.add(color);
        return color;
    }

    private Optional<Color> checkIfSpeciesHasColor(int hashCode) {

        for (Color color : colors) {
            if (color.getHash() == hashCode()) {
                return Optional.of(color);
            }
        }
        return Optional.ofNullable(null);
    }

    private int countInsectsLiving() {

        int count = insects.size();
        // for(Insect insect:insects) {
        // if(!insect.isRemoved()) {
        // count++;
        // }
        // }
        return count;

    }

    private void checkIfEnoughFood() {

        int count = foods.size();
        // for(Food food:foods) {
        // if(!food.isRemoved()) {
        // count++;
        // }
        // }
        if (count < MAXFOOD) {
            spawnFood();
        }

    }

    private void spawnFood() {
        Food food = new Food(random.nextInt(730)+50, random.nextInt(530)+50);

        foods.add(food);
        game.addGameObject(food);
    }

    private void spawnInsect(Genome genome, Color color) {
        Insect insect = new Insect(random.nextInt(40)+20, random.nextInt(50)+20, 1, 10, false, genome, color.getR() / 255f, color.getG() / 255f,
                color.getB() / 255f, (MAXFOOD + MAXINSECTS) * 3,2,2);
        insects.add(insect);
        game.addGameObject(insect);
    }

}
