 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import Threads.BlackBoard;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase para el clasificador con el algoritmo Fuzzy C-means
 * @author Gonzalo
 */
public class FCmeans extends Clusterer{
    ClusterFCM[] clusters;
    ArrayList<Point3> Data3;
    float m;

    /**
     * Constructor
     * @param num cantidad de clusters
     * @param err error maximo permitido, sirve para detener el algoritmo
     * @param m parametro para el algoritmo
     * @param Dat lista de datos en tres componentes
     */
    public FCmeans(byte num,float err,float m,ArrayList<Point3> Dat){
        ClusterAmount=num;
        clusters=new ClusterFCM[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterFCM();
        }
        Data3=Dat;
        Datasize=Data3.size();

        error=err;
        this.m=m;
        
    }
   
    /**
     * Constructor
     * @param num cantidad de clusters
     * @param err error maximo permitido, sirve para detener el algoritmo
     * @param m parametro para el algoritmo
     */
    public FCmeans(byte num,float err,float m){
        ClusterAmount=num;
        clusters=new ClusterFCM[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterFCM();
        }
        Datasize=BlackBoard.getDataSize();
        error=err;
        this.m=m;
        
    }
    
    /**
     * Clasificación por el algoritmo Fuzzy C-means
     */
    @Override
    public void clasify(){
        randomFill();
        
        double distmin;
        int iter=1;
        float errormax;
        do{
            //showState();
            //System.out.println("\nIteracion :"+iter);
            iter++;
            for(int j=0;j<this.ClusterAmount;j++){
                double auxr=0,auxg=0,auxb=0;
                double sumafunciones=0;
                 for(int r=0;r<Datasize;r++){
                  sumafunciones+=Math.pow(Membership(Data3.get(r),j),this.m);
                }
                 for(int r=0;r<Datasize;r++){
                  auxr+=Math.pow(Membership(Data3.get(r),j),this.m)*(Data3.get(r)).getX();
                  auxg+=Math.pow(Membership(Data3.get(r),j),this.m)*(Data3.get(r)).getY();
                  auxb+=Math.pow(Membership(Data3.get(r),j),this.m)*(Data3.get(r)).getZ();
                 }
                 auxr/=sumafunciones;
                 auxg/=sumafunciones;
                 auxb/=sumafunciones;
                 
                clusters[j].setCenter(new Point3D((float)auxr,(float)auxg,(float)auxb));
                
            }
            
            errormax=0;
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=0;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                    errormax+=clusters[j].diff();
            }
            System.out.print("\tError "+errormax+"\n");
             //System.out.print("Max "+errormax);
             //System.out.print("\tPer "+errorpermitido);
        }while(errormax>error);
        
        
        
    }
    
    /**
     * Clasificación para usar en multihilo
     */
    @Override
    public void clasifyTH(){
        randomFill();
        float auxdist;
        float distmin;
        int iter=1;
        float errormax;
        
        
        do{
            //showState();
            //System.out.println("\nIteracion :"+iter);
            iter++;
            for(int j=0;j<this.ClusterAmount;j++){
                double auxr=0,auxg=0,auxb=0;
                double sumafunciones=0;
                 for(int r=0;r<Datasize;r++){
                  sumafunciones+=Math.pow(Membership(BlackBoard.getP3(r),j),this.m);
                }
                 for(int r=0;r<Datasize;r++){
                  auxr+=Math.pow(Membership(BlackBoard.getP3(r),j),this.m)*(BlackBoard.getP3(r)).getX();
                  auxg+=Math.pow(Membership(BlackBoard.getP3(r),j),this.m)*(BlackBoard.getP3(r)).getY();
                  auxb+=Math.pow(Membership(BlackBoard.getP3(r),j),this.m)*(BlackBoard.getP3(r)).getZ();
                 }
                 auxr/=sumafunciones;
                 auxg/=sumafunciones;
                 auxb/=sumafunciones;
                 
                clusters[j].setCenter(new Point3D((float)auxr,(float)auxg,(float)auxb));
                clusters[j].showCluster();
                
            }
            
            errormax=0;
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=0;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                    errormax+=clusters[j].diff();
            }
            System.out.print("\tError "+errormax+"\n");
             //System.out.print("Max "+errormax);
             //System.out.print("\tPer "+errorpermitido);
        }while(errormax>error);
        
    }
    
    @Override
    void randomFill(){
        Random rnd=new Random();
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i].setCenter(new Point3D(255*rnd.nextFloat(),255*rnd.nextFloat(),255*rnd.nextFloat()));
        }
    }
    
    /**
     * Devuelve los resultados de la clasificación 
     * @return arreglo con los resultados ordenados
     */
    public float[][] getResult(){
        sortClusters();
        float[][] res=new float [3][ClusterAmount];
        for(int i=0;i<ClusterAmount;i++){
            res[0][i]=(clusters[i].getCenter()).getX();
            res[1][i]=(clusters[i].getCenter()).getY();
            res[2][i]=(clusters[i].getCenter()).getZ();
        }
        
        
        return res;
    }
    
    
    @Override
    void showState(){
        for(int i=0;i<ClusterAmount;i++){
            System.out.print("\n");
                   clusters[i].showCluster();
            }
    }
    
    @Override
    void sortClusters(){
        int tam=ClusterAmount;
        ClusterFCM auxi;
        for(int i=0;i<tam;i++){
            for(int j=0;j<tam-1;j++){
		if(Calculus.norm(clusters[j].getCenter())>Calculus.norm(clusters[j+1].getCenter())){
                    auxi=clusters[j];
                    clusters[j]=clusters[j+1];
                    clusters[j+1]=auxi;
                }
            }
        }
    }
    
    private float Membership(Point3 p,int i){
        float res=0;
        for(int j=0;j<this.ClusterAmount;j++){
            res+=Math.pow(Math.pow(Calculus.distance(p,clusters[i].getCenter())/Calculus.distance(p,clusters[j].getCenter()),2),1/(this.m-1));
        }
        return (1/res);
    }

}
