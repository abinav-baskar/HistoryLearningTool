package com.akb.hgame;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Pixmap;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

    private int howManyClicked=0,buttonWidth = 51,buttonHeight=15,buttonY=200,gameScore=0,
            totalScore = 0,bigBoxX=60,bigBoxWidth=256/*265*/,bigBoxTopY=15,bigBoxDiffY=75,bigBoxHeight=59;
    private int[] clickOrder,allButtonX={84,180,277},whichFont,posOfEach,whichFontCopy,originalFont,bl,
            rightFont;
    private boolean up = true,moveOn = false,doAll = false,showing = false;
    private boolean[] dim,beenClicked,smallBeenClicked,rings;
    private Rectangle click = new Rectangle(0,0,40,23);
    private Rectangle[] bigBox;
    private TextureRegion[] whichSmallBox,whichBigBox;
    private float midPointX = Gdx.graphics.getWidth()/2;
    private String[] leftNames;
    float[] whichBigTransparency,whichSmallTransparency;
    private AnswerHandler ansHan;
    private int[] inputOrder;
    private Happen[] ev;
    private boolean interim,showOnceOnly = true,changeFontOnce = true;
    private boolean[] dontShowSmallBox;
    private String[] bottomLeft = {"UNDO","CHECK",""};
    private String bottomRight = "-->";


    public static Pixmap pm;
    //Happen[] worldEv;
    private EvInit evinit;

    public GameWorld(Happen[] Scev) {
        bl = new int[2];
        whichFontCopy = new int[5];
        ev = new Happen[5];
        leftNames = new String[5];
        originalFont = new int[5];
        ev = Scev;
        evinit = new EvInit();
        whichFont = new int[5];
        interim = false;
        //moveOn = false;
        //ev = evinit.get5();
        ansHan = new AnswerHandler();
        setSmallBeenClicked(new boolean[6]);
        dim = new boolean[5];
        beenClicked = new boolean[5];
        bigBox = new Rectangle[5];
        whichBigTransparency = new float[5];
        changeFontOnce = true;
        posOfEach = evinit.getPosOfEach(Scev);
        whichSmallTransparency = new float[5];
        dontShowSmallBox = new boolean[3];
        rightFont = new int[5];

        for (int t = 0; t <5; t++) {

            leftNames[t] = ev[t].getName();
            beenClicked[t] = false;
            whichFont[t] = 0;
            bigBox[t] = new Rectangle(bigBoxX/2,bigBoxTopY/2+bigBoxDiffY*t/2,bigBoxWidth/2,bigBoxHeight/2);
            whichBigTransparency[t] = 1f;

        }
        clickOrder = new int[5];
        System.out.println(84-(buttonWidth/2) + ",190");
        whichBigBox = new TextureRegion[5];
        whichSmallBox = new TextureRegion[3];
        for (int i = 0; i < 5; i++) {
            whichBigBox[i] = AssetLoader.thbox;
            inputOrder = new int[5];
            rings = new boolean[4];

        }
        for (int i = 0; i < 3; i ++) {
            //smallBeenClicked[i] = f;
            whichSmallBox[i] = AssetLoader.thbox;
            whichSmallTransparency[i] = 0.25f;

            System.out.println("whichsmallboxhasbeenset");}

        for (int i = 0; i <2; i++) {
            bl[i] = 2;
        }
        whichSmallTransparency[2] = 0.25f;
        dontShowSmallBox[2] = true;
    }

    public void update(float delta)  	{

        decideAlpha(interim);
    }

    public void decideAlpha(boolean interim) {
        if (interim) {
            whichSmallTransparency[2] = 1f;
            dontShowSmallBox[2] = false;
        }
        if (howManyClicked > 0||interim) {
            whichSmallTransparency[0] = 1f;
            dontShowSmallBox[0] = false;
        }
        else {
            whichSmallTransparency[0] = 0.25f;
            dontShowSmallBox[0] = true;
        }
    }

    public void onMoveOn() {
        inputOrder = ansHan.Mark(clickOrder,ev,/*evinit.getPosOfEach()*/posOfEach);
        rings = ansHan.GenerateRings(inputOrder);
        gameScore = ansHan.getScore(inputOrder);
        System.out.println("gamescore: " + gameScore);
        smallBeenClicked[2] = false;
        totalScore += gameScore;
        moveOn=false;

    }



    public void onClick(int x, int y) {
        for (int a = 0; a < 5; a++) {
            if (x > 60 && x < 316 && y > a*(bigBoxDiffY)+bigBoxTopY && y < a*bigBoxDiffY+64&&!beenClicked[a]) {
                onBigBoxPressed(a); }
               else if (interim&&smallBeenClicked[0]) {
                    System.out.println("Check off");
                    whichBigTransparency[a] = 0.25f;
                showCorrectAnswer(false);
                }



        }

        for (int i = 0; i < 3; i++) {
            if ( x/2 > allButtonX[i]-(buttonWidth/2) && x/2 < allButtonX[i]-(buttonWidth/2) + buttonWidth
                    &&	y/2 > buttonY && y/2 < buttonY+buttonHeight) {
                onSmallBoxPressed(i);


            }
        }
    }
    public void whenInterim() {
        if (moveOn==false) {
            moveOn=true;
            howManyClicked = 0;

            System.out.println("BLO) is 2");
            doAll = true;
            interim = true;
        }
        decideAlpha(interim);
        bl[0] = 1;

    }

    public void onBigBoxPressed(int i) {

        whichBigTransparency[i] = 0.25f;
        beenClicked[i] = true;
        clickOrder[howManyClicked]=i;
        System.out.println("clickorder[" + howManyClicked+ "] = " + clickOrder[howManyClicked] );
        rightFont[howManyClicked] = ChangeFontSize(i,ev[clickOrder[howManyClicked]].getName());

        howManyClicked++;
        bl[0] = 0;

        if (howManyClicked==5) {
            whenInterim();
        }
        whichFont[i]++;
        //whichSmallTransparency[i] = 1f;

    }
    public void onSmallBoxPressed(int i) {

        if (i == 0&&howManyClicked>0||interim) {

            smallBeenClicked[i] = true;
            if (interim) {
                //showCorrectAnswer(true);
                //showing = true;

            } else {
                System.out.println("Undo clicked");
                undo();
            }
        }
        if (i==2/*&&moveOn == true*/&&interim) {
            smallBeenClicked[i] = true;
            System.out.println("Smallbeenclicked 3 is true" + i);

            whichSmallTransparency[i] = 0.25f;

        }
        else {
            smallBeenClicked[2] = false;
            System.out.println("smallbeenclicked is false");
        }
        if (i == 0 && interim) {
            showCorrectAnswer(false);
            //	 showOnceOnly = true;


        }

    }

    public void showCorrectAnswer(boolean d) {
        System.out.println("showing");
        if (interim) {
            if (d) {
                for (int i = 0; i < 5; i++) {


                    leftNames[i] = ev[posOfEach[i]].getName();

                    whichFontCopy[i] = ChickenChangeFontSize(i, leftNames[i]);
                    whichFont[i] = whichFontCopy[i];
                    whichFont[i] --;
                    whichBigBox[i] = AssetLoader.correctbox;
                    System.out.println("leftNames" + i + " " + ev[posOfEach[i]].getName()+"font " + whichFont[i]);



                    //	if (showOnceOnly) {
                    //whichFont[i] --;
                    //showOnceOnly=false; /*need a once boolean*/
                    //	}

                    whichBigTransparency[i] = 1f;

                }}
            else {
                for (int i = 0; i < 5; i++) {

                    leftNames[i] = ev[i].getName();

                    whichBigTransparency[i] = 0.25f;
                    //whichFont[i] = ChangeFontSize(i,ev[i].getName());
                    whichFont[i] = originalFont[i];
                    whichFont[i] +=1;
                    whichBigBox[i] = AssetLoader.thbox;

                    //whichFont[i]++;
                }
            }
        }
    }

    public void undo() {
        whichBigTransparency[clickOrder[howManyClicked-1]] = 1f;
        beenClicked[clickOrder[howManyClicked-1]] = false;
        whichFont[clickOrder[howManyClicked-1]] -=1;
        howManyClicked--;
        if (howManyClicked == 0) {
            bl[0] = 2;
        }
    }


    public int[] touchDown(int x, int y) {
        //	if (InputHandler.touchUp(x,y) == true) {
        int[] jeff = {x,y};


        if ( x/2 > allButtonX[0]-(buttonWidth/2) && x/2 < allButtonX[0]-(buttonWidth/2) + buttonWidth
                &&	y/2 > buttonY && y/2 < buttonY+buttonHeight) {
            showCorrectAnswer(true);
            System.out.println("Touchdon");
        }
        else {
            //System.out.println("SUCCESFUL IDENTIFICATION OF FAILURE");
            //showCorrectAnswer(false);
        }
        return jeff;

    }


    public void mouseMoved(int x, int y) {

        for (int a = 0; a < 5; a++) {
            //if (x > 20 && x < 270 && y > a*40+10 && y < a*40+69) {
            if (x > 60 && x < 316 && y > a*(bigBoxDiffY)+bigBoxTopY && y < a*bigBoxDiffY+64&&!beenClicked[a]) {
                dim[a] = true;
                whichBigBox[a] = AssetLoader.secbox;
                //System.out.println("Secbox it is for " + a);
            }
            else {dim[a] = false;
                whichBigBox[a] = AssetLoader.thbox;}
        }
        for (int i = 0; i < 3; i++) {
            if ( x/2 > allButtonX[i]-(buttonWidth/2) && x/2 < allButtonX[i]-(buttonWidth/2) + buttonWidth
                    &&	y/2 > buttonY && y/2 < buttonY+buttonHeight /* && !smallBeenClicked[i]*/&&!dontShowSmallBox[i]) {
                whichSmallBox[i] = AssetLoader.secbox;
            }
            else whichSmallBox[i] = AssetLoader.thbox;
        }


    }
    public int ChangeFontSize(int i, String rendVent) {

        int fontSize = 0;
        //for (int a = 0; a < 5; a++ ) {
        if (rendVent.length() > 21) {
            System.out.println(rendVent + "is bigger thanb 19");
            fontSize = 1;
            if (changeFontOnce) {
                System.out.println("Changing");
                whichFont[i] +=2;

               // changeFontOnce = false;
            }
        }
        //}
        originalFont[i] = whichFont[i];




        return whichFont[i];

    }

    public int ChickenChangeFontSize(int i, String rendVent) {

        int fontSize = 1;
        //for (int a = 0; a < 5; a++ ) {
        if (rendVent.length() > 21) {
            System.out.println(rendVent + "is bigger thanb 19");
            fontSize = 1;
            //if (changeFontOnce) {
            whichFont[i] +=2;
            fontSize +=2;
            //changeFontOnce = false;
            //}
        }
        //}
        //originalFont = whichFont;




        return fontSize;

    }






    //public Rectangle getRect() {
    //  return rect;
    //						}

    public Rectangle[] getBigBox() {
        return bigBox;
    }


    public int GetButtonWidth() {
        return buttonWidth;
    }
    public int getHowManyClicked() {
        return howManyClicked;
    }
    public boolean[] getDim() {
        return dim;
    }
    public boolean[] getBeenClicked() {
        return beenClicked;
    }
    public boolean getMoveOn() {
        return moveOn;
    }
    public void setMoveOn(boolean set) {
        moveOn = set;
    }
    public int[] getClickOrder() {
        return clickOrder;
    }
    public int getButtonY() {
        return buttonY;
    }
    public int[] getAllButtonX() {
        return allButtonX;
    }

    public int getButtonHeight() {
        return buttonHeight;
    }
    public TextureRegion[] getWhichSmallBox() {
        return whichSmallBox;
    }
    public TextureRegion[] getWhichBigBox() {
        return whichBigBox;
    }

    public int[] getWhichFont() {
        return whichFont;
    }

    public boolean[] getSmallBeenClicked() {
        return smallBeenClicked;
    }

    public void setSmallBeenClicked(boolean[] smallBeenClicked) {
        this.smallBeenClicked = smallBeenClicked;
    }
    public boolean[] getRings() {
        return rings;
    }
    public int getGameScore() {
        return gameScore;
    }

    public float[] getWhichTransparency() {
        return whichBigTransparency;
    }
    public float[] getWhichSmallTransparency() {
        return whichSmallTransparency;
    }
    public boolean isDoAll() {
        return doAll;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public boolean isInterim() {
        return interim;
    }
    public float getMidPointX() {
        return midPointX;
    }
    public String[] getLeft() {
        return leftNames;
    }

    public String[] getBottomLeft() {
        return bottomLeft;
    }

    public String getBottomRight() {
        return bottomRight;
    }

    public int[] getBl() {
        return bl;
    }

    public int[] getRightFont() {
        return rightFont;
    }

    public void setChangeFontOnce(boolean changeFontOnce) {
        this.changeFontOnce = changeFontOnce;
    }
}


