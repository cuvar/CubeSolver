import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Frame extends JFrame implements ActionListener, KeyListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    public static final int MARGIN = 20;
    public static final int GROUND = 26*MARGIN + 1;
    public static final Color COLOR_BG = new Color(60,60,64);

    private Timer timer;
    private final Board boardPanel;
    private final Preview previewPanel;

    public static Figure figure;
    public static ArrayList<Figure> figures;



    public Frame(){
        //general setup
        setTitle("Tetris");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setFocusable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this);

        boardPanel = new Board(MARGIN, MARGIN, 16*MARGIN + 1, GROUND);
        previewPanel = new Preview(boardPanel.getWidth() + MARGIN, MARGIN, 6*MARGIN + 1, 6*MARGIN + 1);

        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(COLOR_BG);
        panel.add(boardPanel);
        panel.add(previewPanel);
        add(panel);

        //init list
        figures = new ArrayList<>();
        int r = (int) (Math.random() * 7);
        figures.add(new Figure(2));

        //other objects
        timer = new Timer(50, this);
        timer.start();
    }



    //ActionListener
    //
    @Override
    public void actionPerformed(ActionEvent e) {

        //figure gravity
        if (figures.get(0).isFalling()){
            //System.out.println("true");
            if(!figures.get(0).softDrop || !figures.get(0).hardDrop){
                figures.get(0).gravity();
                repaint();
            }
        }

        if(figures.get(0).hasLanded()) { //überarbeiten wenn figures
            figures.get(0).landed = true;
        }
    }



    //KeyListener
    //
    @Override
    public void keyPressed(KeyEvent e) {
       int key = e.getKeyCode();
        //exit game
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

        //harddrop
        if(key == KeyEvent.VK_R) {
            restartGame();
        }

        //rotates figure
        if(key == KeyEvent.VK_W) {
            if(!figures.get(0).landed) {
                figures.get(0).rotate();
                repaint();
            }

        }

        //figure to left
        if(key == KeyEvent.VK_A) {
            figures.get(0).moveLeft();
        }

        //figure to right
        if(key == KeyEvent.VK_D) {
            figures.get(0).moveRight(boardPanel.getWidth());
        }

        //softdrop
        if(key == KeyEvent.VK_S) {
            if(figures.get(0).isFalling()) {      //überarbeiten wenn figures
                if (!figures.get(0).softDrop || !figures.get(0).hardDrop) {
                    figures.get(0).softDrop();
                    repaint();

                }
            } else {
                figures.get(0).landed = true;
            }
        }

        //harddrop
        if(key == KeyEvent.VK_SPACE) {
            if(figures.get(0).isFalling()) {                 //überarbeiten wenn figures
                if (!figures.get(0).softDrop || !figures.get(0).hardDrop) {
                    figures.get(0).hardDrop();
                    repaint();
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}


    //Restarts the game
    public void restartGame(){
        figures.clear();
        boardPanel.clean();

        int r = (int) Math.random() * 6;
        figures.add(new Figure(r));

        timer.restart();
    }
}
