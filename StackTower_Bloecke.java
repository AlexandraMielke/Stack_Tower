class Main{
    public static void main(String[] args){

    }
}

class Block{

    int [] farbe=new int [9];      // Array für die verscheidene Farbauswahl der Blöcke z.B. 0=blau, 1=grün ...
    int hoeheBlock = 100;           // Höhe vom Block entspricht 100 Pixel
    int breiteBlock = 100;          // Anfangsbreite vom Block entspricht 100 Pixel
    int [] Position = {100, 800};   // Anfangsposition links vom spielfelt; Position durch rechte Ecke unten vom Block festgelget [breite, höhe] 
    
    public void zufallsFarbeBlock(){
        int min = 1;
        int max = 10;
        int value;

        Random random = new Random();

        value = random.nextInt(max + min) + min;
        System.out.println(value);
    }

    public void BewegungXAchse(){

    }

    public void berechneObAufTower(){

    }

    public void Fallen(){

    }

    public void Teilung(){

    }

    public void Scrollen(){

    }

}