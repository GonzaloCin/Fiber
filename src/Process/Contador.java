/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Process;

import Grouping.PointCompare;
import Images.AbstractImage;
import Images.SeparatedImage;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Gonzalo
 */
public class Contador {
    byte [][] binary;
    TreeMap<Point,Integer> ClustersMap;
    ArrayList<Point> actuales;
    int cantidad,ancho,alto;
    int max=0,min=10000;
    int lasti;
    int lastj;
    
    public Contador(BufferedImage im){
        ancho=im.getWidth();
        alto=im.getHeight();
        binary=AbstractImage.toBinary(im);
    }
    
    public Contador(byte[][] map,int w,int h){
      ancho=w;
      alto=h;
      binary=map;
    }
    
    public Contador(SeparatedImage SI){
      ancho=SI.getWidth();
      alto=SI.getHeight();
      binary=new byte[alto][ancho];
      for(int y=0;y<alto;y++){
            for(int x=0;x<ancho;x++){
                binary[y][x]=SI.getpixel(x,y);
            }
        }
    }
    
    public void contarIslas(){
        boolean finishedWithoutError=false;
        actuales=new ArrayList<>();
        ClustersMap = new TreeMap<>(new PointCompare());
        for(int j=0;j<ancho;j++){
            for(int i=0;i<alto;i++){
                if(binary[i][j]==1){
                    actuales.clear();
                    actuales.add(new Point(j,i));
                    lasti=i;
                    lastj=j;
                    do{
                        try {
                            recorrido(lasti,lastj);
                            finishedWithoutError=true;
                        }
                        catch(StackOverflowError e){
                            System.out.print("StackOverflow, ");
                            finishedWithoutError=false;
                        }
                    }while(!finishedWithoutError);
                    //Hacer erosion de cada fibra aislada para calcular el centroide y el tamaño con una mayor presición
                        //Primero hacer una matriz de bytes que contenga completamente todos los pixeles y ademas espacio para dilatar el mismo numero de erosiones que se hicieron en la pantalla principal.
                            //Sacar el maximo y minimo de x y y y a eso ampliarlos dos veces la cantidad de dilataciones que se harán
                            //Dilatar
                        //Calcular centroide y tamaño
                        
                     
                    ClustersMap.put(Mean(actuales),actuales.size());
                }
            }
        }
        cantidad = ClustersMap.size();
    }
    
    //Considerar que las coordenadas son xh yv y en matriz es jh y iv 
    private void recorrido(int y,int x){
        if(binary[y][x]==1){
            binary[y][x]=3;
            if((y-1)>-1 && (x-1)>-1){
                if(binary[y-1][x-1]==1){
                    actuales.add(new Point(x-1,y-1));
                    lasti=y-1;
                    lastj=x-1;
                    recorrido(y-1,x-1);
                }
            }
            if((y-1)>-1){
                if(binary[y-1][x]==1){
                    actuales.add(new Point(x-1,y));
                    lasti=y-1;
                    lastj=x;
                    recorrido(y-1,x);
                }
            }
            if((x-1)>-1){
                if(binary[y][x-1]==1){
                    actuales.add(new Point(x,y-1));
                    lasti=y;
                    lastj=x-1;
                    recorrido(y,x-1);
                }
            }
            if((y-1)>-1 && (x+1)<ancho){
                if(binary[y-1][x+1]==1){
                    actuales.add(new Point(x-1,y+1));
                    lasti=y-1;
                    lastj=x+1;
                    recorrido(y-1,x+1);
                }
            }
            if((y+1)<alto && (x-1)>-1){
                if(binary[y+1][x-1]==1){
                    actuales.add(new Point(x+1,y-1));
                    lasti=y+1;
                    lastj=x-1;
                    recorrido(y+1,x-1);
                }
            }
            if((y+1)<alto ){
                if(binary[y+1][x]==1){
                    actuales.add(new Point(x+1,y));
                    lasti=y+1;
                    lastj=x;
                    recorrido(y+1,x);
                }
            }
            if((x+1)<ancho){
                if(binary[y][x+1]==1){
                    actuales.add(new Point(x,y+1));
                    lasti=y;
                    lastj=x+1;
                    recorrido(y,x+1);
                }
            }
            if((y+1)<alto && (x+1)<alto){
                if(binary[y+1][x+1]==1){
                    actuales.add(new Point(x+1,y+1));
                    lasti=y+1;
                    lastj=x+1;
                    recorrido(y+1,x+1);
                }
            }
        }
        
    }
    
    
    private Point Mean(ArrayList<Point> lista){
        int x=0;
        int y=0;
        for (Point p : lista) {
            x += p.getX();
            y += p.getY();
        }
        x/=lista.size();
        y/=lista.size();
        return new Point(x,y);
    }
    
    public TreeMap getClusters(){
        return ClustersMap;
    }
    
    public int getConteo(){
        return cantidad;
    }
    
    
    public int getMax(){
        if (max==0){
            for(int v : ClustersMap.values()){
                if(max<v){
                    max=v;
                }
           }
        }
        //min=max;
        return max;
    }
    
    public int getMin(){
        if (min==10000){
            for(int v : ClustersMap.values()){
                if(min>v){
                    min=v;
                }
           }
        }
        return min;
    }
}
