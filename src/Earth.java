public class Earth extends Spell{
    public Earth() {
        damage = 10;
        manaPrice = 5;
    }

    @Override
    public void visit(Entity entity) {
        if(!entity.earth)
            entity.receiveDamage(damage);
    }
}
