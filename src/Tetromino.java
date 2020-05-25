import java.awt.*;
import java.lang.reflect.Array;

public class Tetromino {


    private final int BLOCK_NUM = 4;
    final int blockType;
    final Color color;
    final int width;
    final int height;

    int x;
    int y;
    boolean softDrop = false;
    boolean hardDrop = false;
    boolean landed = false;
    private Block[] blocks = new Block[BLOCK_NUM];


    public Tetromino(int blockType) {

        this.blockType = blockType;
        this.x = 160;
        this.y = 20;

        switch (blockType) {
            case 0:     //O
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.yellow;

                blocks[0] = new Block(1*x, 1*y, color);
                blocks[1] = new Block(2*x, 1*y, color);
                blocks[2] = new Block(1*x, 2*y, color);
                blocks[3] = new Block(2*x, 2*y, color, true);
                break;

            case 1:     //T
                width = 3*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.red;

                blocks[0] = new Block(1*x, 1*y, color);
                blocks[1] = new Block(2*x, 1*y, color, true);
                blocks[2] = new Block(3*x, 1*y, color);
                blocks[3] = new Block(2*x, 2*y, color);
                break;

            case 2:     //I
                width = 1*Frame.MARGIN;
                height = 4*Frame.MARGIN;
                color = Color.blue;

                blocks[0] = new Block(x, 1*y, color);
                blocks[1] = new Block(x, 2*y, color);
                blocks[2] = new Block(x, 3*y, color, true);
                blocks[3] = new Block(x, 4*y, color);
                break;

            case 3:     //J
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.green;

                blocks[0] = new Block(2*x, 1*y, color);
                blocks[1] = new Block(2*x, 2*y, color);
                blocks[2] = new Block(2*x, 3*y, color, true);
                blocks[3] = new Block(1*x, 3*y, color);
                break;

            case 4:     //L
                width = 2*Frame.MARGIN;
                height = 3*Frame.MARGIN;
                color = Color.white;

                blocks[0] = new Block(1*x, 1*y, color);
                blocks[1] = new Block(1*x, 2*y, color);
                blocks[2] = new Block(1*x, 3*y, color, true);
                blocks[3] = new Block(2*x, 3*y, color);
                break;

            case 5:     //S
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.magenta;

                blocks[0] = new Block(2*x, 1*y, color);
                blocks[1] = new Block(3*x, 1*y, color);
                blocks[3] = new Block(2*x, 2*y, color);
                blocks[2] = new Block(1*x, 2*y, color, true);
                break;

            case 6:     //Z
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.cyan;

                blocks[0] = new Block(1*x, 1*y, color);
                blocks[1] = new Block(2*x, 2*y, color);
                blocks[2] = new Block(2*x, 3*y, color, true);
                blocks[3] = new Block(3*x, 3*y, color);
                break;

            default:
                width = 0;
                height = 0;
                color = Color.pink;
                break;
        }
    }
}
