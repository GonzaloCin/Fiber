package Threads;
import Grouping.Kmeans;

/**
 *
 * @author Gonzalo
 */
public class ThreadK extends Thread {
    Kmeans kmeansr;
    Kmeans kmeansg;
    Kmeans kmeansb;
    double TI,TF;
    public void setDataR(byte num,float err,int[] Dat){
        this.kmeansr=new Kmeans(num,err,Dat);
    }
    public void setDataG(byte num,float err,int[] Dat){
        this.kmeansg=new Kmeans(num,err,Dat);
    }
    public void setDataB(byte num,float err,int[] Dat){
        this.kmeansb=new Kmeans(num,err,Dat);
    }
    
    
    @Override
    public void run(){
        //TI=System.currentTimeMillis();
        System.out.println("Thread Kmeans iniciado satisfacotiamente");
        kmeansr.clasify();
        kmeansg.clasify();
        kmeansb.clasify();
        float[][] res= new float[3][kmeansr.getCAmount()]  ;
        //Como guardo los resultados antes de que muera el thread?????
        res[0]=kmeansr.getResult();
        res[1]=kmeansg.getResult();
        res[2]=kmeansb.getResult();
        
        BlackBoard.saveK(res);
         System.out.println("Thread Kmeans Terminado");
        //TF=System.currentTimeMillis();
          //  System.out.printf("Thread Kmeans Terminado Tiempo de Ejecución= %d ms\n", TF-TI);
           
    }
}
