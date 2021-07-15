import java.awt.*;
import java.awt.geom.*;

public class Circle extends Tile
{
   // Make another class variable for the Circle class
   private int radius;
   
   public Circle(int x, int y, int r, Color c)
   {
      // Use the parent's constructor/superclass constructor
      super(x, y, r * 2, r * 2, c);
      // Assign all other class variables to the appropriate values
      this.radius = r;
   }

   public void draw(Graphics2D g2)
   {
      Ellipse2D.Double myCirc = new Ellipse2D.Double(super.getX(), super.getY(), this.radius, this.radius);
      g2.setColor(super.getColor());
      g2.fill(myCirc);
   }

   public boolean isHit(int x, int y)
   {
      if (x > super.getX() && x < super.getX()+this.radius)
      {
         if(y > super.getY() && y < super.getY() + this.radius)
         {
            return true;
         }
      }
      return false;
   }
}