/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Images;

import Grouping.Calculus;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.SampleModel;

/**
 *
 * @author Gonzalo
 */
public class SeparatedImage {
    BufferedImage image;//solo la genera para devolverla
    byte[][] bi;//codificacion en 0s 1s 2s y/0 3s
    int [] col;//{r,g,b}
    byte [][] colorbyte;//Codificación rastes segun, valores bgr unsigned
    SampleModel SM;
    int w,h,t;//dimensiones
    boolean imageisAssigned;
    
    
    public SeparatedImage(BufferedImage img,byte[][] map){
        image=Calculus.Clone(image);
        bi=map;
    }
    
    public SeparatedImage(int w, int h, byte [][] cb, SampleModel sp){
        this.w=w;
        this.h=h;
        this.colorbyte=cb;
        SM=sp;
        bi=new byte[h][w];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                bi[i][j]=0;
            }
        }
        imageisAssigned=false;
        
    }
    
    public int getWidth(){
        return w;
    }
    
    public int getHeight(){
        return h;
    }
    
    public SampleModel getSampleModel(){
        return SM;
    }
    
    public byte[][] getColorByte(){
        return colorbyte;
    }
    
    public byte getpixel(int x, int y){
        return bi[y][x];
    }
    
    public void setColored(int x,int y,byte c){
        bi[y][x]=c;
    }
    
    public BufferedImage getImage(){
        if(!imageisAssigned){
            byte resultado[]=new byte[3*h*w];
            int pos=0;            
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    pos=(i*w*3)+j*3; 
                    resultado[pos]=colorbyte[bi[i][j]][0];
                    resultado[pos+1]=colorbyte[bi[i][j]][1];
                    resultado[pos+2]=colorbyte[bi[i][j]][2];
            }
        }
        image=new BufferedImage(w,h,BufferedImage.TYPE_3BYTE_BGR);
        image.setData(Raster.createRaster(SM, new DataBufferByte(resultado, resultado.length), new Point() ) );
        imageisAssigned=true;
        }
        return image;
    }
    
    //img.setData(Raster.createRaster(img.getSampleModel(), new DataBufferByte(srcbuf, srcbuf.length), new Point() ) );
}
