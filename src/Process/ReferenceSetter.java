package Process;
import GUI.Algorithms;
import GUI.Techniques;
import Grouping.Point3D;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Omar López Ortega
 */
public class ReferenceSetter
{    
    public static double[] metricas = new double[8];
    private static int rgb;
    public static ArrayList<Point3D> centrosFCM=new ArrayList<Point3D>();
    static CoordinateMapper t = new CoordinateMapper();
    public static Color [] centros = new Color[4];
    public static double [][] desviaciones = new double[4][3];
    public static void defineMetricasGrados()
    {      
       
        //metricas[0] = t.obtenerMetricaGrados(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue());
        //metricas[0] = t.obtenerMetricaGrados(1, 1, 1);
        metricas[0] = t.obtenerMetricaGrados(33, 39, 72);
        //metricas[1] = t.obtenerMetricaGrados(15,25,45);//dg = 169
        metricas[1] = t.obtenerMetricaGrados(84,88,121);
        //metricas[2] = t.obtenerMetricaGrados(65,105,225);
        metricas[2] = t.obtenerMetricaGrados(127,130,159);
        //metricas[3] = t.obtenerMetricaGrados(198,226,255);
        metricas[3] = t.obtenerMetricaGrados(186,190,201);
        //metricas[4] = t.obtenerMetricaGrados(Color.white.getRed(), Color.white.getGreen(), Color.white.getBlue());
        metricas[4] = t.obtenerMetricaGrados(Color.white.getRed(), Color.white.getGreen(), Color.white.getBlue());
        //metricas[5] = t.obtenerMetricaGrados(176,226,255);
        //metricas[6] = t.obtenerMetricaGrados(70,130,180);
        //metricas[7] = t.obtenerMetricaGrados(176,226,255);     
    }
    

    public static void defineMetricasEuclideana(int tipo){
        if(HumanRGB.isAsigned(tipo)){
            System.out.println("Conocimiento de Humano:\t Tipo: "+tipo);
           
            for(int i=0;i<4;i++){
                centros[i]= new Color(HumanRGB.getRed(i,tipo),HumanRGB.getGreen(i,tipo),HumanRGB.getBlue(i,tipo));
                
                desviaciones[i][0]= HumanRGB.getDRed(i,tipo);
                desviaciones[i][1]=HumanRGB.getDGreen(i,tipo);
                desviaciones[i][2]=HumanRGB.getDBlue(i,tipo);
                
                System.out.println("\t"+desviaciones[i][0]+","+desviaciones[i][1]+","+desviaciones[i][2]);
                
            }
        }else if(LearnedRGB.isAsigned(tipo)) {
            System.out.println("Conocimiento de Maquina:");
            for(int i=0;i<4;i++){
                centros[i]= new Color(LearnedRGB.getRed(i,tipo),LearnedRGB.getGreen(i,tipo),LearnedRGB.getBlue(i,tipo));
            }
        }
        else{
            System.out.println("NO HAY CONOCIMIENTO");
        }
        
    }
    
    public static void defineMetricasEuclideana(Techniques tincion,Algorithms algoritmo ){
        if(GlobalKnowledge.isAssigned(tincion,algoritmo)){
            for(byte i=0;i<4;i++){
                centros[i]= new Color(GlobalKnowledge.getRed(tincion,algoritmo,i),GlobalKnowledge.getGreen(tincion,algoritmo,i),GlobalKnowledge.getBlue(tincion,algoritmo,i));
                
                desviaciones[i][0]= GlobalKnowledge.getRedD(tincion,algoritmo,i);
                desviaciones[i][1]=GlobalKnowledge.getGreenD(tincion,algoritmo,i);
                desviaciones[i][2]=GlobalKnowledge.getBlueD(tincion,algoritmo,i);
                
                //System.out.println("\t"+desviaciones[i][0]+","+desviaciones[i][1]+","+desviaciones[i][2]);   
                System.out.println(centros[i].toString());
            }
        }
        else{
            System.out.println("NO HAY CONOCIMIENTO defineMetricaEuclideana");
        }
        
    }
    
