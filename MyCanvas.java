import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
/**
MyCanvas.java
Tully, Cassandra
07/16/2021
*/
public class MyCanvas extends JPanel implements KeyListener, MouseListener, MouseMotionListener
{
   //Private Vars
   private String mode = "E";
   private ArrayList<Tile> myShapes = new ArrayList<Tile>();
   private int shapeIndexPos = 0;
   private int offsetX = 0;
   private int offsetY = 0;
   private boolean dragging = false;

   /**
   Constructor for our MyCanvas class
   Sets up the screen correctly with all the variables
   and properties to consider
   */
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
      addMouseMotionListener(this);
   }
   
   /**
    * run when a key is pressed
    * @param e key pressed
    */
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
      else if (key == KeyEvent.VK_C)
      {
         mode = "C";
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
      else if(mode.equals("C"))
      {
         if (key == KeyEvent.VK_ENTER)
         {
            myShapes.clear();
            repaint();
         }
      }
   }
   
   /**
    * run when a key is pressed
    * @param e key pressed
    */
   public void keyReleased(KeyEvent e)
   {
   
   }
   
   /**
    * run when a key is pressed
    * @param e key pressed
    */
   public void keyTyped(KeyEvent e)
   {
      System.out.println(e.getKeyLocation());
   }
   
   //MOUSE STUFF\\
   /**
    * run when a mouse action occurs
    * @param e mouse action (Clicked, Pressed, Entered, Exited, Released)
    */
   public void mouseClicked(MouseEvent e)
   {
  
   }

   /**
    * run when a mouse action occurs
    * @param e mouse action (Clicked, Pressed, Entered, Exited, Released)
    */
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
               offsetX = mouseX-myShapes.get(i).getX();
               offsetY = mouseY-myShapes.get(i).getY();
               dragging = true;
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

   /**
    * run when a mouse action occurs
    * @param e mouse action (Clicked, Pressed, Entered, Exited, Released)
    */
   public void mouseReleased(MouseEvent e)
   {
      dragging = false;
   }

   /**
    * run when a mouse action occurs
    * @param e mouse action (Clicked, Pressed, Entered, Exited, Released)
    */
   public void mouseEntered(MouseEvent e)
   {
   }

   /**
    * run when a mouse action occurs
    * @param e mouse action (Clicked, Pressed, Entered, Exited, Released)
    */
   public void mouseExited(MouseEvent e)
   {
      
   }
   
   //Mouse Movement Stuff
   /**
    * run when a mouse movemnt occurs
    * @param e mouse action Dragged when clicked then moved
    */
   public void mouseDragged(MouseEvent e)
   {
      if (dragging)
      {
            myShapes.get(shapeIndexPos).setX(e.getX() - offsetX);
            myShapes.get(shapeIndexPos).setY(e.getY() - offsetY);
            repaint();
      }
   }

   /**
    * run when a mouse move occurs
    * @param e mouse action enters the component
    */
   public void mouseMoved(MouseEvent e)
   {      

   }
   
   //****************************************************************
  
   /**
    * paint component
    *@param Graphics g representing some paintbrush
    */
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