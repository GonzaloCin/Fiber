/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import Threads.BlackBoard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Gonzalo
 */
public class KohonenMap extends Clusterer{
    ClusterKM[] clusters;
    ArrayList<Point3> Data3;
    byte radioV;
    float alfa;
    short winner;
    ArrayList<Integer> shuffler;
    public KohonenMap(byte num,float ap,byte veci,float err,ArrayList<Point3> Dat){
        ClusterAmount=num;
        alfa=ap;
        radioV=veci;
        clusters=new ClusterKM[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterKM();
        }
        Data3=Dat;
        Datasize=Data3.size();
        error=err;
        shuffler=new ArrayList<Integer>();
        for(int i=0;i<Datasize;i++){
            shuffler.add(i);
        }
    }
    
    public KohonenMap(byte num,float ap,byte veci,float err){
        ClusterAmount=num;
        alfa=ap;
        radioV=veci;
        clusters=new ClusterKM[ClusterAmount];
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i]=new ClusterKM();
        }
        Datasize=BlackBoard.getDataSize();
        error=err;
        shuffler=new ArrayList<Integer>();
        for(int i=0;i<Datasize;i++){
            shuffler.add(i);
        }
    }
    
    @Override
    public void clasify(){
        train();
        int tot=Data3.size();
        float dist;
        float min;
        int ind=0;
        for(int i=0;i<tot;i++){
            min=255;
            ind=0;
            for(int k=0;k<ClusterAmount;k++){
                dist=Calculus.distance(Data3.get(i),clusters[k]);
                if(dist<min){
                        min=dist;
                        ind=k;
                    }
            }
            clusters[ind].insert(Data3.get(i));
        }
        showState();
    }
    
    private void train(){
        randomFill();
        float errormax=2*this.error;
        int ep=0;
        while(errormax>error){
            System.out.println("Epoch"+ep+"\t Size "+Data3.size());
            showState();
            Collections.shuffle(shuffler);
            for(int j=0;j<this.ClusterAmount;j++){
               (clusters[j]).updateCenter();
            }
            for(int i=0;i<Datasize;i++){
                update(i,Data3.get(shuffler.get(i)),winner(Data3.get(shuffler.get(i))));
            }
            
            
            errormax=clusters[0].diff();
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=1;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                if(errormax<clusters[j].diff()){
                    errormax=clusters[j].diff();
                }
            }
            System.out.println("Error maximo="+errormax);
            ep++;
        }
    }
    
    private void trainTH(){
        randomFill();
        float errormax=2*this.error;
        int ep=0;
        while(errormax>error){
            System.out.println("Epoch"+ep+"\t Size "+Data3.size());
            showState();
            Collections.shuffle(shuffler);
            for(int j=0;j<this.ClusterAmount;j++){
               (clusters[j]).updateCenter();
            }
            for(int i=0;i<Datasize;i++){
                update(i,BlackBoard.getP3(shuffler.get(i)),winner(BlackBoard.getP3(shuffler.get(i))));
            }
            
            
            errormax=clusters[0].diff();
            //System.out.println("Err 0: "+clusters[0].dif());
            for(int j=1;j<this.ClusterAmount;j++){
              //  System.out.println("Err "+j+": "+clusters[j].dif());
                if(errormax<clusters[j].diff()){
                    errormax=clusters[j].diff();
                }
            }
            System.out.println("Error maximo="+errormax);
            ep++;
        }
    }
    
    
    @Override
    void showState(){
        for(int i=0;i<ClusterAmount;i++){
            System.out.print("\n");
                   clusters[i].showCluster();
            }
    }
    
    @Override
    void randomFill(){
        Random rnd=new Random();
        for(byte i=0;i<ClusterAmount;i++){
            clusters[i].setCenter(new Point3D(255*rnd.nextFloat(),255*rnd.nextFloat(),255*rnd.nextFloat()));
        }
    }
    
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
    
    private void update(int t,Point3 nvo,int gan){
        this.clusters[gan].setCenter(Calculus.sum((clusters[gan]).getCenter(),Calculus.scalarProduct(Calculus.sum(nvo,Calculus.scalarProduct((clusters[gan]).getCenter(),-1)), alfa*functionT(t))));
        byte ne=Neighbor(t);
        for(int i=0;i<ClusterAmount;i++){
            if(Math.abs(gan-i)< ne && Math.abs(gan-i)>0){
                this.clusters[i].setCenter(Calculus.sum((clusters[i]).getCenter(),Calculus.scalarProduct(Calculus.sum(nvo,Calculus.scalarProduct((clusters[i]).getCenter(),-1)), alfa*functionT(t)*functionV(t))));
            }
        }
    }
    
    private byte winner(Point3 a){
        byte aux=0;
        float min=250;
        float dist;
        for(int i=0;i<ClusterAmount;i++){
            dist=Calculus.distance(a,clusters[i]);
            if(dist<min){
                min=dist;
                aux=(byte)i;
            }
        }
        a.printPoint3();
        System.out.print("\tWinner"+aux);
        (clusters[aux]).showCluster();
        return aux;
    }

    
    private float functionT(int t){
        return (float)Math.exp(-Math.pow((float)(3*t/Datasize),2)/2);
    }
    
    private float functionV(int t){
        return (float)Math.exp(-Math.pow((float)(3*t/Datasize),2)/2);
    }
    
    private byte Neighbor(int n){
        return (byte)Math.round((float)radioV*Math.exp(-Math.pow((float)(3*n/Datasize),2)/2));
    }
    
    @Override
    void sortClusters(){
        int tam=ClusterAmount;
        ClusterKM auxi;
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
    
    @Override
    public void clasifyTH(){
        trainTH();
        int tot=BlackBoard.getDataSize();
        float dist;
        float min;
        int ind=0;
        for(int i=0;i<tot;i++){
            min=255;
            ind=0;
            for(int k=0;k<ClusterAmount;k++){
                dist=Calculus.distance(BlackBoard.getP3(i),clusters[k]);
                if(dist<min){
                        min=dist;
                        ind=k;
                    }
            }
            clusters[ind].insert(BlackBoard.getP3(i));
        }
        showState();
    }
    
}
