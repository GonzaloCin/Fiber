/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

/**
 * Clase para almacenar tres datos ordenados de tipo flotante
 * @author Gonzalo
 */
public class Point3D {
    
    private final float x;
    private final float y;
    private final float z;
    
    /**
     * Constructor
     * @param a objeto Point3D
     */
    public Point3D(Point3D a){
        this.x=a.getX();
        this.y=a.getY();
        this.z=a.getZ();        
    }
    
    /**
     * Constructor
     * @param a objeto Point3
     */
    public Point3D(Point3 a){
        this.x=a.getX();
        this.y=a.getY();
        this.z=a.getZ();        
    }
    
    /**
     * Contructor
     * @param a primera entrada
     * @param b segunda entrada
     * @param c tercera entrada
     */
    public Point3D(float a,float b, float c){
        this.x=a;
        this.y=b;
        this.z=c; 
    }
    
    /**
     * Multiplicación por escalar
     * @param d parametro flotante
     * @return nuevo objeto Point3D resultado de prodcto
     */
    public Point3D producto(float d){
        return new Point3D(this.x*d,this.y*d,this.z*d);
    }
    
    /**
     * Suma con un objeto Point3
     * @param a objeto Point3
     * @return objeto suma de los objetos
     */
    public Point3D suma(Point3 a){
        return suma(new Point3D(a));
    }
    
    /**
     * Suma con un objeto Point3D
     * @param a objeto Point3D
     * @return objeto suma de los objetos
     */
    public Point3D suma(Point3D a){
        return new Point3D(this.x+a.getX(),this.y+a.getY(),this.z+a.getZ());
    }
    
    /**
     * Devuelve valor de primera entrada
     * @return x
     */
    public float getX(){
        return this.x;
    }
    
    /**
     * Devuelve el valor de la segunda entrada
     * @return y
     */
    public float getY(){
        return this.y;
    }
    
    /**
     * Devuelve el valor de la tercera entrada
     * @return z
     */
    public float getZ(){
        return this.z;
    }
    
    /**
     * Imprime información de objeto a terminal
     */
    public void printPoint3D(){
        System.out.printf("(%3.2f,%3.2f,%3.2f)",this.x,this.y,this.z);
    }
}
