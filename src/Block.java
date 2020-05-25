import java.awt.*;

public class Block {

    final int width;
    int x;
    int y;
    final Color color;
    boolean center;
   /*boolean softDrop;
    boolean hardDrop;
    boolean landed;*/

    //for non-centered blocks
    public Block(int x, int y, Color color){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = false;
        /*this.softDrop = false;
        this.hardDrop = false;
        this.landed = false;*/

    }

    //for centered blocks
    public Block(int x, int y, Color color, boolean centered){
        this.width = Frame.MARGIN;
        this.color = color;
        this.x = x;
        this.y = y;
        this.center = centered;
        /*this.softDrop = false;
        this.hardDrop = false;
        this.landed = false;*/

    }


/*
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

    void hardDrop(int ground) {
        softDrop = false;
        hardDrop = true;

        this.y = ground;
        this.landed = true;

        softDrop = true;
        hardDrop = false;
    }

*/

}
