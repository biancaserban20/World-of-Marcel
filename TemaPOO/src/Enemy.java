import java.util.ArrayList;
import java.util.Random;
public class Enemy extends Entity implements CellElement {

    public Enemy() {
        Random random = new Random();
        life = getRandomNumberInRange(10, 20);
        mana = getRandomNumberInRange(20, 100);
        fire = random.nextBoolean();
        ice = random.nextBoolean();
        earth = random.nextBoolean();
        int no_abilities = getRandomNumberInRange(2, 4);
        abilities = new ArrayList<>();
        int type;
        for(int i = 0; i < no_abilities; i++) {
            type = getRandomNumberInRange(1, 3);
            if(type == 1)
                 abilities.add(new Ice());
            else if(type == 2)
                abilities.add(new Fire());
            else
                abilities.add(new Earth());
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max)
        {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    void receiveDamage(int value) {
        Random random = new Random();
        boolean chance = random.nextBoolean();
        if(chance)
            life = life - value;
        else
            System.out.println("The enemy has outsmarted the hero and dodged the damage");
    }

    @Override
    int getDamage() {
        Random random = new Random();
        int damage = getRandomNumberInRange(1, 2);
        boolean chance = random.nextBoolean();
        if(chance)
            return 2 * damage;
        return damage;
    }


    @Override
    public String toString() {
        return "Enemy{" +
                "life=" + life +
                ", mana=" + mana +
                ", fire=" + fire +
                ", ice=" + ice +
                ", earth=" + earth +
                '}';
    }

    @Override
    public char toCharacter() {
        return 'E';
    }

}
