package engine;

/**
 * Created by med_high on 06.06.2015.
 */
public class SimplePosition {
    private short[] pos=new short[2];
    public short getX(){
        return pos[0];
    }

    public short getY(){
        return pos[1];
    }
    public boolean isEmpty(){
        if (pos == null) {
            return true;
        }else{
            return false;
        }
    }

    public void setPos(short[] pos) {
        this.pos = pos;
    }
    public void setPos(short x,short y){
        this.pos[0]=x;
        this.pos[1]=y;
    }

    public short[] getPos() {

        return pos;
    }

    public SimplePosition() {
    }

    public SimplePosition(short[] pos) {
        this.pos = pos;
    }

}
