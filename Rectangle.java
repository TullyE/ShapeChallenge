import java.awt.*;
import java.awt.geom.*;


public class Rectangle extends Tile
{
    public Rectangle(int x, int y, int h, int w, Color c)
    {
        super(x, y, w, h, c);
    }

    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double myRect = new Rectangle2D.Double(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        g2.setColor(super.getColor());
        g2.fill(myRect);
    }
}