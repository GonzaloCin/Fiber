/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.util.ArrayList;

/**
 * Clase abstracta para clusters, modelo basico que concretan lo distintos tipos de cluster
 * @author Gonzalo
 */
public abstract class Cluster {
    
    /**
     * Vaciar cluster de datos que pertenecen, habitual al final de una iteración del algoritmo
     */
    public abstract void empty();

    /**
     * Calcula el centroide del cluster
     */
    public abstract void calculeCenter();
    
    /**
     * Calcula la diferencia entre el centroide actual y el anterior
     * @return distancia de los centroides
     */
    public abstract float diff();
    
    /**
     * Imprime información del cluster a terminal
     */
    public abstract void showCluster();
   
}
