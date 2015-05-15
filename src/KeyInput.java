import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class KeyInput implements KeyListener
{    
    
    private boolean up, down, left, right;
    
    public KeyInput(JFrame frame){
	
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setFocusTraversalKeysEnabled(false);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
            right = true;
        else if(e.getKeyCode()== KeyEvent.VK_LEFT)
            left = true;
        else if(e.getKeyCode()== KeyEvent.VK_DOWN)
	    down = true;
        else if(e.getKeyCode()== KeyEvent.VK_UP)
            up = true;
    }

    public void keyReleased(KeyEvent e) {
	up=false;down=false;left=false;right=false;
//        if(e.getKeyCode()== KeyEvent.VK_RIGHT)
//            draw.moveRight();
//        else if(e.getKeyCode()== KeyEvent.VK_LEFT)
//            draw.moveLeft();
//        else if(e.getKeyCode()== KeyEvent.VK_DOWN)
//            draw.moveDown();
//        else if(e.getKeyCode()== KeyEvent.VK_UP)
//            draw.moveUp();
    }
    
    public void keyTyped(KeyEvent e) {
        keyPressed(e);
    }

    public static void run(JFrame frame, ImagePanel panel) {
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                MainFrame frame = new MainFrame();
//                frame.setTitle("SQUARE GAME");
                frame.setResizable(false);
                frame.setSize(1000, 1000);
                frame.setMinimumSize(new Dimension(1000, 1000));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		ImageIcon duck = new ImageIcon("./src/Images/Pokemon/493.png");
		panel = new ImagePanel(duck.getImage());
		panel.removeAll();
		pokemonList.add(new Pokemon(493,100,100));
		panel.updateImages(pokemonList);
                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
//            }
//        });
//    }
    }
}
