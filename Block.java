import java.util.Random;             //Für Zufallszahl

public class Block{
    int color;                              // Feslegen der Zahl für die Farbauswahl der Blöcke z.B. 1=blau, 2=grün ...  
    int StartHightBlock = 100;                   // Höhe vom Block entspricht 100 Pixel
    int StartWidthBlock = 100;                   // Anfangsbreite vom Block entspricht 100 Pixel
    int widthPlayground = 1000;             // Breite des Spielfeldes
    int movingPixel = 5;                    // ANzahl an Pixel die der Block springt bei der Bewegung
    int [] anfangsposition = new int[1];    // Anfangspoition [X-Achse, Y-Achse]
                                            // Anfangsposition links vom spielfelt; Position durch rechte Ecke unten vom Block festgelget; Spielfeldgröße 1000 x 1000
    int movingDirection;                    // Bewegungsrichtung rechts links speichern 1 = rechts, 2 = links;
    int StartstandingBlockPosition = 550;        // x-Position für den stehendem Block
    boolean blockOnTower;                   // gibt an ob der block auf dem Tower landet oder nicht


    //@AlexandraMielke: Hinzugefügt zum besseren Handling - Diskussion noch ausstehend
    int width;
    int height;
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
    public int[] startPositionStandingBlockDown(){
        anfangsposition[0] = StartstandingBlockPosition;
        anfangsposition[1] = 0;

        return anfangsposition;
    }

    public int[] startPositionStandingBlockMiddel(){
        anfangsposition[0] = StartstandingBlockPosition;
        anfangsposition[1] = StartHightBlock;

        return anfangsposition;
    }

    public int[] startPositionStandingBlockTop(){
        anfangsposition[0] = StartstandingBlockPosition;
        anfangsposition[1] = StartHightBlock * 2;

        return anfangsposition;
    }

    public int[] startpositionMovingBlock(){
        anfangsposition[0] = StartWidthBlock;
        anfangsposition[1] = widthPlayground - StartWidthBlock;

        return anfangsposition;
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
    public boolean calculatIfOnTower(int Xposition, int standingBlockPosition, int widthBlock){
        if(Xposition>(standingBlockPosition-widthBlock) && Xposition<(standingBlockPosition+widthBlock)){
            blockOnTower = true;
        }else{
            blockOnTower = false;
        }
        
        return blockOnTower;
    }

    //Block bewegt sich nach unten
    public int fall(int YPosition){
        if(YPosition>=StartHightBlock*3){
            YPosition = YPosition - movingPixel;
        }
        return YPosition;
    }

    //Der Block wird verkleinert (Wichtig Position des gefallen Block auf dem stehenden Block oder nicht)
    public int split(int Xposition, int standingBlockPosition, int WidthBlock){
        if(Xposition>standingBlockPosition){
         WidthBlock = WidthBlock - (Xposition - standingBlockPosition);
        }
        if(Xposition<standingBlockPosition){
            WidthBlock = Xposition - (standingBlockPosition - WidthBlock);
        }

         return WidthBlock;
     }


    //neue Position für den stehenden Block; X-Postion vom Bewegten Block und stehnden muss übergeben werden
    public int newPositionStandingBlockTop(int Xposition, int oldStandingBlockPosition){
        // wenn X-Position nicht über dem Block sondern außerhalb (wird abgetrennt), neue Position vom Block bestimmen
        if(Xposition>oldStandingBlockPosition){
            Xposition = oldStandingBlockPosition;
        }
        return Xposition;
    }

    // Drei Blöcke müssen scrollen -> In einem Array die jeweiligen YPositionen der drei Blöcke verändern
    public int[] scroll(int [] YPosition){
        if(YPosition[1] != 0){
            YPosition[0]= YPosition[0] - movingPixel;
            YPosition[1]= YPosition[1] - movingPixel;
            YPosition[2]= YPosition[2] - movingPixel;
        }
        return YPosition;
    }

}