import java.util.Random;                        //Für Zufallszahl

public class Block{
    private static final int margin =100;
    
    int color;                                  // Feslegen der Zahl für die Farbauswahl der Blöcke z.B. 1=blau, 2=grün ...  
                  
    //int startPositionX;                       // Wird nicht verwendet
    //int startPositionY;                    
                                           
    boolean movingRight;                        // Bewegungsrichtung Rechts: movingRight = true, Links: movingRight = false
    //int StartstandingBlockPosition = 450;     // Wird nicht verwendet
    boolean blockOnTower;                       // wenn blockOnTower = true -> Block landet auf Tower; ansonsten false

    int height;                                 // Höhe vom Block
    int width;                                  // Breite vom Block
    int xposition;                              // linke obere Ecke vom Block bestimmt die x- und y-Position
    int yposition;                              // Spielfeld wie Koordinatensystem betrachten von oberer linke Ecke aus: x-Ache wird nach rechts größer, y-Achse wrd nach unten größer

    public static void main(String[] args) {

    }

    //Methoden

    //Farbe durch Zufallszahl bestimmen
    public int randomColorBlock(){
        int min = 1;
        int max = 19;

        Random random = new Random();

        color = random.nextInt(max + min) + min; 
        return color;
    }

    /**
     * Anfangsposition vom stehenden Block unten, mitte und oben
     * 
     * @param xposition wird Berechnet: (Hälfte der Spielfeldweite) - (Hälfte von der Blockbreite am Anfang)
     * @param yposition wird Berechnet: (Spilefeldhöhe) - (Höhe vom Block); Bei mitteleren Block die Höhe*2 und beim obersten die Höhe*3
     * Spielfeldweite, Spielfeldhöhe und Blockbreite werden von der Klasse Gameplay übergeben
     */ 
    public void startPositionStandingBlockDown(){
        this.width = Gameplay.StartWidthBlock;
        this.height = Gameplay.StartHeightBlock;
        this.color = this.randomColorBlock(); 
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - this.height;

    }

    public void startPositionStandingBlockMiddle(){
        this.width = Gameplay.StartWidthBlock;
        this.height = Gameplay.StartHeightBlock;
        this.color = this.randomColorBlock();
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - 2*this.height;

    }

    public void startPositionStandingBlockTop(){
        this.width = Gameplay.StartWidthBlock;
        this.height = Gameplay.StartHeightBlock;
        this.color = this.randomColorBlock();
        this.xposition = (int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = Gameplay.playingFieldHeight - 3*this.height;

    } // muss hier nicht auch noch die weite übergen werden wie beim bewegten Block?

    /**
     * Startet in der Mitte
     * 
     * @param xpostion und yposituin wie Start der stehnden Blöcken
     * @param standingBlock oberster stehnder Block (Breite notwendig) -> Größe verändert sich im Spielverlauf
     */
    public void startpositionMovingBlock(Block standingBlock){
        this.height = Gameplay.StartHeightBlock;
        this.width = standingBlock.width;
        this.color = this.randomColorBlock();
        //start left so cheating is impossible
        this.xposition = Gameplay.StartWidthBlock; //(int)(Gameplay.playingFieldWidth / 2) - (Gameplay.StartWidthBlock / 2);
        this.yposition = 2 * this.height;       

    }

    /**
     * Bewegung des bewegten Block auf der x-Achse
     * 
     * @param xpostion: die Aktuelle x-Postion vom Block
     * @param margin: Der Block soll nicht bis zum Spielfeldrand gehen sondern noch einen Abstand haben
     * @param movingRight: Wenn true soll sich der Block nach rechts bewegen, bei false nach links
     * @param panParameter: Verschieung der Position um die definierte Pixelanzahl in Gameplay
     */
    public void movingXAxis(){

        // festlegen: wenn block ganz links -> rechtsbewegung; wenn block ganz rechts -> linksbewegung
        if(this.xposition <= Block.margin){
            movingRight = true;
        }
        if(this.xposition >= Gameplay.playingFieldWidth - 2*Block.margin)
        {
            movingRight = false;
        }
        
        // Bewegung des Blockes -> ändern der x-Position
        if(movingRight == true)
        {
            this.xposition = this.xposition + Gameplay.panParameter;
        }
        else
        {
            this.xposition = this.xposition - Gameplay.panParameter;
        }
    }

    
    /**
     * Es wird berechnte ob der bewegte Block auf dem Tower landen wird oder nicht
     * 
     * @param standingBlock (Block) oberster stehender Block
     * @param xpostion: die Aktuelle x-Postion
     * @param this.width: Die Aktuelle Breite vom bewegten Block
     * @param standingBlock.width: Die Aktuelle breite vom obersten stehenden Block
     * @return (bool): Block landet auf dem Tower
     */
    public boolean calculatIfOnTower(Block standingBlock){
        
        if(this.xposition <= (standingBlock.xposition)) //bewegter Block steht links über   ich meine: standingBlock.xposition statt Gameplay.playingFieldWidth/2
        {
            if((this.xposition + this.width) <= (standingBlock.xposition)) return false;   // Block ist zu weit links und landet nicht auf den stehenden Block
            else return true;
        }
        else //bewegter Block steht rechts über
        {
            if((this.xposition >= (standingBlock.xposition + standingBlock.width))) return false;   // Block ist zu weit rechts und landet nicht auf den stehenden Block
            else return true;
        }
        
    }

  
    /**
     * MovingBlock auf Tower fallen lassen
     *
     * @param ypostion: die Aktuelle y-Postion vom bewegten Block
     * @param StartHeigthBlock: Die Anfangshöhe der einzelnden Blöcke (mit 4 Multiplizieren -> Block soll auf dem Stapel landen: 4 Blöcke sind jetzt gestapelt)
     * @param fallParameter: Mit wievielen Piweln sich der Block nach unten bewegen soll (von Gameplay übergeben)
     * @return (bool): True wenn Block gelandet ist
     */
    public boolean fall(){
        if(this.yposition < (Gameplay.playingFieldHeight - Gameplay.StartHeightBlock*4)){
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

    
    /**
     * Der Block wird verkleinert und die neue x-Position wird bestimmt
     *
     * @param standingBlock (Block) Block auf dem der fallende Block gelandet ist
     * @param xpostion: die Aktuelle x-Postion 
     * @param width: Die Aktuelle Breite vom Block
     */
    public void split(Block standingBlock){
        if(this.xposition<standingBlock.xposition) //Ein linkes Stück vom gelandeten Block schaut über den Rand hinaus und soll abgeschnitten werden
        {
            this.width = this.xposition + this.width - standingBlock.xposition;
            this.xposition = standingBlock.xposition;
        }
        else{
            if(this.xposition>standingBlock.xposition) //Ein rechtes Stück vom gelandeten Block schaut über den Rand hinaus und soll abgeschnitten werden
            {
                this.width = (standingBlock.xposition + standingBlock.width) - this.xposition;
            }
        }

     }


    /**
     * Die Blöcke sollen nach unten Scrollen, bis nur noch 3 Stück zu sehen sind
     *
     * @param ypostion: die Aktuelle y-Postion von den stehenden Blöcken
     * @param height: Höhe des untersten Blockes wird beim Scrollen kleiner
     * @param scrollParameter: mit wievieln Pixeln die Blöcke Gescrollt werden sollen
     */
    public void scroll(int scrollParameter){
        this.yposition = this.yposition + scrollParameter;
        
        //this.height = this.height - scrollParameter;
    }

}
