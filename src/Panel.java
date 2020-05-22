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


        for(Figure f : Frame.figures){
            //draw figure
            switch (f.blockType) {
                case 0:     //O
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, f.height);
                    break;
                case 1:     //T
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, margin);
                    g.fillRect(f.x + margin, f.y + margin, margin, margin);
                    break;
                case 2:     //I
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, f.height);
                    break;
                case 3:     //J
                    g.setColor(f.color);
                    g.fillRect(f.x + margin, f.y, margin, f.height);
                    g.fillRect(f.x, f.y + 2*margin, margin, margin);
                    break;
                case 4:     //L
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, margin, f.height);
                    g.fillRect(f.x + margin, f.y + 2*margin, margin, margin);
                    break;
                case 5:     //S
                    g.setColor(f.color);
                    g.fillRect(f.x + margin, f.y, f.width, margin);
                    g.fillRect(f.x, f.y + margin, f.width, margin);
                    break;
                case 6:     //Z
                    g.setColor(f.color);
                    g.fillRect(f.x, f.y, f.width, margin);
                    g.fillRect(f.x + margin, f.y + margin, f.width, margin);
                    break;
                default:
                    break;
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }
}
