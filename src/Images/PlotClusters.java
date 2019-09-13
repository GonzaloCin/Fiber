package Images;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.DefaultListModel;

/**
 *
 * @author GustavoLeonardo
 */
public class PlotClusters {
    
    public static void PlotCluster(Graphics g,double [][] clusters, int size, int alto, int ancho, int altoE, int anchoE) {
        
        for (int i = 0; i < size; i++) {
            g.setColor(Color.red);
            //g.drawString("*", ((((int)clusters[i][0])*ancho)/anchoE) , ((((int)clusters[i][1])*alto)/anchoE));
            if(clusters[i][0]!='\0'){
                g.fillRect((int)clusters[i][0], (int)clusters[i][1], 3, 3);
            }
            
        }
        
    }
    
    public static void PlotCluster(Graphics g,DefaultListModel<Integer> clustersx,DefaultListModel<Integer> clustersy, int size, int alto, int ancho, int altoE, int anchoE) {
        
        for (int i = 0; i < size; i++) {
            g.setColor(Color.red);
            //g.drawString("*", ((((int)clusters[i][0])*ancho)/anchoE) , ((((int)clusters[i][1])*alto)/anchoE));
                g.fillRect((clustersx.getElementAt(i)*ancho/anchoE)-1, (clustersy.getElementAt(i)*alto/altoE)-1, 3, 3);

            
        }
        
    }
    
    public static void PlotCluster(Graphics g,DefaultListModel<Integer> clustersx,DefaultListModel<Integer> clustersy, int size, int alto, int ancho, int altoE, int anchoE,String Tipo) {
        Color aux=Color.BLACK;
        switch (Tipo){
            case "Oxidativas":
                aux=Color.GREEN;
            break;
            case "Glucoliticas":
                aux=Color.BLUE;
            break;
            case "Rapidas":
                aux=Color.BLUE;
            break;
            case "Intermedias":
                aux=Color.RED;
            break;
            case "Lentas":
                aux=Color.GREEN;
            break;
        }
        
        for (int i = 0; i < size; i++) {
            g.setColor(aux);
            //g.drawString("*", ((((int)clusters[i][0])*ancho)/anchoE) , ((((int)clusters[i][1])*alto)/anchoE));
                //g.fillRect((clustersx.getElementAt(i)*ancho/anchoE)-2, (clustersy.getElementAt(i)*alto/altoE)-2, 5, 5);
                g.fillOval((clustersx.getElementAt(i)*ancho/anchoE)-2, (clustersy.getElementAt(i)*alto/altoE)-2, 5, 5);

            
        }
        
    }
    
    public static void PlotCluster(BufferedImage Vis,TreeMap<Point,Integer> Clusters,int anchoPro , int altoPro , String Tipo) {
        Color aux=Color.BLACK;
        switch (Tipo){
            case "Oxidativas":
                aux=Color.GREEN;
            break;
            case "Glucoliticas":
                aux=Color.BLUE;
            break;
            case "Rapidas":
                aux=Color.BLUE;
            break;
            case "Intermedias":
                aux=Color.RED;
            break;
            case "Lentas":
                aux=Color.GREEN;
            break;
        }
        Graphics g =Vis.getGraphics();
        int ancho = Vis.getWidth();
        int alto = Vis.getHeight();
        int lado, lmedio;
        //Elegir el tamaño de los puntos en función del tamaño de la imagen que ese está mostrando
        int Mpx= (int)((ancho*alto)/1000000);
        switch(Mpx){
            case 0:
            case 1: 
                lado=3; lmedio=1;
            break;
            case 2:
            case 3:
            case 4:
                lado=5; lmedio=2;
            break;
            case 5:
            case 6:
            case 7:
                lado=6; lmedio=3;
            break;
            case 8:
            case 9:
            case 10:
                lado=7; lmedio=3;
            break;
            case 11:
            case 12:
            case 13:
                lado=8; lmedio=4;
            break;
            case 14:
            default:
                lado=9; lmedio=4;
            break;
        }
        
        for(Map.Entry<Point,Integer> i : Clusters.entrySet()){
            g.setColor(aux);
            g.fillOval((i.getKey().x*ancho/anchoPro)-lmedio, (i.getKey().y*alto/altoPro)-lmedio, lado, lado);
        }
        g.dispose();
    }
    
    
    
    public static void PlotCluster(Graphics g,double [][] clusters, int size, int alto, int ancho, int altoE, int anchoE,int punto) {
        
        for (int i = 0; i < size; i++) {
            if (i==punto) {
                g.setColor(Color.red);
                g.drawString("o", ((((int)clusters[i][0])*ancho)/anchoE) , ((((int)clusters[i][1])*alto)/anchoE) );
            } else {
                g.setColor(Color.black);
                g.drawString("*", ((((int)clusters[i][0])*ancho)/anchoE) , ((((int)clusters[i][1])*alto)/anchoE) );
            }
            
        }
        
    }
    
}
