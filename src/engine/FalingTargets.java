package engine;

import org.lwjgl.Sys;

import java.util.*;

/**
 * Created by med_high on 07.06.2015.
 */
public class FalingTargets {
    public WeakHashMap<SimplePosition,Boolean> map; //20x20 pixels position from x0y0 of picture //true = flaming


    public FalingTargets() {
        map = new WeakHashMap<>();
    }

    public int frag(int[] mass){
        int frags = 0;
        double x=0; double y=0;
        x=(double)(mass[0]+mass[2])/2;
        y=(double)(mass[1]+mass[3])/2;
        for (Map.Entry<SimplePosition,Boolean> tmp:map.entrySet()){

            //Math.sqrt(Math.pow((double)mass[2]-mass[0],2d)+Math.pow((double)mass[3]-mass[1],2d));
           double distance= Math.sqrt(Math.pow(Math.max(tmp.getKey().getX(),x)-Math.min(tmp.getKey().getX(),x),2d)+
                                      Math.pow(Math.max(tmp.getKey().getY(),y)-Math.min(tmp.getKey().getY(),y),2d));
            if(distance<=25D) {
                tmp.setValue(true);
                frags++;
            }
        }
        return frags;
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
    public void newThreadCreator(int nanos){
        Thread creator = new Thread(){
            @Override
            public void run() {
                for (;;) {
                    synchronized (map){
                    addTarget();
                    }
                    try {
                        sleep((long)nanos);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        creator.start();
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
    public HashMap<SimplePosition,Boolean> getImageTargetPositionsFlaming(){
        HashMap<SimplePosition,Boolean> tmpmap = new HashMap<>();
        SimplePosition sp = new SimplePosition();
        for (Map.Entry<SimplePosition,Boolean> tmp:map.entrySet()) {
            if (tmp.getValue() == true){
                sp.setPos((short)(tmp.getKey().getX()-20),(short)(tmp.getKey().getY()-20));
                tmpmap.put(sp, false);
            }
        }
        return tmpmap;
    }
}
