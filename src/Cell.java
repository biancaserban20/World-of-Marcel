public class Cell {
    int Ox;
    int Oy;
    enum Type{
        START,
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }
    Type cell_type;
    CellElement content;
    boolean indicator; // 0 nevizitata, 1 vizitata
    public Cell(Type type, int x, int y, CellElement cnt, boolean ind){
        cell_type = type;
        Ox = x;
        Oy = y;
        content = cnt;
        indicator = ind;

    }
}
