package com.akb.hgame.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.akb.hgame.HGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
         //       return new GwtApplicationConfiguration(480, 320);
                return new GwtApplicationConfiguration(720, 450);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new HGame();
        }
}