package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.barleybreak.BarleyBreak;
import com.bonbonita.barleybreak.Controllers.Controller;
import com.bonbonita.barleybreak.Models.MyActor;

import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by BonBonita on 10.03.2019.
 */

public class GameScreen implements Screen {
    private final BarleyBreak app;
    private Stage stage;

    private enum STATE{START, PLAYING, GAME_OVER}
    STATE state;

    int array[] = new int[16];
    int array4x4[][] = new int[4][4];
    MyActor[] actors;
    Controller controller;

    public GameScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);
//фон
        final Image background = new Image(Assets.getTexture(Assets.BACKGROUND));;
        background.setPosition(0, 0);
        background.setScale(app.SCREEN_HEIGHT/ background.getHeight());
        stage.addActor(background);
//кнопка реплэй
        Texture replay_Texture = Assets.getTexture(Assets.REPLAY_BUTTON);
        ImageButton replay = new ImageButton(new TextureRegionDrawable(new TextureRegion(replay_Texture)));
        replay.setPosition(app.SCREEN_WIDTH  - replay.getWidth() -15,  app.SCREEN_HEIGHT - replay.getHeight()-15 );
        stage.addActor(replay);
        replay.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                //-----------------------------------------------------------------
                app.setScreen(new LoadingScreen(app));
                //--------------------------------------------------------------------
                dispose();
            }
        });
        //создаю цифровой массив пятнашек
        for(int i = 0; i < array.length; i++)
            array[i] = i;
        System.out.println("1D array in start:");
        PrintArray(array);
        shuffleArray(array);
        System.out.println("1D array after shuffle:");
        PrintArray(array);
        int k = 0;
        for(int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
            {
                array4x4[i][j] = array[k];
                k++;
            }
        System.out.println("2D array :");
        PrintArray(array4x4);
        //создаю одномерный массив актёров без местоположения
        actors = new MyActor[16];

        for(int i = 0; i< 16; i++)
        {
            actors[i] = new MyActor(i, app);
            final float aspectRatio = (app.SCREEN_WIDTH - 2f * app.SCREEN_WIDTH / 10f)/(4f * actors[i].getImage().getWidth());
            actors[i].getImage().setOrigin( actors[i].getImage().getWidth() / 2f,  actors[i].getImage().getHeight() / 2f);
            for(int i1 = 0; i1< 4; i1++)
                for (int j = 0; j < 4; j++)
                    if(array4x4[i1][j] == i) {
                        // ЗДЕСЬ КОВІРЯТЬСЯ
                        actors[i].setMyPosition(i1, j);
                        actors[i].getImage().setScale((float) (aspectRatio), (float) (aspectRatio));
                        actors[i].getImage().setPosition(actors[i].getPosX(), actors[i].getPosY());
                        stage.addActor(actors[i].getImage());
                        //------------------
                        final int I = i1;
                        final int J = j;
                        actors[i].getImage().addListener(new ActorGestureListener() {
                            @Override
                            public void tap(InputEvent event, float x, float y, int count, int button) {
                                super.tap(event, x, y, count, button);
                                //Здесь добавить то, что будем делать при нажатии на пятнашку
                                System.out.println("Pressed the break " + actors[I * 4 + J].getNum());
                                System.out.println("I = " + I);
                                System.out.println("J = " + J);
                                //--------------------------------
                                controller = new Controller(I, J, array4x4);

                                if (controller.getDirection().equals("none")) {
                                    //позже переделать так, чтобы масштабирование проходило относительно центра originX, originY
                                    System.out.println("none");
                                    ScaleToAction scaleByAction1 = new ScaleToAction();
                                    scaleByAction1.setScale(.9f * aspectRatio);
                                    scaleByAction1.setDuration(.2f);

                                    ScaleToAction scaleByAction2 = new ScaleToAction();
                                    scaleByAction2.setScale(1f * aspectRatio);
                                    scaleByAction2.setDuration(.2f);

                                    SequenceAction sequenceAction = new SequenceAction(scaleByAction1, scaleByAction2);
                                    actors[I * 4 + J].getImage().addAction(sequenceAction);

                                } else if (controller.getDirection().equals("left")) {
                                    System.out.println("left");

                                    for (int i = controller.getI0() + 1; i <= I; i++) {
                                        int temp = array4x4[i - 1][J];
                                        array4x4[i - 1][J] = array4x4[i][J];
                                        if (i == I)
                                            array4x4[i][J] = 0;
                                    }
                                    controller.WhereIs0(I, J, array4x4);
                                } else if (controller.getDirection().equals("right")) {
                                    System.out.println("right");

                                    for (int i = controller.getI0() - 1; i >= I; i--) {
                                        array4x4[i + 1][J] = array4x4[i][J];
                                        if (i == I)
                                            array4x4[i][J] = 0;
                                    }
                                    controller.WhereIs0(I, J, array4x4);
                                } else if (controller.getDirection().equals("up")) {
                                    System.out.println("up");
                                    for (int j = controller.getJ0() + 1; j <= J; j++) {
                                        array4x4[I][j - 1] = array4x4[I][j];
                                        if (j == J)
                                            array4x4[I][j] = 0;
                                    }
                                    controller.WhereIs0(I, J, array4x4);
                                } else if (controller.getDirection().equals("down")) {
                                    System.out.println("down");
                                    for (int j = controller.getJ0() - 1; j >= J; j--) {
                                        array4x4[I][j + 1] = array4x4[I][j];
                                        if (j == J)
                                            array4x4[I][j] = 0;
                                    }
                                    controller.WhereIs0(I, J, array4x4);
                                }
                                //--------------------------------
                                PrintArray(array4x4);

                            }

                        });
                    }

                //-------------


        }
        state = STATE.START;

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
        for (int i = 0; i < 16; i++)
            System.out.print(array[i] + " " );
        System.out.println();
    }

    private void PrintArray(int[][] array4x4)
    {
        for(int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
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
        switch (state){
            case START:
                state = STATE.PLAYING;
                break;
            case PLAYING:
                //here I must to write down  a method for controlling end of game
                if(funMayBeEndOfGame()== true)
                {
                   state = STATE.GAME_OVER;
                    break;
                }






                for(int h = 0; h< 16; h++)
                {
                    boolean flag = false;
                    for(int i = 0; i < 4; i++)
                    {
                        for (int j = 0; j < 4; j++)
                        {
                            if(array4x4[i][j] == actors[h].getNum())
                            {
                                actors[h].setMyPosition(i, j);
                                MoveToAction mta = new MoveToAction();
                                mta.setPosition(actors[h].getPosX(), actors[h].getPosY());
                                actors[h].getImage().addAction(mta);
                                flag = true;
                                break;
                            }
                        }
                        if(flag == true)
                            break;
                    }
                }

                break;
            case GAME_OVER:
                //
                Texture welldone_Texture = Assets.getTexture(Assets.WELLDONE_BUTTON);
                ImageButton welldone = new ImageButton(new TextureRegionDrawable(new TextureRegion(welldone_Texture)));
                welldone.setPosition(app.SCREEN_WIDTH / 2  - welldone.getWidth() / 2 ,  app.SCREEN_HEIGHT /2 - welldone.getHeight() /2 );
                stage.addActor(welldone);
                welldone.addListener(new ActorGestureListener(){
                    @Override
                    public void tap(InputEvent event, float x, float y, int count, int button){
                        super.tap(event, x, y, count, button);
                        //-----------------------------------------------------------------
                        app.setScreen(new LoadingScreen(app));
                        //--------------------------------------------------------------------
                        dispose();
                    }
                });
                break;
        }

        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    public boolean funMayBeEndOfGame(){
        int testNum = 1;
        boolean flag = true;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if(array4x4[i][j] != testNum)
                {
                    flag = false;
                    break;
                }
                else
                {
                    testNum++;
                    if(testNum == 14)
                        return true;
                }

            }
            if(flag == false) break;
        }

        return flag;
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
        stage.dispose();
    }
}
