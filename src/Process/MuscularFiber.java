package Process;

import Grouping.Calculus;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;

public class MuscularFiber
{
    int[][] mapa;
    double [][] submapa;
    double [][] clusters;
    int clusters_submapa;   
    BufferedImage imagen;
    int columnas,filas,contador,total_islas,size;
    int[] a;//almacena los tamaños
    int max_p,min_p,promedio_total,promedio,promedio2,promedio3;
    double promedio25;
    int N=0;
    int S=0;//Desplazamientos
    int E=0;
    int O=0;

    public MuscularFiber(BufferedImage imagen,String filtro) 
    {
        switch (filtro){
                case "Erosion":
                    this.imagen=Calculus.Erode(imagen);
                break;
                case "Dilatacion":
                    this.imagen=Calculus.Dilate(imagen);
                break;
                case "Cerradura":
                    this.imagen=Calculus.Closing(imagen);
                break;
                case "Apertura":
                    this.imagen=Calculus.Opening(imagen);
                break;
                case "E2D1":
                    this.imagen=Calculus.OpeningNM(imagen,2,1);
                break;
                case "E3D2":
                    this.imagen=Calculus.OpeningNM(imagen,3,2);
                break;
                
                    
        }
        this.imagen=imagen;
        //filas=imagen.getWidth();
        //columnas=imagen.getHeight();
        columnas=imagen.getWidth();
        filas=imagen.getHeight();
        a= new int[imagen.getWidth()*imagen.getHeight()];
        clusters_submapa=0;
        clusters = new double [30000][2];
    }
    
    public MuscularFiber(BufferedImage imagen, int promedio,int tincion)
    {   
        this.imagen=imagen;
        //this.imagen = this.dilatacion(this.clonarBufferedImage(imagen));
        //Calculus.SaveImage(imagen);
        //Calculus.SaveImage(this.dilatacion(this.clonarBufferedImage(imagen)));
        //Calculus.SaveImage(Calculus.Dilate(imagen));
        //Calculus.SaveImage(Calculus.Erode(imagen));
        //Calculus.SaveImage(this.imagen);
        //Calculus.SaveImage(Calculus.Closing(imagen));
        filas=imagen.getHeight();
        columnas=imagen.getWidth();        
        //        filas=imagen.getWidth();
        //        columnas=imagen.getHeight();        
        this.promedio_total= promedio;
        a= new int[imagen.getWidth()*imagen.getHeight()];
        clusters_submapa=0;
        clusters = new double [30000][2];
    }
    
    public MuscularFiber(BufferedImage imagen, int promedio,String filtro)
    {   
        switch (filtro){
                case "Erosion":
                    this.imagen=Calculus.Erode(imagen);
                break;
                case "Dilatacion":
                    this.imagen=Calculus.Dilate(imagen);
                break;
                case "Cerradura":
                    this.imagen=Calculus.Closing(imagen);
                break;
                case "Apertura":
                    this.imagen=Calculus.Opening(imagen);
                break;
                case "E2D1":
                    this.imagen=Calculus.OpeningNM(imagen,2,1);
                break;
                case "E3D2":
                    this.imagen=Calculus.OpeningNM(imagen,3,2);
                break;
                
                
                    
        }
        //this.imagen=imagen;
        //this.imagen = this.dilatacion(this.clonarBufferedImage(imagen));
        //Calculus.SaveImage(imagen);
        //Calculus.SaveImage(this.dilatacion(this.clonarBufferedImage(imagen)));
        //Calculus.SaveImage(Calculus.Dilate(imagen));
        //Calculus.SaveImage(Calculus.Erode(imagen));
        //Calculus.SaveImage(this.imagen);
        //Calculus.SaveImage(Calculus.Closing(imagen));
        filas=imagen.getHeight();
        columnas=imagen.getWidth();        
        //        filas=imagen.getWidth();
        //        columnas=imagen.getHeight();        
        this.promedio_total= promedio;
        a= new int[imagen.getWidth()*imagen.getHeight()];
        clusters_submapa=0;
        clusters = new double [30000][2];
    }  
    
