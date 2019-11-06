/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import GUI.Languages;
import Grouping.Group;
import Grouping.Point3;
import Process.GlobalKnowledge;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Gonzalo
 */
public class BlackBoard {
    public static JLabel label;
    public static byte imagetype;
    public static File currentPath=null;
    public static String fileName = null;
    
    public static float[][] dataFCM;
    
    public static float[][] dataK;
    public static float[][] dataK3;
    public static float[][] dataKM;
    public static float[][] dataH;
    public static float[][] dataHV;
    public static float[][] dataAggregated;
    
    static boolean asignedFCM=false;
    
    static boolean asignedK=false;
    static boolean asignedK3=false;
    static boolean asignedKM=false;
    static boolean asignedH=false;
    public static ArrayList<Point3> SharedData;
    //static ArrayList<Group> GroupsTH; //The only object that uses a Groups' list is the Kmeans
    
    public static void setFileName(String fi){
        fileName=fi;
    }
    
    public static String getFileName(){
        return fileName;
    }
    
    public static void setImagePath(File fi){
        currentPath=fi;
    }
    
    public static File getImagePath(){
        return currentPath;
    }
    
    public static void setSharedData(ArrayList<Point3> dat){
        BlackBoard.SharedData=dat;
    }
    
    public static synchronized Point3 getP3(int i){
        return SharedData.get(i);
    }
    
    public static void reset(){
        asignedK=false;
        asignedK3=false;
        asignedKM=false;
        asignedFCM=false;
    }
    
    public static void guardalab(JLabel la){
        label=la;
    }
    
    public static int getDataSize(){
        return SharedData.size();
    }
    
    public static void saveFCM(float[][] res){
        dataFCM=res;
        asignedFCM=true;
        aggregate();
    }
    
    public static void saveK(float[][] res){
        dataK=res;
        asignedK=true;
        aggregate();
    }
    public static void saveK3(float[][] res){
        dataK3=res;
        asignedK3=true;
        aggregate();
    }
    public static void saveKM(float[][] res){
        dataKM=res;
        asignedKM=true;
        aggregate();
    }
    public static void saveH(float[][] res,byte type){
        dataH=res;
        asignedH=true;
        imagetype=type;
        aggregate();
    }
    
    public static void saveV(float[][] res){
        dataHV=res;
    }
    
    
    private static void aggregate(){
        if(asignedFCM && asignedK3){
            if(Languages.language == Languages.Language.Spanish){
                label.setText("Finalizado");
            }else if(Languages.language == Languages.Language.English){
                label.setText("Finished");
            }
        }
        
        if(asignedFCM && asignedK3 && asignedH /*&& asignedKM*/){
            dataAggregated=new float [3][dataFCM[0].length];
            for(byte j=0;j<3;j++){
                for(byte k=0;k<dataFCM[0].length;k++){
                  dataAggregated[j][k]=0.5f*dataH[j][k]+0.3f*dataFCM[j][k]+0.2f*dataK3[j][k];//+0.2f*dataKM[j][k];
                }     
            }
            GlobalKnowledge.SaveH(dataH, imagetype);
            GlobalKnowledge.SaveFCM(dataFCM, imagetype);
            GlobalKnowledge.SaveK3(dataK3, imagetype);
            //GlobalKnowledge.SaveKM(dataKM, imagetype); ///falta convergencia
            GlobalKnowledge.SaveAg(dataAggregated, imagetype); 
            GlobalKnowledge.SaveVar(dataHV,imagetype);
            if(Languages.language == Languages.Language.Spanish){
                label.setText("Agregue Conocimiento");
            }else if(Languages.language == Languages.Language.English){
                label.setText("Now Add Knowledge");
            }
            SharedData=null;
            System.gc();
            if(Languages.language == Languages.Language.Spanish){
                JOptionPane.showMessageDialog(null, "Listo, Fuzzy c-means, Kmeans 3d , Experto y Agregacion Agregado\n Ahora Agrega Conocimiento en la Ventana Principal");
            }else if(Languages.language == Languages.Language.English){
                JOptionPane.showMessageDialog(null, "OK, Added Fuzzy c-means, Kmeans 3d , Expert and Aggregation \n Now add knowledge at the main window");
            }
        }
    }
}