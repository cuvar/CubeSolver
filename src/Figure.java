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
                width = 2*Panel.margin;
                height = 2*Panel.margin;
                color = Color.yellow;
                break;
            case 1:     //T
                width = 3*Panel.margin;
                height = 2*Panel.margin;
                color = Color.red;
                break;
            case 2:     //I
                width = 4*Panel.margin;
                height = 1*Panel.margin;
                color = Color.blue;
                break;
            case 3:     //J
                width = 2*Panel.margin;
                height = 3*Panel.margin;
                color = Color.green;
                break;
            case 4:     //L
                width = 2*Panel.margin;
                height = 3*Panel.margin;
                color = Color.white;
                break;
            case 5:     //S
                width = 2*Panel.margin;
                height = 2*Panel.margin;
                color = Color.magenta;
                break;
            case 6:     //Z
                width = 2*Panel.margin;
                height = 2*Panel.margin;
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

    void softDrop() {
        softDrop = true;
        hardDrop = false;

        int dif = (Panel.barrierHeight + Panel.margin) - (this.y + this.height);
        if(dif >= 20) {
            this.y += 20;
        } else {
            this.y+= dif;
        }
        softDrop = false;
        hardDrop = true;
    }

    void hardDrop() {
        softDrop = false;
        hardDrop = true;

        this.y = Panel.barrierHeight - this.height;
        this.landed = true;

        softDrop = true;
        hardDrop = false;
    }


    boolean hasLanded(){
        if(this.y + this.height == Panel.barrierHeight + Panel.margin) {
            return true;
        } else {
            return false;
        }
    }

    boolean isFalling(){
        if(this.y + this.height < Panel.barrierHeight + Panel.margin){
            return true;
        } else {
            return false;
        }
    }
}
