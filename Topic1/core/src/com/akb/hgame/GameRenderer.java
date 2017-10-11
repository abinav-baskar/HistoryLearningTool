package com.akb.hgame;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    private EvInit myEvinit;
    private Happen[] rendVent;
    private int[] whichFontColour,fontSize;
    private int howManyTimes;
    private boolean not5yet = true;
    private TextureRegion[] flash;
    private AnswerHandler ansHan;
    private int[] whichRightFonts;


    public GameRenderer(GameWorld world,Happen[] scEvent) 	{
        myWorld = world;
        myEvinit = new EvInit();

        rendVent = scEvent;


        cam = new OrthographicCamera();
        cam.setToOrtho(true,360,225);
        //cam.setToOrtho(true,720,450);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
        whichFontColour = new int[5];
        fontSize = new int[5];
        flash = new TextureRegion[2];
        flash[0] = 	AssetLoader.secbox;
        flash [1] = AssetLoader.thbox;
        whichRightFonts = new int[5];




//		AssetLoader.changeFontSize(0.16);

    }

    public int[] GetFontSize() {
        return fontSize;

    }
    public void SetFontSize(int[] newSize) {

        this.fontSize = newSize;
    }




    public void render(float runTime) 	{
        Gdx.gl.glClearColor(10/51.0f, 15/153.0f, 230/255.0f, 1);


        Gdx.gl.glClearColor(64/255f, 101/255f, 180/255f, 1);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batcher.begin();

        for (int u = 0; u <5; u++) {

            batcher.setColor(1,1,1,myWorld.getWhichTransparency()[u]);

            batcher.draw(myWorld.getWhichBigBox()[u],myWorld.getBigBox()[u].x,myWorld.getBigBox()[u].y,
                    myWorld.getBigBox()[u].width,myWorld.getBigBox()[u].height);

            AssetLoader.front[myWorld.getWhichFont()[u]].draw(batcher, myWorld.getLeft()[u],
                    myWorld.getBigBox()[u].x+5, myWorld.getBigBox()[u].y+10);
        }

        batcher.setColor(1,1,1,1f);

        for (int u = 0; u < 5; u++) {
            whichFontColour[u] = 0;

            if (fontSize[myWorld.getClickOrder()[u]] == 1) {
                whichFontColour[u] += 2;
            }
        }

        howManyTimes = myWorld.getHowManyClicked();

        if (myWorld.isDoAll()) {
            howManyTimes = 5;
        }

        for (int z = 0; z < howManyTimes;z++) {
      //      batcher.draw(AssetLoader.thbox,/*160*/203,10+z*40,127,31);
            batcher.draw(AssetLoader.thbox,203,myWorld.getBigBox()[z].y,
                    myWorld.getBigBox()[z].width,myWorld.getBigBox()[z].height);
            if (howManyTimes == 5) {
                for (int i = 0; i < 5; i++) {
                    whichRightFonts[i] = (2-(myWorld.getWhichFont()[myWorld.getClickOrder()[i]]%3)*2);
                }
            }

            AssetLoader.front[myWorld.getRightFont()[z]].draw(batcher, rendVent[myWorld.getClickOrder()[z]].getName(), 208, myWorld.getBigBox()[z].y+10);
        }

        for (int i = 0; i < 3; i+=2) {
            batcher.setColor(1,1,1,myWorld.getWhichSmallTransparency()[i]);

            batcher.draw(myWorld.getWhichSmallBox()[i],myWorld.getAllButtonX()[i]-(myWorld.GetButtonWidth()/2),
                    myWorld.getButtonY(),myWorld.GetButtonWidth(),myWorld.getButtonHeight());
        }

        for (int k = 0; k < 2; k++) {
            if (k == 0) {
                AssetLoader.front[0].draw(batcher, myWorld.getBottomLeft()[myWorld.getBl()[k]],myWorld.getAllButtonX()[k]-20,myWorld.getButtonY()+4);
            }
            else if (myWorld.isInterim())  {
                AssetLoader.front[0].draw(batcher, "-------->",myWorld.getAllButtonX()[k+1]-20,myWorld.getButtonY()+4);
            }
        }

        for (int i = 0; i < 4; i++) {
            if (myWorld.getRings()[i]) {
                AssetLoader.front[4].draw(batcher, "}", 330,24+40*i);
                AssetLoader.front[0].draw(batcher, "+1", 346,34+40*i);
            }
        }

        if (myWorld.isInterim()) {
            AssetLoader.front[4].draw(batcher, ""+myWorld.getGameScore(), 173,195);
        }

        batcher.end();








    }
}


