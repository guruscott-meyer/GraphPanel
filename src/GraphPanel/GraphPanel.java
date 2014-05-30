/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GraphPanel;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.*;
import java.beans.*;

/**
 *
 * @author Scott Meyer
 */
public class GraphPanel extends JPanel implements MouseInputListener, PropertyChangeListener{
    
    static final int SCALE_MULT = 2;
    static final int POINT_SIZE = 4;
    static final int LABEL_OFFSET = 30;
    static final int NUM_MARKS  = 10;
    static final int MARK_LENGTH = 10;
    //static final Integer RESOLUTION = 100;
    
    private static ArrayList<Coordinate> plotList;
    private static ArrayList<FunctionFramework> funcList;
    private static double scale;
    private static Point origin;
    private static boolean graphing;
    private static boolean plotting;
    
    private static Point oldLocation;
    
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    public GraphPanel() {
        
        super();
               
        scale = 10.0;
        origin = new Point( 0, getHeight() );
        graphing = false;
        plotting = false;
        oldLocation = new Point( 0, 0 );
        
        this.setOpaque( true );
        this.setBackground( Color.white );
        
        init();
    }
    
    private void init() {
        addMouseListener( this );
        addMouseMotionListener( this );
        addPropertyChangeListener( this );
    }
    
    public void setPlotList ( ArrayList<Coordinate> newList ) 
    {
        ArrayList<Coordinate> oldList = plotList;
        plotList = newList;
        firePropertyChange( "plotList", oldList, plotList );
    }
    
    public ArrayList<Coordinate> getPlotList() {
        return plotList;
    }
    
    public void setCoordinate ( Coordinate newCoord, int index ) {
        Coordinate oldCoord = plotList.get( index );
        plotList.set( index, newCoord );
        firePropertyChange( "plotList", oldCoord, newCoord );
    }
    
    public Coordinate getCoordinate ( int index ) {
        return plotList.get( index );
    }
    
    public void setFunctionList( ArrayList<FunctionFramework> newList )
         {
         ArrayList<FunctionFramework> oldList = funcList;
         funcList = newList;
         firePropertyChange( "formList", oldList, funcList );
         }
    
    public ArrayList<FunctionFramework> getFunctionList()
         {
         return funcList;
         }
    
    public void setFunction( FunctionFramework newFunc, int index ) {
        FunctionFramework oldFunc = funcList.get( index );
        funcList.set( index, newFunc );
        firePropertyChange( "formList", oldFunc, newFunc );
    }
    
    public FunctionFramework getFunction( int index ) {
        return funcList.get( index );
    }
    
    public void setGraphing( boolean newgraphing )
         {
         boolean oldGraphing = graphing;
	 graphing = newgraphing;
         firePropertyChange( "graphing", oldGraphing, graphing );
	 }

    public boolean isGraphing()
         {
	 return graphing;
	 }

    public void setPlotting( boolean newplotting ) {
        boolean oldPlotting = plotting;
        plotting = newplotting;
        firePropertyChange( "plotting", oldPlotting, plotting );
    }
    
    public boolean isPlotting() {
        return plotting;
    }
    
    public void setScale( double newScale ) {
        double oldScale = scale;
        scale = newScale;
        firePropertyChange( "scale", oldScale, scale );
    }
    
    public double getScale() {
        return scale;
    }
       
    public void setOrigin( Point newOrigin ) {
        Point oldOrigin = origin;
        origin = newOrigin;
        firePropertyChange( "origin", oldOrigin, origin );
    }
    
    public Point getOrigin() {
        return origin;
    }
    
    public void resetOrigin() {
        setOrigin( new Point( 0, 0 ) );
    }
    
    private Coordinate getMax( Coordinate[] list )
      {
      try
         {
         Arrays.sort( list  );
         return list[ list.length - 1 ];
	 }
      catch( NoSuchElementException nsee )
         {
	 return new Coordinate( 0.0, 0.0 );
	 }
      }

