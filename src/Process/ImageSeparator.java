package Process;

import GUI.Algorithms;
import GUI.Techniques;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * @author Omar Lopez Ortega
 */
public class ImageSeparator
{
    //private BufferedImage[] imagenesParciales;// = new BufferedImage[3];//4 para atp?
    //private BufferedImage imagenActual;
    public static int colorSRGB;
    
    public static BufferedImage[] devuelveArregleImagenesParcialesM(BufferedImage imagenClasificada, int tipo)
    {
        int cant=2;
        if (tipo==0 ||tipo==2){
            cant=2;
        }else if(tipo==1){
            cant=3;
        }
        BufferedImage[] imagenesParciales = new BufferedImage[cant];
        Color colorAux;
        int pixel;
        int pixelR, pixelG, pixelB;
        for(int i = 0 ; i < cant; i++)
        {
            imagenesParciales[i] = new BufferedImage(imagenClasificada.getWidth(), imagenClasificada.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        }

        for(int i = 0; i < imagenClasificada.getWidth(); i++)
        {
            for (int j = 0; j < imagenClasificada.getHeight(); j++)
            {
                colorAux = new Color(imagenClasificada.getRGB(i, j));
                
                /*if(colorAux.getRGB() == Color.DARK_GRAY.getRGB())                
                {
                    pixel = Color.DARK_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }                
                if(colorAux.getRGB() == Color.gray.getRGB())
                {
                    pixel = Color.GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                    
                }
                if(colorAux.getRGB() == Color.LIGHT_GRAY.getRGB())
                {
                    pixel = Color.LIGHT_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }*/
                if(colorAux.getRed() == LearnedRGB.getRed(0, tipo) && colorAux.getGreen() == LearnedRGB.getGreen(0, tipo) && colorAux.getBlue() == LearnedRGB.getBlue(0, tipo))
                {
                    pixelR = LearnedRGB.getRed(0, tipo);
                    pixelG = LearnedRGB.getGreen(0, tipo);
                    pixelB = LearnedRGB.getBlue(0, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                if(colorAux.getRed() == LearnedRGB.getRed(1, tipo) && colorAux.getGreen() == LearnedRGB.getGreen(1, tipo) && colorAux.getBlue() == LearnedRGB.getBlue(1, tipo))                
                {
                    pixelR = LearnedRGB.getRed(1, tipo);
                    pixelG = LearnedRGB.getGreen(1, tipo);
                    pixelB = LearnedRGB.getBlue(1, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }                
                /*if(colorAux.getRed() == LearnedRGB.getRed(2, tipo) && colorAux.getGreen() == LearnedRGB.getGreen(2, tipo) && colorAux.getBlue() == LearnedRGB.getBlue(2, tipo))
                {
                    pixelR = LearnedRGB.getRed(2, tipo);
                    pixelG = LearnedRGB.getGreen(2, tipo);
                    pixelB = LearnedRGB.getBlue(2, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                    
                }*/
                
                
                if(tipo==1){
                    if(colorAux.getRed() == LearnedRGB.getRed(2, tipo) && colorAux.getGreen() == LearnedRGB.getGreen(2, tipo) && colorAux.getBlue() == LearnedRGB.getBlue(2, tipo))
                    {
                        pixelR = LearnedRGB.getRed(2, tipo);
                        pixelG = LearnedRGB.getGreen(2, tipo);
                        pixelB = LearnedRGB.getBlue(2, tipo);
                        colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                        imagenesParciales[2].setRGB(i, j, colorSRGB);
                    }
                    else
                    {
                        pixel = Color.WHITE.getRGB();
                        colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                        imagenesParciales[2].setRGB(i, j, colorSRGB);

                    }
                }
                
            }
        }
        return imagenesParciales;
    }
    
    
    
    
    public static BufferedImage[] devuelveArregleImagenesParcialesH(BufferedImage imagenClasificada, int tipo)
    {
        int cant=2;
        if (tipo==0 ||tipo==2){
            cant=2;
        }else if(tipo==1){
            cant=3;
        }
        BufferedImage[] imagenesParciales = new BufferedImage[cant];
        Color colorAux;
        int pixel;
        int pixelR, pixelG, pixelB;
        for(int i = 0 ; i < cant; i++)
        {
            imagenesParciales[i] = new BufferedImage(imagenClasificada.getWidth(), imagenClasificada.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        }

        for(int i = 0; i < imagenClasificada.getWidth(); i++)
        {
            for (int j = 0; j < imagenClasificada.getHeight(); j++)
            {
                colorAux = new Color(imagenClasificada.getRGB(i, j));
                
                /*if(colorAux.getRGB() == Color.DARK_GRAY.getRGB())                
                {
                    pixel = Color.DARK_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }                
                if(colorAux.getRGB() == Color.gray.getRGB())
                {
                    pixel = Color.GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                    
                }
                if(colorAux.getRGB() == Color.LIGHT_GRAY.getRGB())
                {
                    pixel = Color.LIGHT_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }*/
                if(colorAux.getRed() == HumanRGB.getRed(0, tipo) && colorAux.getGreen() == HumanRGB.getGreen(0, tipo) && colorAux.getBlue() == HumanRGB.getBlue(0, tipo))
                {
                    pixelR = HumanRGB.getRed(0, tipo);
                    pixelG = HumanRGB.getGreen(0, tipo);
                    pixelB = HumanRGB.getBlue(0, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                if(colorAux.getRed() == HumanRGB.getRed(1, tipo) && colorAux.getGreen() == HumanRGB.getGreen(1, tipo) && colorAux.getBlue() == HumanRGB.getBlue(1, tipo))                
                {
                    pixelR = HumanRGB.getRed(1, tipo);
                    pixelG = HumanRGB.getGreen(1, tipo);
                    pixelB = HumanRGB.getBlue(1, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }                
                /*if(colorAux.getRed() == HumanRGB.getRed(2, tipo) && colorAux.getGreen() == HumanRGB.getGreen(2, tipo) && colorAux.getBlue() == HumanRGB.getBlue(2, tipo))
                {
                    pixelR = HumanRGB.getRed(2, tipo);
                    pixelG = HumanRGB.getGreen(2, tipo);
                    pixelB = HumanRGB.getBlue(2, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                    
                }*/
                
                
                if(tipo==1){
                    if(colorAux.getRed() == HumanRGB.getRed(2, tipo) && colorAux.getGreen() == HumanRGB.getGreen(2, tipo) && colorAux.getBlue() == HumanRGB.getBlue(2, tipo))
                    {
                        pixelR = HumanRGB.getRed(2, tipo);
                        pixelG = HumanRGB.getGreen(2, tipo);
                        pixelB = HumanRGB.getBlue(2, tipo);
                        colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                        imagenesParciales[2].setRGB(i, j, colorSRGB);
                    }
                    else
                    {
                        pixel = Color.WHITE.getRGB();
                        colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                        imagenesParciales[2].setRGB(i, j, colorSRGB);

                    }
                }
                
            }
        }
        return imagenesParciales;
    }
    
//    public static BufferedImage devuelveImagenParcial(BufferedImage imagenClasificada,Techniques tipo,Algorithms algoritmo,int cluster){
//        BufferedImage imagenRetorno=new BufferedImage(imagenClasificada.getWidth(),imagenClasificada.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
//        Color colorAux;
//        int pixel;
//        int pixelR, pixelG, pixelB;
//        for(int i = 0; i < imagenClasificada.getWidth(); i++)
//        {
//            for (int j = 0; j < imagenClasificada.getHeight(); j++)
//            {
//                colorAux = new Color(imagenClasificada.getRGB(i, j));
//                if(colorAux.getRed() == GlobalKnowledge.getRed(tipo,algoritmo,cluster) && colorAux.getGreen() == GlobalKnowledge.getGreen(tipo,algoritmo,cluster) && colorAux.getBlue() == GlobalKnowledge.getBlue(tipo,algoritmo,cluster))
//                {
//                    pixelR = HumanRGB.getRed(0, tipo);
//                    pixelG = HumanRGB.getGreen(0, tipo);
//                    pixelB = HumanRGB.getBlue(0, tipo);
//                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
//                    imagenRetorno.setRGB(i, j, colorSRGB);
//                }
//                else
//                {
//                    pixel = Color.WHITE.getRGB();
//                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
//                    imagenRetorno.setRGB(i, j, colorSRGB);
//                }
//            }
//        }
//        return imagenRetorno;
//    }
    
    public static BufferedImage[] devuelveArregleImagenesParciales(BufferedImage imagenClasificada, Techniques tipo,Algorithms alg)
    {
        int cant=2;
        if (tipo==Techniques.NADH ||tipo==Techniques.COX){
            cant=2;
        }else if(tipo==Techniques.ATPASA){
            cant=3;
        }
        BufferedImage[] imagenesParciales = new BufferedImage[cant];
        Color colorAux;
        int pixel;
        int pixelR, pixelG, pixelB;
        for(int i = 0 ; i < cant; i++)
        {
            imagenesParciales[i] = new BufferedImage(imagenClasificada.getWidth(), imagenClasificada.getHeight(),BufferedImage.TYPE_3BYTE_BGR);
        }

        for(int i = 0; i < imagenClasificada.getWidth(); i++)
        {
            for (int j = 0; j < imagenClasificada.getHeight(); j++)
            {
                colorAux = new Color(imagenClasificada.getRGB(i, j));
                
                /*if(colorAux.getRGB() == Color.DARK_GRAY.getRGB())                
                {
                    pixel = Color.DARK_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }                
                if(colorAux.getRGB() == Color.gray.getRGB())
                {
                    pixel = Color.GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                    
                }
                if(colorAux.getRGB() == Color.LIGHT_GRAY.getRGB())
                {
                    pixel = Color.LIGHT_GRAY.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }*/
                if(colorAux.getRed() == GlobalKnowledge.getRed(tipo,alg,0) && colorAux.getGreen() == GlobalKnowledge.getGreen(tipo,alg,0) && colorAux.getBlue() == GlobalKnowledge.getBlue(tipo,alg,0))
                {
                    pixelR = GlobalKnowledge.getRed(tipo,alg,0);
                    pixelG = GlobalKnowledge.getGreen(tipo,alg,0);
                    pixelB = GlobalKnowledge.getBlue(tipo,alg,0);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                        //colorSRGB = (pixel << 16) | (pixel << 8) | pixel;     
                        colorSRGB=Color.WHITE.getRGB();
                        imagenesParciales[0].setRGB(i, j, colorSRGB);
                }
                if(colorAux.getRed() == GlobalKnowledge.getRed(tipo,alg,1) && colorAux.getGreen() == GlobalKnowledge.getGreen(tipo,alg,1) && colorAux.getBlue() == GlobalKnowledge.getBlue(tipo,alg,1))                
                {
                    pixelR = GlobalKnowledge.getRed(tipo,alg,1);
                    pixelG = GlobalKnowledge.getGreen(tipo,alg,1);
                    pixelB = GlobalKnowledge.getBlue(tipo,alg,1);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;                    
                    imagenesParciales[1].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                        //colorSRGB = (pixel << 16) | (pixel << 8) | pixel;     
                        colorSRGB=Color.WHITE.getRGB();
                        imagenesParciales[1].setRGB(i, j, colorSRGB);
                }                
                /*if(colorAux.getRed() == HumanRGB.getRed(2, tipo) && colorAux.getGreen() == HumanRGB.getGreen(2, tipo) && colorAux.getBlue() == HumanRGB.getBlue(2, tipo))
                {
                    pixelR = HumanRGB.getRed(2, tipo);
                    pixelG = HumanRGB.getGreen(2, tipo);
                    pixelB = HumanRGB.getBlue(2, tipo);
                    colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                }
                else
                {
                    pixel = Color.WHITE.getRGB();
                    colorSRGB = (pixel << 16) | (pixel << 8) | pixel;                    
                    imagenesParciales[2].setRGB(i, j, colorSRGB);
                    
                }*/
                
                
                if(tipo==Techniques.ATPASA){
                    if(colorAux.getRed() == GlobalKnowledge.getRed(tipo,alg,2) && colorAux.getGreen() == GlobalKnowledge.getGreen(tipo,alg,2) && colorAux.getBlue() == GlobalKnowledge.getBlue(tipo,alg,2))
                    {
                        pixelR = GlobalKnowledge.getRed(tipo,alg,2);
                        pixelG = GlobalKnowledge.getGreen(tipo,alg,2);
                        pixelB = GlobalKnowledge.getBlue(tipo,alg,2);
                        colorSRGB = (pixelR << 16) | (pixelG << 8) | pixelB;
                        imagenesParciales[2].setRGB(i, j, colorSRGB);
                    }
                    else
                    {
                        pixel = Color.WHITE.getRGB();
                        //colorSRGB = (pixel << 16) | (pixel << 8) | pixel;     
                        colorSRGB=Color.WHITE.getRGB();
                        imagenesParciales[2].setRGB(i, j, colorSRGB);

                    }
                }
                
            }
        }
        return imagenesParciales;
    }
    
}
