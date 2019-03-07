package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.barleybreak.BarleyBreak;
import com.bonbonita.barleybreak.Models.MyActor;
import com.sun.java_cup.internal.runtime.Scanner;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class RuleScreen implements Screen {
    private final BarleyBreak app;
    private Stage stage;

    public RuleScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        MyActor testActor = new MyActor(1, app);
        float aspectRatio = (app.SCREEN_WIDTH - 2f * app.SCREEN_WIDTH / 10f)/(4f * testActor.getImage().getWidth());
        testActor.setMyPosition(0, 0);
        testActor.getImage().setScale((float)(  aspectRatio ),(float)(aspectRatio ));
        testActor.getImage().setPosition(testActor.getPosX(), testActor.getPosY());
        stage.addActor(testActor.getImage());

        MyActor testActor2 = new MyActor(2, app);
        testActor2.setMyPosition(1, 1);
        testActor2.getImage().setScale((float)( aspectRatio ),(float)( aspectRatio ));
        testActor2.getImage().setPosition(testActor2.getPosX(), testActor2.getPosY());
        stage.addActor(testActor2.getImage());

        MyActor testActor3 = new MyActor(3, app);
        testActor3.setMyPosition(0, 2);
        testActor3.getImage().setScale((float)( aspectRatio ),(float)( aspectRatio ));
        testActor3.getImage().setPosition(testActor3.getPosX(), testActor3.getPosY());
        stage.addActor(testActor3.getImage());

        MyActor testActor4 = new MyActor(4, app);
        testActor4.setMyPosition(0, 3);
        testActor4.getImage().setScale((float)( aspectRatio ),(float)( aspectRatio ));
        testActor4.getImage().setPosition(testActor4.getPosX(), testActor4.getPosY());
        stage.addActor(testActor4.getImage());

    }
    private void clearScreen(){
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
