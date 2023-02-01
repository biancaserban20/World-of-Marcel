public class CFactory {
    public Character build(String profession, String name, int level, int experience){
        if(profession.equals("Mage"))
            return new Mage(name,0,0, experience, level);
        else if (profession.equals("Rogue"))
            return new Rogue(name, 0, 0, experience, level);
        return new Warrior(name, 0, 0, experience, level);
    }
}
