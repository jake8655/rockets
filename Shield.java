import greenfoot.*;

public class Shield extends Boost {
    @Override
    protected void activateEffect(Spaceship ship) {
        ship.activateShield();
    }
}
