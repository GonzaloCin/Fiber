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
public class ClusterFCM extends Cluster3D{
    Point3D Center3;
    Point3D previousCenter;
    float m;
    
    ClusterFCM(){
        Belonging=new ArrayList<Point3>();
    }
    
    /**
     *
     * @return centro del cluster
     */
    public Point3D getCenter(){
        return this.Center3;
    }
    
    /**
     * Asigna un nuevo centro al cluster
     * @param p punto con tres componentes flotantes
     */
    @Override
    public void setCenter(Point3D p){
        this.previousCenter=Center3;
        this.Center3=p;
    }
    
    /**
     * Inserta un nuevo dato al cluster
     * @param a punto de tres componentes a insertar
     */
    @Override
    public void insert(Point3 a){
        Belonging.add(a);
    }
    
    /**
     * Vacia la lista de puntos que pertenecen al cluster
     */
    @Override
    public void empty(){
        Belonging.clear();
    }
    
    /**
     * Calcula el cambio que hay entre el centroide anterior y el actual
     * @return distancia entre centroide anterior y actual
     */
    @Override
    public float diff(){
        return Calculus.distance(Center3,previousCenter);
    }
    
    /**
     * Calcula el centroide del cluster como el promedio de los puntos que pertenecen al cluster
     */
    @Override
    public void calculeCenter(){
        previousCenter=Center3;
        float a=0,b=0,c=0;
        //Center3=new Point3D(0,0,0);
        for(int i=0;i<this.Belonging.size();i++){
            a+=Belonging.get(i).getX();
            b+=Belonging.get(i).getY();
            c+=Belonging.get(i).getZ();
            //Center3=Calculus.sum(Center3,Belonging.get(i));
        }
        if(Belonging.size()!=0){
            a/=Belonging.size();
            b/=Belonging.size();
            c/=Belonging.size();
            Center3=new Point3D(a,b,c);
            //Center3=Calculus.scalarProduct(Center3, 1/Belonging.size());
        }else{
            System.out.print("Atencion /0 K3");
            Center3=new Point3D(128,128,128);;
        }  
    }
    
    /**
     * Imprme datos de cluster a terminal
     */
    @Override
    public void showCluster(){
        System.out.print("Center ");
        Center3.printPoint3D();
        //System.out.println("Data Amount: "+this.Belonging.size());
    }
    
    
}

