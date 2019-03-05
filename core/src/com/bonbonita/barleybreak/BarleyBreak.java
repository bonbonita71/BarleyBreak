package com.bonbonita.barleybreak;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bonbonita.barleybreak.Views.Assets;
import com.bonbonita.barleybreak.Views.LoadingScreen;
import com.bonbonita.barleybreak.Views.PlayScreen;
import com.bonbonita.barleybreak.Views.RecordScreen;
import com.bonbonita.barleybreak.Views.RuleScreen;
import com.bonbonita.barleybreak.Views.SplashScreen;

public class BarleyBreak extends Game {
	//screens
	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public PlayScreen playScreen;
	public RecordScreen recordScreen;
	public RuleScreen ruleScreen;

	public int SCREEN_WIDTH = 720;
	public int SCREEN_HEIGHT = 1280;

	public OrthographicCamera camera;
	
	@Override
	public void create () {
		Assets.init();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		playScreen = new PlayScreen(this);
		recordScreen = new RecordScreen(this);
		ruleScreen = new RuleScreen(this);

		setScreen(loadingScreen);

	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		loadingScreen.dispose();
		splashScreen.dispose();
		playScreen.dispose();
		ruleScreen.dispose();
		recordScreen.dispose();

		Assets.dispose();
	}
}
