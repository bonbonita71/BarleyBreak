package com.bonbonita.barleybreak.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bonbonita.barleybreak.BarleyBreak;
import com.bonbonita.barleybreak.Views.Assets;

/**
 * Created by BonBonita on 05.03.2019.
 */

public class MyActor  {
    private final BarleyBreak app;
    Texture face;
    Image image;
    int posX;
    int posY;
    int posI;
    int posJ;
    int Num;


    public MyActor(int Num, BarleyBreak app) {
        this.app = app;
        this.Num = Num;
        switch (Num) {
            case 0:
                face = Assets.getTexture(Assets.BREAK_00);
                break;
            case 1:
                face = Assets.getTexture(Assets.BREAK_01);
                break;
            case 2:
                face = Assets.getTexture(Assets.BREAK_02);
                break;
            case 3:
                face = Assets.getTexture(Assets.BREAK_03);
                break;
            case 4:
                face = Assets.getTexture(Assets.BREAK_04);
                break;
            case 5:
                face = Assets.getTexture(Assets.BREAK_05);
                break;
            case 6:
                face = Assets.getTexture(Assets.BREAK_06);
                break;
            case 7:
                face = Assets.getTexture(Assets.BREAK_07);
                break;
            case 8:
                face = Assets.getTexture(Assets.BREAK_08);
                break;
            case 9:
                face = Assets.getTexture(Assets.BREAK_09);
                break;
            case 10:
                face = Assets.getTexture(Assets.BREAK_10);
                break;
            case 11:
                face = Assets.getTexture(Assets.BREAK_11);
                break;
            case 12:
                face = Assets.getTexture(Assets.BREAK_12);
                break;
            case 13:
                face = Assets.getTexture(Assets.BREAK_13);
                break;
            case 14:
                face = Assets.getTexture(Assets.BREAK_14);
                break;
            case 15:
                face = Assets.getTexture(Assets.BREAK_15);
                break;
        }
        image = new Image(face);
    }

    public void setMyPosition(int i, int j){
        this.posI = i;
        this.posJ = j;
        float aspectRatio = (app.SCREEN_WIDTH - 2f * app.SCREEN_WIDTH / 10f)/(4f * face.getWidth());
        this.posX = (int)(app.SCREEN_WIDTH/10f + j * face.getWidth() * aspectRatio -1.25 * face.getWidth() * aspectRatio);
        this.posY = (int)(app.SCREEN_HEIGHT/5f + (4f - i)* face.getWidth() * aspectRatio - 1.25 *  face.getWidth() * aspectRatio);
        /*
        System.out.println("app.SCREEN_WIDTH " + app.SCREEN_WIDTH);
        System.out.println("face.getWidth() " + face.getWidth());
        System.out.println("aspectRatio " + aspectRatio);
        System.out.println("app.SCREEN_HEIGHT " + app.SCREEN_WIDTH);
        System.out.println("posX " + posX);
        System.out.println("posY " + posY);
        System.out.println();
        */
    }

    public Image getImage(){
        return image;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public int getPosI(){
        return posI;
    }
    public int getPosJ(){
        return posJ;
    }
    public int getNum(){
        return Num;
    }
}
