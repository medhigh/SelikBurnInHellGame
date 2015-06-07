package engine;

import javafx.geometry.Pos;

/**
 * Created by med_high on 06.06.2015.
 */
public class Position {
    /*
    [0]=x1
    [1]=y1
    [2]=x2
    [3]=y2
     */
    private short[] pos=new short[4];

    public short getX1(){
        return pos[0];
    }
    public short getY1(){
        return pos[1];
    }
    public short getX2(){
        return pos[3];
    }
    public short getY2(){
        return pos[3];
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
    public Position setPos(short x1,short y1,short x2,short y2){
        this.pos[0]=x1;
        this.pos[1]=y1;
        this.pos[2]=x2;
        this.pos[3]=y2;
        return this;
    }

    public short[] getPos() {

        return pos;
    }

    public Position() {
    }

    public Position(short[] pos) {
        this.pos = pos;
    }
}
