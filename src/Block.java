import java.awt.*;

public class Block {

    final int width;
    int x;
    int y;
    final Color color;
    boolean center;

    //constructor for normal blocks
    public Block(int x, int y, Color color){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = false;

    }

    //constructor for centered blocks
    public Block(int x, int y, Color color, boolean centered){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = centered;

    }

    //sets block's position
    void setPos(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }
}
