/*
 * Author:  Alexandra Mielke
 * Date:    November 2022
 * 
 * Content: Graphic User Interface class for the Game "Stack Tower". Programmed 
 * as part of the course embedded systems, master electrical engineering.
 * 
 * Last modified:  30.12.2022
 * 
 */


 //*************************************************************************** IMPORTS
import java.awt.*;
import javax.swing.*;

class PlayGround extends JPanel
{
    /**
     * @param args
     * 
     * Attributes:
     *  playingFieldWidth
     *  playingFieldHeigth
     *  playerScore
     */

    //*************************************************************************** CLASS VARIABLES
    public static int playerScore;

    private static JFrame mainFrame;
    private int[] layers = {-3000,0,300};
    private JLayeredPane layeredPane;
    private JLabel block1, block2, block3, block4;

    public static void main(String[] args)
    {
        //Create first panel
        PlayGround field = initPlayGround(1000, 1000);

        //Create Block objects (would be better, if block color was decided in the constructor)
        Block block1 = new Block();
        block1.color = 4;
        block1.height = 100;
        block1.width = 100;
        block1.xposition = 450;
        block1.yposition = 300;
        Block block2 = new Block();
        block2.color = 1;
        block2.height = 100;
        block2.width = 100;
        block2.xposition = 450;
        block2.yposition = 400;
        Block block3 = new Block();
        block3.color = 2;
        block3.height = 100;
        block3.width = 100;
        block3.xposition = 450;
        block3.yposition = 500;
        Block block4 = new Block();
        block4.color = 3;
        block4.height = 100;
        block4.width = 100;
        block4.xposition = 450;
        block4.yposition = 600;
        
        field.drawBackground(0);
        field.drawScoreBoard();
        field.drawFrame(block1, block2, block3, block4);


        block3.color = 9;
        block3.xposition = 250;
        field.drawFrame(block1, block2, block3, block4);          
    }

    private PlayGround(JLayeredPane layeredPane)
    {
        super(new GridLayout(1,1));

        this.layeredPane = layeredPane;        
    }

    //*************************************************************************** PUBLIC

    public static PlayGround initPlayGround(int playingFieldHeight, int playingFieldWidth)
    {
        /*
         * Creates PlayGround Object. Should only be called once.
         * 
         * Args:
         *      playingFieldHeight (int): Height of the generated field. Should be 1000.
         *      playingFieldWidth (int):  Width of the generated field. Should be 1000.
         * 
         * Returns:
         *      None
         * 
         */
        
        PlayGround.playerScore = 0;    

        //Create empty main background panel
        PlayGround.mainFrame = new JFrame("Stack Tower");  
        PlayGround.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        PlayGround.mainFrame.setSize(playingFieldWidth,playingFieldHeight);
        ImageIcon icon = new ImageIcon("image/Stack_Tower.png");
        PlayGround.mainFrame.setIconImage(icon.getImage());

        //Create content pane
        PlayGround contentPane = new PlayGround(mainFrame.getLayeredPane());
        contentPane.setOpaque(true);
        mainFrame.setContentPane(contentPane);

        //Display the window
        mainFrame.setVisible(true);
        


        //this.drawBackground(0);
        return contentPane;
    }

