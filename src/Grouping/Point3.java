/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.awt.Color;

/**
 * Clase de datos de tres componentes
 * @author Gonzalo
 */
public class Point3{
    private final short x;
    private final short y;
    private final short z;
    
    /**
     * Constructor
     * @param a short
     * @param b short
     * @param c short
     */
    public Point3(short a,short b, short c){
        this.x=a;
        this.y=b;
        this.z=c; 
    }
    
    /**
     * Constructor
     * @param a entero
     * @param b entero
     * @param c entero
     */
    public Point3(int a,int b, int c){
        this.x=(short)a;
        this.y=(short)b;
        this.z=(short)c; 
    }
    
    /**
     * Constructor
     * @param col objeto color
     */
    public Point3(Color col){
       this.x=(short)col.getRed();
       this.y=(short)col.getGreen();
        this.z=(short)col.getBlue();
    }
    
    /**
     * Devuelve el valor de la primera componente
     * @return primera componente x
     */
    public short getX(){
        return this.x;
    }
    
    /**
     * Devuelve el valor de la segunda componente
     * @return segunda componente y
     */
    public short getY(){
        return this.y;
    }
    
    /**
     * Devuelve el valor de la tercer componente
     * @return tercer componente z
     */
    public short getZ(){
        return this.z;
    }
    
    /**
     * Imprime los valores de las componentes a terminal
     */
    public void printPoint3(){
        System.out.print("("+this.x+","+this.y+","+this.z+")");
    }
    
}
