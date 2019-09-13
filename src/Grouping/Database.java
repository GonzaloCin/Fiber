/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.awt.Color;
//import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CreationHelper;
//Hacer polimorfica con respecto a tipo de datos, separados o 3d
/**
 *
 * @author Gonzalo
 */
public class Database {
    int [] red = new int [256];
    int [] green = new int [256];
    int [] blue = new int [256];
    int total=0;
    ArrayList<Point3> RGB=new ArrayList<Point3>();
    BufferedImage AuxImage;
    Color color;
    
    HSSFWorkbook libro; 
    HSSFSheet hoja;
    HSSFRow fila;
    HSSFCell  celdar;
    HSSFCell  celdag;
    HSSFCell  celdab;
    HSSFCell  celdan;
    
    /**
     * Constructor, inicializa el arreglo de variables donde almacenar datos
     */
    public Database(){
        for(int i=0;i<256;i++){
            red[i]=0;
            green[i]=0;
            blue[i]=0;
        }
    }
    
    /**
     * Devuelve un arreglo con las frecuencias de cada tono rojo
     * @return arreglo de frecuencias de tonos rojos
     */
    public int [] getRed(){
        return red;
    }
    
    /**
     * Devuelve un arreglo con las frecuencias de cada tono verde
     * @return arreglo de frecuencias de tonos verdes
     */
    public int [] getGreen(){
        return green;
    }

    /**
     * Devuelve un arreglo con las frecuencias de cada tono azul
     * @return arreglo de frecuencias de tonos azul
     */
    public int [] getBlue(){
        return blue;
    }

    /**
     * Devuelve el total de elementos agregados
     * @return conteo de datos agregados
     */
    public int getTotal(){
        return total;
    }
    
    /**
     * Devuelve arreglo completo de puntos agregados
     * @return ArrayList RGB
     */
    public ArrayList<Point3> getComplete(){
        return RGB;
    }
    
    private void add(ArrayList<Point3> newer){
        int a=newer.size();
        for(int l=0;l<=a;l++){
            this.RGB.add(newer.get(l));
        }
    }
    
    private void add(long [] r,long [] g, long [] b){
        for(int i=0;i<256;i++){
            red[i]+=r[i];
            green[i]+=g[i];
            blue[i]+=b[i];
            total+=r[i];
            //total+=g[i];
            //total+=b[i];
        }
    }
    
    /**
     * Agrega datos de una imagen en componentes separadas
     * @param fi archivo a abrir
     */
    public void add(File fi){///Checar proyecto PerformanceTest
        try
        {
            AuxImage = ImageIO.read(fi);
        }
        catch (Exception e)
        {
            System.out.println("Error al abrir archivo");
        }
        System.out.println("Tamaño: "+AuxImage.getWidth()+"x"+AuxImage.getHeight());
        for(int i=0;i<AuxImage.getWidth();i++){
            for(int j=0;j<AuxImage.getHeight();j++){
                color=new Color(AuxImage.getRGB(i, j));
                red[color.getRed()]++;
                green[color.getGreen()]++;
                blue[color.getBlue()]++;  
            }
        }
    }
    

    private void addComplete(File fi,int res){///Checar proyecto PerformanceTest
        try
            {
               //Asignamos a la variable bmp la imagen leida
                AuxImage = ImageIO.read(fi);
            }
            catch (Exception e)
            {
            }
        AuxImage=Calculus.ReduceImage(AuxImage,res); //pretendo reucir una imagen a aproximadamente la cantidad res(en pixeles) conservando aspecto
        System.out.println("Imagen Añadida para analisis "+fi.getName()+" Tamaño: "+AuxImage.getWidth()+"x"+AuxImage.getHeight());
        
        final byte[] pixels = ((DataBufferByte) AuxImage.getRaster().getDataBuffer()).getData();
        final boolean hasAlphaChannel = AuxImage.getAlphaRaster() != null;
        int pixelLength;
        if (hasAlphaChannel) {
           pixelLength = 4;
        } else {  
           pixelLength = 3;
        }
          for (int pixel = 0; pixel < pixels.length; pixel += pixelLength) {
                RGB.add(new Point3((short)(pixels[pixel + 2] & 0xff),(short)(pixels[pixel + 1] & 0xff),(short)(pixels[pixel] & 0xff)));  
           }
        
        
    }
    
    
    
    
//     public void add(BufferedImage im){ ///Checar proyecto PerformanceTest
//        //AuxImage = im;
//        System.out.println("Tamaño: "+im.getWidth()+"x"+im.getHeight());
//        for(int i=0;i<im.getWidth();i++){
//            for(int j=0;j<im.getHeight();j++){
//                color=new Color(im.getRGB(i, j));
//                red[color.getRed()]++;
//                green[color.getGreen()]++;
//                blue[color.getBlue()]++;  
//            }
//        }
//     }

    /**
     * Añade datos de una imagen en componentes separadas, reducuda a la resolución especificada
     * @param image archivo de imagen
     * @param res resolución a reducir en pixeles
     */
     
     public void add(BufferedImage image,int res) {
        AuxImage=Calculus.ReduceImage(image,res);
        System.out.println("Imagen Agregada Tamaño: "+AuxImage.getWidth()+"x"+AuxImage.getHeight());
        final byte[] pixels = ((DataBufferByte) AuxImage.getRaster().getDataBuffer()).getData();
        final boolean hasAlphaChannel = AuxImage.getAlphaRaster() != null;
        int pixelLength;
        if (hasAlphaChannel) {
           pixelLength = 4;
        } else {  
           pixelLength = 3;
        }
          for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
              red[(int) pixels[pixel + 2] & 0xff]++;
                green[(int) pixels[pixel + 1] & 0xff]++;
                blue[(int) pixels[pixel] & 0xff]++;
           }
    }
     
    /**
     *  Añade datos de imagen en componentes de tres dimensiones, reducida a la resolucion especificada
     * @param image archivo de imagen
     * @param res resolución a reducir en pixeles
     */
    public void addComplete(BufferedImage image,int res) {
        AuxImage=Calculus.ReduceImage(image,res);
        System.out.println("Imagen Agregada Tamaño: "+AuxImage.getWidth()+"x"+AuxImage.getHeight());
        final byte[] pixels = ((DataBufferByte) AuxImage.getRaster().getDataBuffer()).getData();
        final boolean hasAlphaChannel = AuxImage.getAlphaRaster() != null;
        int pixelLength;
        if (hasAlphaChannel) {
           pixelLength = 4;
        } else {  
           pixelLength = 3;
        }
          for (int pixel = 0; pixel < pixels.length; pixel += pixelLength) {
                RGB.add(new Point3((short)(pixels[pixel + 2] & 0xff),(short)(pixels[pixel + 1] & 0xff),(short)(pixels[pixel] & 0xff)));  
           }
    }
     
    /**
     * Añade una lista de imagenes en componentes de tres dimensiones, reducidas a la resolución especificada
     * @param list lista de archivos a agregar
     * @param res resolucion a reducir en pixeles
     */
    public void analyseComplete(ArrayList<File> list,int res){
         for(int i=0;i<list.size();i++){
             addComplete(list.get(i),res);
         }
     }
}
