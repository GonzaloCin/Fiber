/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Gonzalo
 */
public class Histogram {
    
    //ArrayList<Point3D> points;
    //File selectedImage;
    
    //public BufferedImage Image;
    //String name;

    public static void drawHistogram(int[] data,JPanel panel,Color colorbarras){
        DefaultCategoryDataset basedatos= new DefaultCategoryDataset();
        String serie= "Numero de Pixeles";
        for(int i=0; i<data.length-1;i++){
                basedatos.addValue(data[i], serie, ""+i);
        }
        JFreeChart chart=ChartFactory.createBarChart(null, null,null, basedatos, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot=(CategoryPlot) chart.getPlot();
        BarRenderer renderer=(BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0,colorbarras);
        chart.setAntiAlias(true);
        chart.setBackgroundPaint(new Color(214,217,223));
        panel.removeAll();
        panel.repaint();
        panel.setLayout(new java.awt.BorderLayout());
        panel.add(new ChartPanel(chart));
        panel.validate();
    }
    
    
}
 