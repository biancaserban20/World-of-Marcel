import java.util.ArrayList;
import java.util.List;

public class Inventory {

    List< Potion > potions;
    int maxWeight;
    int coins;

    public Inventory(int maxWeight, int coins){
        this.potions = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.coins = coins;
    }

    public void addPotion(Potion p)
    {
            potions.add(p);
    }
    public void deletePotion(Potion p)
    {
        potions.remove(p);
    }
    public int getWeight(){
        int weight = 0;
        for(int i = 0; i < potions.size(); i++)
            weight = weight + potions.get(i).getWeightPotion();

        return maxWeight - weight;
    }
}
