import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    public static final int margin = 20;
    public static final int barrierWidth = 16 * margin;
    public static final int barrierHeight = 26 * margin;
    private final int previewWidth = 6 * margin;

    public Panel(){
        setBackground(new Color(60,60,64));


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //draw barrier
        g.setColor(Color.white);
        g.drawRect(margin, margin, barrierWidth, barrierHeight);

        g.drawRect(barrierWidth + margin, margin, previewWidth, previewWidth);



        //draw block
        g.setColor(Frame.block.color);
        g.fillRect(Frame.block.x, Frame.block.y, Frame.block.width, Frame.block.width);




        Toolkit.getDefaultToolkit().sync();
    }
}
