/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Threads;

import Grouping.FCmeans;

/**
 *
 * @author Gonzalo
 */
public class ThreadFCM extends Thread{
    FCmeans fcmeans;
    
    
    public void setData(byte num,float err, float m){
        this.fcmeans=new FCmeans(num,err,m);
    }
    
    @Override
    public void run(){
        //double TI,TF;
        //TI=System.currentTimeMillis();
        System.out.println("Thread FCmeans iniciado satisfacotiamente");
        
        fcmeans.clasifyTH();
        BlackBoard.saveFCM(fcmeans.getResult());
        System.out.println("Thread FCmeans Terminado");
        //TF=System.currentTimeMillis();
          //  System.out.printf("Thread Kmeans3D Terminado Tiempo de Ejecución= %d ms\n", TF-TI);
        
    }


    
}
