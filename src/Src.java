/**
 * Created by med_high on 07.06.2015.
 */
import org.newdawn.slick.*;
import org.newdawn.slick.SlickException;
public  class Src {

    Image SELIK;
    Animation FIRE;

    public void init ()throws SlickException{
        SELIK = new Image("src/source/selik.png");
        Image fire1 = new Image("src/source/fire1.png");
        Image fire2 = new Image("src/source/fire2.png");
        Image fire3 = new Image("src/source/fire3.png");
        Image fire4 = new Image("src/source/fire4.png");
        Image fire5 = new Image("src/source/fire5.png");
        Image fire6 = new Image("src/source/fire6.png");
        Image fire7 = new Image("src/source/fire7.png");
        Image[] frames = {fire1,fire2,fire3,fire4,fire5,fire6,fire7};
        FIRE = new Animation(frames,70);
    }
    public Src() throws SlickException{
        init();
    }
}
