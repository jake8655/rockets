import greenfoot.*;

import java.util.ArrayList;

public abstract class Spaceship extends Actor {
    protected int score;
    protected int currentSpeed;

    private int BOOST_DURATION = 2000;

    private int reloadCooldown;
    // Have to use a list because of accurate time tracking of multiple boosts activated at
    // once.
    private ArrayList<Long> boostStartTimes;

    private int shieldHealth;

    protected boolean frozen;

    public Spaceship(int initialScore, int initialSpeed) {
        this.reloadCooldown = 0;
        this.boostStartTimes = new ArrayList<Long>();

        this.score = initialScore;
        this.currentSpeed = initialSpeed;
        this.shieldHealth = 0;
        this.frozen = false;
    }

    public void increaseScore(int by) {
        this.score += by;
    }

    public void decreaseScore(int by) {
        if (this.shieldHealth - by >= 0) {
            this.shieldHealth -= by;
            return;
        }

        int toDecrease = by - this.shieldHealth;
        this.shieldHealth = 0;
        this.score -= toDecrease;
    }

    public int getScore() {
        return this.score;
    }

    public void act() {
        this.handleSpeedReduction();

        if (this.reloadCooldown > 0) {
            this.reloadCooldown--;
        }

        this.handleDeath();
    }

    protected void shoot() {
        if (!this.canShoot()) {
            return;
        }

        World world = this.getWorld();
        Bullet bullet = new Bullet(this);
        int coordX = this.getX();
        int coordY = this.getY();

        world.addObject(bullet, coordX, coordY);
        bullet.setRotation(this.getRotation());
        bullet.move(75);
        this.reloadCooldown = 10;
    }

    private boolean canShoot() {
        return this.reloadCooldown <= 0;
    }

    private void handleDeath() {
        if (this.score > 0) {
            return;
        }

        World world = this.getWorld();
        world.removeObject(this);
    }

    public void increaseSpeed() {
        this.currentSpeed *= 2;
        this.boostStartTimes.add(System.currentTimeMillis());
    }

    private void handleSpeedReduction() {
        if (this.boostStartTimes.size() == 0) {
            return;
        }

        long currentTime = System.currentTimeMillis();
        ArrayList<Long> toRemove = new ArrayList<Long>();

        for (Long startTime : this.boostStartTimes) {
            if (currentTime - startTime > BOOST_DURATION) {
                this.currentSpeed /= 2;
                toRemove.add(startTime);
            }
        }
        this.boostStartTimes.removeAll(toRemove);
    }

    public void activateShield() {
        this.shieldHealth = 20;
    }

    public abstract void repair(int by);

    public void freeze() {
        this.frozen = true;
    }

    public void unfreeze() {
        this.frozen = false;
    }
}
