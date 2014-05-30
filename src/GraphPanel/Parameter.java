/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphPanel;

import java.beans.*;

/**
 *
 * @author admin
 */
public class Parameter implements Function {
    
    private double value;
    protected final PropertyChangeSupport pcs = new PropertyChangeSupport( this );
    
    public void addPropertyChangeListener( PropertyChangeListener listener ) {
       pcs.addPropertyChangeListener( listener );
   }
   
   public void removePropertyChangeListener( PropertyChangeListener listener ) {
       pcs.removePropertyChangeListener( listener );
   }
    
    public double getValue() {
        return value;
    }
    
    public void setValue( double newVal ) {
        double oldVal = value;
        value = newVal;
        pcs.firePropertyChange("Value", oldVal, newVal);
    }
    
    @Override
    public double getY( double a, double b ) {
        return value;
    }
      
}
