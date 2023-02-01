public abstract class Character extends Entity {

    String name;
    int x, y;
    Inventory inventory;
    int experience;
    int level;
    int strength, charisma, dexterity;
    // formule

    public boolean buyPotion(Potion potion){
        if(inventory.coins >= potion.getPrice() && inventory.getWeight() >= potion.getWeightPotion()) {
            inventory.addPotion(potion);
            inventory.coins -= potion.getPrice();
            return true;
        }
        else
            System.out.println("Could not buy potion");
        return false;

    }

}
