/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphPanel;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class Coordinate extends Object implements Comparable<Coordinate> {
    
    private Double X, Y;
    
    public Coordinate (){
        X = 0.0;
        Y = 0.0;
    }
    
    public Coordinate (Double x, Double y) {
        X = x;
        Y = y;
    }
    
    public void setX (Double x) {
        X = x;
    }
    
    public void setY (Double y) {
        Y = y;
    }
    
    public Double getX () {
        return X;
    }
    
    public Double getY () {
        return Y;
    }

    @Override
    public int compareTo( Coordinate o ) {
        if ( Objects.equals(this.getX(), o.getX()) && Objects.equals(this.getY(), o.getY()) )
            return 0;
        else if ( this.getX() > o.getX() && this.getY() > o.getY()  )
            return 1;
        else
            return -1;
    }
}

