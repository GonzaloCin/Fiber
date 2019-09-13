/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gonzalo
 */
public class Kmeans extends Clusterer{
    ClusterK[] clusters;
    ArrayList<Group> Data;
    
    public Kmeans(byte num,float err,ArrayList<Group> Dat){
        ClusterAmount=num;
        clusters=new ClusterK[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterK();
        }
        Data=Dat;
        error=err;
    }
    
    
    
    public Kmeans(byte num,float err,int[] Dat){
        ClusterAmount=num;
        clusters=new ClusterK[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterK();
        }
        Data=Calculus.toGroupArray(Dat);
        error=err;
    }
    
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
            for(int r=0;r<Data.size();r++){
                int cen=0;
                distmin=Calculus.distance(Data.get(r),clusters[0]);
                for(int s=1;s<this.ClusterAmount;s++){
                    if(distmin>Calculus.distance(Data.get(r),clusters[s])){
                        distmin=Calculus.distance(Data.get(r),clusters[s]);
                        cen=s;
                    }
                }
                clusters[cen].insert(Data.get(r));
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
    
    @Override
    void randomFill(){
        Random rnd=new Random();
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i].setCenter(255*rnd.nextFloat());
        }
    }
    
    
    
    public float[] getResult(){
        sortClusters();
        float[] res=new float [ClusterAmount];
        for(int i=0;i<ClusterAmount;i++){
            res[i]=(clusters[i]).getCenter();
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
        ClusterK auxi;
        for(int i=0;i<tam;i++){
            for(int j=0;j<tam-1;j++){
		if((clusters[j]).getCenter()>(clusters[j+1]).getCenter()){
                    auxi=clusters[j];
                    clusters[j]=clusters[j+1];
                    clusters[j+1]=auxi;
                }
            }
        }
    }
    
    @Override
    public void clasifyTH(){
        
    }
}
