/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockwars;

import java.util.ArrayList;

public class SackBST
{
  private Node root;
  private int n;

  // info after a successful find:
  private Node parent;
  private boolean isLeft;

  private ArrayList<String> travList;
  private int travCurrent;

  public SackBST()
  {
    root = null;
    n = 0;
  }
  
  public void add( String s )
  {
    if( n == 0 )
      root = new Node( s );
    else
    {
      Node current = root;
      boolean done = false;

      while( !done )
      {
        if( s.compareTo( current.data ) < 0 )
        {
          if( current.left == null ) 
         {
            current.left = new Node( s );
            done = true;
          }
          else
            current = current.left;
        }
        else
        {
          if( current.right == null )
          {
            current.right = new Node( s );
            done = true;
          }
          else
            current = current.right;
        }
      }
    }

    n++;
  }
// add 

  public void initForTraverse()
  {
    travList = new ArrayList<String>();
    preorderTraverse( root );
    travCurrent = 0;
  }

  public boolean hasNext()
  {
    return travCurrent < travList.size();
  }

  public String next()
  {
    String temp = travList.get(travCurrent);
    travCurrent++;
    //return temp;
     // or, if you are slightly evil:
     //
    return travList.get( travCurrent++ );
  }

  // dump into travList all the data in the subtree
  // rooted at start
  private void preorderTraverse( Node start )
  {
    if( start != null )
    {
      travList.add( start.data );
      preorderTraverse( start.left );
      preorderTraverse( start.right );
    }
  }

  public boolean find( String key )
  {
    return find( key, root, null );
  }

  // given a key to find, a reference to some node, and a reference its
  // parent, find...
  // (start and parent might be null)
  private boolean find( String key, Node start, Node pa )
  {
    if( start == null )
    {
      return false;
    }
    else
    {
      if( key.compareTo( start.data) == 0 )
      {
// found it!        
// set the mysterious instance variables:
        if( pa.left == start )
          isLeft = true;
        else
          isLeft = false;

        parent = pa;
        return true;
      }
      else if( key.compareTo( start.data ) < 0 )
      {
// if it is here, it's in the left subtree
        return find( key, start.left, start );
      }
      else 
      {
// if it is here, it's in the right subtree
        return find( key, start.right, start );
      }
    }
  }

  public String get()
  {
    if( parent == null )
    {
// found item in root
      return root.data;
    }
    else
    {
// found item in a node that has a parent
      if( isLeft )
        return parent.left.data;
      else
        return parent.right.data;
    }
  }

//*****************************************************************

  // given a reference to the root of some subtree and
  // a list, add all the items in the subtree rooted at current
  // to the list
  public void traverseInOrder( Node current, ArrayList<String> list )
  {
    if( current == null )
    {

    }
    else
    {
      // add results of the traversal of the left subtree to list:
      traverseInOrder( current.left, list );
      
      // add this root's data:
      list.add( current.data );

      // add right subtree:
      traverseInOrder( current.right, list );
    }
  }

  public ArrayList<String> traverseInOrder()
  {
    ArrayList<String> result = new ArrayList<String>();
    traverseInOrder( root, result );
    return result;
  }

// -------------------- instance methods but not part of Sack ADT
  public void display( double x, double y, Camera cam, double spread,
                         double levelHeight )
  {
    int h = height( root );
    // System.out.println( "height is " + h );
    display( x, y, root, cam, Math.pow(2,h-1)*spread, levelHeight );    
  }

  private void display( double x, double y, 
                        Node node, Camera cam, 
                        double width, double levelHeight )
  {

    if( node != null )
    {
      cam.drawCenteredText( node.data, x, y );
      double smidge1 = 0.75, smidge2 = 2.5;


      if( node.left != null )
        cam.drawLine( x, y-smidge1, x-width, y - levelHeight + smidge2 );

      if( node.right != null )
        cam.drawLine( x, y-smidge1, x+width, y - levelHeight + smidge2 );

      display( x - width, y - levelHeight, node.left, cam, 
                                           width/2, levelHeight );   
      display( x + width, y - levelHeight, node.right, cam, 
                                           width/2, levelHeight );   
    }   
  }


  // find height of tree (number of levels in longest branch)
  // rooted at the given node

  public int height( Node node )
  {
    if( node == null )
      return 0;
    else if( node.left == null && node.right == null )
      return 1;
    else
    {
      int lh, rh;
      lh = height( node.left );
      rh = height( node.right );
      int h = 1 + (int) Math.max( lh, rh );
      return h;
    }
  }


  public static void main(String[] args)
  {
    SackBST sack = new SackBST();
    sack.add( "fish" );
    sack.add( "dog" );
    sack.add( "ball" );
    sack.add( "Washington" );
    sack.add( "Cylon" );
    sack.add( "Dalek" );
    sack.add( "Spock" );
    sack.add( "Blue Box" );
    sack.add( "TARDIS" );
    sack.add( "shark" );
    sack.add( "goat" );
    sack.add( "yoshi" );
    sack.add( "unicorn" );

/*  old test stuff
    ArrayList<String> list = sack.traverseInOrder(); 
    System.out.println( list );
*/
    sack.initForTraverse(); 
    while( sack.hasNext() )
    {
      System.out.println( sack.next() );
    }

    System.out.println("find goat:" + sack.find("goat") );
    System.out.println("get gives: " + sack.get() );
  }
}

