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
                width = 2 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.yellow;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 1:     //T
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.red;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x + 2 * Frame.MARGIN, y, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 2:     //I
                width = Frame.MARGIN;
                height = 4 * Frame.MARGIN;
                color = Color.blue;

                blocks[0] = new Block(x, y, Color.red);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2 * Frame.MARGIN, color, true);
                blocks[3] = new Block(x, y + 3 * Frame.MARGIN, color);
                break;

            case 3:     //J
                width = 2 * Frame.MARGIN;
                height = 3 * Frame.MARGIN;
                color = Color.green;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + 2 * Frame.MARGIN, color);
                blocks[3] = new Block(x, y + 2 * Frame.MARGIN, color, true);
                break;

            case 4:     //L
                width = 2 * Frame.MARGIN;
                height = 3 * Frame.MARGIN;
                color = Color.white;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2 * Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + 2 * Frame.MARGIN, color, true);
                break;

            case 5:     //S
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.magenta;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + 2 * Frame.MARGIN, y, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                break;

            case 6:     //Z
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.cyan;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color, true);
                blocks[3] = new Block(x + 2 * Frame.MARGIN, y + Frame.MARGIN, color);
                break;

            default:
                width = 0;
                height = 0;
                color = Color.pink;
                break;
        }
    }


    void changeX(int dif){
        this.x += dif;

        for(Block b : blocks){
            b.changeX(dif);
        }
    }


    void changeY(int dif){
        this.y += dif;

        for(Block b : blocks){
            b.changeY(dif);
        }
    }


    Block getCenteredBlock() {
        int k = 0;
        for (int i = 0; i < BLOCK_NUM; i++) {
            if (blocks[i].center) {
                k = i;
            }
        }
        return blocks[k];
    }


    //figure's fall speed
    void gravity() {
        if (y + height + 5 <= Frame.GROUND) {
            changeY(5);
        } else {
            int inc = Frame.GROUND - (y + height);
            changeY(inc);
        }

    }

    //pressing S lets the figure drop faster
    void softDrop() {
        softDrop = true;
        hardDrop = false;

        int dif = Frame.GROUND - (y + height);
        changeY(Math.min(dif, 20));
        softDrop = false;
        hardDrop = true;
    }

    //pressing space immediately drops the figure
    void hardDrop() {
        softDrop = false;
        hardDrop = true;

        //change each y coord of blocks
        int dif = Frame.GROUND - (getCenteredBlock().width + getCenteredBlock().y);
        changeY(dif);

        this.landed = true;
        softDrop = true;
        hardDrop = false;
    }

    //pressing A moves figure left
    void moveLeft() {
        if (!landed) { //figure hasn't landed already
            if (x > 0) { //figure is in board
                if (x >= Frame.MARGIN) {   //dif is large enough
                    changeX(-Frame.MARGIN);
                } else {
                    changeX(-x);
                }
            }
        }
    }

    //pressing D moves figure right
    void moveRight(int rightBorder) {
        if (!landed) { //figure hasn't landed already
            if (x + width <= rightBorder) { //figure is in board
                int dif = rightBorder - (x + width);  //dif btw figure and border
                //dif is large enough
                changeX(Math.min(Frame.MARGIN, dif));
            }
        }
    }

    //rotates blocks 90° to the right
    void rotate(int ground) {
        if (ground - (y + height) > width) {

            //switch figure's w and h
            int h = height;
            height = width;
            width = h;

            Block c = getCenteredBlock();

            for (Block b : blocks) {

                int delta = 0;
                int dx = Math.abs(b.x - c.x);
                int dy = Math.abs(b.y - c.y);

                if (dx == dy) {
                    delta = dx + dy;
                } else delta = Math.max(dx, dy);


                if (c.x == b.x) {
                    if (c.y == b.y) {
                        //center
                    } else if (c.y > b.y) {
                        //nach oben links
                        b.x += delta;
                        b.y += delta;
                    } else if (c.y < b.y) {
                        //nach unten rechts
                        b.x -= delta;
                        b.y -= delta;
                    }
                } else if (c.x > b.x) {
                    if (c.y == b.y) {
                        //nach oben rechts
                        b.x += delta;
                        b.y -= delta;
                    } else if (c.y > b.y) {
                        //nach rechts
                        b.x += delta;
                    } else if (c.y < b.y) {
                        //nach oben
                        b.y -= delta;
                    }
                } else if (c.x < b.x) {
                    if (c.y == b.y) {
                        //nach unten links
                        b.x -= delta;
                        b.y += delta;
                    } else if (c.y > b.y) {
                        //nach unten
                        b.y += delta;
                    } else if (c.y < b.y) {
                        //nach links
                        b.x -= delta;
                    }
                }

            }
        }

    }

    //check if figure is on ground
    boolean hasLanded(int ground) {
        return this.y + this.height == ground;
    }

    //check if figure is still falling
    boolean isFalling(int ground) {
        return this.y + this.height < ground;
    }
}
