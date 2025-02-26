import greenfoot.*;

public class RepairStation extends Boost {
    @Override
    protected void activateEffect(Spaceship ship) {
        ship.repair(20);
    }
}
