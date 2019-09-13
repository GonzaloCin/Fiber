/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;
import Grouping.Kmeans3;
//import Grouping.Point3;
//import java.util.ArrayList;


/**
 *
 * @author Gonzalo
 */
public class ThreadK3 extends Thread {
    Kmeans3 kmeans3;
    
    
    public void setData(byte num,float err){
        this.kmeans3=new Kmeans3(num,err);
    }
    
    @Override
    public void run(){
        //double TI,TF;
        //TI=System.currentTimeMillis();
        System.out.println("Thread Kmeans3D iniciado satisfacotiamente");
        
        kmeans3.clasifyTH();
        BlackBoard.saveK3(kmeans3.getResult());
        System.out.println("Thread Kmeans3D Terminado");
        //TF=System.currentTimeMillis();
          //  System.out.printf("Thread Kmeans3D Terminado Tiempo de Ejecución= %d ms\n", TF-TI);
        
    }
}
