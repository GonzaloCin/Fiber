package Muscular;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import Process.MuscularFiber2;
import Images.AbstractImage;
/**
 *
 * @author GustavoLeonardo
 */
public class PruebaMuscular {
    
    public static void main(String[] args) throws IOException {
        AbstractImage a = new AbstractImage();
        a.open_Image();
        MuscularFiber2 mf = new MuscularFiber2(a.originalImage);
        
        mf.cargarArreglo();
        
        System.out.println(mf.primer_conteo());
        
    }
    
}
