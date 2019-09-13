/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import Images.SeparatedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gonzalo
 */
public class Calculus {
    
    
    public static void sort(ClusterK[] vec){
        byte tam=(byte)vec.length;
        float auxi;
        for(byte i=0;i<tam;i++){
            for(byte j=0;j<tam-1;j++){
		if(vec[j].getCenter()>vec[j+1].getCenter()){
                    auxi=vec[j].getCenter();
                    vec[j].setCenter(vec[j+1].getCenter());
                    vec[j+1].setCenter(auxi);
                }
            }
        }
    }
    
    public static void sort(Point3[] vec){
        byte tam=(byte)vec.length;
        Point3 auxi;
        for(byte i=0;i<tam;i++){
            for(byte j=0;j<tam-1;j++){
		if(norm(vec[j])>norm(vec[j+1])){
                    auxi=vec[j];
                    vec[j]=vec[j+1];
                    vec[j+1]=auxi;
                }
            }
        }
    }
    
    public static ArrayList<Group> toGroupArray(int[] base){
        ArrayList<Group> ret=new ArrayList<Group>();
        for(int i=0;i<base.length;i++){
            //System.out.println("(v"+aux.getValor()+",c"+aux.getCantidad()+")");
            ret.add(new Group((short)i,base[i])); 
        }
        return ret;
    }
    
    public static void sort(ClusterK3[] vec){
        byte tam=(byte)vec.length;
        Point3D auxi;
        for(byte i=0;i<tam;i++){
            for(byte j=0;j<tam-1;j++){
		if(norm(vec[j].getCenter())>norm(vec[j+1].getCenter())){
                    auxi=vec[j].getCenter();
                    vec[j].setCenter(vec[j+1].getCenter());
                    vec[j+1].setCenter(auxi);
                }
            }
        }
    }
    
    public static void sort(ClusterKM[] vec){
        byte tam=(byte)vec.length;
        Point3D auxi;
        for(byte i=0;i<tam;i++){
            for(byte j=0;j<tam-1;j++){
		if(norm(vec[j].getCenter())>norm(vec[j+1].getCenter())){
                    auxi=vec[j].getCenter();
                    vec[j].setCenter(vec[j+1].getCenter());
                    vec[j+1].setCenter(auxi);
                }
            }
        }
    }
    
    public static float norm(Point3 p){
        return (float)sqrt(pow(p.getX(),2)+pow(p.getY(),2)+pow(p.getZ(),2));
    }
    public static float norm(Point3D p){
        return (float)sqrt(pow(p.getX(),2)+pow(p.getY(),2)+pow(p.getZ(),2));
    }
    
    public static float distance(Point3D a,Point3D b){
        return (float) sqrt(pow(a.getX()-b.getX(),2)+pow(a.getY()-b.getY(),2)+pow(a.getZ()-b.getZ(),2));
    }
    
    public static float distance(short[] a,Point3D b){
        return (float) sqrt(pow(a[0]-b.getX(),2)+pow(a[1]-b.getY(),2)+pow(a[2]-b.getZ(),2));
    }
    
    public static float distance(Point3 a,Point3D b){
        return distance(new Point3D(a),b);
    }
    public static float distance(Point3D a,Point3 b){
        return distance(a,new Point3D(b));
    }
    
    public static float distance(Group g,ClusterK c){
        return distance(g.value,c.getCenter());
    }
    
    public static float distance(Point3 g,ClusterK3 c){
        return distance(g,c.getCenter());
    }
    
    public static float distance(Point3 g,ClusterKM c){
        return distance(g,c.getCenter());
    }
    
    public static float distance(Color a,Color b){
        return (float) sqrt(pow(a.getRed()-b.getRed(),2)+pow(a.getGreen()-b.getGreen(),2)+pow(a.getBlue()-b.getBlue(),2));
    }
    
    public static float distance(Color a,Point3D b){
        return (float) sqrt(pow(a.getRed()-b.getX(),2)+pow(a.getGreen()-b.getY(),2)+pow(a.getBlue()-b.getZ(),2));
    }
    
    
    public static float distance(float a, float b){
        return Math.abs(a-b);
    }
    
