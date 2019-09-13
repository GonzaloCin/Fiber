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
public abstract class Clusterer {
    
    byte ClusterAmount;
    float error;
    int Datasize;
    
    public abstract void clasify();
    public abstract void clasifyTH();
    abstract void randomFill();  
    abstract void showState();
    abstract void sortClusters();
    
    public int getCAmount(){
        return ClusterAmount;
    }
}
