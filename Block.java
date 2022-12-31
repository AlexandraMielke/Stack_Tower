import java.util.Random;             //Für Zufallszahl

public class Block{
    int color;                              // Feslegen der Zahl für die Farbauswahl der Blöcke z.B. 1=blau, 2=grün ...  
    int StartHightBlock = 100;                   // Höhe vom Block entspricht 100 Pixel
    int StartWidthBlock = 100;                   // Anfangsbreite vom Block entspricht 100 Pixel
    int widthPlayground = 1000;             // Breite und Höhe des Spielfeldes
    int highPlayground = 1000;
    int movingPixel = 5;                    // Anzahl an Pixel die der Block springt bei der Bewegung
    int startPositionX;                   // Anfangsposition links vom spielfelt; Position durch rechte Ecke unten vom Block festgelget; Spielfeldgröße 1000 x 1000
    int startPositionY;                    
                                           
    int movingDirection;                    // Bewegungsrichtung rechts links speichern 1 = rechts, 2 = links;
    int StartstandingBlockPosition = 550;        // x-Position für den stehendem Block
    boolean blockOnTower;                   // gibt an ob der block auf dem Tower landet oder nicht

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
    public int[] startPositionStandingBlockDown(){
        this.startPositionX = StartstandingBlockPosition;
        this.startPositionY = 0;
    }

    public int[] startPositionStandingBlockMiddel(){
        this.startPositionX = StartstandingBlockPosition;
        this.startPositionY = StartHightBlock;
    }

    public int[] startPositionStandingBlockTop(){
        this.startPositionX = StartstandingBlockPosition;
        this.startPositionY = StartHightBlock * 2;
    }

    public int[] startpositionMovingBlock(){
        this.startPositionX = StartWidthBlock;
        this.startPositionY = highPlayground - StartHightBlock;    
        
        // Rechte ecke unten von Block gibt die x- und y-Position y-Postion in einem Spielfeld Feld
        // y-Position = Höhe von Spielfeld - höhe vom Block
    }

    //Bewegung und Berechnung (die Aktuelle X-Position muss mit übergeben werden)
    public int movingXAxis(int Xposition, int widthBlock){

        // festlegen: wenn block ganz links -> rechtsbewegung; wenn block ganz rechts -> linksbewegung
        if(Xposition == widthBlock){
            movingDirection = 1;
        }
        if(Xposition == widthPlayground){
            movingDirection = 2;
        }

        // Ausführung der rechtsbewegung/ linksbewegung
        if(Xposition<widthPlayground && movingDirection == 1){
            Xposition = Xposition + movingPixel;
        }
        if(Xposition>widthBlock && movingDirection == 2){
            Xposition = Xposition - movingPixel;
        }
        return Xposition;
    }

    // Gibt aus ob der Block auf dem Tower landet (true) oder nicht (false); Aktuelle X-Position vom bewegten und stehenden Block muss übergeben werden
    // Gameplay soll folgende Werte übergeben: 
    //calculateIfOnTower(xPosition vom Fallenden BLock, Xpostion vom obersten stehenden Block, Breite fallender Block)
    public boolean calculatIfOnTower(int Xposition, int standingBlockPositionX, int widthBlock){
        if(Xposition>(standingBlockPositionX - widthBlock) && Xposition<(standingBlockPositionX + widthBlock)){
            blockOnTower = true;
        }else{
            blockOnTower = false;
        }
        
        return blockOnTower;
    }

    //MovingBlock auf Tower fallen lassen
    public int fall(int YPosition){
        if(YPosition > StartHightBlock*3){
            this.YPosition = YPosition - movingPixel;
        }else{
            return true;
        }
        
    }

    //Der Block wird verkleinert (Wichtig Position des gefallen Block auf dem stehenden Block oder nicht)
    //split(Xpostion vom bewegten Block, Postion vom obersten stehenden Block, Breite vom fallenden Block)
    public int split(int Xposition, int standingBlockPosition, int WidthBlock){
        if(Xposition>standingBlockPosition){
            this.WidthBlock = WidthBlock - (Xposition - standingBlockPosition);
        }
        if(Xposition<standingBlockPosition){
            this.WidthBlock = Xposition - (standingBlockPosition - WidthBlock);
        }

        //neue Position für den stehenden Block; X-Postion vom Bewegten Block und stehnden muss übergeben werden
        // wenn X-Position nicht über dem Block sondern außerhalb (wird abgetrennt), neue Position vom Block bestimmen
        if(Xposition>standingBlockPosition){
            this.Xposition = standingBlockPosition;
        }else{
            this.Xposition = Xposition;
        }
     }


    // Drei Blöcke müssen scrollen -> In einem Array die jeweiligen YPositionen der drei Blöcke verändern
    public int scroll(int YPositionDown, int YPositionMiddel, int YPositionTop){
        if(YPositionMiddel != 0){
            this.YPositionDown = YPositionDown - movingPixel;
            this.YPositionMiddel = YPositionMiddel - movingPixel;
            this.YPositionTop = YPositionTop - movingPixel;
        }else{
            return true;
        }
    }

}
