package Images;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author GustavoLeonardo
 */
public class Whitening {
    
    private int xval, yval;
    private boolean first;
    private MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
}
