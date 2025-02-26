import greenfoot.*;

public abstract class GalacticItem extends Actor {
    private int health;

    public GalacticItem(int initialHealth) {
        this.getImage().scale(50, 50);
        this.health = initialHealth;
    }

    public void act() {
        this.health--;

        Spaceship ship = (Spaceship) this.getOneIntersectingObject(Spaceship.class);

        if (this.health <= 0) {
            this.die(ship);
            this.getWorld().removeObject(this);
            return;
        }

        if (ship == null) {
            return;
        }

        this.shipIntersecting(ship);
    }

    protected abstract void die(Spaceship intersectingShip);

    protected abstract void shipIntersecting(Spaceship ship);
}
