package com.bonbonita.barleybreak.Controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bonbonita.barleybreak.Models.Break;
import com.bonbonita.barleybreak.Models.MyActor;

/**
 * Created by BonBonita on 10.02.2019.
 */

public class Controller {
    //int[][] breaks = new int[4][4] ;
    int i;
    int j;
    int i0;
    int j0;
    String direction ="";

    public Controller(int i, int j, int[][] breaks){
        this.i = i;
        this.j = j;
        //this.breaks = breaks;
        WhereIs0(i, j, breaks);
        if(j == j0)
            direction = (i < i0)? "right" : "left";
        else if(i == i0)
            direction = (j < j0)? "down" : "up" ;
        else
            direction = "none";
    }

    public void WhereIs0(int I, int J, int[][] breaks){
        this.i = I;
        this.j = J;
        for(int k = 0; k < 4; k++)
            for (int j = 0; j < 4; j++)
                if(breaks[k][j] == 0)
                {
                    i0 = k;
                    j0 = j;
                }
        System.out.println("i = " + i + " j = " + j);
        System.out.println("i0 = " + i0 + " j0 = " + j0);
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
    public int getI(){
        return this.i;
    }
    public int getJ(){
        return this.j;
    }
}
