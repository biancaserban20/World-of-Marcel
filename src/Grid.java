import java.util.ArrayList;
import java.util.Random;


public class Grid extends ArrayList< ArrayList <Cell> > {
    int length;
    int width;
    Character character;
    Cell current;

    private Grid(int length, int width, Character character) {
        super(width);
        this.length = length;
        this.width = width;
        this.current = new Cell(Cell.Type.EMPTY, 0,0, null, true);
        this.character = character;
        this.character.x = 0;
        this.character.y = 0;
    }

    public static Grid generate(int length, int width, Character character)
    {
        Random random = new Random();
        int no_shops = getRandomNumberInRange(2,4); // number of shops
        int no_enemies = getRandomNumberInRange(4,6); // number of enemies
        boolean randomShop;
        boolean randomEnemy;
        //int ok = 0;
        Grid map = new Grid(length, width, character);
        for(int i = 0;i < width; i++) {
            ArrayList<Cell> row = new ArrayList<Cell>(length);
            for (int j = 0; j < length; j++) {
                if((i != 0 && j != 0)  && (i != width - 1 && j != length - 1)) {
                    if (no_shops > 0) {
                        randomShop = random.nextBoolean();
                        if (randomShop) {
                            row.add(j, new Cell(Cell.Type.SHOP, i, j, new Shop(), false));
                            //ok = 1; // am pus un Shop pe pozitia i,j
                            no_shops--;
                            continue;
                        }
                    }
                    if (no_enemies > 0) {
                        randomEnemy = random.nextBoolean();
                        if (randomEnemy) {
                            row.add(j, new Cell(Cell.Type.ENEMY, i, j, new Enemy(), false));
                            //ok = 1; // am pus un Enemy pe pozitia i,j
                            no_enemies--;
                            continue;
                        }
                    }
                }
                if (i == 0 && j == 0)
                    row.add(j, new Cell(Cell.Type.START, i, j, null, true));
                else if (i == width - 1 && j == length - 1)
                    row.add(j, new Cell(Cell.Type.FINISH, i, j, null, false));
                else
                    row.add(j, new Cell(Cell.Type.EMPTY, i, j, null, false));
            }
            map.add(row);
        }

        map.current = map.get(0).get(0);
        map.current.indicator = true;
        map.current.cell_type = Cell.Type.START;
        return map;
    }

    public static Grid generateHardCodedScenario(int length, int width, Character character)
    {
        Random random = new Random();
        Grid map = new Grid(length, width, character);
        for(int i = 0;i < width; i++) {
            ArrayList<Cell> row = new ArrayList<Cell>(length);
            for (int j = 0; j < length; j++) {
                if (i == 0 && j == 0)
                    row.add(j, new Cell(Cell.Type.START, i, j, null, true));
                else if (i == width - 1 && j == length - 1)
                    row.add(j, new Cell(Cell.Type.FINISH, i, j, null, false));
                else if ( (i == 0 && j == 3) || (i == 1 && j == 3) || (i == 2 && j == 0))
                    row.add(j, new Cell(Cell.Type.SHOP, i, j, new Shop(), false));
                else if(i == 3 && j == 4)
                    row.add(j, new Cell(Cell.Type.ENEMY, i, j, new Enemy(), false));
                else
                    row.add(j, new Cell(Cell.Type.EMPTY, i, j, null, false));
            }
            map.add(row);
        }

        map.current = map.get(0).get(0);
        map.current.indicator = true;
        map.current.cell_type = Cell.Type.START;
        return map;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max)
        {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void goNorth(){
        if(current.Ox == 0)
            System.out.println("Out of the Magic Land, You CANNOT go UP");
        else{
            ArrayList<Cell> row = this.get(current.Ox - 1);
            Cell cell = row.get(current.Oy);
            if(!cell.indicator){
                Random random = new Random();
                if(random.nextInt() % 5 == 0){
                    character.inventory.coins += 5;
                    System.out.println("You found a little treasure.");
                }
                Game.getInstance().story(cell);
            }
            cell.indicator = true;
            current = cell;
            character.x = current.Ox;

        }

    }
    public void goSouth(){
        if(current.Ox == width - 1)
            System.out.println("Out of the Magic Land, You CANNOT go DOWN");
        else{
            ArrayList<Cell> row = this.get(current.Ox + 1);
            Cell cell = row.get(current.Oy);
            if(!cell.indicator){
                Random random = new Random();
                if(random.nextInt() % 5 == 0){
                    character.inventory.coins += 5;
                    System.out.println("You found a little treasure.");
                }
                Game.getInstance().story(cell);
            }
            cell.indicator = true;
            current = cell;
            character.x = current.Ox;


        }
    }
    public void goWest(){
        if(current.Oy == 0)
            System.out.println("Out of the Magic Land, You CANNOT go to the LEFT");
        else{
            ArrayList<Cell> row = this.get(current.Ox);
            Cell cell = row.get(current.Oy - 1);
            if(!cell.indicator){
                Random random = new Random();
                if(random.nextInt() % 5 == 0){
                    character.inventory.coins += 5;
                    System.out.println("You found a little treasure.");
                }
                Game.getInstance().story(cell);
            }
            cell.indicator = true;
            current = cell;
            character.y = current.Oy;
        }
    }
    public void goEast(){
        if(current.Oy == length - 1)
            System.out.println("Out of the Magic Land, You CANNOT go to the RIGHT");
        else{

            ArrayList<Cell> row = this.get(current.Ox);
            Cell cell = row.get(current.Oy + 1);
            if(!cell.indicator){
                Random random = new Random();
                if(random.nextInt() % 5 == 0){
                    character.inventory.coins += 5;
                    System.out.println("You found a little treasure.");
                }
                Game.getInstance().story(cell);
            }
            cell.indicator = true;
            current = cell;
            character.y = current.Oy;

        }
    }

    public void printGrid() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (!this.get(i).get(j).indicator)
                    System.out.print("? ");
                else if (this.get(i).get(j).cell_type == Cell.Type.FINISH) {
                    System.out.print("F ");
                } else if (this.get(i).get(j).cell_type == Cell.Type.START) {
                    System.out.print("P ");
                } else if (this.get(i).get(j).cell_type == Cell.Type.EMPTY) {
                    System.out.print("N ");
                } else
                    System.out.print(this.get(i).get(j).content.toCharacter() + " ");

            }
            System.out.println("");
        }
    }
}
