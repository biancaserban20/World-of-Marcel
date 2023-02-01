import java.util.ArrayList;
import java.util.Random;

public class Mage extends Character{

    public Mage(String name, int x, int y, int experience, int level){
        this.maxLife = 100;
        this.manaMax = 50;
        this.life = this.maxLife;
        this.mana = this.manaMax;
        this.name = name;
        this.x = x;
        this.y = y;
        this.inventory = new Inventory(20, 50);
        this.experience = experience;
        this.level = level;
        this.ice = true;
        this.strength = (5 * level) + 1;
        this.charisma = (10 * level) + 5;
        this.dexterity = (level / 2) + 2;
        this.abilities = new ArrayList<>();
        this.abilities.add(new Ice());
        this.abilities.add(new Fire());
        this.abilities.add(new Earth());
    }
    void receiveDamage(int value) {
        if(life == 0)
            System.out.println(this.name + "is dead. Game Over!");
        else if(dexterity >= 10 && strength >= 3) {
            Random random = new Random();
            if (random.nextBoolean()) {
                life = life - (value / 2);
            }
            else
                life = life - value;
        }
        else
            life = life - value;
    }

    @Override
    int getDamage() {
        int damage = 10;
        if(charisma >= 25) {
            Random random = new Random();
            if (random.nextBoolean())
                return 2 * damage;
        }
        return damage;
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", level=" + level +
                '}';
    }
}