    public void drawFrame(Block block1, Block block2, Block block3, Block block4)
    {
        /*
         * Draws current Frame.
         * 
         * Args:
         *      blockX (Block): Block Object that contains its height, width, position and color. Please refer to Block.java for more detail.
         * 
         * Returns:
         *      None
         * 
         */

        /*
         * Braucht 4 Blockobjekte
         * move background --> dabei müssten dann alle Blöcke und der Background bewegt werden, wobei Caro die Blockbewegung vorgibt und ich die Hintergrund Bewegung
         * 
         * 
         * Jedes Mal werden alle 4 Blöcke gezeichnet
         * - Entfernen der alten Blöcke
         * - Hinzufügen der neuen Blöcke
         */    
            
        //clear all old labels --> Dann sind iwie alle Blöcke weg
        /*Container parent = this.block1.getParent();
        parent.remove(this.block1);
        parent = this.block2.getParent();
        parent.remove(this.block2);
        parent = this.block3.getParent();
        parent.remove(this.block3);
        parent = this.block4.getParent();
        parent.remove(this.block4);
        parent.validate();
        parent.repaint();*/
        
        this.block1 = this.createRect(block1);
        layeredPane.add(this.block1, Integer.valueOf(layers[1]));

        this.block2 = this.createRect(block2);
        layeredPane.add(this.block2, Integer.valueOf(layers[1]));

        this.block3 = this.createRect(block3);
        layeredPane.add(this.block3, Integer.valueOf(layers[1]));

        this.block4 = this.createRect(block4);
        layeredPane.add(this.block4, Integer.valueOf(layers[1]));
    }

    public void updatePlayerScore(int Score)
    {
        /*
         * Updates text in Player Score 
         * 
         * Args:
         *      score (int): The player's current score.
         * 
         * Returns:
         *      None
         * 
         */
    }

    public void drawGameOver()
    {
        /*
         * 
         * 
         * Args:
         *      None
         * 
         * Returns:
         *      None
         * 
         */
    }

    public void moveBackground()
    {
        /*
         * Description
         * 
         * Args:
         *      None
         * 
         * Returns:
         *      None
         * 
         */
    }

    //*************************************************************************** PRIVATE UTIL FUNCTIONS

    private void drawBackground(int counter)
    {
        /*
         * Description
         * 
         * Args:
         *      None
         * 
         * Returns:
         *      None
         * 
         */
        ImageIcon backgroundImg = new ImageIcon("image/Background/Background" + Integer.toString(counter) + ".png");
        JLabel background = new JLabel(backgroundImg);

        background.setBounds(0,0,1000,1000);
        background.setOpaque(true);
        background.setBackground(Color.BLACK);

        //add to frame
        layeredPane.add(background, Integer.valueOf(layers[0]));
        //return backgroundLabel;
    }

    private static Color getColor(int colornumber)
    {
        /*
         * Description
         * 
         * Args:
         *      None
         * 
         * Returns:
         *      None
         * 
         */
        switch (colornumber)
        {
            case 1:
                return new Color(25, 98, 147, 254); //Blue
            case 2:
                return new Color(255, 127, 15, 254); //Orange
            case 3:
                return new Color(44,160,44, 254); //green
            case 4:
                return new Color(214,39,40, 254); //red
            case 5:
                return new Color(148,103,189,254); //purple
            case 6:
                return new Color(164,52,132, 254); //berry
            case 7:
                return new Color(227,119,194, 254); //rose
            case 8:
                return new Color(140,86,75, 254); //brown
            case 9:
                return new Color(188,189,34, 254); //may green
            case 10:
                return new Color(227,190,3, 254); //mustard yellow
            default:
                return new Color(127,127,127, 254); //grey
        }
    }

    private JLabel createRect(Block block)
    {
        /*
         * Description
         * 
         * Args:
         *      None
         * 
         * Returns:
         *      None
         * 
         */
        JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(PlayGround.getColor(block.color));
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(block.xposition,block.yposition,block.width, block.height);     
        return label;
    }

    private void drawScoreBoard()
    {
        JLabel scoreBoard = new JLabel("Score: " + Integer.toString(PlayGround.playerScore));
        scoreBoard.setVerticalAlignment(JLabel.CENTER);
        scoreBoard.setHorizontalAlignment(JLabel.CENTER);
        scoreBoard.setOpaque(true);
        scoreBoard.setBackground(new Color(0,0,0, 150));
        scoreBoard.setForeground(Color.white);
        scoreBoard.setBorder(BorderFactory.createLineBorder(Color.black));
        scoreBoard.setBounds(800,900,150, 50);    
        layeredPane.add(scoreBoard, Integer.valueOf(layers[1])); 
    }
}


  
    
  



