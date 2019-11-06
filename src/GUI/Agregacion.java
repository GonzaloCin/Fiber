package GUI;

import Grouping.Calculus;
import Grouping.Database;
import Threads.BlackBoard;
import Threads.ThreadFCM;
import Threads.ThreadK3;
import Threads.ThreadKM;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.math3.stat.correlation.Covariance;
/**
 *
 * @author Gonzalo
 */
public class Agregacion extends javax.swing.JFrame {
    BufferedImage original,reducida,zoom,auxMuestra;
    DefaultListModel<Color> ColoresT1=new DefaultListModel<>();
    DefaultListModel<Color> ColoresT2=new DefaultListModel<>();
    DefaultListModel<Color> ColoresT3=new DefaultListModel<>();
    DefaultListModel<Color> ColoresFondo=new DefaultListModel<>();
    ArrayList<Point> CoorT1=new ArrayList<>();
    ArrayList<Point> CoorT2=new ArrayList<>();
    ArrayList<Point> CoorT3=new ArrayList<>();
    ArrayList<Point> CoorF=new ArrayList<>();
    
    double[][] Mt1=new double[3][10];
    double[][] Mt2=new double[3][10];
    double[][] Mt3=new double[3][10];
    double[][] MtF=new double[3][10];
    
    //Varianzas
    float[] Vt1=new float[3];
    float[] Vt2=new float[3];
    float[] Vt3=new float[3];
    float[] VtF=new float[3];
   
    //Medias
    float[] Ct1=new float[3];
    float[] Ct2=new float[3];
    float[] Ct3=new float[3];
    float[] CtF=new float[3];
    
    float []auxRed;
    float []auxGreen;
    float []auxBlue;
    
    Color coloraux;
    Graphics graphic;
    float Zoomant;
    
    //double[] auxRed={0,0,0,0};
    //double[] auxGreen={0,0,0,0};
    //double[] auxBlue={0,0,0,0};
    ThreadFCM thfcm;
    ThreadK3 thk3;
    ThreadKM thkm;
    Database base_de_datos;
    byte tipoimagen;
    
    boolean UsingData=false;
    
    /**
     * Creates new form MAHAGUI
     */
    public Agregacion() {
        initComponents();
        CheckLanguage();
        Tipo3.setVisible(false);
        FondoOk.setVisible(false);
        Tipo1Ok.setVisible(false);
        Tipo2Ok.setVisible(false);
        Tipo3Ok.setVisible(false);
        Espere.setVisible(false);
    }
    
    public void CheckLanguage(){
        if(Languages.language == Languages.Language.Spanish){
            MenuArchivo.setText("Archivo");
            MenuOpciones.setText("Opciones");
            Abrir.setText("Abrir");
        }else if(Languages.language == Languages.Language.English){
            MenuArchivo.setText("File");
            MenuOpciones.setText("Options");
            Abrir.setText("Open");
            FondoOtros.setText("Background Other");
            Tipo1.setText("Oxidative (dark)");
            Tipo2.setText("Glycolytic (light)");
            Espere.setText("Runing Algorithms...");
            GuardarVal.setText("Save Values");
        } 
    }
    
    private void paintFondoM(){
        
        //Campo.getGraphics().drawImage(reducida,0,0,700,700,null);
        LMuestra.setIcon(new ImageIcon(auxMuestra));
    } 
    
