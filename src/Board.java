import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private final int width;
    private final int height;
    private boolean clear;

    public Board(int x, int y, int w, int h){
        this.width = w;
        this.height = h;

        clear = false;

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


    public void clean(){
        clear = true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(0, 0, width - 1, height - 1);

        for(Figure f : Frame.figures){
            //draw figure
            for (Block b : f.blocks) {
                g.setColor(b.color);
                g.fillRect(b.x, b.y, b.width, b.width);
            }

            g.setColor(Color.black);
            g.fillOval(f.origin.x - 4, f.origin.y - 4, 8, 8 );
        }

        if(clear){
            g.setColor(Frame.COLOR_BG);
            g.fillRect(0, 0, width - 1, height - 1);
            g.setColor(Color.red);
            g.drawRect(0, 0, width - 1, height - 1);
        }

        Toolkit.getDefaultToolkit().sync();

    }
}
