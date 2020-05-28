import javax.imageio.ImageReader;
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
    public static final Color COLOR_BG = new Color(60,60,64);

    private Timer timer;
    private JPanel panel;
    private Board boardPanel;
    private Preview previewPanel;

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

        //other objects
        timer = new Timer(50, this);
        timer.start();

        boardPanel = new Board(MARGIN, MARGIN, 16*MARGIN + 1, 26*MARGIN + 1);
        previewPanel = new Preview(boardPanel.getWidth() + MARGIN, MARGIN, 6*MARGIN + 1, 6*MARGIN + 1);


        //Panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(COLOR_BG);
        panel.add(boardPanel);
        panel.add(previewPanel);
        add(panel);

        //init figure
        figure = new Figure(6);

        //init list
        figures = new ArrayList<>();
        figures.add(figure);
    }



    //ActionListener
    //
    @Override
    public void actionPerformed(ActionEvent e) {


        //figure gravity
        if (figure.isFalling(boardPanel.getHeight())){
            if(!figure.softDrop || !figure.hardDrop){
                figure.gravity(boardPanel.getHeight());
                repaint();
            }
        }

        if(figure.hasLanded(boardPanel.getHeight())) { //überarbeiten wenn figures
            figure.landed = true;
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

        //rotates figure
        if(key == KeyEvent.VK_W) {
            if(!figure.landed) {
                figure.rotate();
            }
        }

        //figure to left
        if(key == KeyEvent.VK_A) {
            figure.moveLeft();
        }

        //figure to right
        if(key == KeyEvent.VK_D) {
            figure.moveRight(boardPanel.getWidth());
        }

        //softdrop
        if(key == KeyEvent.VK_S) {
            if(figure.isFalling(boardPanel.getHeight())) {      //überarbeiten wenn figures
                if (!figure.softDrop || !figure.hardDrop) {
                    figure.softDrop(boardPanel.getHeight());
                    repaint();

                }
            } else {
                figure.landed = true;
            }
        }

        //harddrop
        if(key == KeyEvent.VK_SPACE) {
            if(figure.isFalling(boardPanel.getHeight())) {                 //überarbeiten wenn figures
                if (!figure.softDrop || !figure.hardDrop) {
                    figure.hardDrop(boardPanel.getHeight());
                    repaint();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
