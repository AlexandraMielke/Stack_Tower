import java.awt.event.*;
import javax.swing.*;

public class Keylistener extends JFrame implements KeyListener{

    public static boolean keyPressed;

    Keylistener()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this);
        Keylistener.keyPressed = false;

        /*label = new JLabel();
        label.setBounds(0, 0, 100, 100);
        label.setBackground(Color.red);
        label.setOpaque(true);

        this.add(label);
        this.setVisible(true);*/
    }

    @Override
    public void keyTyped(KeyEvent e){

        switch(e.getKeyChar())
        {
            default: 
                Keylistener.keyPressed = true; //Press anykey for falling action
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e){

    }

    @Override
    public void keyReleased(KeyEvent e){
        System.out.println("Released; Key char:" +e.getKeyChar());
        //System.out.println("Released; Key code:" +e.getKeyCode());
    }


}
