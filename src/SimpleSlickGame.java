import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import engine.Position;
import engine.SimpleGameEgine;
import engine.SimplePosition;
import javafx.geometry.Pos;
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
        engine.FT.newThreadCreator(900);
    }
    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        if (input.isKeyDown(Input.KEY_W)){
            engine.pushUp();
            engine.pushUp();
            engine.pushUp();
            engine.pushUp();
        }
        if (input.isKeyDown(Input.KEY_S)){
            engine.pushDown();
            engine.pushDown();
            engine.pushDown();
            engine.pushDown();
        }
        if (input.isKeyDown(Input.KEY_A)){
            engine.pushLeft();
            engine.pushLeft();
            engine.pushLeft();
            engine.pushLeft();
            engine.pushLeft();
        }
        if (input.isKeyDown(Input.KEY_D)){
            engine.pushRight();
            engine.pushRight();
            engine.pushRight();
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
        int counter=0;
        g.drawImage(src.TLEN,0,0);
        synchronized (engine.FT.map) {


            for (Map.Entry<SimplePosition, Boolean> tmp : engine.FT.map.entrySet()) {
                counter += engine.FT.frag(engine.getCharPosition());
                if (!tmp.getValue())
                    g.drawImage(src.SELIK, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                else {
                    g.drawImage(src.SELIK, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                    g.drawAnimation(src.FIRE, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                }
                g.drawString("Counter: " + counter, 200, 400);
                g.drawString(engine.getCharPosition()[0] + " " +
                        engine.getCharPosition()[1] + " " +
                        engine.getCharPosition()[2] + " " +
                        engine.getCharPosition()[3] + " " +
                        " ", 200, 420);
            }
            g.setColor(new Color(0, 180, 255));
            g.drawRect(engine.getCharX1(), engine.getCharY1(), engine.getCharWidth(), engine.getCharHeigth());
            engine.FT.fall();
        }
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
            app.setTargetFrameRate(100);
            //Initiate here
            app.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
