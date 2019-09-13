/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo
 */
public class ClusterK extends Cluster{
    ArrayList<Group> Belonging;
    float Center;
    float previousCenter;
    
    ClusterK(){
        Belonging= new ArrayList<Group>();
        Center=0;
    }
    
    /**
     * Devuelve el centroide del cluster
     * @return centroide del cluster
     */
    public float getCenter(){
        return this.Center;
    }
    
    /**
     * Asigna un nuevo centro al cluster
     * @param c numero flotante
     */
    public void setCenter(float c){
        previousCenter=Center;
        Center=c;
    }
    
    /**
     * Agrega un nuevo grupo de datos flotantes al cluster
     * @param g objeto grupo de valores flotantes
     */
    public void insert(Group g){
        Belonging.add(g);
    }
    
    
    /**
     * Vacia la lista de grupos de datos que pertenecen al cluster
     */
    @Override
    public void empty(){
        Belonging.clear();
    }
    
    /**
     * Calcula el centroide del cluster como promedio de grupos que pertenecen
     */
    @Override
    public void calculeCenter(){
      previousCenter=Center;
        Center=0;
        int Belongingsize=this.Belonging.size();
        int totaldata=0;
        for(int i=0;i<Belongingsize;i++){
            Center+=(Belonging.get(i).getValue()*Belonging.get(i).getAmount());
            totaldata+=Belonging.get(i).getAmount();
        }
        if(Belongingsize!=0){
            Center/=totaldata;
        }else{
            System.out.print("Atencion /0 K");
            Center=previousCenter+20;
        }  
    }
    
    /**
     * Calcula el cambio que hay entre el centroide anterior y el actual
     * @return distancia entre centroide anterior y actual
     */
    @Override
    public float diff(){
        return Math.abs(previousCenter-Center);
    }
    
    /**
     * Imprme datos de cluster a terminal
     */
    @Override
    public void showCluster(){
        System.out.println("Center "+this.Center+ "\tData Amount: "+this.Belonging.size());
    }
    
    
}
