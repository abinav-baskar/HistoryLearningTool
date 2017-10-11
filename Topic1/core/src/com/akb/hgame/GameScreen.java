package com.akb.hgame;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Screen;


import com.akb.hgame.EvInit;
import com.akb.hgame.GameRenderer;
import com.akb.hgame.GameWorld;
import com.akb.hgame.Happen;




import com.akb.hgame.InputHandler;



public class GameScreen implements Screen {




    private GameWorld world;

    private GameRenderer renderer;

//private FontHandler fontHandle;

    private EvInit evinit;


    private Happen[] scEvent;



    private int[] inputOrder,whichFonts;

    private boolean[] rings;

//private boolean doWeMoveOn;


    public  boolean doWeMoveOn;


    private float runTime;




    public GameScreen() {

        Gdx.app.log("GameScreen", "Attached");


        whichFonts = new int[5];





        onNewGame();







    /*  GameWorld world = new GameWorld;

      InputHandler handler = new InputHandler(bird);

      Gdx.input.setInputProcessor(handler); */



    }

    public void onNewGame() {

        evinit = new EvInit();

        scEvent = evinit.get5();

        world = new GameWorld(scEvent);

        renderer = new GameRenderer(world,scEvent);

        rings = new boolean[4];

        for (int i = 0; i < 5; i++) {

            whichFonts[i] = (world.ChangeFontSize(/*renderer.GetFontSize()[i]*/i,scEvent[i].getName()));



        }
        world.setChangeFontOnce(false);


        renderer.SetFontSize(whichFonts);

        InputHandler handler = new InputHandler(world);

        Gdx.input.setInputProcessor(handler);



    }



    @Override

    public void render(float delta) {

        runTime += delta;

        world.update(delta);

        renderer.render(runTime);



        if (world.getMoveOn()) {

            //	state = state.Mark;

            //	inputOrder = ansHan.Mark(world.getClickOrder(),scEvent,evinit.getPosOfEach());

            //	rings = ansHan.GenerateRings(inputOrder);

            //world.onMoveOn();

            world.onMoveOn();



            doWeMoveOn = true;





        }

        if (world.getSmallBeenClicked()[2]&&doWeMoveOn) {

            //	world.setMoveOn(false); //moveOn must split into two

            System.out.println("\n\n\nHey!");

            onNewGame();

            doWeMoveOn = false;

        }





    }






    @Override

    public void resize(int width, int height) {

        Gdx.app.log("GameScreen", "resizing");

    }



    @Override

    public void show() {

        Gdx.app.log("GameScreen", "show called");

    }



    @Override

    public void hide() {

        Gdx.app.log("GameScreen", "hide called");

    }



    @Override

    public void pause() {

        Gdx.app.log("GameScreen", "pause called");

    }



    @Override

    public void resume() {

        Gdx.app.log("GameScreen", "resume called");

    }



    @Override

    public void dispose() {

    }

    public boolean[] getRings() {

        return rings;

    }



}