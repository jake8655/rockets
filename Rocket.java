import greenfoot.*;

public class Rocket extends Spaceship {
    private String FORWARD;
    private String LEFT;
    private String RIGHT;
    private String SHOOT;

    public Rocket(String forward, String left, String right, String shoot) {
        super(10, 1);

        this.getImage().scale(100, 65);

        this.FORWARD = forward;
        this.LEFT = left;
        this.RIGHT = right;
        this.SHOOT = shoot;
    }

    public void act() {
        super.act();

        handleMovement();
        handleShooting();
    }

    private void handleMovement() {
        if (Greenfoot.isKeyDown(this.FORWARD)) {
            this.move(this.currentSpeed);
        }

        if (Greenfoot.isKeyDown(this.LEFT)) {
            this.turn(-3);
        }

        if (Greenfoot.isKeyDown(this.RIGHT)) {
            this.turn(3);
        }
    }

    private void handleShooting() {
        if (Greenfoot.isKeyDown(this.SHOOT)) {
            this.shoot();
        }
    }
}
