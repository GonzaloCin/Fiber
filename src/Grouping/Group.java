/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

/**
 * Clase para agrupar datos segun su repetición, para oprimizar el algoritmo Kmeans en una dimensión
 * @author Gonzalo
 */
public class Group{
    //Posiblemente deba cambiar a struct
    short value;
    int amount; 
    
    /**
     * Constructor que asigna valor y cantidad de repeticiones
     * @param i valor
     * @param can cantidad
     */
    public Group(short i,int can){
        this.value=i;
        this.amount=can;
    }
    
    /**
     * Devuelve el valor que representa el grupo
     * @return valor
     */
    public short getValue(){
        return value;
    }
    
    /**
     * Devuelve el la cantidad de veces que se repite el valor
     * @return cantiad
     */
    public int getAmount(){
        return amount;
    }
}