    public static float membership(Point3 p,ArrayList<Point3D> centers,int i,float m){
        float res=0;
        for(int j=0;j<centers.size();j++){
                
            res+=Math.pow(Math.pow(distance(p,centers.get(i))/distance(p,centers.get(j)),2),1/(m-1));
        }
        return (1/res);
    }
    
    public static float membership(Color p,ArrayList<Point3D> centers,int i,float m){
        float res=0;
        for(int j=0;j<centers.size();j++){
            res+=Math.pow(Math.pow(distance(p,centers.get(i))/distance(p,centers.get(j)),2),1/(m-1));
        }
        return (1/res);
    }
    
    public static float membership(short[] p,ArrayList<Point3D> centers,int i,float m){
        float res=0;
        for(int j=0;j<centers.size();j++){
            res+=Math.pow(Math.pow(distance(p,centers.get(i))/distance(p,centers.get(j)),2),1/(m-1));
        }
        return (1/res);
    }
    
    
    public static Point3 sum(Point3 a,Point3 b){
        return new Point3((short)(a.getX()+b.getX()),(short)(a.getY()+b.getY()),(short)(a.getZ()+b.getZ()));
    }
    
    public static Point3D sum(Point3D a,Point3D b){
        return new Point3D(a.getX()+b.getX(),a.getY()+b.getY(),a.getZ()+b.getZ());
    }
    
    public static Point3D sum(Point3D a,Point3 b){
        return sum(a,new Point3D(b));
    }
    public static Point3D sum(Point3 b,Point3D a){
        return sum(a,new Point3D(b));
    }
    
    
    
    public static Point3D scalarProduct(Point3D p,float a){
        return new Point3D(p.getX()*a,p.getY()*a,p.getZ()*a);
    }  
    
    public static Point3D scalarProduct(Point3 p,float a){
        return new Point3D(p.getX()*a,p.getY()*a,p.getZ()*a);
    } 
    
    public static BufferedImage reSizeImage(BufferedImage im,int w,int h){
        if(im.getWidth()<w && im.getHeight()<h){
            System.out.println("\nOriginales:"+im.getWidth()+" X "+im.getHeight());
            return im;
        }
        else{    
            
            System.out.println("\nOriginales:"+im.getWidth()+" X "+im.getHeight()+"\tNuevas:"+w+" X "+h);
            BufferedImage resp=new BufferedImage(w,h,im.getType());
            Graphics2D g = resp.createGraphics();
            g.drawImage(im, 0, 0,w,h,null);
            g.dispose();
            return resp;
        }
        
    }
    
    
    public static BufferedImage ReduceImage(BufferedImage im,int res){
        //System.out.println("Resolucion: "+res);
        if(im.getWidth()*im.getHeight()<res || res==0){
            System.out.println("Originales(Las Mismas):"+im.getWidth()+" X "+im.getHeight());
            return Clone(im);
        }
        else{    
            double newWidth=Math.sqrt((res/im.getHeight())*im.getWidth());
            double newHeight=Math.sqrt((res/im.getWidth())*im.getHeight());
            System.out.println("\nReduceiendo: \n\tOriginales:"+im.getWidth()+" X "+im.getHeight()+"\tNuevas:"+newWidth+" X "+newHeight);
            BufferedImage resp=new BufferedImage((int)newWidth,(int)newHeight,im.getType());
            Graphics2D g = resp.createGraphics();
            g.drawImage(im, 0, 0,(int)newWidth,(int)newHeight,null);
            g.dispose();
            return resp;
        }
        
    }
    
