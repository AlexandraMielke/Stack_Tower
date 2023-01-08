
//Tobias Elebracht
//24.11.2022
//Gamplayklasse fuer Spiel "StackTower" fuer das Modul Embedded Systems
//Version 1, noch nicht einsatzbereit, einige Fehler und offene Stellen vorhanden

public class Gameplay{
    //Variablen

    //private boolean moveBackgroundInGUI = false;
    private boolean gameOver = false;
    private int delayTime = 5;                  //Millisec
    public static int playerScore = 0;
    private int scrollParameter = 5;

    public static int playingFieldHeight = 800;
    public static int playingFieldWidth = 1000;
    public static int StartHeightBlock = 100;                   // Höhe vom Block entspricht 100 Pixel
    public static int StartWidthBlock = 100;                   // Anfangsbreite vom Block entspricht 100 Pixel
    //public int scrollParameter = 1;          // Anzahl an Pixel die der Block springt bei der Bewegung
    public static int fallParameter = 3;            // Anzahl an Pixel die der Block fällt bei der Bewegung
    public static int panParameter = 1;             //Parameter für Bewegung rechts/links

    private Block blockObject1;
    private Block blockObject2;
    private Block blockObject3;
    private PlayGround playGround;

    public static void main(String[] args)
    {        
        Keylistener mainFrame1 = new Keylistener();
        while(true)
        {  
            PlayGround.mainFrame = mainFrame1; //JFrame created here for Keylistener Features
            Gameplay Game = new Gameplay();
            Game.coreGameplayLoop();

            //Gameover - wait for player interaction!
            
            while(Keylistener.keyPressed == false)
            {
                //do nothing
            }
            //fix Keyaction flag
             Keylistener.keyPressed = false;
            //PlayGround.pause(2000);
            System.exit(0); //only works in debug mode. Otherwise game just stops after game over

        }
    }


    //Methoden
    //constructor
    public Gameplay(){
        //Anlegen des Spielfeldes mit Input "Groesse"?
        this.playGround = PlayGround.initPlayGround(Gameplay.playingFieldHeight, Gameplay.playingFieldWidth);

        //Anlegen der Start-Bloecke mit konstanter Position
        this.blockObject1 = new Block();
        this.blockObject2 = new Block();
        this.blockObject3 = new Block();

        //Anordnen der Bloecke:
        //ggf. noch Anpassen der Methodennamen
        blockObject1.startPositionStandingBlockDown();
        blockObject2.startPositionStandingBlockMiddle();
        blockObject3.startPositionStandingBlockTop();

    }


    public void coreGameplayLoop(){
        //Schleife fuer das Erzeugen, Setzen und Auswerten eines Blocks
        //laeuft so lange, bis der bewegte Block den Tower nicht trifft
        while (this.gameOver == false){

            //Erzeugung eines Neuen Block-Objektes:
            Block blockObjekt4 = new Block();
            blockObjekt4.startpositionMovingBlock(blockObject3);;

            //zeichne Spielfeld mit allen 4 Bloecken
            this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);

            while (Keylistener.keyPressed == false)
            {
                //Block Objekt 4 wird horizontal bewegt, bis der Userinput kommt
                blockObjekt4.movingXAxis();
                this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                
                //Delay, screibt Spielgeschwindigkeit fest
                PlayGround.pause(2);
                //Muss hier mit Multithreading gearbeitet werden? Kann man den Input auch waehrend des sleeps abfragen?
                
            }

            Keylistener.keyPressed = false; //reset keyPressed
                //PlayGround.pause(5000);
            //Abfrage ob Block 4 den Tower trifft
            //getter und setter für Block-Attribute anlegen

            if (blockObjekt4.calculatIfOnTower(this.blockObject3) == true){
                //Spiel geht weiter

                while(blockObjekt4.fall() == false){
                    //Fall-Animation vom Block-Objekt4 bis es den Tower erreicht hat
                    blockObjekt4.fall();
                    this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                    //Delay, screibt Spielgeschwindigkeit fest
                    PlayGround.pause(this.delayTime);

                }
                
                
                Gameplay.playerScore = Gameplay.playerScore + 1;
                this.playGround.updatePlayerScore();

                this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                //Delay, screibt Spielgeschwindigkeit fest
                PlayGround.pause(this.delayTime);

                blockObjekt4.split(blockObject3);
                //PlayGround.pause(this.delayTime);
                this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                //Delay, screibt Spielgeschwindigkeit fest
                PlayGround.pause(10);

                while(this.blockObject1.yposition < Gameplay.playingFieldHeight){
                    //Scrollt alle Bloecke mit jeder Schleife um einen bestimmten wert nach unten, bis die Hoehe von Block1 = 0 ist

                    this.blockObject1.scroll(scrollParameter);     //würde den Scrollparameter hier lieber mit übergeben
                    this.blockObject2.scroll(scrollParameter);
                    this.blockObject3.scroll(scrollParameter);
                    blockObjekt4.scroll(scrollParameter);
                    this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                    this.playGround.moveBackground(3);
                    //Delay, screibt Spielgeschwindigkeit fest
                    PlayGround.pause(10); 

                }

                //umkopieren der einzelnen Bloecke um den Speicher klein zu halten:
                this.blockObject1 = this.blockObject2; 
                this.blockObject2 = this.blockObject3;
                this.blockObject3 = blockObjekt4;

                blockObjekt4 = null; 
                //in diesem Fall werden nur 3 Block-Objekte übergeben, blockObject4 = NULL!!
                //this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                //Delay, screibt Spielgeschwindigkeit fest
                PlayGround.pause(this.delayTime);
                
            }


            else{
                //Game Over

                while(blockObjekt4.fall() == false){
                    //Fall-Animation vom Block-Objekt4 bis es den Tower erreicht hat
                    blockObjekt4.fall();
                    this.playGround.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, blockObjekt4);
                    //Delay, screibt Spielgeschwindigkeit fest
                    PlayGround.pause(this.delayTime);

                }
                this.gameOver = true;
                this.playGround.drawGameOver();

            }


        }


    }





}