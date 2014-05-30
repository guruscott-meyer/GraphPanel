/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphPanel;

import java.awt.Color;
import java.beans.*;
import java.util.NoSuchElementException;

/**
 *
 * @author admin
 */
public class FunctionFramework extends Object {
    
    protected Function function;
    protected LabelFunction label;
    protected FunctionFramework framework1, framework2;
    private Color color;
   
   protected final PropertyChangeSupport pcs = new PropertyChangeSupport( this );
    
    public FunctionFramework( Function newfunc, LabelFunction newlabel ) {
        function = newfunc;
        label = newlabel;
        color = Color.black;
    }
    
    public void addPropertyChangeListener( PropertyChangeListener listener ) {
       pcs.addPropertyChangeListener( listener );
   }
   
   public void removePropertyChangeListener( PropertyChangeListener listener ) {
       pcs.removePropertyChangeListener( listener );
   }
    
   public void setColor( Color newColor ) {
       Color oldColor = color;
       color = newColor;
       pcs.firePropertyChange( "color", oldColor, newColor);
   }
   
   public Color getColor() {
       return color;
   }
   
    public void setFramework( FunctionFramework newframe, int index ) {;
        if( index == 0 ) {
            FunctionFramework oldframe = framework1;
            framework1 = newframe;
            pcs.firePropertyChange( "Framework", oldframe, framework1 );
        }
        else {
            FunctionFramework oldframe = framework2;
            framework2 = newframe;
            pcs.firePropertyChange( "Framework", oldframe, framework2 );
        }
    }
    
    public FunctionFramework getFramework( int index ) {
        if( index == 0 ) return framework1;
        else return framework2;
    }
    
    public void replaceFramework( FunctionFramework oldFrame, FunctionFramework newFrame ) throws NoSuchElementException {
        if( framework1 == oldFrame ) framework1 = newFrame;
        else if( framework2 == oldFrame ) framework2 = newFrame;
        else throw new NoSuchElementException();
    }
    
    public void setFunction( Function newfunc ) {
        Function oldfunc = function;
        function = newfunc;
        pcs.firePropertyChange( "Function", oldfunc, function );
    }
    
    public Function getFunction() {
        return function;
    }
    
    public LabelFunction getLabel() {
        return label;
    }
    
    public void setLabel( LabelFunction newlabel ) {
        LabelFunction oldlabel = label;
        label = newlabel;
        pcs.firePropertyChange("Label", oldlabel, label );
    }
    
    public double getY( double x ) {
        return function.getY( x, 0 );
    }
    
    public String getLabelText() {
        return label.getLabel( "", "" );
    }
    
}
