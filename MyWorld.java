import greenfoot.*;

import java.util.List;

public class MyWorld extends World {
    private Rocket[] players = new Rocket[2];

    public MyWorld() {
        super(800, 600, 1);

        Rocket player1 = new Rocket("up", "left", "right", "space");
        this.addObject(player1, 200, 300);

        Rocket player2 = new Rocket("w", "a", "d", "control");
        this.addObject(player2, 600, 300);
        player2.turn(180);

        this.players[0] = player1;
        this.players[1] = player2;
    }

    public void act() {
        this.showText("Score1: " + this.players[0].getScore(), 50, 10);
        this.showText("Score2: " + this.players[1].getScore(), 750, 10);

        this.handleGameOver();

        this.spawnEntities(EnemyShip.class, 2);
        this.spawnEntities(TurboBoost.class, 2);
        this.spawnEntities(Shield.class, 2);
        this.spawnEntities(RepairStation.class, 2);
        this.spawnEntities(BlackHole.class, 2);
        this.spawnEntities(Supernova.class, 2);
    }

    private void handleGameOver() {
        List<Rocket> players = this.getObjects(Rocket.class);

        if (players.size() == 1) {
            Greenfoot.stop();
            this.showText(
                    "Game Over! Last player wins!", this.getWidth() / 2, this.getHeight() / 2);
        }
    }

    private void spawnEntities(Class<? extends Actor> entityClass, int chance) {
        if (Greenfoot.getRandomNumber(500) < chance) {
            try {
                Actor entity = entityClass.getDeclaredConstructor().newInstance();
                this.addObject(
                        entity,
                        Greenfoot.getRandomNumber(this.getWidth()),
                        Greenfoot.getRandomNumber(this.getHeight()));
            } catch (Exception e) {
                return;
            }
        }
    }
}
