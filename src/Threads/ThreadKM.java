/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;
import Grouping.KohonenMap;


/**
 *
 * @author Gonzalo
 */
public class ThreadKM extends Thread {
    KohonenMap SOM;
    double TI,TF;
    public void setData(byte num,float ap,byte veci,float err){
        this.SOM=new KohonenMap(num,ap,veci,err);
    }
    
    @Override
    public void run(){
        TI=System.currentTimeMillis();
        System.out.println("Thread Kohonen Map iniciado satisfacotiamente");
        
        SOM.clasifyTH();
        BlackBoard.saveKM(SOM.getResult());
        TF=System.currentTimeMillis();
            System.out.printf("Thread Kohonen Map Terminado Tiempo de Ejecución= %d ms\n", (float)(TF-TI));
        
    }
}
