import java.awt.*;

public class Figure {

    private final int BLOCK_NUM = 4;
    final int blockType;
    final int width;
    final int height;
    final Color color;

    int x;
    int y;
    boolean softDrop = false;
    boolean hardDrop = false;
    boolean landed = false;
    public Block[] blocks = new Block[BLOCK_NUM];


    public Figure(int blockType) {

        this.blockType = blockType;
        this.x = 160;
        this.y = 20;

        switch (blockType) {
            case 0:     //O
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.yellow;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 1:     //T
                width = 3*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.red;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x + 2*Frame.MARGIN, y, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 2:     //I
                width = 1*Frame.MARGIN;
                height = 4*Frame.MARGIN;
                color = Color.blue;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2*Frame.MARGIN, color);
                blocks[3] = new Block(x, y + 3*Frame.MARGIN, color, true);
                break;

            case 3:     //J
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.green;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + 2*Frame.MARGIN, color);
                blocks[3] = new Block(x, y + 2*Frame.MARGIN, color, true);
                break;

            case 4:     //L
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.white;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2*Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + 2*Frame.MARGIN, color, true);
                break;

            case 5:     //S
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.magenta;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + 2*Frame.MARGIN, y, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color, true);
                break;

            case 6:     //Z
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.cyan;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + 2*Frame.MARGIN, color);
                blocks[3] = new Block(x + 2*Frame.MARGIN, y + 2*Frame.MARGIN, color, true);
                break;

            default:
                width = 0;
                height = 0;
                color = Color.pink;
                break;
        }
    }


    Block getCenteredBlock(){
        int k = 0;
        for (int i = 0; i < BLOCK_NUM; i++){
            if(blocks[i].center){
                k = i;
            }
        }
        return blocks[k];
    }


    int getBlockType() {
        return this.blockType;
    }

    //figure's fall speed
    void gravity() {
        //this.y += 5;
        for (Block b : blocks){
            b.y+=5;
        }
    }

    //pressing s lets the figure drop faster
    void softDrop(int ground) {
        softDrop = true;
        hardDrop = false;

        int dif = (ground + Frame.MARGIN) - (getCenteredBlock().y + getCenteredBlock().width);
        if(dif >= 20) {
            //change each y coord of blocks
            for(Block b : blocks) {
                b.y += 20;
            }
        } else {
            //change each y coord of blocks
            for(Block b : blocks) {
                b.y += dif;
            }
        }
        softDrop = false;
        hardDrop = true;
    }

    //presing space immediately drops the figure
    void hardDrop(int ground) {
        softDrop = false;
        hardDrop = true;

        //change each y coord of blocks
        for(Block bb : blocks) {
            int dif = ground - (bb.width + bb.y);
            bb.y += dif;
            System.out.println(bb.y + "" + dif);
            dif = 0;
        }

        this.landed = true;
        softDrop = true;
        hardDrop = false;
    }

    //rotates blocks 90° to the right
    void rotate(){



    }

    //check if figure is on ground
    boolean hasLanded(int ground){
        if(getCenteredBlock().y + getCenteredBlock().width == ground) {
            return true;
        } else {
            return false;
        }
    }

    //check if figure is still falling
    boolean isFalling(int ground){
        if(getCenteredBlock().y + getCenteredBlock().width < ground){
            return true;
        } else {
            return false;
        }
    }
}