    public MuscularFiber(BufferedImage imagen, int promedio)
    {   
       this.imagen=imagen;
       filas=imagen.getHeight();
        columnas=imagen.getWidth();        
        //        filas=imagen.getWidth();
        //        columnas=imagen.getHeight();        
        this.promedio_total= promedio;
        a= new int[imagen.getWidth()*imagen.getHeight()];
        clusters_submapa=0;
        clusters = new double [30000][2];
    }
    
    
    public int contarIslas()
    { 
      
      size = primer_conteo();
      cargarArreglo();
      int islas=0;
      int aux;
        for(int i = 0; i < imagen.getHeight(); i++ )
        {
            for(int j = 0; j < imagen.getWidth(); j++ )
            {
                if (mapa[i][j]==1) 
                {
//                    islas+=1;
//                    recorrido(i,j);
                 contador=1;
//               
                N=i;//j segun leo
                S=i;//j segun leo
                E=j;//i segun leo
                O=j;//i segun leo     
                try {
                        recorrido(i,j);
                    }
                    catch(StackOverflowError e){
                        System.out.println("Metodo conteo de Islas, no se pudo realizar el conteo");
                    }
                int n=Math.abs(N-S);//a 8 tambien
                int m=Math.abs(E-O);
                int x= j+(n/2);
                int y= i+(m/2);
                if (((min_p)< contador && contador <(max_p)))
                    { //|| contador >(max_p-promedio_total)
                       islas+=1; 
                       clusters[clusters_submapa][0]=x;
                       clusters[clusters_submapa][1]=y;                       
                       //clusters[clusters_submapa][0]=j;
                       //clusters[clusters_submapa][1]=i;
                       clusters_submapa+=1;
//                       System.out.println("contador: "+contador+" islas: "+islas);
                    }
                }
            }
        }  
        
        System.out.println("Numero de clusters: "+clusters_submapa);
//        System.out.println(clusters.length);
//        System.out.println("");
      return islas;
    }
   
    public void cargarArreglo()
    {        
        //mapa = new int[imagen.getWidth()][imagen.getHeight()];//segun leo        
        mapa = new int[imagen.getHeight()][imagen.getWidth()];
        for(int i = 0; i < imagen.getHeight(); i++ )
        {
            for(int j = 0; j < imagen.getWidth(); j++ )
            {
                //Almacenamos el color del píxel  
                Color colorAux=new Color(imagen.getRGB(j,i));
                if (colorAux.equals(Color.white)) {
                    mapa[i][j]=0;
                } else {
                    mapa[i][j]=1;
                }
                //System.out.print(mapa[j][i]+" ");
            }
            //System.out.println("");
        }
 
    }
    
    public int primer_conteo()
    {
        cargarArreglo();
        int islas=0;
        for(int i = 0; i < imagen.getHeight(); i++ )
        {
            for(int j = 0; j < imagen.getWidth(); j++ )
            {
                if (mapa[i][j]==1)//[j][i]segun leo
                {
                    contador=1;
                    islas+=1;
                    try {
                        recorrido(i,j);//j,i segun leo
                    }
                    catch(StackOverflowError e){
                        System.out.println("Metodo conteo de Islas, no se pudo realizar el conteo");
                    }
                    
                    
                    a[islas] = contador;
                    //System.out.println("isla: "+islas+" tamaño "+contador);
                }
            }
        }        
        System.out.println("Numero de Islas: " + islas);        
        int max=0;
        int min=9999999;
        int suma=0;
        
        for (int i = 1; i < a.length; i++) 
        {
            
            if (a[i]>max) {
                max=a[i];
            }
            
            if (a[i]<min) {
                min=a[i];
                
            }
            
            suma= suma+a[i];
        }
        promedio=suma/islas;
        promedio2=2*promedio;
        promedio3=3*promedio;
        promedio25 = 2.5*promedio;
        max_p=max;
        min_p=min;
      return (promedio);
    }
    
