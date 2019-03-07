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
import com.bonbonita.barleybreak.Models.MyActor;

import java.util.Random;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class RecordScreen implements Screen {
    private final BarleyBreak app;
    private Stage stage;
    MyActor[][] actors;

    int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int array4x4[][] = new int[4][4];

    public RecordScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        System.out.println("It's an array:");
        PrintArray(array);
        shuffleArray(array);
        System.out.println("It's a shuffle array:");
        PrintArray(array);
        int k = 0;
        for (int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
            {
                array4x4[i][j] = array[k];
                k++;
            }
        PrintArray(array4x4);

        actors = new MyActor[4][4];
        for (int j = 0; j < 4; j++)
            for(int i = 0; i< 4; i++)
            {
                if(array4x4[i][j] != 0)
                {
                    actors[i][j] = new MyActor(array4x4[i][j], app);
                    float aspectRatio = (app.SCREEN_WIDTH - 2f * app.SCREEN_WIDTH / 10f)/(4f * actors[i][j].getImage().getWidth());
                    actors[i][j].setMyPosition(i, j);
                    actors[i][j].getImage().setScale((float)(aspectRatio),(float)(aspectRatio));
                    actors[i][j].getImage().setPosition(actors[i][j].getPosX(), actors[i][j].getPosY());
                    stage.addActor(actors[i][j].getImage());
                    final int I = i;//нельзя во внутреннем классе использовать не final int i
                    final int J = j;
                    actors[i][j].getImage().addListener(new ActorGestureListener() {
                        @Override
                        public void tap(InputEvent event, float x, float y, int count, int button) {
                            super.tap(event, x, y, count, button);
                            //Здесь добавить то, что будем делать при нажатии на пятнашку
                            System.out.println("Pressed the break " + actors[I][J].getNum());

                        }
                    });
                }
            }
    }

    public void updateMyActorsArray(MyActor[][] actors) {

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

    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }
    private static void swap(int[] a, int i, int change) {
        int temp = a[i];
        a[i] = a[change];
        a[change] = temp;
    }

    private void PrintArray(int[] array)
    {
        for (int j = 0; j < 16; j++)
            System.out.print(array[j] + " " );
        System.out.println();
    }

    private void PrintArray(int[][] array4x4)
    {
        for (int j = 0; j < 4; j++)
        {
            for(int i = 0; i < 4; i++)
            {
                System.out.print(array4x4[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
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
