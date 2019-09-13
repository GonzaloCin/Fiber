package Images;
import Grouping.Calculus;
import Threads.BlackBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gustavo Leonardo Castañeda Martinez
 * @author Omar López Ortega 
 */
public class AbstractImage 
{
    private BufferedImage sourceImage;
    public BufferedImage resizedImage;
    public BufferedImage originalImage;
    public File bi;
    //public BufferedImage byteImage;

   /* public void setBytesImage(int [][] clusters, int nClusters){
        byteImage = new BufferedImage(700,700,BufferedImage.TYPE_BYTE_BINARY);
        for(int i=0,;i<=nClusters;i++){
            binaria.setRGB((int)clusters[i][0],(int)clusters[i][1] ,Color.BLACK.getRGB());   
        }
        guardarTiff(this.byteImage);
    }
    */
    
    public BufferedImage getResizedImage()
    {
        return this.resizedImage;
    }

    public void setResizedImage(BufferedImage imagenRedimensionada) 
    {
        //this.resizedImage = imagenRedimensionada;
        //int type = resizedImage.getType();
        //int type = imagenRedimensionada.getType();
        //resizedImage = new BufferedImage(1200, 1200, type);
        //Graphics2D g = resizedImage.createGraphics();
        //g.drawImage(imagenRedimensionada, 0, 0, 1200, 1200, null);
        //g.dispose();
        resizedImage=Calculus.Clone(imagenRedimensionada);
    }

    public BufferedImage getOriginalImage()
    {
        return originalImage;
    }

    public void setOriginalImage(BufferedImage imagenFuente)
    {
        //BufferedImage bi= new BufferedImage(imagenFuente.getWidth(),imagenFuente.getHeight(),BufferedImage.TYPE_INT_RGB);
        //Graphics g = bi.createGraphics();
        //imagenFuente.(null, g, 0, 0);
        //g.dispose();
        //this.originalImage = bi;
        this.originalImage = Calculus.Clone(imagenFuente);
    }
    
    public void setOriginalImage(Icon imagenfuente) 
    {
        BufferedImage bi= new BufferedImage(imagenfuente.getIconWidth(),imagenfuente.getIconHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        imagenfuente.paintIcon(null, g, 0, 0);
        g.dispose();
        this.originalImage = bi;
    }
    
    private BufferedImage openImage()
    {
        BufferedImage ret = null;
        //Creamos la variable que será devuelta (la creamos como null)
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & TIFF & PNG", "jpg", "gif", "bmp","png","tif","tiff");
        selector.setFileFilter(filtroImagen);
        
        if(BlackBoard.getImagePath() != null){
            selector.setCurrentDirectory(BlackBoard.getImagePath());
        }
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION)
        {   
            try
            {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                BlackBoard.setFileName(selector.getSelectedFile().getName());
                //Asignamos a la variable bmp la imagen leida
                ret = ImageIO.read(imagenSeleccionada);
                
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }       
        }  
        return ret;
    }  
    
    private void resizeImage(BufferedImage b)
    {
        //Código modificado por Omar López Ortega
        //Código Original tomado de http://www.mkyong.com/java/how-to-resize-an-image-in-java/
        //Autor Original Mkyong
       int alto = (int)(b.getHeight() * .7);
       int ancho = (int)(b.getWidth() * .7);
       resizedImage = b;
       int type = resizedImage.getType();
       resizedImage = new BufferedImage(ancho, alto, type);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(b, 0, 0, ancho, alto, null);
       g.dispose();
    }
    
    public void resizeImage(BufferedImage b, double porcentaje)
    {
        //Código modificado por Omar López Ortega
        //Código modificado por Gustavo Leonardo Castañeda Martinez
        //Código Original tomado de http://www.mkyong.com/java/how-to-resize-an-image-in-java/
        //Autor Original Mkyong
       int height = (int)(b.getHeight() * porcentaje);
       int width = (int)(b.getWidth() * porcentaje);
       resizedImage = b;
       int type = resizedImage.getType();
       resizedImage = new BufferedImage(width, height, type);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(b, 0, 0, width, height, null);
       g.dispose();
    }
    
