import greenfoot.*;

public class TurboBoost extends Boost {
    @Override
    protected void activateEffect(Spaceship ship) {
        ship.increaseSpeed();
    }
}
