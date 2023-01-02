import java.util.Random;             //Für Zufallszahl

public class Block{
    private static final int margin =100;
    
    int color;                              // Feslegen der Zahl für die Farbauswahl der Blöcke z.B. 1=blau, 2=grün ...  
    
                      
    int startPositionX;                   // Anfangsposition links vom spielfelt; Position durch rechte Ecke unten vom Block festgelget; Spielfeldgröße 1000 x 1000
    int startPositionY;                    
                                           
    boolean movingRight;                    // Bewegungsrichtung rechts links speichern 1 = rechts, 2 = links;
    int StartstandingBlockPosition = 450;        // x-Position für den stehendem Block
    boolean blockOnTower;                   // gibt an ob der block auf dem Tower landet oder nicht

    int height;
    int width;
    int xposition;
    int yposition;

    public static void main(String[] args) {

    }

    //Methoden

    //Farbe durch Zufallszahl bestimmen
    public int randomColorBlock(){
        int min = 1;
        int max = 10;

        Random random = new Random();

        color = random.nextInt(max + min) + min; 
        return color;
    }

    //Anfangsposition vom stehenden Block unten, mitte und oben und dem bewegten Block
    public void startPositionStandingBlockDown(){
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - this.height;
    }

    public void startPositionStandingBlockMiddle(){
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - 2*this.height;
    }

    public void startPositionStandingBlockTop(){
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - 3*this.height;
    }

    /**
     * Startet in der Mitte
     * 
     * @param standingBlock Top standing Block (needed for its width)
     */
    public void startpositionMovingBlock(Block standingBlock){
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = 4 * this.height;
        this.width = standingBlock.width;
        
        // Rechte ecke unten von Block gibt die x- und y-Position y-Postion in einem Spielfeld Feld
        // y-Position = Höhe von Spielfeld - höhe vom Block
    }

    //Bewegung und Berechnung (die Aktuelle X-Position muss mit übergeben werden)
    public void movingXAxis(){

        // festlegen: wenn block ganz links -> rechtsbewegung; wenn block ganz rechts -> linksbewegung
        if(this.xposition <= Block.margin){
            movingRight = true;
        }
        if(this.xposition >= Gameplay.playingFieldWidth - 2*Block.margin)
        {
            movingRight = false;
        }

        if(movingRight == true)
        {
            this.xposition = this.xposition + Gameplay.panParameter; //
        }
        else
        {
            this.xposition = this.xposition - Gameplay.panParameter; //Gameplay.panParameter
        }
    }

    // Gibt aus ob der Block auf dem Tower landet (true) oder nicht (false); Aktuelle X-Position vom bewegten und stehenden Block muss übergeben werden
    // Gameplay soll folgende Werte übergeben: 
    //calculateIfOnTower(xPosition vom Fallenden BLock, Xpostion vom obersten stehenden Block, Breite fallender Block)
    public boolean calculatIfOnTower(Block standingBlock){
        
        if(this.xposition <= (Gameplay.playingFieldWidth/2)) //left of standing block
        {
            if((this.xposition + this.width) <= (standingBlock.xposition)) return false;
            else return true;
        }
        else //right of standing block
        {
            if((this.xposition >= (standingBlock.xposition + standingBlock.width))) return false;
            else return true;
        }
        
    }

    //MovingBlock auf Tower fallen lassen
    /**
     * 
     * @return (bool): True if black has landed
     */
    public boolean fall(){
        if(this.yposition < Gameplay.StartHeightBlock*4){
            this.yposition = this.yposition + Gameplay.fallParameter;
            return false;
        }else{
            return true;
        }
        
    }

    /*
    //Der Block wird verkleinert (Wichtig Position des gefallen Block auf dem stehenden Block oder nicht)
    //split(Xpostion vom bewegten Block, Postion vom obersten stehenden Block, Breite vom fallenden Block)
    public void split(int standingBlockPosition, int WidthBlock){
        if(Xposition>standingBlockPosition){
            this.width = WidthBlock - (this.xposition - standingBlockPosition);
        }
        if(Xposition<standingBlockPosition){
            this.width = Xposition - (standingBlockPosition - WidthBlock);
        }

        //neue Position für den stehenden Block; X-Postion vom Bewegten Block und stehnden muss übergeben werden
        // wenn X-Position nicht über dem Block sondern außerhalb (wird abgetrennt), neue Position vom Block bestimmen
        if(Xposition>standingBlockPosition){
            this.xposition = standingBlockPosition;
        }else{
            this.xposition = Xposition;
        }
     }*/

     //Der Block wird verkleinert (Wichtig Position des gefallen Block auf dem stehenden Block oder nicht)
    //split(Xpostion vom bewegten Block, Postion vom obersten stehenden Block, Breite vom fallenden Block)
    /**
     * 
     * @param standingBlock (Block) Top standing block
     */
    public void split(Block standingBlock){
        if(this.xposition<standingBlock.xposition) //too big on the left
        {
            this.width = this.xposition + this.width - standingBlock.xposition;
            this.xposition = standingBlock.xposition;
        }
        else{
            if(this.xposition>standingBlock.xposition) //too big on the right
            {
                this.width = (standingBlock.xposition + standingBlock.width) - this.xposition;
            }
        }
        

        //neue Position für den stehenden Block; X-Postion vom Bewegten Block und stehnden muss übergeben werden
        // wenn X-Position nicht über dem Block sondern außerhalb (wird abgetrennt), neue Position vom Block bestimmen
     }


    /**
     * 
     */
    public void scroll(){
        this.yposition = this.yposition + Gameplay.scrollParameter;
        this.height = this.height - Gameplay.scrollParameter;
    }

}
