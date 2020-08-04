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
    public Point origin;


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
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                origin = new Point(x + Frame.MARGIN, y + Frame.MARGIN);
                break;

            case 1:     //T
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.red;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x + 2 * Frame.MARGIN, y, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                origin = new Point((int) (x + (1.5 * Frame.MARGIN)), (int) (y + (0.5 * Frame.MARGIN)));
                break;

            case 2:     //I
                width = Frame.MARGIN;
                height = 4 * Frame.MARGIN;
                color = Color.blue;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2 * Frame.MARGIN, color);
                blocks[3] = new Block(x, y + 3 * Frame.MARGIN, color);
                origin = new Point(x, y + (2 * Frame.MARGIN));
                break;

            case 3:     //J
                width = 2 * Frame.MARGIN;
                height = 3 * Frame.MARGIN;
                color = Color.green;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + 2 * Frame.MARGIN, color);
                blocks[3] = new Block(x, y + 2 * Frame.MARGIN, color);
                origin = new Point((int) (x + (1.5 * Frame.MARGIN)), (int) (y + (1.5 * Frame.MARGIN)));
                break;

            case 4:     //L
                width = 2 * Frame.MARGIN;
                height = 3 * Frame.MARGIN;
                color = Color.white;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x, y + Frame.MARGIN, color);
                blocks[2] = new Block(x, y + 2 * Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + 2 * Frame.MARGIN, color);
                origin = new Point((int) (x + (0.5 * Frame.MARGIN)), (int) (y + (1.5 * Frame.MARGIN)));
                break;

            case 5:     //S
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.magenta;

                blocks[0] = new Block(x + Frame.MARGIN, y, color);
                blocks[1] = new Block(x + 2 * Frame.MARGIN, y, color);
                blocks[2] = new Block(x, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                origin = new Point((int) (x + (1.5 * Frame.MARGIN)), (int) (y + (1.5 * Frame.MARGIN)));
                break;

            case 6:     //Z
                width = 3 * Frame.MARGIN;
                height = 2 * Frame.MARGIN;
                color = Color.cyan;

                blocks[0] = new Block(x, y, color);
                blocks[1] = new Block(x + Frame.MARGIN, y, color);
                blocks[2] = new Block(x + Frame.MARGIN, y + Frame.MARGIN, color);
                blocks[3] = new Block(x + 2 * Frame.MARGIN, y + Frame.MARGIN, color);
                origin = new Point((int) (x + (1.5 * Frame.MARGIN)), (int) (y + (1.5 * Frame.MARGIN)));
                break;

            default:
                width = 0;
                height = 0;
                color = Color.pink;
                origin = new Point(x,y);
                break;
        }
    }


    void changeX(int dif) {
        this.x += dif;
        origin.x += dif;

        for (Block b : blocks) {
            b.changeX(dif);
        }
    }


    void changeY(int dif) {
        this.y += dif;
        origin.y += dif;

        for (Block b : blocks) {
            b.changeY(dif);
        }
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
        int dif = Frame.GROUND - (y + height);
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

    void rotate() {
        //https://stackoverflow.com/questions/233850/tetris-piece-rotation-algorithm#:~:text=But%20there%20are%20rotation%20algorithms,and%20the%20piece%20is%20rotated.
        if (Frame.GROUND - (y + height) > width) {

            int h = width;
            width = height;
            height = h;

            for (int i = 0; i < BLOCK_NUM; i++) {

                // Translates current coordinate to be relative to (0,0)
                Point translatedCoordinates = new Point(((blocks[i].x - blocks[i].width/2) - origin.x)/blocks[i].width, ((blocks[i].y - blocks[i].width/2) - origin.y)/blocks[i].width);

                System.out.println(translatedCoordinates.x + ", " + translatedCoordinates.y);                //DEBUG

                // actual rotation
                int rotatedX = (int)Math.round(- translatedCoordinates.y * Math.sin(Math.PI/2));
                int rotatedY = (int)Math.round(translatedCoordinates.x * Math.sin(Math.PI/2));

                System.out.println(rotatedY + ", " + rotatedX);         //DEBUG
                System.out.println("-----");

                // Translate to get new coordinates relative to
                // original origin
                rotatedX *= blocks[i].width;
                rotatedY *= blocks[i].width;

                rotatedX += origin.x - blocks[i].width/2;
                rotatedY += origin.y - blocks[i].width/2;

                blocks[i].x = rotatedX;
                blocks[i].y = rotatedY;


                //System.out.println(blocks[i].cx + ", " + blocks[i].cy);
            }

            System.out.println("....................");


        }

    }

    //check if figure is on ground
    boolean hasLanded() {
        return this.y + this.height == Frame.GROUND;
    }

    //check if figure is still falling
    boolean isFalling() {
        return this.y + this.height < Frame.GROUND;
    }
}
