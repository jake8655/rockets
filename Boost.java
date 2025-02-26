import greenfoot.*;

public abstract class Boost extends Actor {
    public Boost() {
        this.getImage().scale(50, 50);
    }

    public void act() {
        World world = this.getWorld();

        Spaceship ship = (Spaceship) this.getOneIntersectingObject(Spaceship.class);

        if (ship == null) {
            return;
        }

        this.activateEffect(ship);
        world.removeObject(this);
    }

    protected abstract void activateEffect(Spaceship ship);
}
