/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphPanel;

/**
 *
 * @author admin
 */
public class TwoArgumentFunctionFramework extends FunctionFramework {
    
    public TwoArgumentFunctionFramework( Function newfunc, LabelFunction newlabel, FunctionFramework newframe1, FunctionFramework newframe2 ) {
        super( newfunc, newlabel );
        framework1 = newframe1;
        framework2 = newframe2;
    }
    
    @Override
    public double getY( double x ) {
        return function.getY( framework1.getY( x ), framework2.getY( x ));
    }
    
    @Override
    public String getLabelText() {
        return label.getLabel( framework1.getLabelText(), framework2.getLabelText() );
    }
}
