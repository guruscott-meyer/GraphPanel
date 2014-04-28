package GraphPanel;

import java.awt.Color;
import java.beans.*;

public abstract class Function extends Object
   {

   protected double[] Params;
   private Color color;
   
   protected final PropertyChangeSupport pcs = new PropertyChangeSupport( this );

   public Function()
      {
      color = Color.black;
      }

   protected Function( double[] params )
      {
      Params = params;
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
   
   public void setParams( double[] params ) {
       double[] oldParams = Params;
       Params = params;
       pcs.firePropertyChange( "Params", oldParams, params );
   }
   
   public double[] getParams()
      {
      return Params;
      }
   
   public static double sigmaX( Coordinate[] list )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += list1.getX();
       }
      return temp;
      }
    
   public static double sigmaY( Coordinate[] list )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += list1.getY();
       }
      return temp;
      }
   
   public static double sigmaX( Coordinate[] list, double exp )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += Math.pow(list1.getX(), exp);
       }
      return temp;
      }

   public static double sigmaY( Coordinate[] list, double exp )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += Math.pow(list1.getY(), exp);
       }
      return temp;
      }
   
   public static double sigma( Coordinate[] list )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += list1.getX() * list1.getY();
       }
      return temp;
      }

   public static double sigma( Coordinate[] list, double exp )
      {
      double temp = 0;
       for (Coordinate list1 : list) {
           temp += Math.pow(list1.getX(), exp) * list1.getY();
       }
      return temp;
      }

   public static double powerFormA( Coordinate[] coord )
      {
      return ( coord.length * sigma( coord ) - sigmaX( coord ) * sigmaY( coord ) ) /
             ( coord.length * sigmaX( coord, 2.0 ) - Math.pow( sigmaX( coord ), 2.0 ) );
      }

   public static double powerFormB( Coordinate[] coord )
      {
      return ( sigmaX( coord, 2.0 ) * sigmaY( coord ) - sigma( coord ) * sigmaX( coord ) ) /
             ( coord.length * sigmaX( coord, 2.0 ) - Math.pow( sigmaX( coord ), 2.0 ) );
      }

   public static double powerFormA2( Coordinate[] coord, double exp )
      {
      return sigma( coord, exp ) / sigmaX( coord, exp * 2.0 );
      }

   public static double[] backSolve( double system[][] )
      {
      int i, j, k;
      double multiplier;
      for( i = 0; i < system.length - 1; i++ )
         for( j = i + 1; j < system.length; j++ )
            {
            if( system[j][i] != 0 ) 
               {
               multiplier = system[j][i] / system[i][i];
               for( k = i; k < system[j].length; k++ )
                  system[j][k] -= system[i][k] * multiplier;
               }
            }
      double result[] = new double[ system.length ];
      for( i = system.length - 1; i >= 0; i-- )
         {
         for( j = i + 1; j < system[i].length - 1; j++ )
            system[i][ system[i].length - 1 ] -= system[i][j] * result[j];
         if( system[i][i] != 0 ) result[i] = system[i][ system[i].length - 1 ] / system[i][i];
         else result[i] = 0;
         }
      return result;
      }

   public static Coordinate[] logX( Coordinate[] coord )
      {
      Coordinate[] newcoord = new Coordinate[ coord.length ];
      for( int i = 0; i < coord.length; i++ )
         newcoord[i] = new Coordinate( Math.log( coord[i].getX() ), coord[i].getY() );
      return newcoord;
      }

   public static Coordinate[] logY( Coordinate[] coord )
      {
      Coordinate[] newcoord = new Coordinate[ coord.length ];
      for( int i = 0; i < coord.length; i++ )
         newcoord[i] = new Coordinate( coord[i].getX(), Math.log( coord[i].getY() ) );
      return newcoord;
      }

   public static Coordinate[] logXY( Coordinate[] coord )
      {
      Coordinate[] newcoord = new Coordinate[ coord.length ];
      for( int i = 0; i < coord.length; i++ )
         newcoord[i] = new Coordinate( Math.log( coord[i].getX() ), Math.log( coord[i].getY() ) );
      return newcoord;
      }
   
   public void setParam( int index, double newParam ) {
       double oldParam = Params[index];
       Params[index] = newParam;
       pcs.firePropertyChange( "Params", oldParam, newParam );
   }
   
   public double getParam( int index ) {
       if( Params == null || index < 0 || index >= Params.length ) return 0.0;
       else return Params[index];
   }

   public int getParamCount() {
       return 0;
   }
   
   public abstract String getLabel();

   public abstract double getY( double x );

}