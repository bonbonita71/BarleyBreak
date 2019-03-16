package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

/**
 * Created by BonBonita on 10.03.2019.
 */

public class GameScreen implements Screen {
    private final BarleyBreak app;
    private Stage stage;

    private enum STATE{START, PLAYING, GAME_OVER}
    STATE state = STATE.START;

    int array[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    int array4x4[][] = new int[4][4];
    MyActor[] actors;

    public GameScreen(BarleyBreak app) {
        this.app = app;
    }

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        final Image background = new Image(Assets.getTexture(Assets.BACKGROUND));;
        background.setPosition(0, 0);
        background.setScale(app.SCREEN_HEIGHT/ background.getHeight());
        stage.addActor(background);

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



        actors = new MyActor[16];

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

        k=0;
        for(int i = 0; i< 4; i++)
            for (int j = 0; j < 4; j++)
            {
                actors[k] = new MyActor(array4x4[i][j], app);
                if(array4x4[i][j] != 0)
                {
                    final float aspectRatio = (app.SCREEN_WIDTH - 2f * app.SCREEN_WIDTH / 10f)/(4f * actors[k].getImage().getWidth());
                    actors[k].getImage().setOrigin( actors[k].getImage().getWidth() / 2f,  actors[k].getImage().getHeight() / 2f);
                    System.out.println( actors[k].getImage().getWidth() / 2f);
                    System.out.println( actors[k].getImage().getHeight() / 2f);
                    actors[k].setMyPosition(i, j);
                    actors[k].getImage().setScale((float)(aspectRatio),(float)(aspectRatio));
                    actors[k].getImage().setPosition(actors[k].getPosX(), actors[k].getPosY() );

                    stage.addActor(actors[k].getImage());
                    final int I = i;//нельзя во внутреннем классе использовать не final int i
                    final int J = j;
                    final int K = k;
                    actors[K].getImage().addListener(new ActorGestureListener() {
                        @Override
                        public void tap(InputEvent event, float x, float y, int count, int button) {
                            super.tap(event, x, y, count, button);
                            //Здесь добавить то, что будем делать при нажатии на пятнашку
                            System.out.println("Pressed the break " + actors[K].getNum());
                            //--------------------------------
                            Controller controller = new Controller(I, J, array4x4);
                            controller.WhereIs0(I, J, array4x4);
                            if(controller.getDirection().equals("none"))
                            {
                                //позже переделать так, чтобы масштабирование проходило относительно центра originX, originY
                                System.out.println("none");
                                ScaleToAction scaleByAction1 = new ScaleToAction();
                                scaleByAction1.setScale(.9f * aspectRatio);
                                scaleByAction1.setDuration(.2f);

                                ScaleToAction  scaleByAction2 = new ScaleToAction();
                                scaleByAction2.setScale(1f * aspectRatio);
                                scaleByAction2.setDuration(.2f );

                                SequenceAction sequenceAction = new SequenceAction(scaleByAction1, scaleByAction2);
                                actors[K].getImage().addAction(sequenceAction);


                            }
                            else if(controller.getDirection().equals("left"))
                            {
                                System.out.println("left");

                                for(int i = controller.getI0()+1; i <= I; i++){
                                    int temp = array4x4[i-1][J];
                                    array4x4[i-1][J] = array4x4[i][J];
                                    if(i == I)
                                        array4x4[i][J] = 0;

                                }
                                controller.WhereIs0(I, J, array4x4);
                            }
                            else if(controller.getDirection().equals("right"))
                            {
                                System.out.println("right");

                                for(int i = controller.getI0()-1; i >= I; i--){
                                    array4x4[i+1][J] = array4x4[i][J];
                                    if(i == I)
                                        array4x4[i][J] = 0;
                                }
                                controller.WhereIs0(I, J, array4x4);
                            }
                            else if(controller.getDirection().equals("up"))
                            {
                                System.out.println("up");
                                for(int j = controller.getJ0()+1; j <= J; j++){
                                    array4x4[I][j-1] = array4x4[I][j];
                                    if(j == J)
                                        array4x4[I][j] = 0;
                                }
                                controller.WhereIs0(I, J, array4x4);
                            }
                            else if(controller.getDirection().equals("down"))
                            {
                                System.out.println("down");
                                for(int j = controller.getJ0()-1; j >= J; j--){
                                    array4x4[I][j+1] = array4x4[I][j];
                                    if(j == J)
                                        array4x4[I][j] = 0;
                                }
                                controller.WhereIs0(I, J, array4x4);
                            }
                            //--------------------------------
                            PrintArray(array4x4);

                        }

                    });
                }
                k++;
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
                break;
            case PLAYING:
                break;
            case GAME_OVER:
                break;
        }

        for(int k = 0; k< 16; k++)
        {
            boolean flag = false;
            for(int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    if(array4x4[i][j] == actors[k].getNum())
                    {
                        actors[k].setMyPosition(i, j);
                        MoveToAction mta = new MoveToAction();
                        mta.setPosition(actors[k].getPosX(), actors[k].getPosY());
                        actors[k].getImage().addAction(mta);
                        flag = true;
                        break;
                    }
                }
                if(flag == true)
                    break;
            }
        }

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
        stage.dispose();
    }
}
