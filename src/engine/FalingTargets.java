package engine;

import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * Created by med_high on 07.06.2015.
 */
public class FalingTargets {
    public HashMap<SimplePosition,Boolean> map; //20x20 pixels position from x0y0 of picture //true = flaming

    public FalingTargets() {
        map = new HashMap<>();
    }
    public short frag(Position position){
        short fragCounter = 0;
        if (map != null) {
            short tmp[]=new short[2];
            SimplePosition tmpPos = new SimplePosition();
            for (int i = position.getX1(); i <position.getX2() ; i++) {
                for (int j = position.getY1(); j <position.getY2() ; j++) {
                    if(!map.isEmpty()){
                        tmp[0]=(short)i; tmp[1]=(short)j;
                        tmpPos.setPos(tmp);
                        if(map.containsKey(tmpPos)){
                            if(!map.get(tmpPos)){
                                map.replace(tmpPos,true);
                                fragCounter++;
                            }
                        }
                    }
                }
            }
        }
        return fragCounter;
    }
    public void fall(){
        SimplePosition simplePositionTemp = new SimplePosition();
        for (Map.Entry<SimplePosition,Boolean> tmp:map.entrySet()){
            if((tmp.getKey().getY()==SimpleGameEgine.FIELD[1]+20)) map.remove(tmp);
            else{ // moving down
                simplePositionTemp = tmp.getKey();
                simplePositionTemp.setPos(simplePositionTemp.getX(),(short)(simplePositionTemp.getY()+1));
                if(!map.containsKey(simplePositionTemp)){ // if there is some space
                    tmp.getKey().setPos(tmp.getKey().getX(),(short)(tmp.getKey().getY()+1));
                }
            }
        }
    }
    public void addTarget(){
        Random rand = new Random();
        SimplePosition nextPosition = new SimplePosition();
        int toX = rand.nextInt(SimpleGameEgine.FIELD[0]-40)+20;
        int toY = 0;
        nextPosition.setPos((short) toX, (short) toY);
        if (map != null) {
            if(map.containsKey(nextPosition)){
                addTarget();
            }else{
                map.put(nextPosition,false);
            }
        }
    }
    public HashMap<SimplePosition,Boolean> getImageTargetPositions(){
        HashMap<SimplePosition,Boolean> tmpmap = new HashMap<>();
        SimplePosition sp = new SimplePosition();
        for (Map.Entry<SimplePosition,Boolean> tmp:map.entrySet()) {
            if (tmp.getValue() == false){
                sp.setPos((short)(tmp.getKey().getX()-20),(short)(tmp.getKey().getY()-20));
                tmpmap.put(sp,false);
            }
        }
        return tmpmap;
    }
    public ArrayList<SimplePosition> getImageTargetPositionsFlaming(){
        ArrayList<SimplePosition> list = new ArrayList<>();
        SimplePosition sp = new SimplePosition();
        for (Map.Entry<SimplePosition,Boolean> tmp:map.entrySet()) {
            if (tmp.getValue() == true){
                sp.setPos((short)(tmp.getKey().getX()-20),(short)(tmp.getKey().getY()-20));
                list.add(sp);
            }
        }
        return list;
    }
}