    private void openImage()
    {
        //Creamos la variable que será devuelta (la creamos como null)
        //Creamos un nuevo cuadro de diálogo para seleccionar imagen
        JFileChooser selector=new JFileChooser();
        //Le damos un título
        selector.setDialogTitle("Seleccione una imagen");
        //Filtramos los tipos de archivos
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP & TIFF & PNG", "jpg", "gif", "bmp","tif","tiff","png");
        selector.setFileFilter(filtroImagen);
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                //Devuelve el fichero seleccionado
                //Asignamos a la variable bmp la imagen leida
                this.original = ImageIO.read(selector.getSelectedFile());
                BlackBoard.setImagePath(selector.getSelectedFile());
                this.setTitle(selector.getSelectedFile().getName());
                //reducida=Calculus.ReduceImage(original,(int)(original.getHeight()*original.getWidth()*0.1));
                 reducida=Calculus.ReduceImage(original,(int)(800000));
                //System.out.println("\nAbsolute\t"+selectedImage.getAbsolutePath()+"\nCanonocal\t"+selectedImage.getCanonicalPath()+"\nName\t"+selectedImage.getName()+"\nPath\t"+selectedImage.getPath()+"\nParent\t"+selectedImage.getParent()+"\ntoString\t"+selectedImage.toString());
            }
            catch (Exception e)
            {
            }
            
            
        }        
    }
    
    private void paintPointList(ArrayList<Point> list,int tipo){
        Color colorgraph=new Color(128,128,128);
        switch (tipo){
            case 0:
                colorgraph=Color.MAGENTA;
            break;
            case 1:
                colorgraph=Color.red;
            break;
            case 2:
                colorgraph=Color.green;
            break;
            case 3:
                colorgraph=Color.blue;
            break;              
        }
        
        
        
        int ancho = auxMuestra.getWidth();
        int alto = auxMuestra.getHeight();
        int lado, lmedio;
        //Elegir el tamaño de los puntos en función del tamaño de la imagen que ese está mostrando
        int Mpx= (int)((ancho*alto)/1000000);
        switch(Mpx){
            case 0:
            case 1: 
                lado=3; lmedio=1;
            break;
            case 2:
            case 3:
            case 4:
                lado=5; lmedio=2;
            break;
            case 5:
            case 6:
            case 7:
                lado=6; lmedio=3;
            break;
            case 8:
            case 9:
            case 10:
                lado=7; lmedio=3;
            break;
            case 11:
            case 12:
            case 13:
                lado=8; lmedio=4;
            break;
            case 14:
            default:
                lado=9; lmedio=4;
            break;
        }
             
        Graphics g=auxMuestra.getGraphics();
        g.setColor(colorgraph);
        for(byte i=0;i<list.size();i++){
            g.fillOval((int)(list.get(i).getX())-lmedio,(int)(list.get(i).getY())-lmedio,lado,lado);
        }
        g.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipoIMG = new javax.swing.ButtonGroup();
        TipoFIB = new javax.swing.ButtonGroup();
        GrupoZoom = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        Tipo1 = new javax.swing.JRadioButton();
        Tipo1Ok = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Tipo2 = new javax.swing.JRadioButton();
        Tipo2Ok = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        Tipo3 = new javax.swing.JRadioButton();
        Tipo3Ok = new javax.swing.JLabel();
        Espere = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        FondoOtros = new javax.swing.JRadioButton();
        FondoOk = new javax.swing.JLabel();
        Campo = new javax.swing.JScrollPane();
        LMuestra = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        selectNADH = new javax.swing.JRadioButton();
        selectATP = new javax.swing.JRadioButton();
        selectCOX = new javax.swing.JRadioButton();
        GuardarVal = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        Abrir = new javax.swing.JMenuItem();
        MenuOpciones = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        z100 = new javax.swing.JRadioButtonMenuItem();
        z75 = new javax.swing.JRadioButtonMenuItem();
        z50 = new javax.swing.JRadioButtonMenuItem();
        z25 = new javax.swing.JRadioButtonMenuItem();
        z10 = new javax.swing.JRadioButtonMenuItem();
        z05 = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        TipoFIB.add(Tipo1);
        Tipo1.setText("Oxidativas(oscuras)");

        Tipo1Ok.setText("Ok");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tipo1Ok)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tipo1Ok)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        TipoFIB.add(Tipo2);
        Tipo2.setText("Glucoliticas(claras)");

        Tipo2Ok.setText("Ok");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Tipo2Ok)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Tipo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tipo2Ok)
                .addGap(0, 31, Short.MAX_VALUE))
        );

        TipoFIB.add(Tipo3);
        Tipo3.setText("Tipo 3");

        Tipo3Ok.setText("Ok");

        Espere.setFont(new java.awt.Font("Consolas", 1, 11)); // NOI18N
        Espere.setForeground(new java.awt.Color(255, 0, 0));
        Espere.setText("Ejecutando Algoritmos...");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Tipo3Ok)
                .addGap(22, 22, 22))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Espere)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(Tipo3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(Espere))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tipo3Ok)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TipoFIB.add(FondoOtros);
        FondoOtros.setSelected(true);
        FondoOtros.setText("Fondo Otros");

        FondoOk.setText("Ok");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FondoOtros)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FondoOk)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(FondoOtros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FondoOk)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Campo.setMaximumSize(new java.awt.Dimension(9000, 9000));
        Campo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CampoMouseClicked(evt);
            }
        });

        LMuestra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        LMuestra.setMaximumSize(new java.awt.Dimension(9990, 9990));
        Campo.setViewportView(LMuestra);

        TipoIMG.add(selectNADH);
        selectNADH.setText("NADH");
        selectNADH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectNADHActionPerformed(evt);
            }
        });

        TipoIMG.add(selectATP);
        selectATP.setText("ATPasa");
        selectATP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectATPActionPerformed(evt);
            }
        });

        TipoIMG.add(selectCOX);
        selectCOX.setText("COX");
        selectCOX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectCOXActionPerformed(evt);
            }
        });

        GuardarVal.setText("Guardar Valores");
        GuardarVal.setEnabled(false);
        GuardarVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarValActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(GuardarVal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectNADH)
                    .addComponent(selectATP)
                    .addComponent(selectCOX))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectNADH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectATP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectCOX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GuardarVal)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        MenuArchivo.setText("Archivo");
        MenuArchivo.setEnabled(false);

        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        MenuArchivo.add(Abrir);

        jMenuBar1.add(MenuArchivo);

        MenuOpciones.setText("Opciones");
        MenuOpciones.setEnabled(false);

        jMenu3.setText("Zoom");

        GrupoZoom.add(z100);
        z100.setSelected(true);
        z100.setText("100%");
        z100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z100ActionPerformed(evt);
            }
        });
        jMenu3.add(z100);

        GrupoZoom.add(z75);
        z75.setText("75%");
        z75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z75ActionPerformed(evt);
            }
        });
        jMenu3.add(z75);

        GrupoZoom.add(z50);
        z50.setText("50%");
        z50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z50ActionPerformed(evt);
            }
        });
        jMenu3.add(z50);

        GrupoZoom.add(z25);
        z25.setText("25%");
        z25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z25ActionPerformed(evt);
            }
        });
        jMenu3.add(z25);

        GrupoZoom.add(z10);
        z10.setText("10%");
        z10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z10ActionPerformed(evt);
            }
        });
        jMenu3.add(z10);

        GrupoZoom.add(z05);
        z05.setText("5%");
        z05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                z05ActionPerformed(evt);
            }
        });
        jMenu3.add(z05);

        MenuOpciones.add(jMenu3);

        jMenuBar1.add(MenuOpciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Campo, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Campo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CalculaZoom(){
        float porc=1;
        if(z100.isSelected()){
            porc=1;
        }else if(z75.isSelected()){
            porc=0.75f;
        }else if(z50.isSelected()){
            porc=0.50f;
        }else if(z25.isSelected()){
            porc=0.25f;
        }else if(z10.isSelected()){
            porc=0.1f;
        }else if(z05.isSelected()){
            porc=0.05f;
        }
        
                
        System.out.println("Porcentaje :"+porc);
        
        zoom=Calculus.ReduceImage(original,(int)(original.getHeight()*original.getWidth()*porc));
        auxMuestra=Calculus.Clone(zoom);
        ActualizaListas(porc,Zoomant);
        Zoomant=porc;
    }
    
    private void ActualizaListas(float znuevo, float zanterior){
        for(int i=0 ;i<CoorT1.size();i++){
            CoorT1.set(i,new Point((int)((CoorT1.get(i).getX()*Math.sqrt(znuevo/zanterior))),(int)((CoorT1.get(i).getY())*Math.sqrt(znuevo/zanterior))));
        }
        for(int i=0 ;i<CoorT2.size();i++){
            CoorT2.set(i,new Point((int)((CoorT2.get(i).getX()*Math.sqrt(znuevo/zanterior))),(int)((CoorT2.get(i).getY()*Math.sqrt(znuevo/zanterior)))));
        }
        for(int i=0 ;i<CoorF.size();i++){
            CoorF.set(i,new Point((int)((CoorF.get(i).getX()*Math.sqrt(znuevo/zanterior))),(int)((CoorF.get(i).getY()*Math.sqrt(znuevo/zanterior)))));
        }
        
        
        if(selectATP.isSelected()){
                for(int i=0 ;i<CoorT3.size();i++){
                    CoorT3.set(i,new Point((int)((CoorT3.get(i).getX()*Math.sqrt(znuevo/zanterior))),(int)((CoorT3.get(i).getY()*Math.sqrt(znuevo/zanterior)))));
                }
        }
        
        

    }
    
    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        // TODO add your handling code here:
        BlackBoard.reset();
        openImage();
       CalculaZoom();
        paintFondoM();
        Zoomant=1;
        UsingData=true;
        base_de_datos=new Database();
        //base_de_datos.add(original,0);//??????
        base_de_datos.addComplete(reducida,0);
        BlackBoard.setSharedData(base_de_datos.getComplete());
        
        
        tipoimagen=10;
        byte n=3;
        if(selectNADH.isSelected()){
            tipoimagen=0;
            n=3;
            
        }else if(selectATP.isSelected()){
            tipoimagen=1;
            n=4;
        }else if(selectCOX.isSelected()){
            tipoimagen=2;
            n=3;
        }
        thfcm=new ThreadFCM();
        thfcm.setData(n,0.25f,2f);
        
        thk3=new ThreadK3();
        thk3.setData(n,0.1f);
        
        //thkm=new ThreadKM();
        //thkm.setData(n,0.1f,(byte)3,0.5f);
        Espere.setVisible(true);
        BlackBoard.guardalab(Espere);
        thfcm.start();
        thk3.start();
        //thkm.run(); //aun falta convergencia
        MenuOpciones.setEnabled(true);
        //Espere.setVisible(false);
        
        
    }//GEN-LAST:event_AbrirActionPerformed

    private void selectNADHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectNADHActionPerformed
        // TODO add your handling code here:
        if(Languages.language == Languages.Language.Spanish){
            Tipo1.setText("Oxidativas(oscuras)");
            Tipo2.setText("Glucoliticas(claras)");
        }else if(Languages.language == Languages.Language.English){
            Tipo1.setText("Oxidative (dark)");
            Tipo2.setText("Glycolytic (light)");
        }
        Tipo3.setVisible(false);
        FondoOk.setVisible(false);
        Tipo1Ok.setVisible(false);
       Tipo2Ok.setVisible(false);
       Tipo3Ok.setVisible(false);
       ColoresT1.clear();
       ColoresT2.clear();
       ColoresT3.clear();
       ColoresFondo.clear();
       CoorT1.clear();
       CoorT2.clear();
       CoorT3.clear();
       CoorF.clear();
       MenuArchivo.setEnabled(true);
       //Campo.getGraphics().drawImage(fondo.getResizedImage(),0,0,Campo.getWidth(),Campo.getHeight(),null);
       /*
       paintPointList(CoorT1,1);
       paintPointList(CoorT2,2);
       paintPointList(CoorF,0);
       if(selectATP.isSelected()){
            paintPointList(CoorT3,3);
        }
       paintFondoM();
       */
       
    }//GEN-LAST:event_selectNADHActionPerformed

    private void selectCOXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectCOXActionPerformed
        // TODO add your handling code here:
        if(Languages.language == Languages.Language.Spanish){
            Tipo1.setText("Oxidativas(oscuras)");
            Tipo2.setText("Glucoliticas(claras)");
        }else if(Languages.language == Languages.Language.English){
            Tipo1.setText("Oxidative (dark)");
            Tipo2.setText("Glycolytic (light)");
        }
        Tipo3.setVisible(false);
        FondoOk.setVisible(false);
        Tipo1Ok.setVisible(false);
       Tipo2Ok.setVisible(false);
       Tipo3Ok.setVisible(false);
       ColoresT1.clear();
       ColoresT2.clear();
       ColoresT3.clear();
       CoorT1.clear();
       CoorT2.clear();
       CoorT3.clear();
       CoorF.clear();
       ColoresFondo.clear();
       MenuArchivo.setEnabled(true);
      
       
       /*paintPointList(CoorT1,1);
       paintPointList(CoorT2,2);
       paintPointList(CoorF,0);
       if(selectATP.isSelected()){
            paintPointList(CoorT3,3);
        }
       paintFondoM();
       */
    }//GEN-LAST:event_selectCOXActionPerformed

    private void selectATPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectATPActionPerformed
        // TODO add your handling code here:
        
        if(Languages.language == Languages.Language.Spanish){
            Tipo1.setText("Intermedias(oscuras)");
            Tipo2.setText("Rapidas(medias)");
            Tipo3.setText("Lentas(claras)");
        }else if(Languages.language == Languages.Language.English){
            Tipo1.setText("Intermediate(dark)");
            Tipo2.setText("Fast(medium)");
            Tipo3.setText("Slow(light)");
        }
        Tipo3.setVisible(true);
        FondoOk.setVisible(false);
        Tipo1Ok.setVisible(false);
       Tipo2Ok.setVisible(false);
       Tipo3Ok.setVisible(false);
       ColoresT1.clear();
       ColoresT2.clear();
       ColoresT3.clear();
       CoorT1.clear();
       CoorT2.clear();
       CoorT3.clear();
       CoorF.clear();
       ColoresFondo.clear();
       MenuArchivo.setEnabled(true);
       
       /*
       paintPointList(CoorT1,1);
       paintPointList(CoorT2,2);
       paintPointList(CoorF,0);
       if(selectATP.isSelected()){
            paintPointList(CoorT3,3);
        }
       paintFondoM();
       */
    }//GEN-LAST:event_selectATPActionPerformed

    private void GuardarValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarValActionPerformed
        // TODO add your handling code here:
        Covariance Calcov= new Covariance();
              Ct1[0]=0;
              Ct1[1]=0;
              Ct1[2]=0;
              
              Ct2[0]=0;
              Ct2[1]=0;
              Ct2[2]=0;
              
              Ct3[0]=0;
              Ct3[1]=0;
              Ct3[2]=0;
              
              CtF[0]=0;
              CtF[1]=0;
              CtF[2]=0;
              
              float [][] auxres;
        if(selectNADH.isSelected()){
            
            for(int i=0;i<10;i++){
              Ct1[0]+=(Mt1[0][i]/10);
              Ct1[1]+=(Mt1[1][i]/10);
              Ct1[2]+=(Mt1[2][i]/10);
              
              Ct2[0]+=(Mt2[0][i]/10);
              Ct2[1]+=(Mt2[1][i]/10);
              Ct2[2]+=(Mt2[2][i]/10);
              
              CtF[0]+=MtF[0][i]/10;
              CtF[1]+=MtF[1][i]/10;
              CtF[2]+=MtF[2][i]/10;
            }
            for(int j=0;j<3;j++){
                Vt1[j]=(float)(Calcov.covariance(Mt1[j], Mt1[j]));
                Vt2[j]=(float)Calcov.covariance(Mt2[j], Mt2[j]);
                VtF[j]=(float)Calcov.covariance(MtF[j], MtF[j]);
            }
            auxRed=new float[3];
            auxGreen=new float[3];
            auxBlue=new float[3];
            
            
            auxres=new float[3][3];
            auxRed[0]=Vt1[0];
            auxRed[1]=Vt2[0];
            auxRed[2]=VtF[0];
            
            auxGreen[0]=Vt1[1];
            auxGreen[1]=Vt2[1];
            auxGreen[2]=VtF[1];
            
            auxBlue[0]=Vt1[2];
            auxBlue[1]=Vt2[2];
            auxBlue[2]=VtF[2];
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveV(auxres);
            
            //HumanRGB.setVRed(0,auxRed);
            //HumanRGB.setVGreen(0,auxGreen);
            //HumanRGB.setVBlue(0,auxBlue);
            
            auxRed[0]=Ct1[0];
            auxRed[1]=Ct2[0];
            auxRed[2]=CtF[0];
            
            auxGreen[0]=Ct1[1];
            auxGreen[1]=Ct2[1];
            auxGreen[2]=CtF[1];
            
            auxBlue[0]=Ct1[2];
            auxBlue[1]=Ct2[2];
            auxBlue[2]=CtF[2];
            
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveH(auxres,tipoimagen);
            //HumanRGB.setRed(0,auxRed);
            //HumanRGB.setGreen(0,auxGreen);
            //HumanRGB.setBlue(0,auxBlue); 
             
        }else if(selectATP.isSelected()){
            for(int i=0;i<10;i++){
              Ct1[0]+=Mt1[0][i]/10;
              Ct1[1]+=Mt1[1][i]/10;
              Ct1[2]+=Mt1[2][i]/10;
              
              Ct2[0]+=Mt2[0][i]/10;
              Ct2[1]+=Mt2[1][i]/10;
              Ct2[2]+=Mt2[2][i]/10;
              
              Ct3[0]+=Mt3[0][i]/10;
              Ct3[1]+=Mt3[1][i]/10;
              Ct3[2]+=Mt3[2][i]/10;
              
              CtF[0]+=MtF[0][i]/10;
              CtF[1]+=MtF[1][i]/10;
              CtF[2]+=MtF[2][i]/10;
            }
            for(int j=0;j<3;j++){
                Vt1[j]=(float)Calcov.covariance(Mt1[j], Mt1[j]);
                Vt2[j]=(float)Calcov.covariance(Mt2[j], Mt2[j]);
                Vt3[j]=(float)Calcov.covariance(Mt3[j], Mt3[j]);
                VtF[j]=(float)Calcov.covariance(MtF[j], MtF[j]);
            }
            auxRed=new float[4];
            auxGreen=new float[4];
            auxBlue=new float[4];
            
            
            
            auxres=new float[3][4];
            
            auxRed[0]=Vt1[0];
            auxRed[1]=Vt2[0];
            auxRed[2]=Vt3[0];
            auxRed[3]=VtF[0];
            
             auxGreen[0]=Vt1[1];
            auxGreen[1]=Vt2[1];
            auxGreen[2]=Vt3[1];
             auxGreen[3]=VtF[1];
            
            auxBlue[0]=Vt1[2];
            auxBlue[1]=Vt2[2];
            auxBlue[2]=Vt3[2];
            auxBlue[3]=VtF[2];
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveV(auxres);
            
            //HumanRGB.setVRed(1,auxRed);
            //HumanRGB.setVGreen(1,auxGreen);
            //HumanRGB.setVBlue(1,auxBlue); 
            
            auxRed[0]=Ct1[0];
            auxRed[1]=Ct2[0];
            auxRed[2]=Ct3[0];
            auxRed[3]=CtF[0];
            
             auxGreen[0]=Ct1[1];
            auxGreen[1]=Ct2[1];
            auxGreen[2]=Ct3[1];
             auxGreen[3]=CtF[1];
            
            auxBlue[0]=Ct1[2];
            auxBlue[1]=Ct2[2];
            auxBlue[2]=Ct3[2];
            auxBlue[3]=CtF[2];
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveH(auxres,tipoimagen);
            
            //HumanRGB.setRed(1,auxRed);
            //HumanRGB.setGreen(1,auxGreen);
            //HumanRGB.setBlue(1,auxBlue); 
            
            
        }else if(selectCOX.isSelected()){
            for(int i=0;i<10;i++){
              Ct1[0]+=Mt1[0][i]/10;
              Ct1[1]+=Mt1[1][i]/10;
              Ct1[2]+=Mt1[2][i]/10;
              
              Ct2[0]+=Mt2[0][i]/10;
              Ct2[1]+=Mt2[1][i]/10;
              Ct2[2]+=Mt2[2][i]/10;
              
              CtF[0]+=MtF[0][i]/10;
              CtF[1]+=MtF[1][i]/10;
              CtF[2]+=MtF[2][i]/10;
            }
            for(int j=0;j<3;j++){
                Vt1[j]=(float)Calcov.covariance(Mt1[j], Mt1[j]);
                Vt2[j]=(float)Calcov.covariance(Mt2[j], Mt2[j]);
                VtF[j]=(float)Calcov.covariance(MtF[j], MtF[j]);
            }
            auxRed=new float[3];
            auxGreen=new float[3];
            auxBlue=new float[3];
            
            
            auxres=new float[3][3];
            
            auxRed[0]=Vt1[0];
            auxRed[1]=Vt2[0];
            auxRed[2]=VtF[0];
            
            auxGreen[0]=Vt1[1];
            auxGreen[1]=Vt2[1];
            auxGreen[2]=VtF[1];
            
            auxBlue[0]=Vt1[2];
            auxBlue[1]=Vt2[2];
            auxBlue[2]=VtF[2];
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveV(auxres);
            
            //HumanRGB.setVRed(2,auxRed);
            //HumanRGB.setVGreen(2,auxGreen);
            //HumanRGB.setVBlue(2,auxBlue); 
            
            
            auxRed[0]=Ct1[0];
            auxRed[1]=Ct2[0];
            auxRed[2]=CtF[0];
            
             auxGreen[0]=Ct1[1];
            auxGreen[1]=Ct2[1];
            auxGreen[2]=CtF[1];
            
            auxBlue[0]=Ct1[2];
            auxBlue[1]=Ct2[2];
            auxBlue[2]=CtF[2];
            
            auxres[0]=auxRed;
            auxres[1]=auxGreen;
            auxres[2]=auxBlue;
            BlackBoard.saveH(auxres,tipoimagen);
            
            //HumanRGB.setRed(2,auxRed);
            //HumanRGB.setGreen(2,auxGreen);
            //HumanRGB.setBlue(2,auxBlue); 
            
            
        }
        if(Languages.language == Languages.Language.Spanish){
            JOptionPane.showMessageDialog(null, "Listo, conocimiento de Experto agregado satisfactoriamente");
        }else if(Languages.language == Languages.Language.Spanish){
            JOptionPane.showMessageDialog(null, "OK, Expert knowledge added succsessfully");
        }
    }//GEN-LAST:event_GuardarValActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        //
        //if(UsingData){
            
        //    paintPointList(CoorT1,1);
          //  paintPointList(CoorT2,2);
            //paintPointList(CoorF,0);
        //    if(selectATP.isSelected()){
          //      paintPointList(CoorT3,3);
            //}paintFondoM();
        //}
        
    }//GEN-LAST:event_formComponentShown

    private void CampoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoMouseClicked
        // TODO add your handling code here:
        int corx=evt.getX()+Campo.getHorizontalScrollBar().getValue();
        int cory=evt.getY()+Campo.getVerticalScrollBar().getValue();
        Point au=new Point(corx,cory);
        coloraux=new Color(auxMuestra.getRGB(corx,cory));
        System.out.print("X="+corx+" Y="+cory+"\t");
        System.out.println(coloraux.getRed()+" "+coloraux.getGreen()+" "+coloraux.getBlue());
        
        if(selectNADH.isSelected()){
            if(Tipo1.isSelected()){
                if(ColoresT1.getSize()<10){
                    ColoresT1.addElement(coloraux);
                    CoorT1.add(au);
                    paintPointList(CoorT1,1);
                    if(ColoresT1.getSize()==10){
                        Tipo1Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt1[0][i]=ColoresT1.getElementAt(i).getRed();
                            Mt1[1][i]=ColoresT1.getElementAt(i).getGreen();
                            Mt1[2][i]=ColoresT1.getElementAt(i).getBlue();
                        }
                        
                    }
                }
                
            }else if(Tipo2.isSelected()){
                 if(ColoresT2.getSize()<10){
                    ColoresT2.addElement(coloraux);
                    CoorT2.add(au);
                    paintPointList(CoorT2,2);
                    if(ColoresT2.getSize()==10){
                        Tipo2Ok.setVisible(true);
                        
                        for(int i=0;i<10;i++){
                            Mt2[0][i]=ColoresT2.getElementAt(i).getRed();
                            Mt2[1][i]=ColoresT2.getElementAt(i).getGreen();
                            Mt2[2][i]=ColoresT2.getElementAt(i).getBlue();
                        }
                    }
                }
            }else if(FondoOtros.isSelected()){
                if(ColoresFondo.getSize()<10){
                    ColoresFondo.addElement(coloraux);
                    CoorF.add(au);
                    paintPointList(CoorF,0);
                    if(ColoresFondo.getSize()==10){
                       FondoOk.setVisible(true);
                        for(int i=0;i<10;i++){
                            MtF[0][i]=ColoresFondo.getElementAt(i).getRed();
                            MtF[1][i]=ColoresFondo.getElementAt(i).getGreen();
                            MtF[2][i]=ColoresFondo.getElementAt(i).getBlue();
                        } 
                        
                    }
                
                }
            }
            if(Tipo1Ok.isVisible() && Tipo2Ok.isVisible() && FondoOk.isVisible()){
                GuardarVal.setEnabled(true);              
            }
        }else if(selectATP.isSelected()){
            if(Tipo1.isSelected()){
                if(ColoresT1.getSize()<10){
                    ColoresT1.addElement(coloraux);
                    CoorT1.add(au);
                    paintPointList(CoorT1,1);
                    if(ColoresT1.getSize()==10){
                         Tipo1Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt1[0][i]=ColoresT1.getElementAt(i).getRed();
                            Mt1[1][i]=ColoresT1.getElementAt(i).getGreen();
                            Mt1[2][i]=ColoresT1.getElementAt(i).getBlue();
                        }
                    }
                
                }
            }else if(Tipo2.isSelected()){
                if(ColoresT2.getSize()<10){
                    ColoresT2.addElement(coloraux);
                    CoorT2.add(au);
                    paintPointList(CoorT2,2);
                    if(ColoresT2.getSize()==10){
                        Tipo2Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt2[0][i]=ColoresT2.getElementAt(i).getRed();
                            Mt2[1][i]=ColoresT2.getElementAt(i).getGreen();
                            Mt2[2][i]=ColoresT2.getElementAt(i).getBlue();
                        }   
                    }                  
                }
            }else if(Tipo3.isSelected()){
                if(ColoresT3.getSize()<10){
                    ColoresT3.addElement(coloraux);
                    CoorT3.add(au);
                    paintPointList(CoorT3,3);
                    if(ColoresT3.getSize()==10){
                        Tipo3Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt3[0][i]=ColoresT3.getElementAt(i).getRed();
                            Mt3[1][i]=ColoresT3.getElementAt(i).getGreen();
                            Mt3[2][i]=ColoresT3.getElementAt(i).getBlue();
                        }   
                    }
                }
            }
            else if(FondoOtros.isSelected()){
                if(ColoresFondo.getSize()<10){
                    ColoresFondo.addElement(coloraux);
                    CoorF.add(au);
                    paintPointList(CoorF,0);
                    if(ColoresFondo.getSize()==10){
                        FondoOk.setVisible(true);
                        for(int i=0;i<10;i++){
                            MtF[0][i]=ColoresFondo.getElementAt(i).getRed();
                            MtF[1][i]=ColoresFondo.getElementAt(i).getGreen();
                            MtF[2][i]=ColoresFondo.getElementAt(i).getBlue();
                        } 
                    }
                }
            }
            if(Tipo1Ok.isVisible() && Tipo2Ok.isVisible() && FondoOk.isVisible() && Tipo3Ok.isVisible()){
                GuardarVal.setEnabled(true);              
            }
        }else if(selectCOX.isSelected()){
            if(Tipo1.isSelected()){
                if(ColoresT1.getSize()<10){
                    ColoresT1.addElement(coloraux);
                    CoorT1.add(au);
                    paintPointList(CoorT1,1);
                    if(ColoresT1.getSize()==10){
                        Tipo1Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt1[0][i]=ColoresT1.getElementAt(i).getRed();
                            Mt1[1][i]=ColoresT1.getElementAt(i).getGreen();
                            Mt1[2][i]=ColoresT1.getElementAt(i).getBlue();
                        }
                        
                    }
                }
                
            }else if(Tipo2.isSelected()){
                if(ColoresT2.getSize()<10){
                    ColoresT2.addElement(coloraux);
                    CoorT2.add(au);
                    paintPointList(CoorT2,2);
                    if(ColoresT2.getSize()==10){
                        Tipo2Ok.setVisible(true);
                        for(int i=0;i<10;i++){
                            Mt2[0][i]=ColoresT2.getElementAt(i).getRed();
                            Mt2[1][i]=ColoresT2.getElementAt(i).getGreen();
                            Mt2[2][i]=ColoresT2.getElementAt(i).getBlue();
                        }
                    }
                }
            }else if(FondoOtros.isSelected()){
                if(ColoresFondo.getSize()<10){
                    
                    ColoresFondo.addElement(coloraux);
                    CoorF.add(au);
                    paintPointList(CoorF,0);
                    if(ColoresFondo.getSize()==10){
                       FondoOk.setVisible(true);
                        for(int i=0;i<10;i++){
                            MtF[0][i]=ColoresFondo.getElementAt(i).getRed();
                            MtF[1][i]=ColoresFondo.getElementAt(i).getGreen();
                            MtF[2][i]=ColoresFondo.getElementAt(i).getBlue();
                        } 
                        
                    }
                
                }
            }
            
            
            if(Tipo1Ok.isVisible() && Tipo2Ok.isVisible() && FondoOk.isVisible()){
                GuardarVal.setEnabled(true);              
            }
            
        }                
        //paintPointList(CoorT1,1);
          //  paintPointList(CoorT2,2);
            //paintPointList(CoorF,0);
            //if(selectATP.isSelected()){
              //   paintPointList(CoorT3,3);
             //}
            paintFondoM();
    }//GEN-LAST:event_CampoMouseClicked

    private void z100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z100ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z100ActionPerformed

    private void z50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z50ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z50ActionPerformed

    private void z25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z25ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z25ActionPerformed

    private void z75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z75ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z75ActionPerformed

    private void z10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z10ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z10ActionPerformed

    private void z05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_z05ActionPerformed
        // TODO add your handling code here:
        CalculaZoom();
        paintPointList(CoorT1,1);
            paintPointList(CoorT2,2);
            paintPointList(CoorF,0);
            if(selectATP.isSelected()){
                 paintPointList(CoorT3,3);
             }
        paintFondoM();
    }//GEN-LAST:event_z05ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MAHAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MAHAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MAHAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MAHAGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MAHAGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JScrollPane Campo;
    private javax.swing.JLabel Espere;
    private javax.swing.JLabel FondoOk;
    private javax.swing.JRadioButton FondoOtros;
    private javax.swing.ButtonGroup GrupoZoom;
    private javax.swing.JButton GuardarVal;
    private javax.swing.JLabel LMuestra;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuOpciones;
    private javax.swing.JRadioButton Tipo1;
    private javax.swing.JLabel Tipo1Ok;
    private javax.swing.JRadioButton Tipo2;
    private javax.swing.JLabel Tipo2Ok;
    private javax.swing.JRadioButton Tipo3;
    private javax.swing.JLabel Tipo3Ok;
    private javax.swing.ButtonGroup TipoFIB;
    private javax.swing.ButtonGroup TipoIMG;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton selectATP;
    private javax.swing.JRadioButton selectCOX;
    private javax.swing.JRadioButton selectNADH;
    private javax.swing.JRadioButtonMenuItem z05;
    private javax.swing.JRadioButtonMenuItem z10;
    private javax.swing.JRadioButtonMenuItem z100;
    private javax.swing.JRadioButtonMenuItem z25;
    private javax.swing.JRadioButtonMenuItem z50;
    private javax.swing.JRadioButtonMenuItem z75;
    // End of variables declaration//GEN-END:variables
}