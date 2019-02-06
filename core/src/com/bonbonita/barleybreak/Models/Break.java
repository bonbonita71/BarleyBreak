package com.bonbonita.barleybreak.Models;

import com.badlogic.gdx.graphics.Texture;
import com.bonbonita.barleybreak.Views.Assets;

/**
 * Created by BonBonita on 06.02.2019.
 */

public class Break {
    int Num;        //цифра на пятнашке
    int posX, posY; //позиция на экране
    int posI, posJ; //позиция в матрице
    Texture face;    //адрес текстуры, соответствующей Num

    Break(int num, int posI, int posJ){
        this.Num = num;
        this.posI = posI;
        this.posJ = posJ;
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
    }

    public void setPosX(){
        this.posX = posI;//НАДО ПОРАБОТАТЬ НАД ФОРМУЛОЙ
    }

    public void setPosY(){
        this.posY = posJ;//НАДО ПОРАБОТАТЬ НАД ФОРМУЛОЙ
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }
}
