import greenfoot.*;

import java.util.List;

public class EnemyShip extends Spaceship {
    /** The number of frames (ticks) to wait before choosing a new random direction. */
    private int rotationChangeCooldown;

    private int movementDirection;

    public EnemyShip() {
        super(5, 2);
        this.getImage().scale(110, 75);

        this.rotationChangeCooldown = 50;
        this.movementDirection = Greenfoot.getRandomNumber(360);
    }

    public void act() {
        super.act();

        this.handleMovement();

        Rocket closestPlayer = this.getClosestPlayer();
        if (closestPlayer == null) {
            return;
        }

        this.turnTowards(closestPlayer.getX(), closestPlayer.getY());
        this.shoot();
    }

    private void handleMovement() {
        if (this.rotationChangeCooldown <= 0) {
            this.rotationChangeCooldown = 50;
            this.movementDirection = Greenfoot.getRandomNumber(360);
        }

        this.setRotation(this.movementDirection);
        this.move(this.currentSpeed);
        this.rotationChangeCooldown--;
    }

    private Rocket getClosestPlayer() {
        World world = this.getWorld();

        // Handle the case when the enemy ship was destroyed right before trying to shoot.
        if (world == null) {
            return null;
        }

        List<Rocket> players = world.getObjects(Rocket.class);

        int smallestDistance = Integer.MAX_VALUE;
        Rocket closestPlayer = null;

        for (Rocket player : players) {
            // Get distance between enemy and player using Euclidean distance algorithm.
            int distance =
                    (int) Math.hypot(player.getX() - this.getX(), player.getY() - this.getY());

            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestPlayer = player;
            }
        }

        return closestPlayer;
    }
}
