import java.awt.*;

public class Block {

    final int width;
    int x;
    int y;
    int cx;
    int cy;
    final Color color;

    //constructor for normal blocks
    public Block(int x, int y, Color color){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.cx = x + this.width / 2;
        this.cy = y + this.width / 2;

    }

    void changeX(int dx){
        this.x += dx;
    }

    void changeY(int dy){
        this.y += dy;
    }

}
