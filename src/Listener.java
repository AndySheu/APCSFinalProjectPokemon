import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Listener implements ActionListener {

    public Listener() {

        V.frame.addKeyListener(new TAdapter());
        V.frame.setFocusable(true);
        V.frame.setBackground(Color.BLACK);
//        V.frame.setDoubleBuffered(true);
    }


    public void actionPerformed(ActionEvent e) {
        V.panel.repaint();
    }


    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            Main.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            Main.keyPressed(e);
        }
    }

}