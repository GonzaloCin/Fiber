/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Process;

import java.awt.Point;

/**
 *
 * @author Gonzalo
 */
public class SoleFiber {
    Point center;
    int tam;
    
    public SoleFiber(int x, int y,int tm){
        center=new Point(x,y);
        tam=tm;
    }
    
    public SoleFiber(Point p,int tm){
        center=p;
        tam=tm;
    }
    
    public SoleFiber(int x, int y){
        center=new Point(x,y);
    }
    
    public void setCenter(int x, int y){
        //center=new Point(x,y);
        center.move(x, y);
    }
    
    public void setCenter(Point p){
        //center=new Point(x,y);
        center.setLocation(p);
    }
    
    public void setSize(int tm){
        tam=tm;
    }
    
    
    
}
