package com.akb.hgame;

import com.badlogic.gdx.Game;


public class HGame extends Game {

	@Override

	public void create() {

		System.out.println("ZBGame Created!");
		AssetLoader assetLoader = new AssetLoader();
		assetLoader.load();

		setScreen(new GameScreen());


	}


	@Override

	public void dispose() {



		super.dispose();

		AssetLoader.dispose();

	}






}