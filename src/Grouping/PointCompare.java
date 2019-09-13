/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Grouping;

import java.awt.Point;
import java.util.Comparator;

/**
 *
 * @author Gonzalo
 */
public class PointCompare implements Comparator<Point> {

    @Override
    public int compare(final Point a, final Point b) {
        if (a.x < b.x) {
            return -1;
        }
        else if (a.x > b.x) {
            return 1;
        }
        else {
            if (a.y < b.y) {
                return -1;
            }
            else if (a.y > b.y) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}