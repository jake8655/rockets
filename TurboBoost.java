import greenfoot.*;

public class TurboBoost extends Actor {
    public TurboBoost() {
        this.getImage().scale(60, 60);
    }

    public void act() {
        World world = this.getWorld();

        Spaceship ship = (Spaceship) this.getOneIntersectingObject(Spaceship.class);

        if (ship == null) {
            return;
        }

        ship.increaseSpeed();
        world.removeObject(this);
    }
}
