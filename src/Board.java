import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Board(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        this.setLocation(x, y);
        this.setSize(width, height);
        this.setBackground(Frame.COLOR_BG);


    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(0, 0, width - 1, height - 1);

        for(Figure f : Frame.figures){
            //draw figure
            switch (f.blockType) {
                case 0:     //O
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, f.height);
                    break;
                case 1:     //T
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, Frame.MARGIN);
                    g.fillRect(f.x + Frame.MARGIN, f.y + Frame.MARGIN, Frame.MARGIN, Frame.MARGIN);
                    break;
                case 2:     //I
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, f.height);
                    break;
                case 3:     //J
                    g.setColor(f.color);
                    g.fillRect(f.x + Frame.MARGIN, f.y, Frame.MARGIN, f.height);
                    g.fillRect(f.x, f.y + 2*Frame.MARGIN, Frame.MARGIN, Frame.MARGIN);
                    break;
                case 4:     //L
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, Frame.MARGIN, f.height);
                    g.fillRect(f.x + Frame.MARGIN, f.y + 2*Frame.MARGIN, Frame.MARGIN, Frame.MARGIN);
                    break;
                case 5:     //S
                    g.setColor(f.color);
                    g.fillRect(f.x + Frame.MARGIN, f.y, f.width, Frame.MARGIN);
                    g.fillRect(f.x, f.y + Frame.MARGIN, f.width, Frame.MARGIN);
                    break;
                case 6:     //Z
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, Frame.MARGIN);
                    g.fillRect(f.x + Frame.MARGIN, f.y + Frame.MARGIN, f.width, Frame.MARGIN);
                    break;
                default:
                    break;
            }
        }


        Toolkit.getDefaultToolkit().sync();
    }
}
