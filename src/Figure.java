import java.awt.*;

public class Figure {

    private final int BLOCK_NUM = 4;
    final int blockType;
    int width;
    int height;
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
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, Color.red, true);
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
                width = 3*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.magenta;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + 2*Frame.MARGIN, y, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 6:     //Z
                width = 3*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.cyan;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, Color.green);
                blocks[2] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, Color.red, true);
                blocks[3] = new Block(x + 2*Frame.MARGIN, y + Frame.MARGIN, Color.yellow);
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

    //
    void updateBlockPos(int fx, int fy){

        int bwidth = blocks[0].width;

        switch (blockType) {
            case 0:     //O
                blocks[0].setPos(fx,fy);
                blocks[1].setPos(fx + bwidth, fy);
                blocks[2].setPos(fx,fy + bwidth);
                blocks[3].setPos(fx + bwidth, fy + bwidth);
                break;

            case 1:     //T
                blocks[0].setPos(fx,fy);
                blocks[1].setPos(fx + bwidth, fy);
                blocks[2].setPos(fx + 2*bwidth, fy);
                blocks[3].setPos(fx + bwidth, fy + bwidth);
                break;

            case 2:     //I
                blocks[0].setPos(fx,fy);
                blocks[1].setPos(fx, fy + bwidth);
                blocks[2].setPos(fx,fy + 2*bwidth);
                blocks[3].setPos(fx, fy + 3*bwidth);
                break;

            case 3:     //J
                blocks[0].setPos(fx + bwidth, fy);
                blocks[1].setPos(fx + bwidth, fy + bwidth);
                blocks[2].setPos(fx + bwidth,fy + 2*bwidth);
                blocks[3].setPos(fx, fy + 2*bwidth);
                break;

            case 4:     //L
                blocks[0].setPos(fx, fy);
                blocks[1].setPos(fx, fy + bwidth);
                blocks[2].setPos(fx,fy + 2*bwidth);
                blocks[3].setPos(fx + bwidth, fy + 2*bwidth);
                break;

            case 5:     //S
                blocks[0].setPos(fx + bwidth, fy);
                blocks[1].setPos(fx + 2*bwidth, fy);
                blocks[2].setPos(fx,fy + bwidth);
                blocks[3].setPos(fx + bwidth, fy + bwidth);
                break;

            case 6:     //Z
                blocks[0].setPos(fx, fy);
                blocks[1].setPos(fx + bwidth, fy);
                blocks[2].setPos(fx + bwidth,fy + bwidth);
                blocks[3].setPos(fx + 2*bwidth, fy + bwidth);
                break;

            default:
                break;
        }
    }

    //figure's fall speed
    void gravity(int ground) {
        if(y + height + 5 <= ground){
            y+=5;
            updateBlockPos(x,y);
        } else {
            y += ground - (y+height);
            updateBlockPos(x,y);
        }

    }

    //pressing s lets the figure drop faster
    void softDrop(int ground) {
        softDrop = true;
        hardDrop = false;

        int dif = ground - (y + height);
        if(dif >= 20) {
            y += 20;
            updateBlockPos(x,y);
        } else {
            y += dif;
            updateBlockPos(x,y);
        }
        softDrop = false;
        hardDrop = true;
    }

    //presing space immediately drops the figure
    void hardDrop(int ground) {
        softDrop = false;
        hardDrop = true;

        //change each y coord of blocks
        int dif = ground - (getCenteredBlock().width + getCenteredBlock().y);
        y += dif;
        updateBlockPos(x,y);

        this.landed = true;
        softDrop = true;
        hardDrop = false;
    }

    //pressing A moves figure left
    void moveLeft(){
        if(!landed){ //figure hasn't landed already
            if(x > 0) { //figure is in board
                if(x >= Frame.MARGIN) {   //dif is large enough
                    x -= Frame.MARGIN;
                } else {
                    x = 0;
                }
                updateBlockPos(x,y);
            }
        }
    }

    //pressing D moves figure right
    void moveRight(int rightBorder){
        if(!landed){ //figure hasn't landed already
            if(x + width <= rightBorder) { //figure is in board
                int dif = rightBorder - (x + width);  //dif btw figure and border
                if(Frame.MARGIN < dif) {   //dif is large enough
                    x += Frame.MARGIN;
                } else {    //dif isn't large enough
                    x += dif;
                }
                updateBlockPos(x,y);
            }
        }
    }

    //rotates blocks 90° to the right
    void rotate(){
        //switch figure's w and h
        int h = height;
        height = width;
        width = height;

        Block c = getCenteredBlock();

        for(Block b : blocks) {

            int delta = 0;
            int dx = Math.abs(b.x-c.x);
            int dy = Math.abs(b.y-c.y);

           if (dx==dy) {
                delta = dx;
                System.out.println(dx);
            } else if (dx > dy) {
                delta = dx;
            } else {
                delta = dy;
            }




            if(c.x == b.x){
                if(c.y == b.y){
                    //center
                }

                else if(c.y > b.y){
                    //nach oben links
                    b.x += 1 * delta;
                    b.y += 1 * delta;
                }

                else if(c.y < b.y){
                    //nach unten rechts
                    b.x -= 1 * delta;
                    b.y -= 1 * delta;
                }
            }

            else if (c.x > b.x) {
                if(c.y == b.y){
                    //nach oben rechts
                    b.x += 1 * delta;
                    b.y -= 1 * delta;
                }

                else if(c.y > b.y){
                    //nach rechts
                    b.x += 1 * delta;
                }

                else if(c.y < b.y){
                    //nach oben
                    b.y -= 1 * delta;
                }
            }

            else if(c.x < b.x) {
                if(c.y == b.y){
                    //nach unten links
                    b.x -= 1 * delta;
                    b.y += 1 * delta;
                }

                else if(c.y > b.y){
                    //nach unten
                    b.y += 1 * delta;
                }

                else if(c.y < b.y){
                    //nach links
                    b.x -= 1 * delta;
                }
            }
        }



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
