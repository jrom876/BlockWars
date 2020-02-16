
package blockwars;

/**
 *
 * @author Jake
 */
import static blockwars.Block.getRandomValues;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.io.*;

public class BlockWars extends Basic
{
  private static Random randy = new Random(1);

  public static void main(String[] args)
  {
    // example: hard-coded location and size of window:
    BlockWars a = new BlockWars("Block Wars!!!", 0, 0, 440, 470);
    //  ("Block Wars!!!", 0, 0, 440, 470);

  }

  // instance variables for the application:
  //------------------------------------------------------------------

  private Sack3 sack;
  private Block player;
  private Block monster;
  private Block gorgon;
  //private Block superGorgon;

  //------------------------------------------------------------------

  public BlockWars( String title, int ulx, int uly, int pw, int ph )
  {
    super(title,ulx,uly,pw,ph);

    // code to initialize instance variables before animation begins:
    //------------------------------------------------------------------

    sack = new Sack3();

    player = new Block( 12, 12, "player" );
    sack.add( player );
    //monster = new monster( 2, 22, "monster", 6 );
    //sack.add( monster );
    gorgon = new Block( 20, 1, "gorgon" );
    sack.add( gorgon );
    //monty = new monster( getRandomValues(25), getRandomValues(25), "monty");
    //sack.add( monty );
    //cam.drawText(player.strHealth(), b.getX(), b.getY()+1);
    // generate some random blocks:
makeBlocks("wall", 30);
makeBlocks("food", 20);
makeBlocks("monster", 15);
makeBlocks("gorgon", 3);
//makeBlocks("player", 3);
//makeMonster("monster", 10);
//makeGorgon("gorgon", 5);
//sack.add( new Block( 12, 12, "player" ) );
//sack.add( new Block( 6, 20, "wall" ) );

    // code to finish setting up entire window:
    //------------------------------------------------------------------
  
    setBackgroundColor( Color.black );

    // code to set up camera(s)
    //------------------------------------------------------------------
  
    cameras.add( new Camera( 20, 50, 400, 400,
                         0, 25, 0,
                         Color.white ) );
    /*
    ( new Camera( 20, 50, 400, 400,
                         0, 25, 0,
                         Color.white ) );
    */

    //------------------------------------------------------------------
    // start up the animation:
    super.start();
  }

  public int getRandomValues( int max )
  {	
    int value;
    int count = 0;
    Random ranGen = new Random();      
    value = ranGen.nextInt(max);
      switch (value) {
          case 0:
              count = -1;
              break;
          case 1:
              count = 0;
              break;
          case 2:
              count = 1;
              break;
          default:
              break;
      }
    return count;
  }
  
  private void makeBlocks( String k, int num )
  {
    //int overlapCount = 0;
    for( int j=1; j<=num; j++ )
    {
      int x=randy.nextInt(25);
      int y=randy.nextInt(25);
      //int h = 7;

    System.out.println("add block at " + x + " " + y );
          if( ! sack.find( new Key(x,y) ) )
          {
            sack.add( new Block( x, y, k ) );
          }
          else
          {
    System.exit(1);
            j--;
          }
        }
      }
  
  private void makeMonster(String k,  int num )
  {
    for( int j=1; j<=num; j++ )
    {
      int x=randy.nextInt(25);
      int y=randy.nextInt(25);

    System.out.println("start monster at "+x+", "+y);
          if( ! sack.find( new Key(x,y) ) )
          {
            sack.add( new monster( x, y, k ) );
          }
          else
          {
    System.exit(1);
            j--;
          }
        }
      } 

  private void makeGorgon( String k, int num )
  {
    for( int j=1; j<=num; j++ )
    {
      int x=randy.nextInt(25);
      int y=randy.nextInt(25);
      //int h = 15;

    System.out.println("start gorgon at "+x+", "+y);
          if( ! sack.find( new Key(x,y) ) )
          {
            sack.add( new gorgon( x, y, k ) );
            //kind = "gorgon";
          }
          else
          {
    System.exit(1);
            j--;
          }
        }
      }    
  
 @Override
  public void step()
  {
    if( player.getHealth() <= 0 )
    {
      Camera cam = cameras.get(0);
      cam.activate();
      cam.setColor( Color.red );
      cam.drawCenteredText( "You lose!", 12.5, 12.5 );
    }    
    if (player.getHealth() >=100)
    {
      Camera cam = cameras.get(0);
      cam.activate();
      cam.setColor( Color.black );
      cam.drawCenteredText( "We surrender!!", 12.5, 12.5 );
    }  

    else
    {
      // code to update the world and display the world:
      //------------------------------------------------------------------
  
      // make monsters move randomly:
  
      //-----------------------------------------------------------
      Camera cam = cameras.get(0); 
      cam.activate();
  
      sack.initForTraverse();
      while( sack.hasNext() )
      {
        Block b = sack.next();        
        b.draw( cam );
        double bh = b.getHealth();
        if ("monster".equals(b.getKind()))
        {
            b.moveMonster(getRandomValues(100), getRandomValues(100), sack); 
        } 
        
        else if ("gorgon".equals(b.getKind()))
        {
          b.moveGorgon(getRandomValues(100), getRandomValues(100), sack);
        } 
      }
  
      //------------------------------------------------------------------
    }
  }

  public void keyTyped( KeyEvent e )
  {
    char key = e.getKeyChar();
    
    // code to change instance variables depending on key:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void keyPressed( KeyEvent e )
  {
    int code = e.getKeyCode();

    // code to change instance variables depending on which key pressed (code):
    //------------------------------------------------------------------

    if( code == KeyEvent.VK_LEFT )
    {
      player.move( -1, 0, sack );
    }
    else if( code == KeyEvent.VK_RIGHT )
    {
      player.move( 1, 0, sack );
    }
    else if( code == KeyEvent.VK_UP )
    {
      player.move( 0, 1, sack );
    }
    else if( code == KeyEvent.VK_DOWN )
    {
      player.move( 0, -1, sack );
    }

    //------------------------------------------------------------------
  }

  public void mouseMoved(MouseEvent e)
  {
    super.mouseMoved(e);

    // code to respond to mouse motion:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseDragged(MouseEvent e)
  {
    super.mouseDragged(e);

    // code to respond to mouse motion with a button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseClicked(MouseEvent e)
  {
    super.mouseClicked(e);

    // code to respond to mouse click:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mousePressed(MouseEvent e)
  {
    super.mousePressed(e);

    // code to respond to mouse button pressed:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseReleased(MouseEvent e)
  {
    super.mouseReleased(e);

    // code to respond to mouse button released:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseEntered(MouseEvent e)
  {
    super.mouseEntered(e);

    // code to respond to mouse entering window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

  public void mouseExited(MouseEvent e)
  {
    super.mouseExited(e);

    // code to respond to mouse exiting window:
    //------------------------------------------------------------------

    //------------------------------------------------------------------
  }

}

