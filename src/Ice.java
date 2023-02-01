public class Ice extends Spell{
    public Ice() {
        damage = 20;
        manaPrice = 10;
    }

    @Override
    public void visit(Entity entity) {
        if(!entity.ice)
            entity.receiveDamage(damage);
    }
}
