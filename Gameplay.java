import java.util.concurrent.TimeUnit;

//Tobias Elebracht
//24.11.2022
//Gamplayklasse fuer Spiel "StackTower" fuer das Modul Embedded Systems
//Version 1, noch nicht einsatzbereit, einige Fehler und offene Stellen vorhanden


public class Gameplay{
    //Variablen

    private boolean moveBackgroundInGUI = false;
    private boolean gameOver = false;
    private float delayTime = 0.2;                  //legt Spielgeschwindigkeit fest
    private int playerScore = 0;


    //Methoden
    //constructor
    public Gameplay(){
        //Anlegen des Spielfeldes mit Input "Groesse"?
        PlayGround playGround1 = new PlayGround(1000, 1000);

        //Anlegen der Start-Bloecke mit konstanter Position
        Block blockObject1 = new Block();
        Block blockObject2 = new Block();
        Block blockObject3 = new Block();

        //Anordnen der Bloecke:
        //ggf. noch Anpassen der Methodennamen
        blockObject1.startPositionStandingBlockDown();
        blockObject2.startPositionStandingBlockMiddle();
        blockObject3.startPositionStandingBlockTop();

    }

    public boolean coreGameplayLoop(){
        //Schleife fuer das Erzeugen, Setzen und Auswerten eines Blocks
        //laeuft so lange, bis der bewegte Block den Tower nicht trifft
        while (this.gameOver == false){

            //Erzeugung eines Neuen Block-Objektes:
            Block blockObjekt4 = new Block();
            blockObjekt4.startPositionMovingBlock();

            //zeichne Spielfeld mit allen 4 Bloecken
            playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);

            while (/*SPACEBAR IS PRESSED VARIBALE ==FALSE*/){
                //Block Objekt 4 wird horizontal bewegt, bis der Userinput kommt
                blockObjekt4.movingXAxis();
                playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                
                //Delay, screibt Spielgeschwindigkeit fest
                TimeUnit.SECONDS.sleep(this.delayTime);
                //Muss hier mit Multithreading gearbeitet werden? Kann man den Input auch waehrend des sleeps abfragen?
                
                //hier evtl noch Abfrage des Unserinputs

            }

            //Abfrage ob Block 4 den Tower trifft
            if (blockObjekt4.calculatIfOnTower(this.blockObject3.blockPosition, this.blockObject3.blockWidth) == true){
                //Spiel geht weiter

                while(blockObjekt4.fall() == false){
                    //Fall-Animation vom Block-Objekt4 bis es den Tower erreicht hat
                    blockObjekt4.fall();
                    playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                    //Delay, screibt Spielgeschwindigkeit fest
                    TimeUnit.SECONDS.sleep(this.delayTime);

                }
                
                
                playerScore = playerScore + 1;
                playGround1.updatePlayerScore(playerScore);
                playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                //Delay, screibt Spielgeschwindigkeit fest
                TimeUnit.SECONDS.sleep(this.delayTime);

                blockObjekt4.split();
                playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                //Delay, screibt Spielgeschwindigkeit fest
                TimeUnit.SECONDS.sleep(this.delayTime);

                while(blockObject1.blockHeight != 0){
                    //Scrollt alle Bloecke mit jeder Schleife um einen bestimmten wert nach unten, bis die Hoehe von Block1 = 0 ist

                    blockObject1.scroll();
                    blockObject2.scroll();
                    blockObject3.scroll();
                    blockObjekt4.scroll();
                    playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                    //Delay, screibt Spielgeschwindigkeit fest
                    TimeUnit.SECONDS.sleep(this.delayTime); 

                }

                //umkopieren der einzelnen Bloecke um den Speicher klein zu halten:
                blockObject1 = blockObject2; 
                blockObject2 = blockObject3;
                blockObject3 = blockObject4;

                blockObjekt4 = null; 
                playGround1.drawFrame(this.blockObject1, this.blockObject2, this.blockObject3, this.blockObjekt4 , this.moveBackgroundInGUI);
                //Delay, screibt Spielgeschwindigkeit fest
                TimeUnit.SECONDS.sleep(this.delayTime);
                
            }


            else{
                //Game Over
                gameOver = true;
                playGround1.drawGameOver(gameOver);

            }


        }


    }





}