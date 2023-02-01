import java.util.List;

public abstract class Entity implements Element<Entity>{

    List <Spell> abilities; // sau sa pun ArrayList?
    int life; // viata curenta
    int maxLife; // viata maxima
    int mana, manaMax;
    boolean fire, ice, earth;

    public void regenerateLife(int value)
    {
        life = life + value;
        if(life > maxLife)
        {
            life = maxLife;
        }
    }
    public void regenerateMana(int value)
    {
        mana = mana + value;
        if(mana > manaMax)
        {
            mana = manaMax;
        }
    }
    public void ability(Spell ability, Entity entity){
        if (mana >= ability.manaPrice) {
            entity.accept(ability);
            mana = mana - ability.manaPrice;
        }
    }

    @Override
    public void accept(Visitor<Entity> visitor) {
        visitor.visit(this);
    }

    abstract void receiveDamage(int value);
    abstract int getDamage();
}
