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

import java.util.Random;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class PlayScreen implements Screen{
    private final BarleyBreak app;
    private Stage stage;

    int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int array4x4[][] = new int[4][4];


    public PlayScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));

        //создание позиции в игре пятнашки
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
        PrintArray4x4(array4x4);

        //отображение игрового поля на экране
        Texture game_field_Texture = Assets.getTexture(Assets.GAME_FIELD);
        Image game_field = new Image(game_field_Texture);
        game_field.setScale((float)(app.SCREEN_WIDTH  / game_field.getWidth()),(float)(app.SCREEN_WIDTH  / game_field.getWidth() ));
        game_field.setPosition(0, app.SCREEN_HEIGHT/ 4);
        stage.addActor(game_field);

        //создание массива пятнашек из класса Breek и отображение его на поле

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

    private void PrintArray4x4(int[][] array4x4)
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
