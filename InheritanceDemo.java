import java.awt.*;
import java.util.ArrayList;

public class InheritanceDemo
{
   public static void main(String[] args)
   {
     ArrayList<Tile> myTileList = new ArrayList<Tile>();
     myTileList.add(new Tile(20, 20, 50, 50, Color.BLACK));
     myTileList.add(new Circle(20, 20, 50, Color.RED));
     System.out.println(myTileList);
   }
}