    public int recorrido(int x,int y)///Tarea: aumentar a 8
    {
        if(mapa[x][y]==1)
        {
            mapa[x][y]=3;
            
            if((y-1)>-1 && (x-1)>-1)
            {
                if(mapa[x-1][y-1]==1)
                {
                    contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                    O=y-1;//////?????????????????????????
                    N=x-1;
                    recorrido(x-1,y-1);
                }
            }
            
            
            if((y-1)>-1)
            {
                if(mapa[x][y-1]==1)
                {
                    contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                    O=y-1;
                    recorrido(x,y-1);
                }
            }
            
            if((x+1)<imagen.getHeight() && (y-1)>-1 )////////////////////
            {
                if(mapa[x+1][y-1]==1)
                {
                    contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                    O=y-1;//////?????????????????????????
                    S=x+1;
                    recorrido(x+1,y-1);
                }
            }
            
            if((x-1)>-1)
            {
                    if(mapa[x-1][y]==1)
                    {
                        //System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        contador+=1;
                        N=x-1;
                        recorrido(x-1,y);
                    }
            }
            
            if((x+1)<imagen.getHeight())
            {
                if(mapa[x+1][y]==1)
                {
                        //System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        contador+=1;
                        S=x+1;

                        recorrido(x+1,y);
                }
            }
            
            if((x-1)>-1 && (y+1)<imagen.getWidth() )
            {
                if(mapa[x-1][y+1]==1)
                {
                    contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                    N=x-1;//////?????????????????????????
                    E=y+1;
                    recorrido(x-1,y+1);
                }
            }
            
            if((y+1)<imagen.getWidth())
            {
                if(mapa[x][y+1]==1)
                {
                        //System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        contador+=1;
                        E=y+1;
                        recorrido(x,y+1);
                }
            }
            
            
            
            
            if((y+1)<imagen.getWidth() && (x+1)<imagen.getHeight())
            {
                if(mapa[x+1][y+1]==1)
                {
                    contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                    E=y+1;//////?????????????????????????
                    S=x+1;
                    recorrido(x+1,y+1);
                }
            }
            
            
            
            
        }            
        return contador;
    }    
    public int getMax_p() {
        return max_p-promedio_total;
    }

    public int getMin_p() {
        return min_p+promedio_total;
    }

    public int getPromedio_total() {
        return promedio;
    }

    public double[][] getSubmapa() {
        return submapa;
    }

    public double[][] getClusters() {
        return clusters;
    }

    public int getClusters_submapa() {
        return clusters_submapa;
    }
    
    public BufferedImage detectEdge(BufferedImage src)
    {
       //kernel for edge detection adding upto less than 1
       float edgeArr[]={0,-1,0,-1,4,-1,0,-1,0};
       //creating the convolution operator
       ConvolveOp edgeOp=new ConvolveOp(new Kernel(3,3,edgeArr),ConvolveOp.EDGE_NO_OP,null);
    	
       return edgeOp.filter(src, null);  //operating on image
    }
    
    public BufferedImage invertir(BufferedImage imagen)
    {
        BufferedImage imagenRetorno=this.clonarBufferedImage(imagen);
        Color color;
        int r,g,b;
        
        for( int i = 0; i < imagenRetorno.getWidth(); i++ ){
            for( int j = 0; j < imagenRetorno.getHeight(); j++ ){
                color=new Color(imagenRetorno.getRGB(i, j));
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                imagenRetorno.setRGB(i, j, new Color(255-r,255-g,255-b).getRGB());
            }
        }
        return imagenRetorno;
    }
    
    public BufferedImage clonarBufferedImage(BufferedImage bufferImage)
    {
        BufferedImage copiaImagen=new BufferedImage (bufferImage.getWidth(),bufferImage.getHeight(),bufferImage.getType());
        copiaImagen.setData(bufferImage.getData());
        return copiaImagen;
    }
    
