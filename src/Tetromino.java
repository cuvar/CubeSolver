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

        int k = 0;
        switch (blockType) {
            case 0:     //O
                width = 2*Frame.MARGIN;
                height = 2*Frame.MARGIN;
                color = Color.yellow;

                for(int i = 1; i <= BLOCK_NUM/2; i++) {
                    for(int j = 1; j <= BLOCK_NUM/2; j++) {
                        if(i == 2 && (j == BLOCK_NUM/2)){
                            blocks[k] = new Block(i*x, j*y, color, true);
                        } else  {
                            blocks[k] = new Block(i*x, j*y, color);
                        }
                        k++;
                    }
                }
                k = 0;
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

                for(int i = 0; i < BLOCK_NUM; i++) {
                    if(i == 2){
                        blocks[k] = new Block(x, i*y, color, true);
                    } else  {
                        blocks[k] = new Block(x, i*y, color);
                    }
                }
                k = 0;
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
}
