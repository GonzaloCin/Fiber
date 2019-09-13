/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

/**
 * @author Omar López Ortega
 */
public class ClassifiedImage
{
    private int cantidadClase;
    private int total,height,width;
    private int[] cantidades= new int[5];
    private int[] totales;
    private double[] porcentajes;
    
    private int calculaCantidadClase(int[][]MatrizClases, int tipoClase)
    {
        
        cantidadClase = 0;
        if(tipoClase !=0 && tipoClase != 4)
        {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(MatrizClases[i][j] == tipoClase)
                {
                    cantidadClase++;                    
                }
            }
        }
        }
        return cantidadClase;
    }
    private int[] calculaCantidadClase(int[][]MatrizClases)
    {
        for(int k = 1; k < 4; k++)
        {
            cantidades[k] = this.calculaCantidadClase(MatrizClases, k);
        }       
        return cantidades;
    }
    private double[] calculaPorcentaje(int[][]MatrizClases)
    {
        porcentajes = new double[5];
        totales = this.calculaCantidadClase(MatrizClases);
        total = totales[1]+totales[2]+totales[3];
        for(int i = 1 ; i< 4; i++)
        {            
            porcentajes[i] = ((double)totales[i]/(double)(total))*100;                       
        }
        return porcentajes;
    }
    public int obtenerCantidadPorClase(int[][]MatrizClases, int tipoClase)
    {
        return this.calculaCantidadClase(MatrizClases, tipoClase);
    }
    public int[] obtenerCantidadPorClase(int[][] M, int width, int height)
    {
        this.width=width;
        this.height=height;
        return this.calculaCantidadClase(M);
    }
    public double[] obtenerPorcentajes(int[][]M)
    {
        return this.calculaPorcentaje(M);
    }
}
