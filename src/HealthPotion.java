public class HealthPotion implements Potion{
    private int price, weight, regeneration;

    public HealthPotion(int price, int weight, int regeneration)
    {
        this.price = price; // fara this, rename variabile
        this.weight = weight;
        this.regeneration = regeneration;
    }

    @Override
    public void usePotion(Character character) {
        character.regenerateLife(regeneration);
        character.inventory.deletePotion(this);
    }

    @Override
    public String toString() {
        return " HealthPotion{" +
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
