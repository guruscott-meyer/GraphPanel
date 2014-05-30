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
public class FunctionTools {
    
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
}
