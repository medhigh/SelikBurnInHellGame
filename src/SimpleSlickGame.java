import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import engine.SimpleGameEgine;
import engine.SimplePosition;
import org.newdawn.slick.*;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.geom.Transform;

public class SimpleSlickGame extends BasicGame
{
    public Integer syn=0;
    public SimpleGameEgine engine;
    private Input input;
    private Src src;
    public SimpleSlickGame(String gamename)
    {
        super(gamename);

    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        input = gc.getInput();
        engine = new SimpleGameEgine();
        src = new Src();
        src.init();
        engine.FT.addTarget();
        engine.FT.addTarget();
        engine.FT.addTarget();
        engine.FT.addTarget();
    }
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (input.isKeyDown(Input.KEY_W)){
            engine.pushUp();
            engine.pushUp();
        }
        if (input.isKeyDown(Input.KEY_S)){
            engine.pushDown();
            engine.pushDown();
        }
        if (input.isKeyDown(Input.KEY_A)){
            engine.pushLeft();
            engine.pushLeft();
        }
        if (input.isKeyDown(Input.KEY_D)){
            engine.pushRight();
            engine.pushRight();
        }

    }

    /*
     * Вызываеться при каждой смене кадра(частота смены +FPS)
     */
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawImage(src.SELIK,150,150);
        g.drawAnimation(src.FIRE, 150, 150);
        for (Map.Entry<SimplePosition,Boolean> tmp:engine.FT.map.entrySet()){
            g.drawImage(src.SELIK,tmp.getKey().getX(),tmp.getKey().getY());
        }
        g.setColor(new Color(0,180,255));
        g.drawRect(engine.getCharX1(),engine.getCharY1(),engine.getCharWidth(),engine.getCharHeigth());
        engine.FT.fall();
        engine.FT.addTarget();
    }

    public static void main(String[] args)
    {
        SimpleSlickGame smp =new SimpleSlickGame("Simple Slick Game");

        try
        {
            AppGameContainer app;
            app = new AppGameContainer(smp);
            app.setDisplayMode(640, 480, false);
            app.setShowFPS(true);
            app.setTargetFrameRate(15);
            //Initiate here

            app.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
