/*
 * 
 */
/*package blockwars;

import java.util.Random;

class gorgon extends Block 
{    
 //   gorgon g = new gorgon(x, y, kind);    
    //private static Random randy = new Random(1);
    
    public gorgon(int xIn, int yIn, String k, int h)
	{      
            super(xIn, yIn, k, h);      
            x = xIn;  
            y = yIn;
            kind = k;
            health = h;
	}
    
    public gorgon(int xIn, int yIn, String k)
	{      
            super(xIn, yIn, k);      
            x = xIn;  
            y = yIn;
            kind = k;
            health = 12;
	}
    
    @Override
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
        health += 3;
        sack.remove();
        actuallyMove( dx, dy, sack );
      }
      else if( b.getKind().equals( "wall" ) )
      {
        health --;
      }
      else if( b.getKind().equals( "monster" ) )
      {
        health = health + 1;        
      }
      else if( b.getKind().equals( "gorgon" ) )
      {
        health = health + 1;        
      }
      else if( b.getKind().equals( "player" ) )
      {
        health = health -6;        
      }
    }
  }
    
    private void actuallyMove( int dx, int dy, Sack3 sack )
  {
    sack.find( new Key( x, y ) );
    sack.remove();
    x += dx;  y += dy;
    sack.add( this );
  }
}
    */