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
    boolean indicator; // true = visited
    public Cell(Type type, int x, int y, CellElement cnt, boolean ind){
        cell_type = type;
        Ox = x;
        Oy = y;
        content = cnt;
        indicator = ind;

    }
}
