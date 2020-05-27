import java.awt.*;

public class Block {

    final int width;
    int x;
    int y;
    final Color color;
    boolean center;

    //constructor
    public Block(int x, int y, Color color){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = false;

    }

    //constructor
    public Block(int x, int y, Color color, boolean centered){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = centered;

    }
}
