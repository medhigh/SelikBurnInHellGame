package engine;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by med_high on 06.06.2015.
 */
public class SimpleGameEgine {
    public static final int[] FIELD = {640,480};
    private int[] character = new int[4];
    private HashSet<Position> obstacles;
    private HashMap<SimplePosition,Boolean> obsMap; //false is free for use by character
    public FalingTargets FT;


    public SimpleGameEgine(){
        character[0]=300;
        character[1]=220;
        character[2]=340;
        character[3]=260;
        initiateMap();
        FT=new FalingTargets();
    }
    public SimpleGameEgine(int[] characterPosition) {
        if(
                characterPosition[0]>=0 & characterPosition[0]<=FIELD[0] &&    //x1
                characterPosition[1]>=0 & characterPosition[1]<=FIELD[1] &&   //y1
                characterPosition[2]>=characterPosition[0] & characterPosition[2]<=FIELD[0] &&   //x2
                characterPosition[3]>=characterPosition[1] & characterPosition[0]<=FIELD[1]   //y2
        )
        this.character = characterPosition;
        initiateMap();
        FT=new FalingTargets();
    }
    public void pushLeft(){
        if(character[0]<=0)return;
        SimplePosition sp = new SimplePosition();
        for (int i = character[1]; i <character[3] ; i++) {
            sp.setPos((short) (character[0]-1),(short)i);
            if (obsMap != null) {
                if(obsMap.containsKey(sp)&&obsMap.get(sp))return;
            }
        }
        character[0]--;
        character[2]--;
    }
    public void pushRight(){
        if(character[2]>=FIELD[0])return;
        SimplePosition sp = new SimplePosition();
        for (int i = character[1]; i <character[3] ; i++) {
            sp.setPos((short) (character[2]+1),(short)i);
            if (obsMap != null) {
                if(obsMap.containsKey(sp)&&obsMap.get(sp))return;
            }
        }
        character[0]++;
        character[2]++;
    }
    public void pushUp(){
        if (character[1]<=0)return;
        SimplePosition sp = new SimplePosition();
        for (int i = character[0]; i <character[2]; i++) {
            sp.setPos((short)i,(short)(character[1]-1));
            if (obsMap != null) {
                if(obsMap.containsKey(sp)&&obsMap.get(sp))return;
            }
        }
        character[1]--;
        character[3]--;
    }
    public void pushDown(){
        if (character[3]>=FIELD[1])return;
        SimplePosition sp = new SimplePosition();
        for (int i = character[0]; i <character[2]; i++) {
            sp.setPos((short)i,(short)(character[3]+1));
            if (obsMap != null) {
                if(obsMap.containsKey(sp)&&obsMap.get(sp))return;
            }
        }
        character[1]++;
        character[3]++;
    }

    public void setCharacter(int[] character) {
        this.character = character;
    }

    public static int[] getFIELD() {
        return FIELD;
    }

    public int[] getCharPosition() {
        return character;
    }
    public void initiateMap(){
        this.obstacles = new HashSet<>();
        this.obsMap = new HashMap<>();
        short[] tempPos=new short[2];
        for (int i = 0; i < FIELD[0]; i++) { //for X
            for (int j = 0; j < FIELD[1]; j++) { //for Y
                tempPos[0]=(short)i;
                tempPos[1]=(short)j;
                obsMap.put(new SimplePosition(tempPos),false);
            }
        }

        if (!obstacles.isEmpty()) {
            //Initiate map
            SimplePosition spTemp = new SimplePosition();
            for(Position pos:obstacles){
                for (int i = pos.getX1(); i <pos.getX2(); i++) { //for X
                    for (int j = pos.getY1(); j < pos.getY2(); j++) {
                        spTemp.setPos((short)i,(short)j);
                        if(obsMap.containsKey(spTemp))
                            obsMap.replace(spTemp,true);
                        else{
                            obsMap.put(spTemp,true);
                        }
                    }
                }
            }
        }else{
            //Fill obsMap for Empty
        }
    }
    public int getCharX1(){
        return character[0];
    }
    public int getCharY1(){
        return character[1];
    }
    public int getCharX2(){
        return character[2];
    }
    public int getCharY2(){
        return character[3];
    }
    public int getCharWidth(){
        return character[2]-character[0];
    }
    public int getCharHeigth(){
        return character[3]-character[1];
    }
    public FalingTargets getFT() {
        return FT;
    }

}
