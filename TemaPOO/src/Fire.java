public class Fire extends Spell{
    public Fire() {
        damage = 30;
        manaPrice = 15;
    }

    @Override
    public void visit(Entity entity) {
        if(!entity.fire)
            entity.receiveDamage(damage);
    }
}
