package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.barleybreak.BarleyBreak;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class PlayScreen implements Screen{
    private final BarleyBreak app;
    private Stage stage;

    public PlayScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);
        Texture break_01_Texture = Assets.getTexture(Assets.BREAK_01);
        Texture break_02_Texture = Assets.getTexture(Assets.BREAK_02);
        Texture break_03_Texture = Assets.getTexture(Assets.BREAK_03);
        Texture break_04_Texture = Assets.getTexture(Assets.BREAK_04);
        Texture break_05_Texture = Assets.getTexture(Assets.BREAK_05);
        Texture break_06_Texture = Assets.getTexture(Assets.BREAK_06);
        Texture break_07_Texture = Assets.getTexture(Assets.BREAK_07);
        Texture break_08_Texture = Assets.getTexture(Assets.BREAK_08);
        Texture break_09_Texture = Assets.getTexture(Assets.BREAK_09);
        Texture break_10_Texture = Assets.getTexture(Assets.BREAK_10);
        Texture break_11_Texture = Assets.getTexture(Assets.BREAK_11);
        Texture break_12_Texture = Assets.getTexture(Assets.BREAK_12);
        Texture break_13_Texture = Assets.getTexture(Assets.BREAK_13);
        Texture break_14_Texture = Assets.getTexture(Assets.BREAK_14);
        Texture break_15_Texture = Assets.getTexture(Assets.BREAK_15);

        Texture game_field_Texture = Assets.getTexture(Assets.GAME_FIELD);
        Image game_field = new Image(game_field_Texture);
        game_field.setScale((float)(app.SCREEN_WIDTH  / game_field.getWidth()),(float)(app.SCREEN_WIDTH  / game_field.getWidth() ));
        game_field.setPosition(0, app.SCREEN_HEIGHT/ 4);
        stage.addActor(game_field);


        Image break_01 = new Image(break_01_Texture);
        break_01.setScale((float)(app.SCREEN_WIDTH  / game_field.getWidth()),(float)(app.SCREEN_WIDTH  / game_field.getWidth() ));
        break_01.setPosition(0+20, app.SCREEN_HEIGHT/ 4 +20);
        stage.addActor(break_01);

        Image break_02 = new Image(break_02_Texture);
        break_02.setScale((float)(app.SCREEN_WIDTH  / game_field.getWidth()),(float)(app.SCREEN_WIDTH  / game_field.getWidth() ));
        break_02.setPosition(0 + 20+  break_01.getWidth()/2, app.SCREEN_HEIGHT/ 4 +20 );
        System.out.print(break_01.getWidth());
        stage.addActor(break_02);
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
        stage.getViewport().update(width, height, true);
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
