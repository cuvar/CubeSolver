import javax.swing.*;
import java.awt.*;

public class Preview extends JPanel {

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Preview(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        this.setLocation(x, y);
        this.setSize(width, height);
        this.setBackground(Frame.COLOR_BG);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.drawRect(0, 0, width - 1, height - 1);

        Toolkit.getDefaultToolkit().sync();
    }
}
