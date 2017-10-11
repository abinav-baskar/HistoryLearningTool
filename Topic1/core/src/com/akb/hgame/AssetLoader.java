package com.akb.hgame;



        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Animation;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        //import com.badlogic.gdx.graphics.g2d.Animation;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture,tbox,stbox,corbox;
    public static TextureRegion bg,thbox,secbox,correctbox;
    public static Animation flashAnimation;

    //public static BitmapFont font;
    public static BitmapFont[] front;
    public static BitmapFont singleFont;
    public static Happen evper;

    public static void load() {




        // evper = new Happen(12.2,"2");
        singleFont = new BitmapFont(Gdx.files.internal("Cooperblue.fnt"));
        // evper.SetFont(front[1]);

        front = new BitmapFont[5];
        for (int i = 0; i < 2; i++ ) {
            System.out.println("is is" + i);
            front[i] = new BitmapFont(Gdx.files.internal("Cooperblue.fnt"));
            front[i].getData().setScale((float) 0.18,(float) -0.18);
            //  bg.flip(false, true);

        }

        for (int i = 2; i < front.length; i++ ) {
            System.out.println("front.length" + front.length);
            front[i] = new BitmapFont(Gdx.files.internal("Cooperblue.fnt"));
            front[i].getData().setScale((float) 0.14,(float) -0.14);
        }

        AssetLoader.front[1].setColor(52/ 255.0f,63/ 255.0f,88/ 255.0f,0.25f);
        AssetLoader.front[3].setColor(52/ 255.0f,63/ 255.0f,88/ 255.0f,0.25f);
        AssetLoader.front[4].getData().setScale(0.5f,-0.5f);

        TextureRegion[] flashy = { thbox, secbox };
        flashAnimation = new Animation(0.06f, flashy);
        //    flashAnimation.setPlayMode(Animation.PlayMode.LOOP);




        tbox = new Texture(Gdx.files.internal("tbox.png"));
        //texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        thbox = new TextureRegion(tbox, 0, 0, 2048, 512);
        // thbox = new TextureRegion(tbox, 0, 0, 1536, 512);
        thbox.flip(false, true);

       /* Sprite trans = new Sprite(thbox);
        ( trans).setImageAlpha(0.1f);*/

        stbox = new Texture(Gdx.files.internal("secbox.png"));
       // texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        secbox = new TextureRegion(stbox, 0, 0, 2048, 512);
        //secbox = new TextureRegion(stbox, 0, 0, 1536, 512);
        secbox.flip(false, true);

        corbox = new Texture(Gdx.files.internal("Correctbox.png"));
       // texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        correctbox = new TextureRegion(corbox, 0, 0, 2048, 512);
        correctbox.flip(false, true);




        // font = new BitmapFont(Gdx.files.internal("data/supper.fnt"));
        // font = new BitmapFont(Gdx.files.internal("data/supper.png"));
        //font.setScale(.25f, -.25f);
    }

    public static void changeFontSize (double e) {
        double d = 1/e;
        front[0].getData().setScale((float) e, -(float) e);
        front[1].getData().setScale((float) e, -(float) e);
    }

    public static void dispose() {
     //   texture.dispose();
        //font.dispose();
        tbox.dispose();
        //pm.dispose();
        front[0].dispose();
        front[1].dispose();
        corbox.dispose();
    }

}



