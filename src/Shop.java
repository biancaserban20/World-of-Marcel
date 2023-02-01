import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements CellElement{

    List<Potion> potions;

    public Shop()
    {
        Random random = new Random();
        potions = new ArrayList<>();
        int no_potions = getRandomNumberInRange(2, 4);
        boolean type;
        for(int i = 0; i < no_potions; i++)
        {
            type = random.nextBoolean();
            if(type)
                potions.add( new HealthPotion(10, 15, 20));
            else
                potions.add(new ManaPotion(15, 20, 15));
        }
        potions.add( new HealthPotion(10, 15, 20));
        potions.add( new ManaPotion(15, 20, 15));
    }

    // metoda pentru generarea unei valori intr-un interval de intregi
    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public Potion getPotion(int index)
    {
       Potion p = potions.get(index);
       potions.remove(index);
       return p;
    }

    @Override
    public char toCharacter() {
        return 'S';
    }
}
