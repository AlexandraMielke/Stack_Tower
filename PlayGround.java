/*
 * Author:  Alexandra Mielke
 * Date:    November 2022
 * 
 * Content: Graphic User Interface class for the Game "Stack Tower". Programmed 
 * as part of the course embedded systems, master electrical engineering.
 * 
 * Last modified:  31.12.2022
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
    private final int[] layers = {-3000,0,300};
    private JLayeredPane layeredPane;
    private JLabel blockpanel1, blockpanel2, blockpanel3, blockpanel4, scoreboard, background;
    private int backgroundPos;
    private JScrollPane scrollpanel;

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
        block1.yposition = 200;
        Block block2 = new Block();
        block2.color = 1;
        block2.height = 100;
        block2.width = 100;
        block2.xposition = 450;
        block2.yposition = 700;
        Block block3 = new Block();
        block3.color = 2;
        block3.height = 100;
        block3.width = 100;
        block3.xposition = 450;
        block3.yposition = 800;
        Block block4 = new Block();
        block4.color = 3;
        block4.height = 100;
        block4.width = 100;
        block4.xposition = 450;
        block4.yposition = 900;
        
        
        field.drawFrame(block1, block2, block3, block4);

        int i = 101;
        int w = 100;
        int flag_forwards = 1;
        int flag_bigger = 1;
        while(true)
        {
            block1.xposition = i;
            block1.width = w;
            field.drawFrame(block1, block2, block3, block4); 
            if(i == 800) flag_forwards = 0;
            if(i == 100) 
            {
                flag_forwards = 1;
                
            }
            if(flag_forwards == 1) 
            {
                i = i+1;
            }
            else 
            {
                i=i-1;
                
            }
            if (w == 150) flag_bigger = 0;
            if (w == 100) flag_bigger = 1;
            if(flag_bigger ==1) w = w+1;
            else w = w-1;
            field.moveBackground();
            PlayGround.pause(3);
        }
                 
    }
    //*************************************************************************** CONSTRUCTOR

    /**
     * PlayGround Object contains all planes and shapes needed for the Game. 
     * Please use initGamePlay to construct an object of this type. Block Objects are only created
     * once and are then reused!
     * 
     * @param layeredPane
     */
    private PlayGround(JLayeredPane layeredPane)
    {
        super(new GridLayout(1,1));

        this.layeredPane = layeredPane;        
    }

    //*************************************************************************** PUBLIC FUNCTIONS

    /**
     * Pauses for the amount of millisec given.
     * 
     * @param millisec
     */
    public static void pause(int millisec)
    {
        long Time0 = System.currentTimeMillis();
        long Time1;
        long runTime = 0;
        while (runTime < millisec) { // 1000 milliseconds or 1 second
            Time1 = System.currentTimeMillis();
            runTime = Time1 - Time0;
        }
    }

    /**
     * Creates the game's GUI by adding a background, 
     * a scoreboard and 4 blockobjects.
     * 
     * @param playingFieldHeight (int): Height of shown application. Default 1000.
     * @param playingFieldWidth (int): Width of application. Default 1000.
     * @return PlayGround Object that contains all panels belonging to the game's GUI.
     */
    public static PlayGround initPlayGround(int playingFieldHeight, int playingFieldWidth)
    { 
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

        //create content
        contentPane.createBackground();
        contentPane.drawScoreBoard();
        contentPane.blockpanel1 = contentPane.createBlock();
        contentPane.blockpanel2 = contentPane.createBlock();      
        contentPane.blockpanel3 = contentPane.createBlock();
        contentPane.blockpanel4 = contentPane.createBlock();

        //Display the window
        mainFrame.setVisible(true);

        //move background to start
        contentPane.backgroundPos = contentPane.scrollpanel.getVerticalScrollBar().getMaximum();
        contentPane.scrollpanel.getVerticalScrollBar().setValue(contentPane.backgroundPos);

        
        //this.drawBackground(0);
        return contentPane;
    }

    /**
     * Changes a blockpanel's size, position and color 
     * according to the parameters stored in 
     * the block's respective Block Object. 
     * Please refer to Block.java for more detail.
     * 
     * @param block1 (Block)
     * @param block2 (Block)
     * @param block3 (Block)
     * @param block4 (Block)
     */
    public void drawFrame(Block block1, Block block2, Block block3, Block block4)
    {
        updateBlock(blockpanel1, block1);
        updateBlock(blockpanel2, block2);
        updateBlock(blockpanel3, block3);
        updateBlock(blockpanel4, block4);
    }

    /**
     * Updates the score shown in the scoreboard.
     * 
     * @param Score
     */
    public void updatePlayerScore(int Score)
    {
        this.scoreboard.setText("Score: " + Integer.toString(PlayGround.playerScore));
    }

    /**
     * Draws the Game Over Screen: Blocks are deleted and a 
     * black panel with the last score is printed.
     */
    public void drawGameOver()
    {
        this.deletePanels();

        JLabel label = new JLabel("GAME OVER! Your score was " + Integer.toString(PlayGround.playerScore));
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 150));
        label.setForeground(Color.white);
        label.setBounds(100,0,(mainFrame.getWidth() - 200), mainFrame.getHeight());
        
        layeredPane.add(label, Integer.valueOf(layers[2])); //Treated as pop-up
    }

    /**
     * Moves the background downwards by one pixel.
     */
    public void moveBackground()
    {
        //Shape of origin picture: 1000x7000 pixel
        int min = this.scrollpanel.getVerticalScrollBar().getMinimum();
        this.backgroundPos = this.backgroundPos - 1;
        if(this.backgroundPos > min)
        {
            this.scrollpanel.getVerticalScrollBar().setValue(this.backgroundPos);
        }
    }

    //*************************************************************************** PRIVATE UTIL FUNCTIONS

    /**
     * Translates the numbers 1-10 into 10 different colors.
     * 
     * @param colornumber (int): Number of range 1-10.
     * @return
     */
    private static Color getColor(int colornumber)
    {
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

    /**
     * Creates single see-through block panel.
     */
    private JLabel createBlock()
    {
        JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(127,127,127, 0)); //create see-through blocks
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setBounds(450,900,100,100);
        layeredPane.add(label, Integer.valueOf(layers[1]));
        return label;
    }

    /**
     * Updates a block panels position, size and color according to the values stored in the Block Object.
     * 
     * @param blockpanel
     * @param block
     */
    private void updateBlock(JLabel blockpanel, Block block)
    {
        //check current block settings to change only if necessary
        if(blockpanel.getBackground() != PlayGround.getColor(block.color))         
            blockpanel.setBackground(PlayGround.getColor(block.color));
        if(blockpanel.getLocation() != new Point (block.xposition, block.yposition))
            blockpanel.setLocation(block.xposition, block.yposition);
        if(blockpanel.getSize() != new Dimension(block.width, block.height))
            blockpanel.setSize(block.width, block.height);
    }

    /**
     * Creates a scoreboard with the score stored in "PlayGround.playerscore"
     */
    private void drawScoreBoard()
    {
        this.scoreboard = new JLabel("Score: " + Integer.toString(PlayGround.playerScore));
        this.scoreboard.setVerticalAlignment(JLabel.CENTER);
        this.scoreboard.setHorizontalAlignment(JLabel.CENTER);
        this.scoreboard.setOpaque(true);
        this.scoreboard.setBackground(new Color(0,0,0, 150));
        this.scoreboard.setForeground(Color.white);
        this.scoreboard.setBorder(BorderFactory.createLineBorder(Color.black));
        this.scoreboard.setBounds(800,900,150, 50);    
        layeredPane.add(this.scoreboard, Integer.valueOf(layers[1])); 
    }

    /**
     * Creates a background panel and adds it to the layeredPane.
     * 
     */
    private void createBackground()
    {
        //ImageIcon backgroundImg = new ImageIcon("image/Background/Background" + Integer.toString(counter) + ".png");
        ImageIcon backgroundImg = new ImageIcon("image/Background/Background.png");
        this.background = new JLabel(backgroundImg);

        this.background.setBounds(0,0,1000,1000);
        this.background.setOpaque(true);
        this.background.setBackground(Color.BLACK);

        //add to frame
        //layeredPane.add(this.background, Integer.valueOf(layers[0]));
        this.createScrollpanel(this.background);
    }

    /**
     * Deletes all block panels and the scoreboard.
     */
    private void deletePanels()
    {
        //clear all old labels --> Dann sind iwie alle Blöcke weg
        Container parent = this.blockpanel1.getParent();
        parent.remove(this.blockpanel1);
        parent = this.blockpanel2.getParent();
        parent.remove(this.blockpanel2);
        parent = this.blockpanel3.getParent();
        parent.remove(this.blockpanel3);
        parent = this.blockpanel4.getParent();
        parent.remove(this.blockpanel4);
        parent = this.scoreboard.getParent();
        parent.remove(this.scoreboard);
        parent.validate();
        parent.repaint();
    }

    /**
     * Creates scroll panel with background as label.
     * 
     * @param background (JLabel): Contains background image
     */
    private void createScrollpanel(JLabel background)
    {
        this.scrollpanel = new JScrollPane(background, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainFrame.add(scrollpanel);
        
    }
}


  
    
  



