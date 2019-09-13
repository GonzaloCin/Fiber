/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Muscular;

/**
 *
 * @author Gonzalo
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/** Getting a Rectangle of interest on the screen.
Requires the MotivatedEndUser API - sold separately. */
public class ScreenCaptureRectangle {

    Rectangle captureRect;

    ScreenCaptureRectangle(final BufferedImage screen) {
//Guardar la captura de pantalla obtenida******************************************************
        final BufferedImage screenCopy = new BufferedImage(
                screen.getWidth(),
                screen.getHeight(),
                screen.getType());
        
//Creando donde se muestara la imagen**********************************************************
        final JLabel screenLabel = new JLabel(new ImageIcon(screenCopy));
        JScrollPane screenScroll = new JScrollPane(screenLabel);
    //Poniendo dimensiones al panel
        screenScroll.setPreferredSize(new Dimension(
                (int)(screen.getWidth()/1.5),
                (int)(screen.getHeight()/1.5)));
        //Añadiendo a un panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(screenScroll, BorderLayout.CENTER);
    //Etiqueta a mostrar        
        final JLabel selectionLabel = new JLabel(
                "Drag a rectangle in the screen shot!");
        panel.add(selectionLabel, BorderLayout.SOUTH);

//LLenando el panel con la imagen y el rectangulo************************************************
    //Manada a llama inmediatamente repaint para llenar la imagen a mostrar con el rectangulo
        repaint(screen, screenCopy);
    //Pone en el panel la imagen generada en el paso anterior
        screenLabel.repaint();

//Agregar los eventos del mouse*****************************************************************************
        
        screenLabel.addMouseMotionListener(new MouseMotionAdapter() {
            Point start = new Point();
        //Asigna el punto de inicio del rectangulo
            @Override
            public void mouseMoved(MouseEvent me) {
                start = me.getPoint();
                repaint(screen, screenCopy);
                selectionLabel.setText("Start Point: " + start);
                screenLabel.repaint();
            }
        //Asigna el punto final despues de arrastrar
            @Override
            public void mouseDragged(MouseEvent me) {
                Point end = me.getPoint();
                //Crea el objeto rectangulo
                captureRect = new Rectangle(start,
                        new Dimension(end.x-start.x, end.y-start.y));
                //repinta
                repaint(screen, screenCopy);
                screenLabel.repaint();
                selectionLabel.setText("Rectangle: " + captureRect);
            }
        });

        JOptionPane.showMessageDialog(null, panel);

        System.out.println("Rectangle of interest: " + captureRect);
    }//Termina el constructor
    
//Repinta el rectangulo sobre la figura a mostrar
    public void repaint(BufferedImage orig, BufferedImage copy) {
        Graphics2D g = copy.createGraphics();
        g.drawImage(orig,0,0, null);
        if (captureRect!=null) {
            g.setColor(Color.RED);
            g.draw(captureRect);
            g.setColor(new Color(255,255,255,150));
            g.fill(captureRect);
        }
        g.dispose();
    }

    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        final Dimension screenSize = Toolkit.getDefaultToolkit().
                getScreenSize();
        final BufferedImage screen = robot.createScreenCapture(
                new Rectangle(screenSize));

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScreenCaptureRectangle(screen);
            }
        });
    }
}