    public static void SaveImage(BufferedImage a)//copiado a estadistica celulas
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen1 = new FileNameExtensionFilter("JPG", "jpg");
        chooser.setFileFilter(filtroImagen1);
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
        try
        {
            File bi = new File(chooser.getSelectedFile()+".jpg");
            ImageIO.write(a,"jpg", bi);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        }
    }
    
    
    
    public static BufferedImage Dilate(BufferedImage original){
        int w=original.getWidth(),h=original.getHeight();
        BufferedImage result= new BufferedImage(w,h,original.getType());
        int rgbaux=Color.WHITE.getRGB();
        for(int i=0;i<w;i++){
            result.setRGB(i,0,Color.WHITE.getRGB());
            result.setRGB(i,h-1,Color.WHITE.getRGB());
        }
        for(int j=0;j<h;j++){
        result.setRGB(0,j,Color.WHITE.getRGB());
        result.setRGB(w-1,j,Color.WHITE.getRGB());
        }
        
        int counter=0;
        
        for(int i=1;i<w-1;i++){
            
            for(int j=1;j<h-1;j++){
                rgbaux=Color.WHITE.getRGB();
                counter=0;
                if(original.getRGB(i-1,j-1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j-1);
                    counter++;
                }
                if(original.getRGB(i-1,j)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j);
                    counter++;
                }
                if(original.getRGB(i-1,j+1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j+1);
                    counter++;
                }
                if(original.getRGB(i,j-1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i,j-1);
                    counter++;
                }
                if(original.getRGB(i,j+1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i,j+1);
                    counter++;
                }
                if(original.getRGB(i+1,j-1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j-1);
                    counter++;
                }
                if(original.getRGB(i+1,j)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j);
                    counter++;
                }
                if(original.getRGB(i+1,j+1)!=Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j+1);
                    counter++;
                }
                
                if(counter>0){
                  result.setRGB(i,j,rgbaux);
                }else if(original.getRGB(i,j)!=Color.WHITE.getRGB()){
                  result.setRGB(i,j,original.getRGB(i,j));
                }else{
                   result.setRGB(i,j,Color.WHITE.getRGB());
                }
                
            }
        }
        return result;
    }
    
    
    
    
    public static BufferedImage Erode(BufferedImage original){
        int w=original.getWidth(),h=original.getHeight();
        BufferedImage result= new BufferedImage(w,h,original.getType());
        int rgbaux=Color.WHITE.getRGB();   
        for(int i=0;i<w;i++){
            result.setRGB(i,0,Color.WHITE.getRGB());
            result.setRGB(i,h-1,Color.WHITE.getRGB());
        }
        for(int j=0;j<h;j++){
        result.setRGB(0,j,Color.WHITE.getRGB());
        result.setRGB(w-1,j,Color.WHITE.getRGB());
        }
        for(int i=1;i<w-1;i++){
            for(int j=1;j<h-1;j++){
                rgbaux=original.getRGB(i,j);
                if(original.getRGB(i-1,j-1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j-1);
                }
                if(original.getRGB(i-1,j)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j);
                }
                if(original.getRGB(i-1,j+1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i-1,j+1);
                }
                if(original.getRGB(i,j-1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i,j-1);
                }
                if(original.getRGB(i,j+1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i,j+1);
                }
                if(original.getRGB(i+1,j-1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j-1);
                }
                if(original.getRGB(i+1,j)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j);
                }
                if(original.getRGB(i+1,j+1)==Color.WHITE.getRGB()){
                    rgbaux=original.getRGB(i+1,j+1);
                }
                
                result.setRGB(i,j,rgbaux);
            }
        }
        return result;
    }
    
    public static SeparatedImage Erode(SeparatedImage original){
        int w=original.getWidth(),h=original.getHeight();
        SeparatedImage result= new SeparatedImage(w,h,original.getColorByte(),original.getSampleModel());
       byte sum=0;
        for(int i=1;i<w-1;i++){
            for(int j=1;j<h-1;j++){
                sum=0;
                sum+=original.getpixel(i-1,j-1);
                sum+=original.getpixel(i-1,j);
                sum+=original.getpixel(i-1,j+1);
                sum+=original.getpixel(i,j-1);
                sum+=original.getpixel(i,j+1);
                sum+=original.getpixel(i+1,j-1);
                sum+=original.getpixel(i+1,j);
                sum+=original.getpixel(i+1,j+1);
                sum+=original.getpixel(i, j);
                if(sum==9){
                    result.setColored(i, j,(byte)1);
                }
            }
        }
        return result;
    }
    
    public static SeparatedImage Dilate(SeparatedImage original){
        int w=original.getWidth(),h=original.getHeight();
        SeparatedImage result= new SeparatedImage(w,h,original.getColorByte(),original.getSampleModel());
       byte sum=0;
        for(int i=1;i<w-1;i++){
            for(int j=1;j<h-1;j++){
                sum=0;
                sum+=original.getpixel(i-1,j-1);
                sum+=original.getpixel(i-1,j);
                sum+=original.getpixel(i-1,j+1);
                sum+=original.getpixel(i,j-1);
                sum+=original.getpixel(i,j+1);
                sum+=original.getpixel(i+1,j-1);
                sum+=original.getpixel(i+1,j);
                sum+=original.getpixel(i+1,j+1);
                sum+=original.getpixel(i, j);
                if(sum>0){
                    result.setColored(i, j,(byte)1);
                }
            }
        }
        return result;
    }
    
    
    
    public static BufferedImage Opening(BufferedImage original){
        return Dilate(Erode(original));
    }
    
    public static SeparatedImage Opening(SeparatedImage original){
        return Dilate(Erode(original));
    }
    
    public static BufferedImage Closing(BufferedImage original){
        return Erode(Dilate(original));
    }
    public static SeparatedImage Closing(SeparatedImage original){
        return Erode(Dilate(original));
    }
 
    public static BufferedImage OpeningNM(BufferedImage original,int n,int m){
        BufferedImage anterior;
        
        anterior=Clone(original);
        BufferedImage nueva=anterior;
        for(int i=1;i<=n;i++){
            nueva=Erode(anterior);
            anterior=nueva;
        }
        for(int j=1;j<=m;j++){
            nueva=Dilate(anterior);
            anterior=nueva;
        }
        
        
        return nueva;
    }
    
    public static SeparatedImage OpeningNM(SeparatedImage original,int n,int m){
        SeparatedImage anterior;
        
        anterior=Clone(original);
        SeparatedImage nueva=anterior;
        for(int i=1;i<=n;i++){
            nueva=Erode(anterior);
            anterior=nueva;
        }
        for(int j=1;j<=m;j++){
            nueva=Dilate(anterior);
            anterior=nueva;
        }
        
        
        return nueva;
    }
    
    public static BufferedImage ClosingNM(BufferedImage original,int n,int m){
        BufferedImage anterior;
        
        anterior=Clone(original);
        BufferedImage nueva=anterior;
        for(int i=1;i<=n;i++){
            nueva=Dilate(anterior);
            anterior=nueva;
        }
        for(int j=1;j<=m;j++){
            nueva=Erode(anterior);
            anterior=nueva;
        }
        
        
        return nueva;
    }
    
    public static SeparatedImage ClosingNM(SeparatedImage original,int n,int m){
        SeparatedImage anterior;
        
        anterior=Clone(original);
        SeparatedImage nueva=anterior;
        for(int i=1;i<=n;i++){
            nueva=Dilate(anterior);
            anterior=nueva;
        }
        for(int j=1;j<=m;j++){
            nueva=Erode(anterior);
            anterior=nueva;
        }
        
        
        return nueva;
    }
    
    public static BufferedImage Clone(BufferedImage source){
        BufferedImage copy = new BufferedImage (source.getWidth(),source.getHeight(),source.getType());
        copy.setData(source.getData());
        return copy;
    }
    
    public static SeparatedImage Clone(SeparatedImage source){
        SeparatedImage copy=new SeparatedImage (source.getWidth(),source.getHeight(),source.getColorByte(),source.getSampleModel());
        for(int y=0;y<source.getHeight();y++){
            for(int x=0;x<source.getWidth();x++){
                copy.setColored(x, y,source.getpixel(x, y));
            }
        }
        return copy;
    }
    
    
}
