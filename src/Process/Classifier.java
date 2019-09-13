package Process;

import GUI.Algorithms;
import GUI.Techniques;
import Grouping.Calculus;
import Images.AbstractImage;
import Images.SeparatedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
//import java.util.ArrayList;
//probar static
public class Classifier //porque se extiene de Abstract Image?
{   
    private final float Treshold=0.45f;
    private BufferedImage grayScaleImage;
    private SeparatedImage grayScaleImageSI;
    public int colorSRGB, c,r,g,bl;
    public int[][] MatrizClases;
    //public float [][] MembershipMatrix;
    int Mheight,Mwidth;
    ClassifiedImage stats;
    CoordinateMapper t;
    DistanceCalculator calc;
    AbstractImage a;
    double cons;
 
    private BufferedImage escalaGrisesCoorEsf(BufferedImage b,int tipo)
    {   
        ReferenceSetter.defineMetricasRadianes(tipo);
        t = new CoordinateMapper();
        calc = new DistanceCalculator();        
        double metricaImagen, distAnterior, distActual, distMin;        
        grayScaleImage = b;
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                rIFuente = colorAux.getRed();
                gIFuente = colorAux.getGreen();
                bIFuente = colorAux.getBlue();
                //Se obtienen las metricas en coordenadas esféricas: radio*teta*phi
                //metricaImagen = t.obtenerMetricaGrados(rIFuente, gIFuente, bIFuente);
                metricaImagen = t.obtenerMetricaRadianes(rIFuente, gIFuente, bIFuente);
                //se calculas las distancias empleando la metrica seleccionada
                distAnterior = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[0]);
                //distAnterior = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[0]);
                //
                //si no se desea emplear la metrica radio*teta*phi
                //es posible calcular los diferencias delta por cada variable
                //delta del radio, delta de teta, delta de phi
                //se tienen diversos tipos de distancias
                //distAnterior = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, 0);
                //System.out.print(distAnterior);
                //distAnterior = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, 0);
                //MatrizMetricas[i][j] = metricaImagen; 
                c = 0;
                for(int k = 1; k < 4; k++)
                {                                    
                    //distActual = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[k]);
                    distActual = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[k]);
                    //
                    //distActual = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, k);
                    //System.out.print(" " + distActual + " ");
                    //distActual = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, k);
                    distMin = Math.min(distAnterior, distActual);
                    if(distMin == distActual)
                    {
                        c = k;                        
                    }                                        
                    distAnterior=distActual;                    
                }
                //System.out.println();
                MatrizClases[i][j] = c;                
                //System.out.print(" " + c + " ");
                //System.out.println();
                pixelR = ReferenceSetter.obtenRed(c);
                pixelG = ReferenceSetter.obtenGreen(c);
                pixelB = ReferenceSetter.obtenBlue(c);                
                //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                grayScaleImage.setRGB(i, j,colorSRGB);               
            }
            //System.out.println();
        }
        //System.out.println();
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
    }     
     public BufferedImage calculaEscGrisCoorEsf(BufferedImage sourceI,int tipo)
    {
        return this.escalaGrisesCoorEsf(sourceI,tipo);
    }
     
    public BufferedImage calculaEscGrisEucl(BufferedImage sourceI,int tipo)
    {   BufferedImage aux=null;
        if(LearnedRGB.isAsigned(tipo)){
            aux=this.escalaGrisesEuclM(sourceI,tipo);
        } else if(HumanRGB.isAsigned(tipo)){
            aux=this.escalaGrisesEuclH(sourceI,tipo);   
        }
        return aux;
    }
    
    
    public BufferedImage homogenizeFCM(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        ReferenceSetter.defineMetricasFCM(tincion,algoritmo);
        int numclusters=ReferenceSetter.centrosFCM.size();
        
        grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        Graphics2D gr = grayScaleImage.createGraphics();
       gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       gr.dispose();
       
       
       Mheight=grayScaleImage.getHeight();
       Mwidth=grayScaleImage.getWidth();
       //MembershipMatrix= new float[numclusters][Mheight*Mwidth];
       MatrizClases = new int[Mwidth][Mheight];
       float auxMembership=0f;
       int pixelR, pixelG, pixelB;
       Color colorAux;
       for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                
                for(int d=0;d<numclusters;d++){
                    auxMembership=Calculus.membership(colorAux,ReferenceSetter.centrosFCM,d,2f);
                    //MembershipMatrix[d][i*Mheight+j]=auxMembership;
                    if(auxMembership>=this.Treshold){
                        MatrizClases[i][j] = d;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(d);
                    pixelG = ReferenceSetter.obtenGreen(d);
                    pixelB = ReferenceSetter.obtenBlue(d);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);  
                    }else{
                        MatrizClases[i][j] = 5;  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    //grayScaleImage.setRGB(i, j,Color.WHITE.getRGB());
                    }
                }
                
                
            }
        }
            
       return grayScaleImage;
    }
    
    
    public BufferedImage fasthomogenizeFCM(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        ReferenceSetter.defineMetricasFCM(tincion,algoritmo);
        int numclusters=ReferenceSetter.centrosFCM.size();
       final byte [] original;
       original= ((DataBufferByte) sourceI.getRaster().getDataBuffer()).getData();
       
       Mheight=sourceI.getHeight();
       Mwidth=sourceI.getWidth();
        grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
       byte resultado[]=new byte[3*Mheight*Mwidth];
       
       for(int p=0;p<3*Mheight*Mwidth;p++){
           resultado[p]=(byte)255;
       }
       
       int indice=0;
       //MembershipMatrix= new float[numclusters][Mheight*Mwidth];
       //MatrizClases = new int[Mwidth][Mheight];
       float auxMembership=0f;
       //int pixelR, pixelG, pixelB;
       short[] colorAux={0,0,0};
       short Msk=255;
       for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {   indice=(j*Mwidth*3)+i*3;
                colorAux[2]=(short)(original[indice] & Msk);
                colorAux[1]=(short)(original[indice+1] & Msk);
                colorAux[0]=(short)(original[indice+2] & Msk);
                
                for(int d=0;d<numclusters;d++){
                    auxMembership=Calculus.membership(colorAux,ReferenceSetter.centrosFCM,d,2f);
                    //MembershipMatrix[d][i*Mheight+j]=auxMembership;
                    if(auxMembership>=this.Treshold){
                        resultado[indice+2]=(byte)ReferenceSetter.obtenRed(d);
                        resultado[indice+1]=(byte)ReferenceSetter.obtenGreen(d);
                        resultado[indice]=(byte)ReferenceSetter.obtenBlue(d);
                    }else{
                        //resultado[indice+2]=(byte)255;
                        //resultado[indice+1]=(byte)255;
                        //resultado[indice]=(byte)255;
                        //MatrizClases[i][j] = 5;  
                    }
                }
                
                
            }
        }
       grayScaleImage.setData(Raster.createRaster(sourceI.getSampleModel(), new DataBufferByte(resultado, resultado.length), new Point() ) );     
       return grayScaleImage;
    }
    
        
    public BufferedImage[] homogenizeMemberhsip(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        ReferenceSetter.defineMetricasFCM(tincion,algoritmo);
        int numclusters=ReferenceSetter.centrosFCM.size();
        
        BufferedImage[] imagenesParciales = new BufferedImage[numclusters];
        for(int i = 0 ; i < numclusters; i++)
        {
            imagenesParciales[i] = new BufferedImage(sourceI.getWidth(), sourceI.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        }
        //grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        //Graphics2D gr = grayScaleImage.createGraphics();
       //gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       //gr.dispose();
       Mheight=sourceI.getHeight();
       Mwidth=sourceI.getWidth();
       //MembershipMatrix= new float[numclusters][Mheight*Mwidth];
       MatrizClases = new int[Mwidth][Mheight];
       float auxMembership=0f;
       int pixelR, pixelG, pixelB;
       Color colorAux;
       for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                colorAux=new Color(sourceI.getRGB(i, j));
                
                for(int d=0;d<numclusters;d++){
                    auxMembership=Calculus.membership(colorAux,ReferenceSetter.centrosFCM,d,2f);
                    //MembershipMatrix[d][i*Mheight+j]=auxMembership;
                    if(auxMembership>=this.Treshold){
                        MatrizClases[i][j] = d;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(d);
                    pixelG = ReferenceSetter.obtenGreen(d);
                    pixelB = ReferenceSetter.obtenBlue(d);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    imagenesParciales[d].setRGB(i, j,colorSRGB);  
                    }else{
                        MatrizClases[i][j] = 5;
                        imagenesParciales[d].setRGB(i, j,Color.WHITE.getRGB());  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    //grayScaleImage.setRGB(i, j,Color.WHITE.getRGB());
                    }
                }
                
                
            }
        }
            
       return imagenesParciales;
    }
    
    public SeparatedImage[] fasthomogenizeMemberhsip(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        ReferenceSetter.defineMetricasFCM(tincion,algoritmo);
        int numclusters=ReferenceSetter.centrosFCM.size();
        final byte [] original;
       original= ((DataBufferByte) sourceI.getRaster().getDataBuffer()).getData();
        SeparatedImage[] imagenesParciales = new SeparatedImage[numclusters+1];
        byte[][] aux1=new byte[numclusters][3];
        
        for(int i = 0 ; i < numclusters; i++){
            aux1[i][0]=(byte)ReferenceSetter.centrosFCM.get(i).getZ();
            aux1[i][1]=(byte)ReferenceSetter.centrosFCM.get(i).getY();
            aux1[i][2]=(byte)ReferenceSetter.centrosFCM.get(i).getX();
        }
        imagenesParciales[0] = new SeparatedImage(sourceI.getWidth(), sourceI.getHeight(),aux1,sourceI.getSampleModel());
        
        for(int i = 1 ; i < numclusters+1; i++)
        {   
            byte [][] auxcol;
            auxcol = new byte [2][3];
            auxcol[0][0]=(byte)255;auxcol[0][1]=(byte)255;auxcol[0][2]=(byte)255;
            auxcol[1][0]=(byte)ReferenceSetter.centrosFCM.get(i-1).getZ();
            auxcol[1][1]=(byte)ReferenceSetter.centrosFCM.get(i-1).getY();
            auxcol[1][2]=(byte)ReferenceSetter.centrosFCM.get(i-1).getX();
            imagenesParciales[i] = new SeparatedImage(sourceI.getWidth(), sourceI.getHeight(),auxcol,sourceI.getSampleModel());
        }
        //grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        //Graphics2D gr = grayScaleImage.createGraphics();
       //gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       //gr.dispose();
       Mheight=sourceI.getHeight();
       Mwidth=sourceI.getWidth();
       //MembershipMatrix= new float[numclusters][Mheight*Mwidth];
       //MatrizClases = new int[Mwidth][Mheight];
       float auxMembership=0f;
       float maxMembership=0f;
       short[] colorAux={0,0,0};
       short Msk=255;
       int indice=0;
       byte max=0;
       for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                indice=(j*Mwidth*3)+i*3;
                colorAux[2]=(short)(original[indice] & Msk);
                colorAux[1]=(short)(original[indice+1] & Msk);
                colorAux[0]=(short)(original[indice+2] & Msk);
                maxMembership=0f;
                for(byte d=0;d<numclusters;d++){
                    auxMembership=Calculus.membership(colorAux,ReferenceSetter.centrosFCM,d,2f);
                    //MembershipMatrix[d][i*Mheight+j]=auxMembership;
                    if(auxMembership>=this.Treshold){
                        imagenesParciales[d+1].setColored(i,j,(byte)1);  
                    }
                    if(auxMembership>=maxMembership){
                        maxMembership=auxMembership;
                        max=d;
                    }
                }
                imagenesParciales[0].setColored(i, j, (byte)max);
            }
        }
            
       return imagenesParciales;
    }
    
    
   
    
    
    
    public BufferedImage homogenize3(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        cons=5;
        ReferenceSetter.defineMetricasEuclideana(tincion,algoritmo);
        calc = new DistanceCalculator();        
        double distActual, distMin;
        grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        Graphics2D gr = grayScaleImage.createGraphics();
       gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       gr.dispose();
        //grayScaleImage = sourceI.getSubimage(0, 0, sourceI.getHeight(), sourceI.getWidth());
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                rIFuente = colorAux.getRed();
                        r=0;
                        distMin = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[0].getRed());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[k].getRed());
                            if(distMin > distActual)
                            {   
                                distMin=distActual;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][0]){
                                   r = k;
                                }else{
                                    r=10;
                                }  
                            }                                                          
                        }
                gIFuente = colorAux.getGreen();
                        g=0;
                        distMin = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[0].getGreen());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[k].getGreen());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //g = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][1]){
                                   g = k;
                                }else{
                                    g=11;
                                }
                            }                                                     
                        }
                bIFuente = colorAux.getBlue();
                        bl =0;
                        distMin = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[0].getBlue());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[k].getBlue());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //bl = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][2]){
                                   bl = k;
                                }else{
                                    bl=12;
                                }
                            }                                                      
                        }
                c = 0;
                
                if(r==g && g==bl){
                    c=r;
                    //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);  
                }
                else{
                    MatrizClases[i][j] = 5;  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    grayScaleImage.setRGB(i, j,Color.WHITE.getRGB()); 
                }                     
            }
        }
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
        
    }
    
    
    public BufferedImage homogenize2(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        cons=5;
        ReferenceSetter.defineMetricasEuclideana(tincion,algoritmo);
        calc = new DistanceCalculator();        
        double distActual, distMin;
        grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        Graphics2D gr = grayScaleImage.createGraphics();
       gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       gr.dispose();
        //grayScaleImage = sourceI.getSubimage(0, 0, sourceI.getHeight(), sourceI.getWidth());
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                rIFuente = colorAux.getRed();
                        r=0;
                        distMin = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[0].getRed());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[k].getRed());
                            if(distMin > distActual)
                            {   
                                distMin=distActual;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][0]){
                                   r = k;
                                }else{
                                    r=10;
                                }  
                            }                                                          
                        }
                gIFuente = colorAux.getGreen();
                        g=0;
                        distMin = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[0].getGreen());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[k].getGreen());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //g = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][1]){
                                   g = k;
                                }else{
                                    g=11;
                                }
                            }                                                     
                        }
                bIFuente = colorAux.getBlue();
                        bl =0;
                        distMin = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[0].getBlue());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[k].getBlue());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //bl = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][2]){
                                   bl = k;
                                }else{
                                    bl=12;
                                }
                            }                                                      
                        }
                c = 0;
                
                if(r==g|| g==bl || r==bl){
                    if(r==g && r<5){
                        c=r;
                        //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);
                    }
                    else if(g==bl && g<5){
                        c=g;
                        //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);
                    }
                    else if(r==bl && r<5){
                        c=r;
                                //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);
                    }
                    
                    /*
                    //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);
                    */
                }
                else{
                    MatrizClases[i][j] = 5;  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    grayScaleImage.setRGB(i, j,Color.WHITE.getRGB()); 
                }                     
            }
        }
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
        
    }
    
    public BufferedImage homogenizemin(BufferedImage sourceI,Techniques tincion,Algorithms algoritmo){
        cons=5;
        ReferenceSetter.defineMetricasEuclideana(tincion,algoritmo);
        calc = new DistanceCalculator();        
        double distActual, distMin;
        grayScaleImage=new BufferedImage(sourceI.getWidth(),sourceI.getHeight(),sourceI.getType());
        Graphics2D gr = grayScaleImage.createGraphics();
       gr.drawImage(sourceI, 0, 0, sourceI.getWidth(),sourceI.getHeight(), null);
       gr.dispose();
        //grayScaleImage = sourceI.getSubimage(0, 0, sourceI.getHeight(), sourceI.getWidth());
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                        c=0;
                        distMin = Calculus.distance(colorAux, ReferenceSetter.centros[0]);
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = Calculus.distance(colorAux, ReferenceSetter.centros[k]);
                            if(distMin > distActual)
                            {   
                                distMin=distActual;
                                if(distMin<=cons*Math.max(ReferenceSetter.desviaciones[k][0],Math.max(ReferenceSetter.desviaciones[k][1],ReferenceSetter.desviaciones[k][2]))){
                                   c = k;
                                }else{
                                    c=10;
                                }  
                            }                                                          
                        }
                
                //c = 0;
                
                if(c!=10){
                    //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);  
                }
                else{
                    MatrizClases[i][j] = 5;  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    grayScaleImage.setRGB(i, j,Color.WHITE.getRGB()); 
                }                     
            }
        }
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
        
    }
    
    
    
     
    private BufferedImage escalaGrisesEuclH(BufferedImage b,int tipo)
    {                
        //ReferenceSetter.defineMetricasRadianes(tipo);
        cons=4;
        ReferenceSetter.defineMetricasEuclideana(tipo);
        //t = new CoordinateMapper();
        calc = new DistanceCalculator();        
        double metricaImagen, distAnterior, distActual, distMin;        
        grayScaleImage = b;
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                rIFuente = colorAux.getRed();
                        r=0;
                        distMin = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[0].getRed());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[k].getRed());
                            if(distMin > distActual)
                            {   
                                distMin=distActual;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][0]){
                                   r = k;
                                }else{
                                    r=10;
                                }  
                            }                                                          
                        }
                gIFuente = colorAux.getGreen();
                        g=0;
                        distMin = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[0].getGreen());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[k].getGreen());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //g = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][1]){
                                   g = k;
                                }else{
                                    g=11;
                                }
                            }                                                     
                        }
                bIFuente = colorAux.getBlue();
                        bl =0;
                        distMin = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[0].getBlue());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[k].getBlue());
                            if(distMin > distActual)
                            {
                                distMin=distActual;
                                //bl = k;
                                if(distMin<=cons*ReferenceSetter.desviaciones[k][2]){
                                   bl = k;
                                }else{
                                    bl=12;
                                }
                            }                                                      
                        }
                //Se obtienen las metricas en coordenadas esféricas: radio*teta*phi
                //metricaImagen = t.obtenerMetricaGrados(rIFuente, gIFuente, bIFuente);
                ////////metricaImagen = t.obtenerMetricaRadianes(rIFuente, gIFuente, bIFuente);
                //se calculas las distancias empleando la metrica seleccionada
                ////////distAnterior = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[0]);
                //distAnterior = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[0]);
                //
                //si no se desea emplear la metrica radio*teta*phi
                //es posible calcular los diferencias delta por cada variable
                //delta del radio, delta de teta, delta de phi
                //se tienen diversos tipos de distancias
                //distAnterior = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, 0);
                //System.out.print(distAnterior);
                //distAnterior = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, 0);
                //MatrizMetricas[i][j] = metricaImagen; 
                c = 0;
                
                
                /*for(int k = 1; k < 4; k++)
                {                                    
                    //distActual = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[k]);
                    distActual = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[k]);
                    //
                    //distActual = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, k);
                    //System.out.print(" " + distActual + " ");
                    //distActual = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, k);
                    distMin = Math.min(distAnterior, distActual);
                    if(distMin == distActual)
                    {
                        c = k;                        
                    }                                        
                    distAnterior=distActual;                    
                }*/
                
                if(r==g && g==bl){
                    c=r;
                    //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);  
                }
                else{
                    MatrizClases[i][j] = 5;  
                    ///colorSRGB=(255 << 16) | (255 << 8) | 255;
                    grayScaleImage.setRGB(i, j,Color.WHITE.getRGB()); 
                }                     
            }
            //System.out.println();
        }
        //System.out.println();
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
    }
    
    private BufferedImage escalaGrisesEuclM(BufferedImage b,int tipo)//necesito una nueva funcion con parametro: algotitmo
    {                
        //ReferenceSetter.defineMetricasRadianes(tipo);
        ReferenceSetter.defineMetricasEuclideana(tipo);//usar aqui el parametro algoritmo     ///Debo seguir usando refferencesetter??????
        //t = new CoordinateMapper();
        calc = new DistanceCalculator();        
        double metricaImagen, distAnterior, distActual, distMin;        
        grayScaleImage = b;
        Mheight=grayScaleImage.getHeight();
        Mwidth=grayScaleImage.getWidth();        
        int rIFuente, gIFuente, bIFuente, pixelR, pixelG, pixelB;        
        Color colorAux;
        //MatrizMetricas = new double[grayScaleImage.getTileWidth()][grayScaleImage.getHeight()];
        MatrizClases = new int[Mwidth][Mheight];
        //Recorremos la imagen píxel a píxel        
        for( int i = 0; i < Mwidth; i++ )
        {
            for( int j = 0; j < Mheight; j++ )
            {
                //Almacenamos el color del píxel                
                colorAux=new Color(grayScaleImage.getRGB(i, j));
                //Se obtienen los tres colores (rojo, verde, azul)
                rIFuente = colorAux.getRed();
                        r=0;
                        distAnterior = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[0].getRed());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(rIFuente, ReferenceSetter.centros[k].getRed());
                            distMin = Math.min(distAnterior, distActual);
                            if(distMin == distActual)
                            {   
                                   r = k;
                            }                                        
                            distAnterior=distActual;                    
                        }
                gIFuente = colorAux.getGreen();
                        g=0;
                        distAnterior = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[0].getGreen());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(gIFuente, ReferenceSetter.centros[k].getGreen());
                            distMin = Math.min(distAnterior, distActual);
                            if(distMin == distActual)
                            {
                                   g = k;
                            }                                        
                            distAnterior=distActual;                    
                        }
                bIFuente = colorAux.getBlue();
                        bl =0;
                        distAnterior = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[0].getBlue());
                        for(int k = 1; k < 4; k++)
                        {                                    
                            distActual = calc.obtenerDistanciaLineal(bIFuente, ReferenceSetter.centros[k].getBlue());
                            distMin = Math.min(distAnterior, distActual);
                            if(distMin == distActual)
                            {
                                bl = k;
                            }                                        
                            distAnterior=distActual;                    
                        }
                //Se obtienen las metricas en coordenadas esféricas: radio*teta*phi
                //metricaImagen = t.obtenerMetricaGrados(rIFuente, gIFuente, bIFuente);
                ////////metricaImagen = t.obtenerMetricaRadianes(rIFuente, gIFuente, bIFuente);
                //se calculas las distancias empleando la metrica seleccionada
                ////////distAnterior = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[0]);
                //distAnterior = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[0]);
                //
                //si no se desea emplear la metrica radio*teta*phi
                //es posible calcular los diferencias delta por cada variable
                //delta del radio, delta de teta, delta de phi
                //se tienen diversos tipos de distancias
                //distAnterior = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, 0);
                //System.out.print(distAnterior);
                //distAnterior = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, 0);
                //distAnterior = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, 0);
                //MatrizMetricas[i][j] = metricaImagen; 
                c = 0;
                
                
                /*for(int k = 1; k < 4; k++)
                {                                    
                    //distActual = calc.obtenerDistanciaCuadratica(metricaImagen, ReferenceSetter.metricas[k]);
                    distActual = calc.obtenerDistanciaLineal(metricaImagen, ReferenceSetter.metricas[k]);
                    //
                    //distActual = calc.obtenerDistanciaDeltaC(rIFuente, gIFuente, bIFuente, k);
                    //System.out.print(" " + distActual + " ");
                    //distActual = calc.obtenerDistanciaDeltaMC(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaDiverg(rIFuente, gIFuente, bIFuente, k);
                    //distActual = calc.obtenerDistanciaDeltaL(rIFuente, gIFuente, bIFuente, k);
                    distMin = Math.min(distAnterior, distActual);
                    if(distMin == distActual)
                    {
                        c = k;                        
                    }                                        
                    distAnterior=distActual;                    
                }*/
                
                if(r==g && g==bl){
                    c=r;
                    //System.out.println();
                    MatrizClases[i][j] = c;                
                    //System.out.print(" " + c + " ");
                    //System.out.println();
                    pixelR = ReferenceSetter.obtenRed(c);
                    pixelG = ReferenceSetter.obtenGreen(c);
                    pixelB = ReferenceSetter.obtenBlue(c);                
                    //System.out.println(pixel + " " + ((pixel << 16) | (pixel << 8) | pixel));                
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    //imagenEscalaGrises.setRGB(i, j, new Color(pixel, pixel, pixel).getRGB());
                    grayScaleImage.setRGB(i, j,colorSRGB);  
                }
                else{
                    MatrizClases[i][j] = 5;  
                    colorSRGB=(255 << 16) | (255 << 8) | 255;
                    grayScaleImage.setRGB(i, j,colorSRGB); 
                }
                
                
                             
            }
            //System.out.println();
        }
        //System.out.println();
        this.guardaMatrizClases(MatrizClases);
        this.obtenConteoClases();        
        return grayScaleImage;
    }
     
     
    
     public void guardaMatrizClases(int[][] M)
    {
        M = this.MatrizClases;
    }
    public int[][] devuelveMatrizClases()
    {
        return this.MatrizClases;
    }
     //El méetodo obtenConteoClases sólo obiene los porcentajes de cada tipo de fibra
     public void obtenConteoClases()
    {
        int[] cantidad;
        double[] porcentajes;
        stats = new ClassifiedImage();
        cantidad= stats.obtenerCantidadPorClase(this.devuelveMatrizClases(),Mwidth,Mheight);
        porcentajes = stats.obtenerPorcentajes(this.devuelveMatrizClases());
        
        String []  tipos={"Obscura","Media","Clara"};
        for(int i = 1; i < 4; i++)
        {
            System.out.println("Componentes de fibra tipo " + tipos[i-1] + ": " + cantidad[i] + " porcentaje " + porcentajes[i]);            
        }
    }  
}
