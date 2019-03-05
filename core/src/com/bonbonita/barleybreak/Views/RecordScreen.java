package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.barleybreak.BarleyBreak;
import com.bonbonita.barleybreak.Models.Break;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class RecordScreen implements Screen {
    private final BarleyBreak app;
    private Stage stage;
    Break[][] breaks;

    OrthographicCamera camera;

    public RecordScreen(BarleyBreak app){
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);//!!!!!!!!!НЕ ЗАБЫВАЙ
        camera = new OrthographicCamera(480,800);
        camera.setToOrtho(true);



        Texture image_Texture = Assets.getTexture(Assets.BREAK_01);
        final Image myImage = new Image(image_Texture);
        myImage.setScale((float)(app.SCREEN_WIDTH  / (4 * myImage.getWidth()) ),
                (float)(app.SCREEN_WIDTH   / (4 * myImage.getWidth()) ));
        myImage.setPosition(0, app.SCREEN_HEIGHT/ 4 -10);
        stage.addActor(myImage);
        myImage.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                myImage.addAction(Actions.moveTo(app.SCREEN_WIDTH - app.SCREEN_WIDTH / 4,
                                                app.SCREEN_HEIGHT - app.SCREEN_WIDTH / 4,  5, Interpolation.smooth));
                myImage.addAction(Actions.scaleTo(.15f, .15f, 5f ,Interpolation.exp5Out));
               // myImage.addAction(Actions.rotateTo(45));

                System.out.println("app.SCREEN_WIDTH " + app.SCREEN_WIDTH);
                System.out.println("app.SCREEN_HEIGHT " + app.SCREEN_HEIGHT);

            }
        });
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
