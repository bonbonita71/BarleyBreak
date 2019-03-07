package com.bonbonita.barleybreak.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.bonbonita.barleybreak.BarleyBreak;

/**
 * Created by BonBonita on 04.02.2019.
 */

public class LoadingScreen implements Screen {
    private final BarleyBreak app;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private float progress;
    private float titleWidth;
    private float titleWidth1;
    private String TITLE = "Loading";
    private String TITLE1 = " Game: Barley Break";

    public LoadingScreen(BarleyBreak app) {
        this.app = app;

        shapeRenderer = new ShapeRenderer();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(app.camera.combined);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(Assets.loadingFont, TITLE);
        titleWidth = layout.width;

        GlyphLayout layout1 = new GlyphLayout();
        layout1.setText(Assets.loadingFont1, TITLE1);
        titleWidth1 = layout1.width ;
    }


    @Override
    public void show() {
        shapeRenderer.setProjectionMatrix(app.camera.combined);
        this.progress = 0;
        Assets.load();
    }

    private void update(float delta){
        progress = MathUtils.lerp(progress, Assets.getProgress(), .1f);
        if(Assets.update() && progress >= Assets.getProgress() - .001f) {
            //app.setScreen(app.splashScreen);
            app.setScreen(app.recordScreen);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.enableBlending();
        // draw title
        Assets.loadingFont.draw(batch, TITLE, (app.SCREEN_WIDTH - titleWidth) / 2 - 1, app.SCREEN_HEIGHT / 2 + 50);
        Assets.loadingFont.draw(batch, TITLE1, (app.SCREEN_WIDTH - titleWidth1) / 2 - 1, app.SCREEN_HEIGHT / 2 - 15);
        batch.end();

        update(delta);

        int xOffset = 30;
        int yOffset = 10;

        // draw progress bar - red on black
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(xOffset, app.camera.viewportHeight / 2 - yOffset, app.camera.viewportWidth - xOffset * 2, yOffset * 2);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(xOffset + 2, app.camera.viewportHeight / 2 - (yOffset - 2),
                progress * (app.camera.viewportWidth - (xOffset + 2) * 2), (yOffset - 2) * 2);
        shapeRenderer.end();
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
        batch.dispose();
        shapeRenderer.dispose();
    }
}
