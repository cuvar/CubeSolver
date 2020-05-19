import java.awt.*;

public class Block {

    final int width;
    int x;
    int y;
    final Color color;
    boolean softDrop = false;
    boolean hardDrop = false;
    boolean landed = false;

    public Block(int x, int y, Color color){
        this.width = Panel.margin;
        this.color = color;
        this.x = x;
        this.y = y;

    }

    void gravity() {
        this.y += 5;
    }

    void softDrop() {
        softDrop = true;
        hardDrop = false;

        this.y += 20;

        softDrop = false;
        hardDrop = true;
    }

    void hardDrop() {
        softDrop = false;
        hardDrop = true;

        this.y = Panel.barrierHeight;
        this.landed = true;

        softDrop = true;
        hardDrop = false;
    }

}
