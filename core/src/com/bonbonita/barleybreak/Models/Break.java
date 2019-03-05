package com.bonbonita.barleybreak.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.bonbonita.barleybreak.Views.Assets;

/**
 * Created by BonBonita on 06.02.2019.
 */

public class Break {
    private int Num;        //цифра на пятнашке
    private int posX, posY; //позиция на экране
    private int posI, posJ; //позиция в матрице
    private Texture face;    //адрес текстуры, соответствующей Num
    private Image image;     //

    private int ScreenWidth;
    private int ScreenHeight;
    private float FieldWidth;

    public Break(int num, int posI, int posJ, int screenWidth, int screenHeight, float fieldWidth){
        this.Num = num;
        this.posI = posI;
        this.posJ = posJ;

        this.ScreenWidth = screenWidth;
        this.ScreenHeight = screenHeight;
        this.FieldWidth = fieldWidth;

        switch (Num){
            case 1:
                this.face = Assets.getTexture(Assets.BREAK_01);
                break;
            case 2:
                this.face = Assets.getTexture(Assets.BREAK_02);
                break;
            case 3:
                this.face = Assets.getTexture(Assets.BREAK_03);
                break;
            case 4:
                this.face = Assets.getTexture(Assets.BREAK_04);
                break;
            case 5:
                this.face = Assets.getTexture(Assets.BREAK_05);
                break;
            case 6:
                this.face = Assets.getTexture(Assets.BREAK_06);
                break;
            case 7:
                this.face = Assets.getTexture(Assets.BREAK_07);
                break;
            case 8:
                this.face = Assets.getTexture(Assets.BREAK_08);
                break;
            case 9:
                this.face = Assets.getTexture(Assets.BREAK_09);
                break;
            case 10:
                this.face = Assets.getTexture(Assets.BREAK_10);
                break;
            case 11:
                this.face = Assets.getTexture(Assets.BREAK_11);
                break;
            case 12:
                this.face = Assets.getTexture(Assets.BREAK_12);
                break;
            case 13:
                this.face = Assets.getTexture(Assets.BREAK_13);
                break;
            case 14:
                this.face = Assets.getTexture(Assets.BREAK_14);
                break;
            case 15:
                this.face = Assets.getTexture(Assets.BREAK_15);
                break;
        }
        if(Num != 0)
            this.image = new Image(face);
    }

    public Break(int num, int posI, int posJ, int posX, int posY){
        this.Num = num;
        this.posI = posI;
        this.posJ = posJ;
        this.posX = posX;
        this.posY = posY;
    }

    public void setPosX(){
        //НАДО ПОРАБОТАТЬ НАД ФОРМУЛОЙ
        this.posX = (10 + 512 * posI) * ScreenWidth / (4*512 );
    }
    public void setPosX(int posX){
        //НАДО ПОРАБОТАТЬ НАД ФОРМУЛОЙ
        this.posX = posX;
    }

    public void setPosY(){
        //НАДО ПОРАБОТАТЬ НАД ФОРМУЛОЙ
        //this.posY = (ScreenHeight / 4 - 100 + 512 * 5  - 512 * posJ) * ScreenWidth / (int)FieldWidth ;
        this.posY = (ScreenHeight   + 512 * (2-posJ)) * ScreenWidth / (4*512 );
        //this.posY = (ScreenHeight   - 512 * posJ) * ScreenWidth / (4*512 );
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public Image getImage(){
        return this.image;
    }

    public int getNum(){
        return Num;
    }

    public String getFullInfoAboutBreak()
    {
        return "Num " + Num + "\ti=" + posI + "\tj=" + posJ + "\tx=" + posX + "\ty=" + posY ;
    }
}
