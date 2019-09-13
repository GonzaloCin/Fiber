package Grouping;

import java.util.ArrayList;

/**
 * Subclase de Cluster, tambien abstracta, de la que heredaran clusters de datos de tres dimensiones
 * @author Gonzalo
 */
public abstract class Cluster3D extends Cluster {
    ArrayList<Point3> Belonging;

    /**
     * Inserta el objeto Point3 al cluster
     * @param a objeto Point3
     */
    public abstract void insert(Point3 a);

    /**
     * Asigna el centroide del cluster
     * @param p objeto Point3D
     */
    public abstract void setCenter(Point3D p);
}
