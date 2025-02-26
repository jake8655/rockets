import greenfoot.*;

import java.util.List;

public class Supernova extends GalacticItem {
    public Supernova() {
        super(250);
    }

    @Override
    protected void shipIntersecting(Spaceship ship) {
        ship.decreaseScore(1);
    }

    @Override
    protected void die(Spaceship intersectingShip) {
        World world = this.getWorld();

        List<Spaceship> ships = world.getObjects(Spaceship.class);
        for (Spaceship ship : ships) {
            ship.decreaseScore(5);
        }
    }
}
