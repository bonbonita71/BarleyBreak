package com.bonbonita.barleybreak.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bonbonita.barleybreak.Models.Break;
import com.bonbonita.barleybreak.Models.MyActor;

/**
 * Created by BonBonita on 10.02.2019.
 */

public class Controller {
    MyActor[][] breaks = new MyActor[4][4] ;
    int i;
    int j;
    int i0;
    int j0;
    String direction ="";

    public Controller(int i, int j, MyActor[][] breaks){
        this.i = i;
        this.j = j;
        this.breaks = breaks;
        WhereIs0();
        if(j == j0)
            direction = (i < i0)? "right" : "left";
        else if(i == i0)
            direction = (j < j0)? "down" : "up";
        else
            direction = "none";
    }

    public void WhereIs0(){
        for (int j = 0; j < 4; j++)
            for(int i = 0; i < 4; i++)
                if(breaks[i][j].getNum() == 0)
                {
                    i0 = i;
                    j0 = j;
                }
    }


    public String getDirection(){
        return this.direction;
    }
    public int getI0(){
        return this.i0;
    }
    public int getJ0(){
        return this.j0;
    }
}
