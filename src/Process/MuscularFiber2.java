package Process;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class MuscularFiber2
{
    
    int[][] mapa;
    double [][] submapa;
    double [][] clusters;
    
    int clusters_submapa;
    int puntos_submapa;
    
    BufferedImage imagen;
    int columnas,filas,contador,total_islas,size;
    int[] a;
    int max_p,min_p,promedio_total,promedio;
    
    int N=0;
    int S=0;
    int E=0;
    int O=0;


    public MuscularFiber2(BufferedImage imagen) {
        this.imagen = imagen;
        columnas=imagen.getWidth();
        filas=imagen.getHeight();
        a= new int[filas*columnas];
        puntos_submapa=0;
        clusters_submapa=0;
        submapa = new double [filas*columnas][2];
        clusters = new double [filas*columnas][2];
        
        
    }
    
    public MuscularFiber2(BufferedImage imagen, int promedio) {
        this.imagen = imagen;
        
        filas=imagen.getWidth();
        columnas=imagen.getHeight();
        
        this.promedio_total= promedio;
        a= new int[filas*columnas];
        puntos_submapa=0;
        clusters_submapa=0;
        submapa = new double [filas*columnas][2];
        clusters = new double [filas*columnas][2];
    }
    
    public void cargarArreglo(){
        
        mapa = new int[imagen.getWidth()][imagen.getHeight()];
        
        for(int i = 0; i < imagen.getHeight(); i++ )
        {
            for(int j = 0; j < imagen.getWidth(); j++ )
            {
                //Almacenamos el color del píxel  
                Color colorAux=new Color(imagen.getRGB(j,i));
                if (colorAux.equals(Color.white)) {
                    mapa[j][i]=0;
                } else {
                    mapa[j][i]=1;
                    
                    submapa[puntos_submapa][0]=j;
                    submapa[puntos_submapa][1]=i;
                    puntos_submapa+=1;
                }
                System.out.print(mapa[j][i]+" ");
            }
            System.out.println("");
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
                if (mapa[j][i]==1) {
                 contador=0;
                 islas+=1;
                 recorrido(j,i);
                 a[islas] = contador;
                }
            }
        }   
        System.out.println(islas);
        
        int max=0;
        int min=9999999;
        int suma=0;
        
        for (int i = 1; i < a.length; i++) {
            
            if (a[i]>max) {
                max=a[i];
            }
            
            if (a[i]<min) {
                min=a[i];
                
            }
            
            suma= suma+a[i];
        }
        promedio=suma/islas;
        max_p=max;
        min_p=min;
      return (suma/islas);
    }

    public int recorrido(int x,int y)
    {
        
            if(mapa[x][y]==1)
            {
                mapa[x][y]=3;
                
                
                if((y-1)>-1)
                {
                    if(mapa[x][y-1]==1)
                    {
                        contador+=1;//System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        O=y-1;
                        recorrido(x,y-1);
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
                
                if((y+1)<imagen.getHeight())
                {
                    if(mapa[x][y+1]==1)
                    {
                        //System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        contador+=1;
                        E=y+1;
                        recorrido(x,y+1);
                    }
                }
                
                if((x+1)<imagen.getWidth())
                {
                    if(mapa[x+1][y]==1)
                    {
                        //System.out.println("x: "+x+" y: "+y+mapa[x][y]);
                        contador+=1;
                        S=x+1;
                        recorrido(x+1,y);
                    }
                }
            }
            
            return contador;
    }
}