   private Coordinate getMin( Coordinate[] list )
      {
      try
         {
         Arrays.sort( list );
         return list[0];
	 }
      catch( NoSuchElementException nsee )
         {
	 return new Coordinate( 0.0, 0.0 );
	 }
      }
   
    @Override
    public void paint( Graphics g )
         {
	 //g.setColor( Color.blue );
	 g.clearRect( 0, 0, getWidth(), getHeight() );
	 g.setColor( Color.black );
	 g.setFont( g.getFont().deriveFont( (float) 10 ) );
         
	 g.translate( origin.x, origin.y );
         
         //First, the coordinate plane grid lines
         g.drawLine( 0, - origin.y, 0, getHeight() - origin.y );  //y axis line
         g.drawLine( getWidth() - origin.x, getHeight(), 0 - origin.x, getHeight() ); //x axis line

         //Marks on the grid lines
         //x axis marks
	 for( int i = -origin.x + origin.x % ( getWidth() / NUM_MARKS ); i < getWidth() - origin.x; i += getWidth() / NUM_MARKS ) {
	    g.drawLine( i, getHeight() + MARK_LENGTH, i, getHeight() - MARK_LENGTH );
            //System.out.println( origin.x % 10 );
         }
         //y axis marks
	 for( int i = -origin.y + origin.y % ( getHeight() / NUM_MARKS ); i < getHeight() - origin.y; i += getHeight() / NUM_MARKS )
	    g.drawLine( MARK_LENGTH, i, -MARK_LENGTH, i );

         //Labels to indicate scale
	 g.drawString( String.valueOf( scale ), 
		      getWidth() - String.valueOf( scale ).length() - origin.x - LABEL_OFFSET,
		      getHeight() );

         g.drawString( String.valueOf( scale ), 0, 
		      LABEL_OFFSET - origin.y );
         
         //Plot the points
         if( plotting && plotList != null ) {
             for (Coordinate plotList1 : plotList) {
                 g.fillRect(new Double(plotList1.getX() * new Integer(getHeight()).doubleValue() / scale).intValue() - POINT_SIZE / 2, getHeight() - new Double(plotList1.getY() * new Integer(getHeight()).doubleValue() / scale).intValue() - POINT_SIZE / 2, POINT_SIZE, POINT_SIZE);
             }
         }

         //draw the graph
         if( graphing && funcList != null ) {
            for( FunctionFramework func : funcList ) {
                g.setColor( func.getColor() );
                for( int j = -origin.x; j < getWidth() - origin.x; j++ )
                    g.drawLine(j, getHeight() - new Double(func.getY(new Integer( j ).doubleValue() / new Integer( getWidth() ).doubleValue() * scale ) * getHeight() / scale ).intValue(),
                            j + 1, getHeight() - new Double(func.getY(new Integer( j + 1 ).doubleValue() / new Integer( getWidth() ).doubleValue() * scale ) * getHeight() / scale ).intValue());
                }
            }
         }
    
    @Override
    public void propertyChange( PropertyChangeEvent e )  {
        repaint();
    }
    
    @Override
    public void mouseDragged( MouseEvent e ) {
        Point newLocation = e.getLocationOnScreen();
        origin.translate( newLocation.x - oldLocation.x, newLocation.y - oldLocation.y );
        repaint();
        oldLocation = newLocation;
    }
    
    @Override
    public void mouseMoved( MouseEvent e ) {
        
    }
    
    @Override
    public void mouseClicked( MouseEvent e ) {
        
    }
    
    @Override
    public void mousePressed( MouseEvent e ) {
        //System.out.println( "now in mousePressed method" );
        oldLocation = e.getLocationOnScreen();
    }
    
    @Override
    public void mouseReleased( MouseEvent e ) {
        
    }
    
    @Override
    public void mouseEntered( MouseEvent e ) {
        
    }
    
    @Override
    public void mouseExited( MouseEvent e ) {
        
    }

}