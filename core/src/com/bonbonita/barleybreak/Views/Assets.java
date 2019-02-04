package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class Assets
{
    //static names for assets, for easear usage
    public static String LOGO = "images/logo.png";

    private static AssetManager assetManager;
    public static BitmapFont loadingFont;

    public static void init(){
        assetManager = new AssetManager();

        float fontSize = .5f;
        loadingFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
        loadingFont.getData().setScale(fontSize, fontSize);
    }

    public static void dispose(){
        assetManager.dispose();
        loadingFont.dispose();
    }

    public static void load(){
        assetManager.load(LOGO, Texture.class);
    }

    public static float getProgress()
    {
        return assetManager.getProgress();
    }

    public static boolean update(){
        return assetManager.update();
    }

    public static Texture getTexture(String tex){
        return assetManager.get(tex, Texture.class);
    }
}
