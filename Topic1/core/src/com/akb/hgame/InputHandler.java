package com.akb.hgame;


        import com.badlogic.gdx.InputProcessor;
        import com.akb.hgame.GameWorld;

public class InputHandler implements InputProcessor {

    private GameWorld myWorld;


    public InputHandler(GameWorld world) {
        myWorld = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        myWorld.touchDown(screenX,screenY);

        return true;

    }


    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        myWorld.onClick(screenX,screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        myWorld.mouseMoved(screenX, screenY);
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        // TODO Auto-generated method stub
        return false;
    }

}



