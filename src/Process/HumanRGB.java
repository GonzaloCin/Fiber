/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Process;

/**
 *
 * @author Gonzalo
 */
public class HumanRGB {
    public static int[][] Red = new int[5][3];
    public static int[][] Green=new int[5][3];
    public static int[][] Blue =new int[5][3];
    public static float[][] VRed = new float[5][3];
    public static float[][] VGreen=new float[5][3];
    public static float[][] VBlue =new float[5][3];
    public int res;
    public static boolean [] asigned={false,false,false};
    
    public static boolean isAsigned(int tipo){
        return asigned[tipo];
    }
    
    public static void setRed(int tipo, float[] valores){
        Red[0][tipo]=(int)valores[0];
        Red[1][tipo]=(int)valores[1];
        Red[2][tipo]=(int)valores[2];
        //Red[3][tipo]=(int)valores[3];
        if(valores.length==4){
             Red[3][tipo]=(int)valores[3];
        }else{
            Red[3][tipo]=(int)255;
        }
        System.out.println("\nHumanRGB Rojo: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(Red[i][tipo]+"\t");
        }
        asigned[tipo]=true;
       
    }
    
    public static void setGreen(int tipo, float[] valores){
        Green[0][tipo]=(int)valores[0];
        Green[1][tipo]=(int)valores[1];
        Green[2][tipo]=(int)valores[2];
        //Green[3][tipo]=(int)valores[3];
        if(valores.length==4){
             Green[3][tipo]=(int)valores[3];
        }else{
            Green[3][tipo]=(int)255;
        }
        System.out.println("\nHumanRGB Verde: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(Green[i][tipo]+"\t");
        }
        
        //Green[4][tipo]=(int)valores[4];
    }
    
    public static void setBlue(int tipo, float[] valores){
        Blue[0][tipo]=(int)valores[0];
        Blue[1][tipo]=(int)valores[1];
        Blue[2][tipo]=(int)valores[2];
        //Blue[3][tipo]=(int)valores[3];
        if(valores.length==4){
             Blue[3][tipo]=(int)valores[3];
        }else{
            Blue[3][tipo]=(int)255;
        }
        System.out.println("\nHumanRGB Azul: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(Blue[i][tipo]+"\t");
        }
        //Blue[4][tipo]=(int)valores[4];
    }
    
    public static void setVRed(int tipo, float[] valores){
        VRed[0][tipo]=(int)valores[0];
        VRed[1][tipo]=(int)valores[1];
        VRed[2][tipo]=(int)valores[2];
        //Red[3][tipo]=(int)valores[3];
        if(valores.length==4){
             VRed[3][tipo]=(int)valores[3];
        }else{
            VRed[3][tipo]=(int)0;
        }
        System.out.println("\nHumanRGB Var Rojo: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(VRed[i][tipo]+"\t");
        }
        asigned[tipo]=true;
       
    }
    
    public static void setVGreen(int tipo, float[] valores){
        VGreen[0][tipo]=(int)valores[0];
        VGreen[1][tipo]=(int)valores[1];
        VGreen[2][tipo]=(int)valores[2];
        //Green[3][tipo]=(int)valores[3];
        if(valores.length==4){
             VGreen[3][tipo]=(int)valores[3];
        }else{
            VGreen[3][tipo]=(int)0;
        }
        System.out.println("\nHumanRGB Var Verde: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(VGreen[i][tipo]+"\t");
        }
        
        //Green[4][tipo]=(int)valores[4];
    }
    
    public static void setVBlue(int tipo, float[] valores){
        VBlue[0][tipo]=(int)valores[0];
        VBlue[1][tipo]=(int)valores[1];
        VBlue[2][tipo]=(int)valores[2];
        //Blue[3][tipo]=(int)valores[3];
        if(valores.length==4){
             VBlue[3][tipo]=(int)valores[3];
        }else{
            VBlue[3][tipo]=(int)0;
        }
        
        System.out.println("\nHumanRGB Var Azul: ");
        for(int i=0;i<valores.length;i++){
            System.out.print(VBlue[i][tipo]+"\t");
        }
        //Blue[4][tipo]=(int)valores[4];
    }
    
    public static void setRed(int cluster,int tipo,int valor){
        Red[cluster][tipo]=valor;
        asigned[tipo]=true;
    }
    public static void setGreen(int cluster,int tipo,int valor){
        Green[cluster][tipo]=valor;
    }
    public static void setBlue(int cluster,int tipo,int valor){
        Blue[cluster][tipo]=valor;
    }
    
    public static int getRed(int cluster,int tipo){
        return Red[cluster][tipo];
    }
    public static int getGreen(int cluster,int tipo){
        return Green[cluster][tipo];
    }
    public static int getBlue(int cluster,int tipo){
        return Blue[cluster][tipo];
    }
    
    public static float getDRed(int cluster,int tipo){
        return (float)Math.sqrt(VRed[cluster][tipo]);
    }
    public static float getDGreen(int cluster,int tipo){
        return (float)Math.sqrt(VGreen[cluster][tipo]);
    }
    public static float getDBlue(int cluster,int tipo){
        return (float)Math.sqrt(VBlue[cluster][tipo]);
    }
    
    public static float getVRed(int cluster,int tipo){
        return VRed[cluster][tipo];
    }
    public static float getVGreen(int cluster,int tipo){
        return VGreen[cluster][tipo];
    }
    public static float getVBlue(int cluster,int tipo){
        return VBlue[cluster][tipo];
    }
    
    
    
    public static int getRed(int cluster,String tipo){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        return Red[cluster][aux];
    }
    public static int getGreen(int cluster,String tipo){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        return Green[cluster][aux];
    }
    public static int getBlue(int cluster,String tipo){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        return Blue[cluster][aux];
    }
    
    
    public static void setRed(int cluster,String tipo,int valor){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        Red[cluster][aux]=valor;
    }
    
    public static void setGreen(int cluster,String tipo,int valor){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        Green[cluster][aux]=valor;
    }
    
    public static void setBlue(int cluster,String tipo,int valor){
        int aux=0;
        switch (tipo){
            case "NADH":
                aux=0;
            break;
            case "ATP":
            case "ATPasa":
                aux=1;
            break;
            case "COX":
                aux=2;
            break;
        }
        Blue[cluster][aux]=valor;
    }
    
    private int strtonum(String a){
        switch (a){
            case "NADH":
                res=0;
            break;
            case "ATP":
            case "ATPasa":
                res=1;
            break;
            case "COX":
                res=2;
            break;
            default :
                System.out.println("Error en tipo de imagen(String)");
            break;
        }
        return res;
    }
    
}
