/*
 * Author:  Alexandra Mielke
 * Date:    January 2022
 * 
 * Content: Flaghandler for user interaction
 * 
 * Last modified:  08.01.2023
 * 
 */

import java.awt.event.*;
import javax.swing.*;

/**
 * JFrame type. Adds keylistener for all keys.
 */
public class Keylistener extends JFrame implements KeyListener{

    public static boolean keyPressed;

    /**
     * JFrame type. Adds keylistener for all keys.
     */
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
