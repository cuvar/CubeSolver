import java.awt.*;

public class Figure {

    private final int blockNum;
    final int blockType;
    final Color color;
    final int width;
    final int height;

    int x;
    int y;
    boolean softDrop = false;
    boolean hardDrop = false;
    boolean landed = false;


    public Figure(int blockType) {

        this.blockType = blockType;
        this.blockNum = 4;
        this.x = 160;
        this.y = 20;

        switch (blockType) {
            case 0:     //O
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.yellow;
                break;
            case 1:     //T
                width = 3*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.red;
                break;
            case 2:     //I
                width = 1*Frame.MARGIN;
                height = 4*Frame.MARGIN;
                color = Color.blue;
                break;
            case 3:     //J
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.green;
                break;
            case 4:     //L
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.white;
                break;
            case 5:     //S
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.magenta;
                break;
            case 6:     //Z
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.cyan;
                break;
            default:
                width = 0;
                height = 0;
                color = Color.pink;
                break;
        }

    }


    void gravity() {
        this.y += 5;
    }

    void softDrop(int ground) {
        softDrop = true;
        hardDrop = false;

        int dif = (ground + Frame.MARGIN) - (this.y + this.height);
        if(dif >= 20) {
            this.y += 20;
        } else {
            this.y+= dif;
        }
        softDrop = false;
        hardDrop = true;
    }

    void hardDrop(int ground) {
        softDrop = false;
        hardDrop = true;

        this.y = ground - this.height;
        this.landed = true;

        softDrop = true;
        hardDrop = false;
    }

    //rotates blocks 90° to the right
    void rotate(){



    }

    int getBlockType() {
        return this.blockType;
    }

    boolean hasLanded(int ground){
        if(this.y + this.height == ground + Frame.MARGIN) {
            return true;
        } else {
            return false;
        }
    }

    boolean isFalling(int ground){
        if(this.y + this.height < ground + Frame.MARGIN){
            return true;
        } else {
            return false;
        }
    }
}
