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
 * Clase de clasificador Kmeans en tres componentes
 * @author Gonzalo
 */
public class Kmeans3 extends Clusterer{
   ClusterK3[] clusters;
   ArrayList<Point3> Data3;
    
    /**
     * Constructor
     * @param num cantidad de clusters
     * @param err error maximo permitido, se usa para detener el algoritmo
     * @param Dat lista de datos en tres dimensiones a analizar
     */
    public Kmeans3(byte num,float err,ArrayList<Point3> Dat){
        ClusterAmount=num;
        clusters=new ClusterK3[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterK3();
        }
        Data3=Dat;
        Datasize=Data3.size();

        error=err;
        
    }
   
    /**
     * Constructor
     * @param num cantidad de clusters
     * @param err error maximo permitido, se usa para detener el algoritmo
     */
    public Kmeans3(byte num,float err){
        ClusterAmount=num;
        clusters=new ClusterK3[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterK3();
        }
        Datasize=BlackBoard.getDataSize();
        error=err;
        
    }
    
    /**
     * Clasifica los datos siguiendo el algoritmo Kmeans
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
                clusters[j].empty();
            }
            for(int r=0;r<Datasize;r++){
                int cen=0;
                distmin=Calculus.distance(Data3.get(r),clusters[0]);
                for(int s=1;s<this.ClusterAmount;s++){
                    if(distmin>Calculus.distance(Data3.get(r),clusters[s])){
                        distmin=Calculus.distance(Data3.get(r),clusters[s]);
                        cen=s;
                    }
                }
                (clusters[cen]).insert(Data3.get(r));
            }
                    
            for(int j=0;j<this.ClusterAmount;j++){
                clusters[j].calculeCenter();
            }
            
            errormax=clusters[0].diff();
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=1;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                if(errormax<clusters[j].diff()){
                    errormax=clusters[j].diff();
                }
            }
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
                clusters[j].empty();
            }
            for(int r=0;r<Datasize;r++){
                int cen=0;
                distmin=Calculus.distance(BlackBoard.getP3(r),clusters[0]);
                for(int s=1;s<this.ClusterAmount;s++){
                    auxdist=Calculus.distance(BlackBoard.getP3(r),clusters[s]);
                    if(distmin>auxdist){
                        distmin=auxdist;
                        cen=s;
                    }
                }
                (clusters[cen]).insert(BlackBoard.getP3(r));
            }
                    
            for(int j=0;j<this.ClusterAmount;j++){
                clusters[j].showCluster();
                clusters[j].calculeCenter();
                
            }
            
            errormax=clusters[0].diff();
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=1;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                if(errormax<clusters[j].diff()){
                    errormax=clusters[j].diff();
                }
            }
             //System.out.print("Max "+errormax);
             //System.out.print("\tPer "+errorpermitido);
        }while(errormax>error);
        
    }
    
    @Override
    void randomFill(){//debe ser privada
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
            res[0][i]=((clusters[i]).getCenter()).getX();
            res[1][i]=((clusters[i]).getCenter()).getY();
            res[2][i]=((clusters[i]).getCenter()).getZ();
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
        ClusterK3 auxi;
        for(int i=0;i<tam;i++){
            for(int j=0;j<tam-1;j++){
		if(Calculus.norm((clusters[j]).getCenter())>Calculus.norm((clusters[j+1]).getCenter())){
                    auxi=clusters[j];
                    clusters[j]=clusters[j+1];
                    clusters[j+1]=auxi;
                }
            }
        }
    }

}