    public void open_Image()
    {
        this.sourceImage=openImage();
        setOriginalImage(this.sourceImage);
        
        //resizeImage(this.getOriginalImage());
        //setResizedImage(this.resizedImage);        
    }
    
    public static void guardar(BufferedImage a)//copiado a estadistica celulas
    {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen1 = new FileNameExtensionFilter("PNG", "png");
        chooser.setFileFilter(filtroImagen1);
        if(BlackBoard.getImagePath() != null){
            chooser.setCurrentDirectory(BlackBoard.getImagePath());
        }
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
        try
        {
            File bi = new File(chooser.getSelectedFile()+".png");
            ImageIO.write(a,"png", bi);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        }
    }
    
    public static byte[][] toBinary(BufferedImage image){
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster() != null;
      byte[][] result = new byte[height][width];
      if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+","+pixels[pixel + 3]+")");
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+","+pixels[pixel + 3]+")");
            
            //argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            //argb += ((int) pixels[pixel + 1] & 0xff); // blue
            //argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            //argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            
            if(((short)((int) pixels[pixel] & 0xff))== 255){
               result[row][col] = 0;
            }else{
                result[row][col]=1;
            }
            
            
            col++;
           if (col == width) {
               col = 0;
               //System.out.print("\n");
               row++;
            }
         }
      } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+")");
            //System.out.print(" ("+((int) pixels[pixel] & 0xff)+","+(((int) pixels[pixel + 1] & 0xff) << 8)/256+","+(((int) pixels[pixel + 2] & 0xff) << 16)/(256*256)+")");
            
            //argb += -16777216; // 255 alpha
            //argb += ((int) pixels[pixel] & 0xff); // blue
            //argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            //argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            //result[row][col] = argb;
            if(((short)((int) pixels[pixel] & 0xff))== 255){
               result[row][col] = 0;
            }else{
                result[row][col]=1;
            }
            col++;
            if (col == width) {
               col = 0;
               //System.out.print("\n");
               row++;
            }
         }
      }
      return result;
    }
    
    public static boolean[][] toBool(BufferedImage image){
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
      final int width = image.getWidth();
      final int height = image.getHeight();
      final boolean hasAlphaChannel = image.getAlphaRaster() != null;
      boolean[][] result = new boolean[height][width];
      if (hasAlphaChannel) {
         final int pixelLength = 4;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+","+pixels[pixel + 3]+")");
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+","+pixels[pixel + 3]+")");
            
            //argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
            //argb += ((int) pixels[pixel + 1] & 0xff); // blue
            //argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
            //argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
            
            if(( pixels[pixel] & 0xff)== 255){
               result[row][col] = false;
            }else{
                result[row][col]=true;
            }
            
            
            col++;
           if (col == width) {
               col = 0;
               //System.out.print("\n");
               row++;
            }
         }
      } else {
         final int pixelLength = 3;
         for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
            int argb = 0;
            //System.out.print(" ("+pixels[pixel]+","+pixels[pixel + 1]+","+pixels[pixel + 2]+")");
            //System.out.print(" ("+((int) pixels[pixel] & 0xff)+","+(((int) pixels[pixel + 1] & 0xff) << 8)/256+","+(((int) pixels[pixel + 2] & 0xff) << 16)/(256*256)+")");
            
            //argb += -16777216; // 255 alpha
            //argb += ((int) pixels[pixel] & 0xff); // blue
            //argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
            //argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
            //result[row][col] = argb;
            if(( pixels[pixel] & 0xff)== 255){
               result[row][col] = false;
            }else{
                result[row][col]=true;
            }
            col++;
            if (col == width) {
               col = 0;
               //System.out.print("\n");
               row++;
            }
         }
      }
      return result;
    }
        
    
  

}