    public static void defineMetricasFCM(Techniques tincion, Algorithms algoritmo){
        if(GlobalKnowledge.isAssigned(tincion,algoritmo)){
            int num=0;
            switch(tincion){
                case ATPASA:
                    num=4;
                break;
                case NADH:
                case COX:
                    num=3;
                break;
            }
            centrosFCM.clear();
            for(int i=0;i<num;i++){
                centrosFCM.add(new Point3D(GlobalKnowledge.getRed(tincion,algoritmo,i),GlobalKnowledge.getGreen(tincion,algoritmo,i),GlobalKnowledge.getBlue(tincion,algoritmo,i)));
                centros[i]= new Color((int)GlobalKnowledge.getRed(tincion,algoritmo,i),(int)GlobalKnowledge.getGreen(tincion,algoritmo,i),(int)GlobalKnowledge.getBlue(tincion,algoritmo,i));
                
                /*desviaciones[i][0]= GlobalKnowledge.getRedD(tincion,algoritmo,i);
                desviaciones[i][1]=GlobalKnowledge.getGreenD(tincion,algoritmo,i);
                desviaciones[i][2]=GlobalKnowledge.getBlueD(tincion,algoritmo,i);
                */
                //System.out.println("\t"+desviaciones[i][0]+","+desviaciones[i][1]+","+desviaciones[i][2]);   
                System.out.println(centros[i].toString());
            }
            
            
        }
        else{
            System.out.println("NO HAY CONOCIMIENTO defineMetricasFCM");
        }
    }
    
    
    public static void defineMetricasRadianes(int tipo)
    {
        //metricas[0] = t.obtenerMetricaRadianes(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue());
        metricas[0] = t.obtenerMetricaRadianes(LearnedRGB.getRed(0,tipo),LearnedRGB.getGreen(0,tipo),LearnedRGB.getBlue(0,tipo));
        metricas[1] = t.obtenerMetricaRadianes(LearnedRGB.getRed(1,tipo),LearnedRGB.getGreen(1,tipo),LearnedRGB.getBlue(1,tipo));//dg = 169        
        metricas[2] = t.obtenerMetricaRadianes(LearnedRGB.getRed(2,tipo),LearnedRGB.getGreen(2,tipo),LearnedRGB.getBlue(2,tipo));
        metricas[3] = t.obtenerMetricaRadianes(LearnedRGB.getRed(3,tipo),LearnedRGB.getGreen(3,tipo),LearnedRGB.getBlue(3,tipo));//lg = 211 silver = 192
        //metricas[4] = t.obtenerMetricaRadianes(LearnedRGB.getRed(4,tipo),LearnedRGB.getGreen(4,tipo),LearnedRGB.getBlue(4,tipo));
        //metricas[5] = t.obtenerMetricaRadianes(176,226,255);
        //metricas[6] = t.obtenerMetricaRadianes(70,130,180);
        //metricas[7] = t.obtenerMetricaRadianes(176,226,255);
    }
    /*
     Para las imágenes tipo NADH se emplea escala de Azules
     * De acuerdo con los siguientes parámetros
     * Pixeles Clase 0: Negro (1,1,1)
     * Pixeles Clase 1: MidnightBlue(25,25,112)
     * Pixeles Clase 2: Royal Blue (65,105,225)
     * Pixeles Clase 3: Light Sky Blue (176, 226, 255)
     * Pixeles Clase 4: Blanco (255, 255, 255)
     * Pixeles Clase 5: Gris (128, 128, 128)
     * Pixeles Clase 6: Steel Blue (70, 130, 180)
     * Pixeles Clase 7: Slate Gray 1 (198, 226, 155))
     */
    
   public static int obtenRed(int j){
              
       return centros[j].getRed();
   }
   
   public static int obtenGreen(int j){
       //rgb=LearnedRGB.getGreen(j, tipo);
       
       return centros[j].getGreen();
   }
   
      public static int obtenBlue(int j){
       //rgb=LearnedRGB.getBlue(j, tipo);
       
       return centros[j].getBlue();
   }
      
      public static int obtenR(int j){
              
       return centros[j].getRed();
   }
   
   public static int obtenG(int j){
       //rgb=LearnedRGB.getGreen(j, tipo);
       
       return centros[j].getGreen();
   }
   
      public static int obtenB(int j){
       //rgb=LearnedRGB.getBlue(j, tipo);
       
       return centros[j].getBlue();
   }
   
}
