package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.barleybreak.BarleyBreak;
import com.bonbonita.barleybreak.Controllers.Controller;
import com.bonbonita.barleybreak.Models.Break;

import java.util.Random;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class PlayScreen implements Screen{
    private final BarleyBreak app;
    private Stage stage;
    Break[][] breaks;

    int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int array4x4[][] = new int[4][4];


    public PlayScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);//!!!!!!!!!НЕ ЗАБЫВАЙ
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
        PrintArray(array4x4);

        //отображение игрового поля на экране
        Texture game_field_Texture = Assets.getTexture(Assets.GAME_FIELD);
        Image game_field = new Image(game_field_Texture);
        game_field.setScale((float)(app.SCREEN_WIDTH  / game_field.getWidth() ),
                (float)(app.SCREEN_WIDTH   / game_field.getWidth() ));
        game_field.setPosition(0, app.SCREEN_HEIGHT/ 4 -10);
        stage.addActor(game_field);

        //создание массива пятнашек из класса Breek и отображение его на поле
        breaks = new Break[4][4];
        for (int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
            {
                breaks[i][j] = new Break(array4x4[i][j], i, j, app.SCREEN_WIDTH, app.SCREEN_HEIGHT,  game_field.getWidth());
                breaks[i][j].setPosX();
                breaks[i][j].setPosY();

                breaks[i][j].getImage();
                /*
                System.out.println(breaks[i][j].getNum()+ "  " + breaks[i][j].getPosX()+ "  " + breaks[i][j].getPosY());
                System.out.println("app.SCREEN_WIDTH = " + app.SCREEN_WIDTH + "\n app.SCREEN_HEIGHT = " + app.SCREEN_HEIGHT);
                System.out.println("game_field.getWidth() = " + game_field.getWidth());
                System.out.println("480 / 2600. = " + 480 / 2600.);
               */
                if(breaks[i][j].getNum() != 0)
                {
                    breaks[i][j].getImage().setScale((float)(app.SCREEN_WIDTH  / (4 * 512.)),(float)(app.SCREEN_WIDTH  / ((4 * 512.)) ));
                    breaks[i][j].getImage().setPosition(breaks[i][j].getPosX(), breaks[i][j].getPosY());

                    stage.addActor(breaks[i][j].getImage());
                    final int I = i;//нельзя во внутреннем классе использовать не final int i
                    final int J = j;
                    final float q = game_field.getWidth();
                    breaks[i][j].getImage().addListener(new ActorGestureListener(){
                        @Override
                        public void tap(InputEvent event, float x, float y, int count, int button){
                            super.tap(event, x, y, count, button);
                            //Здесь добавить то, что будем делать при нажатии на пятнашку
                            System.out.println("Press the break " + breaks[I][J].getNum());
                            Controller controller = new Controller(I, J, breaks);
                            Break br = new Break(0, I, J,app.SCREEN_WIDTH, app.SCREEN_HEIGHT, q  );
                            if(controller.getDirection().equals("none"))
                                System.out.println("none");
                            else if(controller.getDirection().equals("left"))
                            {
                                System.out.println("left");
//ПОКА ЧТО ФИГНЯ
                                breaks[I][J].setPosX(breaks[I][J+1].getPosX());
                                /*
                                for(int k = controller.getJ0()+1; k < J; k++)
                                    breaks[I][k-1] = breaks[I][k];
                                breaks[I][J] = br;
                                */
                            }
                            else if(controller.getDirection().equals("right"))
                            {
                                System.out.println("right");
                            }
                            else if(controller.getDirection().equals("up"))
                            {
                                System.out.println("up");
                            }
                            else if(controller.getDirection().equals("down"))
                            {
                                System.out.println("down");
                            }

                            for (int j = 0; j < 4; j++)
                            {
                                for(int i = 0; i < 4; i++)
                                {
                                    System.out.print(breaks[i][j].getNum() + " ");
                                }
                                System.out.println();
                            }
                        }
                    });
                }
            }
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
