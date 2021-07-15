import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

/**
Basic Graphics Program
NAMES HERE
06/22/2021
*/
public class MyCanvas extends JPanel implements KeyListener, MouseListener
{
   // ****************************************************************************************
   // Notice how the class implements the KeyListener interface
   // Check your import statements at the top of your code and make sure everything is there!
   // ****************************************************************************************
   
   /**
   Constructor for our MyCanvas class
   Sets up the screen correctly with all the variables
   and properties to consider
   */
   private String mode = "E";
   private ArrayList<Tile> myShapes = new ArrayList<Tile>();
   private int shapeIndexPos = 0;

   public MyCanvas()
   {
      setSize(new Dimension(500, 500));
      setPreferredSize(new Dimension(500,500));
      setVisible(true);
      // *****************************************
      // Request key input from the screen
      setFocusable(true);
      requestFocusInWindow();
      addKeyListener(this);
      addMouseListener(this);
   }
   
   public void keyPressed(KeyEvent e)
   {

      int key = e.getKeyCode();
      if(key == KeyEvent.VK_E)
      {
         mode = "E";
      }
      else if(key == KeyEvent.VK_D)
      {
         mode = "D";
      }
      else if(key == KeyEvent.VK_B)
      {
         mode = "B";
      }
      else if (key == KeyEvent.VK_F)
      {
         mode = "F";
      }
      else if (key == KeyEvent.VK_M)
      {
         mode = "M";
      }
      if (mode.equals("E"))
      {
         if (key == KeyEvent.VK_N)
         {
            int CircRect = new Random().nextInt(2);
            if(CircRect == 0)
            {
               //ADD CIRCLE OBJECT TO THE ARRAY LIST 
               int radius = new Random().nextInt(130) + 20;
               int x = new Random().nextInt(500-(radius*2));
               int y = new Random().nextInt(500-(radius*2));
               myShapes.add(new Circle(x, y, radius, new Color((new Random().nextInt(255)), new Random().nextInt(255), new Random().nextInt(255))));
               //REPAINT
               repaint();
            }
            else
            {
               //ADD RECTANGLE OBJECT TO THE ARRAY LIST
               int width = new Random().nextInt(130) + 20;
               int height = new Random().nextInt(130) + 20;
               int x = new Random().nextInt(500-width);
               int y = new Random().nextInt(500-height);
               myShapes.add(new Rectangle(x, y, width, height, new Color((new Random().nextInt(255)), new Random().nextInt(255), new Random().nextInt(255))));
               //REPAINT
               repaint();
            }
         }
      }

   }
   
   public void keyReleased(KeyEvent e)
   {
   
   }
   
   public void keyTyped(KeyEvent e)
   {
      System.out.println(e.getKeyLocation());
   }
   
   //MOUSE STUFF \/
   public void mouseClicked(MouseEvent e)
   {
  
   }

   public void mousePressed(MouseEvent e)
   {
      int mouseX = e.getX();
      int mouseY = e.getY();

      if (mode.equals("D"))
      {
         for(int i = myShapes.size()-1; i >= 0; i -= 1)
         {
            if (myShapes.get(i).isHit(mouseX, mouseY))
            {
               myShapes.remove(i);
               repaint();
               break;
            }
         }
      }
      if (mode.equals("M"))
      {
         for(int i = myShapes.size()-1; i >= 0; i -= 1)
         {
            if (myShapes.get(i).isHit(mouseX, mouseY))
            {
               System.out.println("Set indexpos");
               shapeIndexPos = i;
               break;
            }
         }
      }
      if (mode.equals("B"))
      {
         for(int i = myShapes.size()-1; i >= 0; i -= 1)
         {
            if (myShapes.get(i).isHit(mouseX, mouseY))
            {
               Tile tempShape = (Tile) myShapes.get(i);
               myShapes.remove(i);
               myShapes.add(0, tempShape);
               repaint();
               break;
            }
         }
      }
      if (mode.equals("F"))
      {
         for(int i = myShapes.size()-1; i >= 0; i -= 1)
         {
            if (myShapes.get(i).isHit(mouseX, mouseY))
            {
               Tile tempShape = (Tile) myShapes.get(i);
               myShapes.remove(i);
               myShapes.add(tempShape);
               repaint();
               break;
            }
         }
      }
   }

   public void mouseReleased(MouseEvent e)
   {
      if (mode.equals("M"))
      {
         myShapes.get(shapeIndexPos).setX(e.getX());
         myShapes.get(shapeIndexPos).setY(e.getY());
         repaint();
      }
   }

   public void mouseEntered(MouseEvent e)
   {
      
   }

   public void mouseExited(MouseEvent e)
   {
      
   }
   // ***********************************************************************
  
   public void paintComponent(Graphics g)
   {
      // Clear the screen
      super.paintComponent(g);
      // Casting Operation
      // g2 -> representing some paintbrush
      Graphics2D g2 = (Graphics2D) g;

      for(Tile shape : myShapes)
      {
         shape.draw(g2);
      }
   }
   
   /**
   Main Method
   Makes a new JFrame and adds the Canvas to it
   */
   public static void main(String[] args)
   {
      JFrame myFrame = new JFrame("ShapeChallenge");
      myFrame.setSize(new Dimension(500,500));
      myFrame.setPreferredSize(new Dimension(500,500));
      myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myFrame.add(new MyCanvas());
      myFrame.pack();
      myFrame.setVisible(true);
   }

}