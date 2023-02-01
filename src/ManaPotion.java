public class ManaPotion implements Potion {

    private int price, weight, regeneration;

    public ManaPotion(int price, int weight, int regeneration)
    {
        this.price = price;
        this.weight = weight;
        this.regeneration = regeneration;
    }

    @Override
    public void usePotion(Character character) {
        character.regenerateMana(regeneration);
        character.inventory.deletePotion(this);
    }

    @Override
    public String toString() {
        return " ManaPotion{" +
                "price=" + price +
                ", weight=" + weight +
                ", regeneration=" + regeneration +
                '}';
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getRegeneration() {
        return regeneration;
    }

    @Override
    public int getWeightPotion() {
        return weight;
    }
}
