import greenfoot.*;

public class BlackHole extends GalacticItem {
    public BlackHole() {
        super(250);
    }

    @Override
    protected void shipIntersecting(Spaceship ship) {
        ship.freeze();
    }

    @Override
    protected void die(Spaceship intersectingShip) {
        if (intersectingShip != null) {
            intersectingShip.unfreeze();
        }
    }
}
