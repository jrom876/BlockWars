/*
 * 
 */
package blockwars;

//import static blockwars.Block.getRandomValues;
import java.awt.Color;
import java.util.Random;

public class Block
{
//////////// Variable declarations ///////////// 
  int x, y;  // coordinates of center of the block
  String kind;  // the kind of block
  
  double health;
   
/////////////// Block constructors /////////////  
  public Block(int xIn, int yIn, String k, int h)
  {
    x=xIn;  y=yIn;
    kind = k; 
    health = h;
  }

  public Block( int xIn, int yIn, String k )
  {
    x=xIn;  y=yIn;
    kind=k;
    health = 20;
  }
  
  public Block( int xIn, int yIn )
  {
    x=xIn;  y=yIn;
    kind="Block";
    health = 1;
  }
  ////////////////////////////////////////////////
  //////////////  Block methods //////////////////
  ////////////////////////////////////////////////  
  public Key getKey()
  {
    return new Key( x, y );
  }
  public int getX()
  {
    return x;
  }
  public int getY()
  {
    return y;
  }

  public String getKind()
  {
    return kind;
  }  
  
  public double getHealth()
  {
    return health;
  }
  
  public double setHealth(int n)
  {
    health = n;  
    return health;
  }
  
  public double incHealth(int n)
  {
    health +=n;  
    return health;
  }
  
  public String strHealth()
  {
    double a = getHealth();
    String b = String.valueOf(a);
    //System.out.println(b);
    return b;
  }
///////////  Draw  ////////////
  public void draw( Camera cam )
  {
    if( kind.equals("wall") )
    {
      cam.setColor( Color.gray );
      //cam.drawText(strHealth(), x, y);
          }
    else if( kind.equals("player") )
    {
      cam.setColor( Color.blue ); 
      cam.drawText(strHealth(), x-1, y+1);
    }
    else if( kind.equals("monster") )
    {
      cam.setColor( Color.red );            
      cam.drawText(strHealth(), x-1, y+1);
      //health = setHealth(1);
    }
    else if( kind.equals("food") )
      cam.setColor( Color.green );
    
    else if( kind.equals("gorgon") )
    {
      cam.setColor( Color.magenta );
      cam.drawText(strHealth(), x-1, y+1);
      //health = setHealth(10);
    }    
    //cam.drawText(strHealth(), x-0.5, y+1);
    cam.fillRect( x-0.5, y-0.5, 1, 1 );    
  }

  /////////////  Move  ///////////////
  private void actuallyMove( int dx, int dy, Sack3 sack )
  {
    sack.find( new Key( x, y ) );
    sack.remove();
    x += dx;  y += dy;
    sack.add( this );
  }

  public void move( int dx, int dy, Sack3 sack )
  {
        if( ! sack.find( new Key( x+dx, y+dy ) ) )
        {// the location is empty
          actuallyMove( dx, dy, sack );
        }
        else
        {
          Block b = sack.get(); 
            if( b.getKind().equals( "food" ) )
            {
              //b.incHealth(6);
              health +=6;
              sack.remove();
              actuallyMove( dx, dy, sack );
            }
            else if( b.getKind().equals( "wall" ) )
            {
              sack.remove();
              actuallyMove( dx, dy, sack );
              health --;
            }
            else if( b.getKind().equals( "monster" ) )
            {
              sack.remove();
              actuallyMove( dx, dy, sack );
              health -=3;
            }
            else if( b.getKind().equals( "gorgon" ) )
            {
              sack.remove();
              actuallyMove( dx, dy, sack ); 
              health -=6;
            }
            else if( b.getKind().equals( "player" ) )
            {
              b.incHealth(-6);
              health -=6;
            }          
        }    
    }
 
 ///////////////////////////////////////////////
  public void moveMonster( int dx, int dy, Sack3 sack )
  {
    if( ! sack.find( new Key( x+dx, y+dy ) ) )
    {// the location is empty
      actuallyMove( dx, dy, sack );
    }
    else
    {
      //Block d = sack.get();
      Block b = sack.get();
      int mh = 5;
      b.setHealth(mh);
      //int mh = 8;
      if( b.getKind().equals( "food" ) )
      {
        sack.remove();
        actuallyMove( dx, dy, sack );
        //health = mh;
        b.setHealth(mh + 3);        
      }            
      /*else if( b.getKind().equals( "player" ) )
      {
        mh = b.setHealth(mh - 6);
        health = mh;        
      }*/
      
    }
  } 
 ///////////////////////////////////////// 
  public void moveGorgon( int dx, int dy, Sack3 sack )
  {
    if( ! sack.find( new Key( x+dx, y+dy ) ) )
    {// the location is empty
      actuallyMove( dx, dy, sack );
    }
    else
    {
      Block b = sack.get();
      b.setHealth(10);
      if( b.getKind().equals( "food" ) )
      {
        //health += 3;
        sack.remove();
        actuallyMove( dx, dy, sack );
      }     
      /*else if( b.getKind().equals( "player" ) )
      {
        health = health -6;        
      }*/
    }
  } 
////////////////////////////////////////
/////////////////////////////////////////
  public static int getRandomValues()
  {	
    int value;
    int count = 0;
    Random ranGen = new Random();      
    value = ranGen.nextInt(2);
      switch (value) {
          case 0:
              count = -25;
              break;
          case 1:
              count = 0;
              break;
          case 2:
              count = 25;
              break;
          default:
              break;
      }
    return count;
  }

  public Key moveRandy()
  {
    //Random randy = new Random(6);
    int dx = getRandomValues();
    int dy = getRandomValues();
    x += dx;  
    y += dy;
    Key d = new Key(x,y);
    return d;
  }  
  
public int moveRandyX()
{
    //Random randy = new Random(6);
    int dx = getRandomValues();
    x += dx; 
    return x;
}  
        
public int moveRandyY()
{
    //Random randy = new Random(6);
    int dy = getRandomValues();
    y += dy;
    return y;
}  
  
  public int compareTo( Block other )
  {
    return this.getKey().compareTo( other.getKey() );
  }

  @Override
  public String toString()
  {
      return "(" + x + "," + y + ")";
  }

    int compareTo(String data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

