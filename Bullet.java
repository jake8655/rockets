import greenfoot.*;

public class Bullet extends Actor {
    private Spaceship bulletOwner;

    public Bullet(Spaceship bulletOwner) {
        this.bulletOwner = bulletOwner;
    }

    public void act() {
        World world = this.getWorld();

        if (this.isAtEdge()) {
            world.removeObject(this);
            return;
        }

        Spaceship enemy = (Spaceship) this.getOneIntersectingObject(Spaceship.class);

        if (enemy == null) {
            this.move(3);
            return;
        }

        this.bulletOwner.increaseScore();
        enemy.decreaseScore();
        world.removeObject(this);
    }
}
