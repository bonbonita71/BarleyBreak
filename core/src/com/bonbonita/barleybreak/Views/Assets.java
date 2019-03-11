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
    public static String GAME_FIELD = "images/breaks_and_field/gamefield.png";
    public static String BREAK_00 = "images/breaks_and_field/00.png";
    public static String BREAK_01 = "images/breaks_and_field/01.png";
    public static String BREAK_02 = "images/breaks_and_field/02.png";
    public static String BREAK_03 = "images/breaks_and_field/03.png";
    public static String BREAK_04 = "images/breaks_and_field/04.png";
    public static String BREAK_05 = "images/breaks_and_field/05.png";
    public static String BREAK_06 = "images/breaks_and_field/06.png";
    public static String BREAK_07 = "images/breaks_and_field/07.png";
    public static String BREAK_08 = "images/breaks_and_field/08.png";
    public static String BREAK_09 = "images/breaks_and_field/09.png";
    public static String BREAK_10 = "images/breaks_and_field/10.png";
    public static String BREAK_11 = "images/breaks_and_field/11.png";
    public static String BREAK_12 = "images/breaks_and_field/12.png";
    public static String BREAK_13 = "images/breaks_and_field/13.png";
    public static String BREAK_14 = "images/breaks_and_field/14.png";
    public static String BREAK_15 = "images/breaks_and_field/15.png";
    public static String REPLAY_BUTTON = "images/buttons/playbtn.png";
    public static String INFO_BUTTON = "images/buttons/infobtn.png";
    public static String RECORD_BUTTON = "images/buttons/recordsbtn.png";
    public static String BACKGROUND = "images/background.png";

    private static AssetManager assetManager;
    public static BitmapFont loadingFont;
    public static BitmapFont loadingFont1;

    public static void init(){
        assetManager = new AssetManager();

        float fontSize = .5f;
        loadingFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
        loadingFont.getData().setScale(fontSize, fontSize);

        loadingFont1 = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
        loadingFont1.getData().setScale(fontSize *3, fontSize*3);
    }

    public static void dispose(){
        assetManager.dispose();
        loadingFont.dispose();
        loadingFont1.dispose();
    }

    public static void load(){
        assetManager.load(LOGO, Texture.class);
        assetManager.load(GAME_FIELD, Texture.class);
        assetManager.load(BREAK_00, Texture.class);
        assetManager.load(BREAK_01, Texture.class);
        assetManager.load(BREAK_02, Texture.class);
        assetManager.load(BREAK_03, Texture.class);
        assetManager.load(BREAK_04, Texture.class);
        assetManager.load(BREAK_05, Texture.class);
        assetManager.load(BREAK_06, Texture.class);
        assetManager.load(BREAK_07, Texture.class);
        assetManager.load(BREAK_08, Texture.class);
        assetManager.load(BREAK_09, Texture.class);
        assetManager.load(BREAK_10, Texture.class);
        assetManager.load(BREAK_11, Texture.class);
        assetManager.load(BREAK_12, Texture.class);
        assetManager.load(BREAK_13, Texture.class);
        assetManager.load(BREAK_14, Texture.class);
        assetManager.load(BREAK_15, Texture.class);
        assetManager.load(REPLAY_BUTTON, Texture.class);
        assetManager.load(RECORD_BUTTON, Texture.class);
        assetManager.load(INFO_BUTTON, Texture.class);
        assetManager.load(BACKGROUND, Texture.class);

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
