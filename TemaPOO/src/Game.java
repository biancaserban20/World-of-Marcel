import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Game {
    private  List < Account > accounts;
    private Map<Cell.Type, List < String >> stories;
    private static Game game = null;

    private Game(){
        this.accounts = new ArrayList<>();
        this.stories = new HashMap<>();

    }

    public static Game getInstance(){
        if(game == null)
            game = new Game();
        return game;
    }
    public void run () throws InvalidCommandException {
        try {
            Game game = getInstance();
            String p = Paths.get("").toAbsolutePath().toString();
            p += "\\accounts.json";
            String in = Files.readString(Paths.get(p));
            JSONObject jsonObject = new JSONObject(in);
            JSONArray accounts = jsonObject.getJSONArray("accounts");
            getAccounts(accounts);

            String s = Paths.get("").toAbsolutePath().toString();
            s += "\\stories.json";
            String inputStory = Files.readString(Paths.get(s));
            JSONObject jsonObj = new JSONObject(inputStory);
            getStories(jsonObj);

            System.out.println("Enter T for terminal, G for GUI, H for hardcoded");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            if(command.equals("T")){
                System.out.println("Enter email:");
                String email = scanner.next();
                System.out.println("Enter password:");
                String password = scanner.next();
                boolean check = false;
                int index_acc = 0;
                int index = 0;
                for(Account acc : this.accounts){
                    if(acc.info.getCredentials().getEmail().equals(email) && acc.info.getCredentials().getPassword().equals(password)){
                        check = true;
                        System.out.println("Choose a character:");
                        for(int i = 0; i < acc.characters.size(); i++){
                            System.out.println(i + acc.characters.get(i).toString());
                        }
                        index = scanner.nextInt();
                        index_acc = this.accounts.indexOf(acc);
                    }

                }

                Character myHero = this.accounts.get(index_acc).characters.get(index);
                Grid myMap = Grid.generate(5,5,myHero);
                myMap.printGrid();
                while(myHero.life > 0 && !myMap.get((myMap.length) - 1).get(myMap.width - 1).indicator) {
                    System.out.println("Where do you want to go to? N - NORTH, S - SOUTH, W - WEST, E - EAST");
                    command = scanner.next();
                    if(command.equals("N"))
                        myMap.goNorth();
                    else if(command.equals("S"))
                        myMap.goSouth();
                    else if(command.equals("W"))
                        myMap.goWest();
                    else if(command.equals("E"))
                        myMap.goEast();
                    options(myMap.current, myHero);
                    myMap.printGrid();
                }
            }
            else if(command.equals("H")){

                Character myHero = this.accounts.get(0).characters.get(0);
                Grid myMap = Grid.generateHardCodedScenario(5,5,myHero);
                myMap.printGrid();


                String cmd;
                // 3 cells to the right
                for(int i = 0; i < 3; i++) {
                    if(!scanner.next().equals("P")){
                        throw new InvalidCommandException();
                    }
                    myMap.goEast();
                    myMap.printGrid();
                }

                // the cell is a SHOP
                Shop shop = (Shop) myMap.current.content;
                System.out.println("You are in a Shop. You'll buy 2 potions.");
                // print the list of potions
                for (int i = 0; i < shop.potions.size(); i++)
                    System.out.println(i + shop.potions.get(i).toString());
                System.out.println("Before purchase, coins:" + myHero.inventory.coins + " weight " + myHero.inventory.getWeight());

                myHero.buyPotion(shop.potions.get(shop.potions.size()-1));
                shop.getPotion(shop.potions.size()-1);
                myHero.buyPotion(shop.potions.get(shop.potions.size()-1));
                shop.getPotion(shop.potions.size()-1);

                System.out.println("After purchase, coins:" + myHero.inventory.coins + " weight " + myHero.inventory.getWeight());
                System.out.println("These are the available left potions, but you leave the shop!");
                for (int i = 0; i < shop.potions.size(); i++)
                    System.out.println(i + shop.potions.get(i).toString());

                myMap.printGrid();
                if(!scanner.next().equals("P")){
                    throw new InvalidCommandException();
                }
                myMap.goEast();
                myMap.printGrid();


                // 3 cells down
                for(int i = 0; i < 3; i++) {
                    if(!scanner.next().equals("P")){
                        throw new InvalidCommandException();
                    }
                    myMap.goSouth();
                    myMap.printGrid();

                }
                System.out.println("You have entered a legendary battle!");
                Enemy enemy = (Enemy) myMap.current.content;
                System.out.println(enemy.toString());
                System.out.println("You'll enter S three times to use your abilities");
                // in case the hero kills instantly the enemy
                enemy.life += 10;
                for(int i = 0; i < 3; i++) {
                    if(!scanner.next().equals("S")){
                        throw new InvalidCommandException();
                    }
                    System.out.println("You have used a " + myHero.abilities.get(i).getClass() +" spell!");
                    myHero.ability(myHero.abilities.get(i), enemy);
                    System.out.println(enemy.toString());

                }

                System.out.println("It seems the enemy casted an unknown spell!");
                myHero.life -= 20;
                myHero.mana -=20;
                System.out.println("Hero's life " + myHero.life + " hero's mana " + myHero.mana);
                System.out.println("You'll enter P two times to use your potions");

                if(!scanner.next().equals("P")){
                    throw new InvalidCommandException();
                }
                myHero.inventory.potions.get(0).usePotion(myHero);

                if(!scanner.next().equals("P")){
                    throw new InvalidCommandException();
                }
                myHero.inventory.potions.get(0).usePotion(myHero);
                System.out.println("Hero's life " + myHero.life + " hero's mana " + myHero.mana);

                System.out.println("Now it seems you battle the enemy to the death");
                System.out.println(enemy.toString());
                System.out.println("Hero's life " + myHero.life + " hero's mana " + myHero.mana);
                Random random = new Random();
                while(enemy.life>0 && myHero.life>0){
                    System.out.println("Enter A to attack!");
                    if(!scanner.next().equals("A")){
                        throw new InvalidCommandException();
                    }

                    enemy.receiveDamage(myHero.getDamage());

                    boolean chance = random.nextBoolean();
                    if (chance) {
                        System.out.println("You received a normal attack.");
                        myHero.receiveDamage(enemy.getDamage());
                    }
                    else {

                        int nr = random.nextInt(enemy.abilities.size());
                        enemy.ability(enemy.abilities.get(nr), myHero);
                        System.out.println("The enemy casted a " +enemy.abilities.get(nr).getClass() +" spell.");
                    }

                }
                System.out.println(enemy.toString());
                System.out.println("Hero's life " + myHero.life + " hero's mana " + myHero.mana);
                System.out.println("Enter P to outveil a happy ending!");
                // one cell down
                if(!scanner.next().equals("P")){
                    throw new InvalidCommandException();
                }
                myMap.goSouth();
                myMap.printGrid();


            }


        } catch (IOException | InformationIncompleteException e) {
            e.printStackTrace();
        }

    }

    public void getAccounts(JSONArray array) throws InformationIncompleteException {
        Account.Information.InformationBuilder information;
        int no_games;
        List <Character> characters;
        for(int i = 0; i <array.length(); i++){
            JSONObject account = array.getJSONObject(i);
            JSONObject credentials = account.getJSONObject("credentials");
            information = getInformation(account, getCredentials(credentials));
            no_games = Integer.parseInt(account.getString("maps_completed"));
            JSONArray charact = account.getJSONArray("characters");
            characters = getCharacters(charact);
            Account currentAccount = new Account(information.build(), characters, no_games);
            this.accounts.add(currentAccount);
        }
    }

    public Credentials getCredentials(JSONObject object){
        String email, password;
        email = object.getString("email");
        password = object.getString("password");
        return new Credentials(email, password); // return?
    }

    public Account.Information.InformationBuilder getInformation(JSONObject account, Credentials credentials){
        String name = account.getString("name");
        Account.Information.InformationBuilder builder = new Account.Information.InformationBuilder(credentials, name);
        String country = account.getString("country");
        builder.country(country);
        Collection <String> favGames = new TreeSet<>();
        JSONArray games = account.getJSONArray("favorite_games");
        for(int i = 0; i < games.length(); i++)
            favGames.add(games.getString(i));
        builder.games(favGames);
        return builder;
    }

    public List<Character> getCharacters(JSONArray characters){
        List<Character> characterList = new ArrayList<>();
        int level, experience;
        String name, profession;
        Character character;
        CFactory factory = new CFactory();
        for(int i = 0; i < characters.length(); i++){
            profession = characters.getJSONObject(i).getString("profession");
            name = characters.getJSONObject(i).getString("name");
            level = Integer.parseInt(characters.getJSONObject(i).getString("level"));
            experience = characters.getJSONObject(i).getInt("experience");
            character = factory.build(profession, name, level, experience);
            characterList.add(character);
        }
        return characterList;
    }

    public void getStories(JSONObject jsonObj){

        List<String> arrayEmpty = new ArrayList<>();
        List<String> arrayEnemy = new ArrayList<>();
        List<String> arrayStart = new ArrayList<>();
        List<String> arrayFinish = new ArrayList<>();
        List<String> arrayShop = new ArrayList<>();


        JSONArray stories = jsonObj.getJSONArray("stories");
        for(int i = 0; i < stories.length(); i++){
            JSONObject story = stories.getJSONObject(i);
            String type = story.getString("type");
            String value = story.getString("value");

            if(type.equals("EMPTY"))
                arrayEmpty.add(value);
            else if(type.equals("ENEMY"))
                arrayEnemy.add(value);
            else if(type.equals("START"))
                arrayStart.add(value);
            else if(type.equals("FINISH"))
                arrayFinish.add(value);
            else
                arrayShop.add(value);
        }

        this.stories.put(Cell.Type.EMPTY, arrayEmpty );
        this.stories.put(Cell.Type.ENEMY, arrayEnemy);
        this.stories.put(Cell.Type.START, arrayStart);
        this.stories.put(Cell.Type.FINISH, arrayFinish);
        this.stories.put(Cell.Type.SHOP, arrayShop);

    }
    public void options (Cell current, Character myHero){
        Scanner scanner = new Scanner(System.in);
        String command;
        int index;
        Random random = new Random();

        if(current.cell_type == Cell.Type.ENEMY){
            Enemy enemy = (Enemy) current.content;
            if(enemy.life > 0) {
                while (myHero.life > 0 && enemy.life > 0) {
                    System.out.println("Enter A if you want to attack, S to use a spell, P to use a potion");
                    command = scanner.next();
                    if (command.equals("A")) {
                        enemy.receiveDamage(myHero.getDamage());
                    } else if (command.equals("S")) {
                        System.out.println("Choose the spell! I for Ice, F for Fire, E for Earth");
                        command = scanner.next();
                        if (command.equals("I"))
                            myHero.ability(myHero.abilities.get(0), enemy);
                        else if (command.equals("F"))
                            myHero.ability(myHero.abilities.get(1), enemy);
                        else
                            myHero.ability(myHero.abilities.get(2), enemy);
                    } else if (command.equals("P")) {
                        if (!myHero.inventory.potions.isEmpty()) {
                            for (int i = 0; i < myHero.inventory.potions.size(); i++) {
                                System.out.println(i + myHero.inventory.potions.get(i).toString());
                            }
                            System.out.println("Choose the potion you need! Enter the number of the potion:");
                            index = scanner.nextInt();
                            myHero.inventory.potions.get(index).usePotion(myHero);

                        } else {
                            System.out.println("You do not have potions. You'll attack normally");
                            enemy.receiveDamage(myHero.getDamage());
                        }

                        boolean chance = random.nextBoolean();
                        if (chance)
                            myHero.receiveDamage(enemy.getDamage());
                        else {
                            int nr = random.nextInt(enemy.abilities.size());
                            enemy.ability(enemy.abilities.get(nr), myHero);
                        }
                    }

                }
                if (myHero.life <= 0)
                    System.out.println("You are dead. Game Over!");
                else {
                    System.out.println("Dead enemy!");
                    int nr = random.nextInt(10);
                    if (nr < 8) {
                        myHero.inventory.coins += 20;
                        System.out.println("You found a treasure!");
                    }
                }
            }
            else
                System.out.println("You defeated this enemy");
        }

        else if(current.cell_type == Cell.Type.SHOP){
            Shop shop = (Shop) current.content;
            System.out.println("You are in a Shop. Enter B if you want to buy a potion. Enter X if you want to exit");
            command = scanner.next();
            if (command.equals("B"))
                while(!shop.potions.isEmpty() && !command.equals("X")) {
                    for (int i = 0; i < shop.potions.size(); i++)
                        System.out.println(i + shop.potions.get(i).toString());
                    System.out.println("Choose a potion. Enter the number of the potion you need:");
                    index = scanner.nextInt();
                    if(myHero.buyPotion(shop.potions.get(index)))
                        shop.getPotion(index);
                    System.out.println("Continue to buy - B, exit - X");
                    command = scanner.next();
                }
            if( command.equals("X") )
                System.out.println("You left the shop.");
        }

    }

    public void story (Cell current){
            Random random = new Random();
            int nr = random.nextInt(stories.get(current.cell_type).size());
            System.out.println(stories.get(current.cell_type).get(nr));
    }

}
