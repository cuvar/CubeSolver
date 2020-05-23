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

    private Panel panel;
    private Timer timer;
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

        //Panel
        panel = new Panel();
        add(panel);

        //init figure
        figure = new Figure(1);

        //init list
        figures = new ArrayList<>();
        figures.add(figure);
    }



    //ActionListener
    //
    @Override
    public void actionPerformed(ActionEvent e) {
        //figure gravity
        if (figure.isFalling()){
            if(!figure.softDrop || !figure.hardDrop){
                figure.gravity();
                repaint();
            }
        }

        if(figure.hasLanded()) { //überarbeiten wenn figures
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
            if(figure.x > Panel.margin && !figure.landed) {
                figure.x -= figure.width;
            }
            System.out.println(figure.x + " - " + figure.y + " - " + figure.width);
        }

        //figure to right
        if(key == KeyEvent.VK_D) {
            if(figure.x < Panel.barrierWidth && !figure.landed) {
                figure.x += figure.width;
            }
            System.out.println(figure.x + " - " + figure.y + " - " + figure.width);
        }

        //softdrop
        if(key == KeyEvent.VK_S) {
            if(figure.isFalling()) {      //überarbeiten wenn figures
                if (!figure.softDrop || !figure.hardDrop) {
                    figure.softDrop();
                    repaint();

                }
            } else {
                figure.landed = true;
            }
        }

        //harddrop
        if(key == KeyEvent.VK_SPACE) {
            if(figure.isFalling()) {                 //überarbeiten wenn figures
                if (!figure.softDrop || !figure.hardDrop) {
                    figure.hardDrop();
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
