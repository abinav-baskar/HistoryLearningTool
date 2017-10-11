package com.akb.hgame;



        import java.util.Arrays;
        import java.util.Random;
        //import java.util.Scanner;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.files.FileHandle;


public class EvInit {

   // public static final String NEWLINE = System.getProperty("line.separator");
    public static final String NEWLINE = "\n";


    //private Happen[]  ev2;
    FileHandle handle = Gdx.files.internal("try-cast.txt");
    long text2 = handle.length();
    String text = handle.readString();

   // Scanner getLength = new Scanner(text);
    private int[] posOfEach;


   /* public int getLength() {
      //  getEv2(text,2);
        int eLen=0;
        while (getLength.hasNextLine()) {
            eLen++;
            getLength.nextLine();
        }
        eLen = eLen/2;

        return eLen;
    }*/

    /*public Happen[] loadEv(int eLen,Scanner inFile) {
        System.out.println("The text length is: " + text2);

        System.out.println("LoadEv is go");
        int c = 0;

        Happen[] lev = new Happen[eLen];
       lev = getEv2(text,0);

        while (inFile.hasNext()) {
           lev[c] = new Happen(1.1,"stillonLoadEv");
           lev[c] = getEv(inFile,c);
           // ev[c] = getEv2(text,c);
            c++;
        }

        //	System.out.println("ev[2]: " + ev[2].getName());
        return lev;

    }*/

   /* public Happen getEv(Scanner input,int c) {



        String nameo =	input.next();
        String name = nameo + input.nextLine();
        Double date = input.nextDouble();

        Happen e = new Happen(date,name);

        return e;


    }*/

    public Happen[] getEv2(String allText,int c) {

    Happen[] ev2 = new Happen[200];
        int lastX = -1;
        int currentX = 0;
        String newThing = "";
        String[] allEv = new String[200];
        int evCount = 0;

        for (int x = 0; x < allText.length(); x++) {
            char po = allText.charAt(x);
         //   System.out.println("Char " + x + " is: " + po);
            if (Character.toString(po).equals(NEWLINE)) {

                currentX = x;
                newThing ="";
                for (int z = lastX+1; z < currentX; z++) {
                    newThing += allText.charAt(z);
                }
                //x+=1;
                lastX = currentX;
               // System.out.println(newThing);
                System.out.println("Evcount: " + evCount);
                allEv[evCount] = newThing;
                evCount++;

            }
        }

        for (int k = 0; k < evCount; k++) {
            System.out.println("allev " + k + " is " + allEv[k]);
        }


    for (int j = 0; j < evCount; j+=2) {
        System.out.println("parse" + Double.parseDouble(allEv[j + 1]));
          ev2[j/2] = new Happen(Double.parseDouble(allEv[j + 1]),allEv[j]);

    }
        for (int k = 0; k < 5; k++) {
            System.out.println("NewHappen " + k + " is " + ev2[k].getName() + " and date is " + ev2[k].date);
        }

return ev2;

    }

    public int getLength2(String allText) {
        int howManyEvs = 0;

        for (int x = 0; x < allText.length(); x++) {
            char po = allText.charAt(x);
            //   System.out.println("Char " + x + " is: " + po);
            if (Character.toString(po).equals(NEWLINE)) {
                howManyEvs++;
            }}
        howManyEvs /= 2;
    System.out.println("There are " + howManyEvs + " events");
   return howManyEvs;
    }

    /*public Happen getEvs(int c) {
        return Ev;
    }*/

    //public void get5(int eLen,Happen[] ev) {
    public Happen[] get5() {
        //Scanner inFile = new Scanner(text);

      //  int eLen = getLength();
       int eLen =  getLength2(text);
        //int eLen = 5;
        Happen[] ev = new Happen[eLen];
      //  ev = loadEv(eLen,inFile);
        ev = getEv2(text,4);
      //  System.out.println("LOADEV:" + ev[1].getName());
        Random rn = new Random();

        int rn1 = 999;
        int rn2 = 999;

        String 	Ssave1 = " ";
        String 	Ssave2 = " ";
        Double  Isave1 = 999.9;
        Double	Isave2 = 999.9;


        for (int i = 0; i < 25; i++) 	{
            rn1 = rn.nextInt(eLen);

            do 	{
                rn2 = rn.nextInt(eLen);
            }	while (rn2 == rn1);



            Ssave1 = ev[rn1].getName();
            Ssave2 = ev[rn2].getName();

            Isave1 = ev[rn1].getDate();
            Isave2 = ev[rn2].getDate();

            ev[rn1].setName(Ssave2);
            ev[rn2].setName(Ssave1);

            ev[rn1].setDate(Isave2);
            ev[rn2].setDate(Isave1);




        }

        for (int q = 0; q < 5; q++) {
            System.out.println("ev["+q+"] is:" + ev[q].getName());
        }

        ev = makeCorpos(ev);

        for (int e = 0; e<5;e++) {

            System.out.println("Event randpos" + "it: " + e + ":" + ev[e].getName() + " and it's date : " + ev[e].date );
            System.out.println("and it's position in the correct order: " + (ev[e].corpos) );

        }
        //	posOfEach OfEach(ev);

        for (int i = 0; i < 5; i ++) {

            // ev[i].SetFont(AssetLoader.front[i]);
            ev[i].getName();

        }
        return ev;
    }


    public Happen[] makeCorpos(Happen[] p) {
        Double[] original = new Double[5];

        for (int s = 0; s < 5; s++) {
            original[s] = p[s].date;

        }

        Arrays.sort(original);

        for (int e = 0; e<5;e++) {
            p[e].randpos = e;
            p[e].corpos = Arrays.binarySearch(original,p[e].getDate());
			/*	System.out.println("Event " + (p[e].randpos + 1) + ": " + p[e].getName() + " and it's date : " + p[e].date );
				System.out.println("and it's position in the correct order: " + (p[e].corpos) );*/

        }
        return p;

    }

    public int[] getPosOfEach(Happen[] p) {
        int[] posofEach = new int[5];
        System.out.println("p:" + p[4].corpos);
        for (int x = 0; x < 5; x++) 	{


			/*	for	(int f=0; f<5; f++)	{
					if	(p[f].corpos == x-1) 	{
						posofEach[x] = f;

											}
          }*/
        }
        //System.out.println("corpos" + p[1].corpos);


        for (int x = -1; x < 5; x++) 	{
            for	(int f=0; f<5; f++)	{


                if	(p[f].corpos == x) 	{
                    posofEach[x] = f;


                }
            }
        }

        for (int c = 0; c <5; c++) {
            System.out.println("psofoeach!!!!!" + posofEach[c] );
        }
        return posofEach;

    }

    //public Happen[] getHappen() {

    //return ev;
    //}
    public int[] takePosOfEach() {
        return posOfEach;
    }
	/*public Happen[] getGet5() {
		return get5();
	}*/

}


