import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class Frame extends JFrame implements ActionListener, KeyListener {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    private Panel panel;
    private Timer timer;
    public static Block block;

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

        //init block
        block = new Block(20,20, Color.red);
    }



    //ActionListener
    //
    @Override
    public void actionPerformed(ActionEvent e) {
        //block gravity
        if (block.y < Panel.barrierHeight){
            if(!block.softDrop || !block.hardDrop){
                block.gravity();
                repaint();
            }
        }

        if(block.y == Panel.barrierHeight) { //überarbeiten wenn figures
            block.landed = true;
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

        //block to left
        if(key == KeyEvent.VK_A) {
            if(block.x > Panel.margin && !block.landed) {
                block.x -= block.width;
            }
            System.out.println(block.x + " - " + block.y + " - " + block.width);
        }

        //block to right
        if(key == KeyEvent.VK_D) {
            if(block.x < Panel.barrierWidth && !block.landed) {
                block.x += block.width;
            }
            System.out.println(block.x + " - " + block.y + " - " + block.width);
        }

        //softdrop
        if(key == KeyEvent.VK_S) {
            if(block.y < Panel.barrierHeight - Panel.margin) {      //überarbeiten wenn figures
                if (!block.softDrop || !block.hardDrop) {
                    block.softDrop();
                    repaint();

                }
            } else {
                block.landed = true;
            }
        }

        //harddrop
        if(key == KeyEvent.VK_SPACE) {
            if(block.y < Panel.barrierHeight) {                 //überarbeiten wenn figures
                if (!block.softDrop || !block.hardDrop) {
                    block.hardDrop();
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