    public BufferedImage solobordes(BufferedImage bordes)
    {
        
        BufferedImage trabajar= this.invertir(this.detectEdge(bordes));        
        
        return trabajar;
    }
    
    public void cargarArreglo(BufferedImage img)
    {
        
        mapa = new int[img.getWidth()][img.getHeight()];
        
        for(int i = 0; i < img.getHeight(); i++ )
        {
            for(int j = 0; j < img.getWidth(); j++ )
            {
                //Almacenamos el color del píxel  
                Color colorAux=new Color(img.getRGB(j,i));
                if (colorAux.equals(Color.BLACK)) {
                    mapa[j][i]=1;
                } else {
                    mapa[j][i]=0;
                }
                //System.out.print(mapa[j][i]+" ");
            }
            //System.out.println("");
        }
    }
    
    
    
    public BufferedImage dilatacion(BufferedImage imagen)
    {
        BufferedImage imagen_original=imagen;
        BufferedImage imagen_dilatacion=new BufferedImage(imagen_original.getWidth(),imagen_original.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        for (int i=0;i<imagen_original.getWidth();i++)
        {
            for (int j=0;j<imagen_original.getHeight();j++)
            {
                Color color_obtenido=new Color(imagen_original.getRGB(i, j));
                Color color_dilatacion=color_obtenido;
                int color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                /*
                 * Posiciones
                 *              
                 * P1   P2  P3
                 * P4       P5
                 * P6   P7  P8
                 */
                if (i-1>=0 && j-1>=0){
                    Color P1=new Color(imagen_original.getRGB(i-1, j-1));
                    if ((P1.getRed()+P1.getGreen()+P1.getBlue())<=color_rgb_original)
                    color_dilatacion=P1;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (i-1>=0){    
                    Color P2=new Color(imagen_original.getRGB(i-1, j));
                    if ((P2.getRed()+P2.getGreen()+P2.getBlue())<=color_rgb_original)
                    color_dilatacion=P2;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (i-1>=0 && j+1<imagen_original.getHeight()){
                    Color P3=new Color(imagen_original.getRGB(i-1, j+1));
                    if ((P3.getRed()+P3.getGreen()+P3.getBlue())<=color_rgb_original)
                    color_dilatacion=P3;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (j-1>=0){
                    Color P4=new Color(imagen_original.getRGB(i, j-1));
                    if ((P4.getRed()+P4.getGreen()+P4.getBlue())<=color_rgb_original)
                    color_dilatacion=P4;
                }   
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (j+1<imagen_original.getHeight()){  
                    Color P5=new Color(imagen_original.getRGB(i, j+1));
                    if ((P5.getRed()+P5.getGreen()+P5.getBlue())<=color_rgb_original)
                    color_dilatacion=P5;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (i+1<imagen_original.getWidth() && j-1>=0){
                    Color P6=new Color(imagen_original.getRGB(i+1, j-1));
                    if ((P6.getRed()+P6.getGreen()+P6.getBlue())<=color_rgb_original)
                    color_dilatacion=P6;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (i+1<imagen_original.getWidth()){
                    Color P7=new Color(imagen_original.getRGB(i+1, j));
                    if ((P7.getRed()+P7.getGreen()+P7.getBlue())<=color_rgb_original)
                    color_dilatacion=P7;
                }
                color_rgb_original=color_dilatacion.getRed()+color_dilatacion.getGreen()+color_dilatacion.getBlue();
                if (i+1<imagen_original.getWidth() && j+1<imagen_original.getHeight()){
                    Color P8=new Color(imagen_original.getRGB(i+1, j+1));
                    if ((P8.getRed()+P8.getGreen()+P8.getBlue())<=color_rgb_original)
                    color_dilatacion=P8;
                }                
                imagen_dilatacion.setRGB(i,j,color_dilatacion.getRGB());
            }
         }
        return imagen_dilatacion;
    }
    
}
