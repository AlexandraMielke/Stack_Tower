import java.awt.*;
import javax.swing.*;

class PlayGround
{
    /**
     * @param args
     * 
     * Attributes:
     *  playingFieldWidth
     *  playingFieldHeigth
     *  playerScore
     */

    public int playingFieldHeight;
    public int playingFieldWidth;
    public int playerScore;
    private JFrame mainFrame;

    public static void main(String[] args)
    {
        //Create first panel
        PlayGround field = new PlayGround(1000, 1000);
        field.drawFrame();

        
        /*Rectangles rects = new Rectangles();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(rects);
        frame.setSize(360, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
          
    }

    public PlayGround(int playingFieldHeight, int playingFieldWidth)
    {
        /* Braucht playingFieldWidth & playingFieldHeight mit Default 1000
         * 
         */
        this.playerScore = 0;
        this.playingFieldHeight = playingFieldHeight;
        this.playingFieldWidth = playingFieldWidth;

        //Create empty main background panel
        JFrame mainFrame = new JFrame("Stack Tower");  
        this.mainFrame = mainFrame;   
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.mainFrame.setSize(this.playingFieldWidth,this.playingFieldHeight);
        ImageIcon icon = new ImageIcon("image/Stack_Tower.png");
        this.mainFrame.setIconImage(icon.getImage());
        /*JDesktopPane panel = new JDesktopPane();
        Color strange_color = new Color(150, 50, 50, 150);
        panel.setBackground(strange_color);        
        this.mainFrame.add(panel);
        this.mainFrame.setVisible(true);*/
        this.drawBackground(0);
    }

    public void drawFrame()
    {
        /*
         * Braucht 4 Blockobjekte (von denen aktuell noch nicht klar ist, wie sie aussehen)
         * move background --> dabei müssten dann alle Blöcke und der Background bewegt werden, wobei Caro die Blockbewegung vorgibt und ich die Hintergrund Bewegung
         * 
         * 
         * Jedes Mal werden alle 4 Blöcke gezeichnet
         * - Entfernen der alten Blöcke
         * - Hinzufügen der neuen Blöcke
         */

         /* Erzeugung eines neuen Frames mit dem 
            Titel "Beispiel JFrame " */       
        
        Rectangles rects = new Rectangles();
        //this.mainFrame.add(rects);
        this.mainFrame.setSize(360, 300);
        this.mainFrame.setLocationRelativeTo(null);
        //this.mainFrame.setVisible(true);
        this.mainFrame.getContentPane().add(rects);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);

        /*JDesktopPane panel = new JDesktopPane();
        Color strange_color = new Color(150, 50, 50, 150);
        panel.setBackground(strange_color);
        
        this.mainFrame.add(panel);
        this.mainFrame.setVisible(true);*/
    }

    public void updatePlayerScore(int Score)
    {

    }

    public void drawGameOver()
    {

    }

    public void moveBackground()
    {

    }

    private void drawBackground(int counter)
    {
        BackgroundImage background = new BackgroundImage (new ImageIcon("image/Background/Background" + Integer.toString(counter) + ".png").getImage());
        this.mainFrame.getContentPane().add(background);
        this.mainFrame.pack();
        this.mainFrame.setVisible(true);
    }

    private Color getColor(int colornumber)
    {
        switch (colornumber)
        {
            case 1:
                return new Color(25, 98, 147, 150); //Blue
            case 2:
                return new Color(255, 127, 15, 150); //Orange
            case 3:
                return new Color(44,160,44, 150); //green
            case 4:
                return new Color(214,39,40, 150); //red
            case 5:
                return new Color(148,103,189,150); //purple
            case 6:
                return new Color(164,52,132, 150); //berry
            case 7:
                return new Color(227,119,194, 150); //rose
            case 8:
                return new Color(140,86,75, 150); //brown
            case 9:
                return new Color(188,189,34, 150); //may green
            case 10:
                return new Color(227,190,3, 150); //mustard yellow
            default:
                return new Color(127,127,127, 150); //grey
        }
    }
}


  
    
  



