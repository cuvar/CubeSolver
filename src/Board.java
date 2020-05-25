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
            g.setColor(f.color);
            for (Block b : f.blocks) {
                g.fillRect(b.x, b.y, b.width, b.width);
            }
        }


        Toolkit.getDefaultToolkit().sync();
    }
}
