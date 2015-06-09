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
    static {
        // error System.setProperty("org.lwjgl.librarypath", "lib");
        //org.lwjgl.librarypath
        //System.loadLibrary("//"); // no .dll or .so extension!
        //System.loadLibrary("lib"); // no .dll or .so extension!
        ///System.loadLibrary("lib/"); // no .dll or .so extension!
        //System.loadLibrary("lib/lib"); // no .dll or .so extension!
        //!! error System.loadLibrary("lwjgl64"); // no .dll or .so extension!
    }
    public Integer score=0;
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

        g.drawImage(src.TLEN,0,0);
        synchronized (engine.FT.map) {

            for (Map.Entry<SimplePosition, Boolean> tmp : engine.FT.map.entrySet()) {
                score += engine.FT.frag(engine.getCharPosition());
                if (!tmp.getValue())
                    g.drawImage(src.SELIK, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                else {
                    g.drawImage(src.SELIK, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                    g.drawAnimation(src.FIRE, tmp.getKey().getX() - 20, tmp.getKey().getY() - 20);
                }
            }
            g.setColor(new Color(0, 180, 255));
            g.drawString("Counter: " + score, 200, 400);
            g.drawString(engine.getCharPosition()[0] + " " +
                        engine.getCharPosition()[1] + " " +
                        engine.getCharPosition()[2] + " " +
                        engine.getCharPosition()[3] + " " +
                        " ", 200, 420);
            g.drawRect(engine.getCharX1(), engine.getCharY1(), engine.getCharWidth(), engine.getCharHeigth());
            engine.FT.fall();
            // TEST CODE BEGINS!!! :
            Image imgg = src.SELIK.copy();
            imgg.setRotation(180);
            //g.drawImage(imgg,200,200);
            g.drawAnimation(src.FIRE, engine.getCharX1()+4, engine.getCharY1()+2);
            //g.drawString(((Float)(imgg.getTextureOffsetX())).toString(),200,200);
        }
    }

    public static void main(String[] args)
    {   //System.setProperty("org.lwjgl.librarypath", "C:/Users/med_high/IdeaProjects/TheGame/lib/");

           //System.setProperty("org.lwjgl.librarypath", "jar://$MODULE_DIR$/lib/");
           //System.setProperty("org.lwjgl64.librarypath", "jar://$MODULE_DIR$/lib/");
        SimpleSlickGame smp =new SimpleSlickGame("Simple Slick Game");

        try
        {
            AppGameContainer app;
            app = new AppGameContainer(smp);
            app.setDisplayMode(640, 480, false);
            app.setShowFPS(true);
            app.setTargetFrameRate(150);
            //Initiate here
            app.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SimpleSlickGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
