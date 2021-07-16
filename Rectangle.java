import java.awt.*;
import java.awt.geom.*;
/**
Rectangle.java
Tully, Cassandra
07/16/2021
*/
public class Rectangle extends Tile
{
    /**
     * default constructor of a rectangle same as a tile
     * @param x coord
     * @param y coord
     * @param h height
     * @param w width
     * @param c color
     */
    public Rectangle(int x, int y, int h, int w, Color c)
    {
        super(x, y, w, h, c);
    }

    /**
     * Draws this tile using the given graphics pen.
     * @param g2 the paintbrush
     */
    public void draw(Graphics2D g2)
    {
        Rectangle2D.Double myRect = new Rectangle2D.Double(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        g2.setColor(super.getColor());
        g2.fill(myRect);
    }
}