package com.mygdx.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.Screens.PlayScreen;
import com.mygdx.game.MyGdxGame;

public class Tank1 extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion tank;

    private static Tank1 instance;

    public static Tank1 getInstance(World world, PlayScreen screen, String name, int x, int y, int posx, int posy){
        if(instance == null) instance = new Tank1(world, screen, name, x, y, posx, posy);
        return instance;
    }

    private Tank1(World world, PlayScreen screen, String name, int x, int y, int posx, int posy){
        super(screen.getAtlas().findRegion(name));
        this.world = world;

        BodyDef bdef = new BodyDef();
        bdef.position.set(posx/MyGdxGame.PPM, posy/MyGdxGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef  = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/MyGdxGame.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

        tank = new TextureRegion(getTexture(), x, y, 48, 48);
        setBounds(x, y, 16/MyGdxGame.PPM, 16/MyGdxGame.PPM);
        setRegion(tank);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,
                b2body.getPosition().y - getHeight()/2);
    }
}