//********************************************************************
//  Einstein.java       Author: Lewis/Loftus
//
//  Demonstrates a basic applet.
//********************************************************************

import javax.swing.JApplet;
import java.awt.*;

public class Drawing extends JApplet
{
   //-----------------------------------------------------------------
   //  Draws a quotation by Albert Einstein among some shapes.
   //-----------------------------------------------------------------
   public void paint (Graphics page){
       
	
       	// draw basic polygons
      	int[] x_points = new int[]{23, 160, 40, 93};
		int[] y_points = new int[]{40, 50, 6, 92};
		page.drawPolygon(x_points, y_points, 4);
		page.fillPolygon(x_points, y_points, 4);
		
		// try a polyline
		int[] x1_points = new int[]{3, 100, 193, 84};
		int[] y1_points = new int[]{140, 5, 65, 111};
		page.drawPolyline(x1_points, y1_points, 4);
		
		
		// use plygon class and pre-defined colours
		int[] x2_points = new int[]{23, 160, 40, 93};
		int[] y2_points = new int[]{140, 150, 16, 192};
		Polygon myPoly = new Polygon(x2_points, y2_points, 4); 
		page.setColor(Color.green);
		page.drawPolygon(myPoly);
		
		//	use plygon class and pre-defined colours
		int[] x3_points = new int[]{150, 260, 140, 193};
		int[] y3_points = new int[]{140, 150, 160, 192};
		Polygon myPoly2 = new Polygon(x3_points, y2_points, 4); 
		page.setColor( new Color(245,192,137));
		page.fillPolygon(myPoly2);
		
		// clear the space
		page.clearRect(50, 50, 140, 240);
		
      
   }
      
}
