/*
 * 
 */
package blockwars;

public class Sack3
{
  // static variables:
  public final static int M = 11;

  // IV's:
  private HTNode[] a;  // holds the "hash table"
  private int n;   // number of blocks in the sack

  private int location;  // location where the last find operation succeeded
  private HTNode previous;  // either points to the node before the one that
                              // had the key or is null meaning the first node
                              // from a[location] had the key
  
  private int travIndex;
  private HTNode travCursor;
  private int numberTraversed;

  public Sack3()
  {
    a = new HTNode[M];
    n = 0;
  }
  
  // determine whether there is a block in the sack with the given key
  // (and gather internal information so that get and remove can be
  //  efficiently performed immediately after the find operation, 
  //  if desired)
  public boolean find( Key key )
  {
    int index = h( key );
  
    if( a[index] == null )
    {
      return false;
    }
    else
    {// there is a node below      
      HTNode current = a[index];
      HTNode prev = null;
  
      while( current != null && ! current.data.getKey().equals( key ) )
      {
        prev = current;
        current = current.next;      
      }
  
      // either reached end of linked list or found the target key
      if( current == null )
      {
        return false;
      }
      else
      {
        // save internal data to allow get/remove:
        previous = prev;
        location = index;        
        return true;
      }  
    }
  }
  
  // return a reference to the block that was most recently found
  // using the internal information about the find operation
  public Block get()
  {
    if( previous != null )
      return previous.next.data;
    else
      return a[location].data;
  }
 
//////////////  Construction Zone  ////////////////
  public Block get(int lx, int ly)
  {
    if( previous != null )
      return previous.next.data;
    else
      return a[location].data;
  }
////////////  End Construction Zone  ////////////// 
  // remove the block that was most recently found
  public void remove()
  {
    if( previous == null )
    {
      a[location] = a[location].next;
    }
    else
    {
      previous.next = previous.next.next;
    }
  
    n--;
  }
  
  // return the number of blocks in the sack
  public int size()
  {
    return n;
  }
  
  // add the given block to the sack
  public void add( Block b )
  {
    int index = h( b.getKey() );
  
    if( a[index] == null )
    {// adding the first item to the linked list in this position
      a[index] = new HTNode( b );
    }
    else
    {// adding a later item
      HTNode temp = new HTNode( b );
      temp.next = a[index];
      a[index] = temp;
    }
  
    n++;
  }
  
  // prepare the sack to be traversed
  public void initForTraverse()
  {
    if( n == 0 )
      return;
  
    travIndex = 0;
    while( a[travIndex] == null )
      travIndex++;
  
    travCursor = a[travIndex];
    
    numberTraversed = 0;
  }
  
  // return whether the sack has a next block in the traversal
  // (returns false if the last block in the traversal has
  //   already been reported)
  public boolean hasNext()
  {
    return numberTraversed < n;
  }
  
  // return a reference to the next block in the traversal
  public Block next()
  {
    Block temp = travCursor.data;
  
    numberTraversed++;
  
    // get ready for the next request:
    if( numberTraversed < n )
    {
      if( travCursor.next != null )
      {// still have another node in this list
        travCursor = travCursor.next;
      }
      else
      {// have to move on to another spot in the array
  
        travIndex++;
        while( a[travIndex] == null )
         travIndex++;
  
        travCursor = a[travIndex];
        
      }
    }
  
    return temp;
  }
  
  private int h( Key key )
  {
    return ( (int) Math.abs(7*key.x + 5*key.y + 3)) % M;
  }
  
}// Sack

