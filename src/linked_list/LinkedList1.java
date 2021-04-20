



package linked_list;


public class LinkedList1 {

 /**
       The Node class stores a list element
       and a reference to the next node.
    */
    
    private class Node 
    {
        String value;   
        Node next;      
        
        /**
           Constructor.            
           @param val The element to store in the node.
           @param n The reference to the successor node.
        */
        
        Node(String val, Node n)
        {
            value = val;
            next = n;
        } 
        
        /**
           Constructor. 
           @param val The element to store in the node.
        */
        
        Node(String val)
        {
           // Call the other (sister) constructor.
           this(val, null);            
        }
    }	
	 
    private Node first;  // list head
    private Node last;   // last element in list
	     
    /**
       Constructor.
    */
    
    public LinkedList1()
    {
        first = null;
        last = null;        
    }
    
    /**
       The isEmpty method checks to see 
		 if the list is empty.
		 @return true if list is empty, 
		 false otherwise.
    */
    
    public boolean isEmpty()
    {        
        return first == null;       
    }
    
    /**
       The size method returns the length of the list.
       @return The number of elements in the list.
    */
    
    public int size()
    {
       int count = 0;
       Node p = first;     
       while (p != null)
       {
           // There is an element at p
           count ++;
           p = p.next;
       }
       return count;
    }
    
    /**
       The add method adds an element to
		 the end of the list.
       @param e The value to add to the
		 end of the list.       
    */
    
    public void add(String e)
    {
      if (isEmpty()) 
      {
          first = new Node(e);
          last = first;
      }
      else
      {
          // Add to end of existing list
          last.next = new Node(e);
          last = last.next;
      }      
    }
    
    /**
       The add method adds an element at a position.
       @param e The element to add to the list.
       @param index The position at which to add 
		 the element.
       @return 
         
    */
    public boolean add(int index, String e)
    {
         if (index < 0  || index > size()) 
         {
             return false;
         }
         
         // Index is at least 0
         if (index == 0)
         {
             // New element goes at beginning
             first = new Node(e, first);
             if (last == null)
                 last = first;
             return true;
         }
         
         // Set a reference pred to point to the node that
         // will be the predecessor of the new node
         Node pred = first;        
         for (int k = 1; k <= index - 1; k++)        
         {
            pred = pred.next;           
         }
         
         // Splice in a node containing the new element
         pred.next = new Node(e, pred.next);  
         
         // Is there a new last element ?
         if (pred.next.next == null){
             last = pred.next; 
         }
        return true;
    }
    
    /**
       The toString method computes the string
       representation of the list.
       @return The string form of the list.
    */
    
    public String toString()
    {
      StringBuilder strBuilder = new StringBuilder();
      
      // Use p to walk down the linked list
      Node p = first;
      if (first.next==null){
          strBuilder.append(p.value);
      }
      else{
          while (p.next != null){
              String str=p.value+", ";
              strBuilder.append(str); 
              p = p.next;
            }  
          strBuilder.append(p.value);
      }
      return strBuilder.toString(); 
    }
    
    /**
       The remove method removes the element at an index.
       @param index The index of the element to remove. 
       @return The element removed.  
       @exception IndexOutOfBoundsException When index is 
                  out of bounds.     
    */
    
    public String remove(int index)
    {
       String element;  // The element to return
       if (index < 0 || index >= size())
       {  
           element=null;
           return element;
//           String message = String.valueOf(index);
//           throw new IndexOutOfBoundsException(message);
       }
       
//       String element;  // The element to return     
       if (index == 0)
       {
          // Removal of first item in the list
          element = first.value;    
          first = first.next;
          if (first == null)
              last = null;             
       }
       else
       {
          // To remove an element other than the first,
          // find the predecessor of the element to
          // be removed.
          Node pred = first;
          
          // Move pred forward index - 1 times
          for (int k = 1; k <= index -1; k++)
              pred = pred.next;
          
          // Store the value to return
          element = pred.next.value;
          
          // Route link around the node to be removed
          pred.next = pred.next.next;  
          
          // Check if pred is now last
          if (pred.next == null)
              last = pred;              
       }
       return element;        
    }  
    
    /**
       The remove method removes an element.
       @param element The element to remove.
       @return true if the remove succeeded, 
		 false otherwise.
    */
    
    public boolean remove(String element)
    {
       if (isEmpty()) 
           return false;      
      
       if (element.equals(first.value))
       {
          // Removal of first item in the list
          first = first.next;
          if (first == null)
              last = null;       
          return true;
       }
      
      // Find the predecessor of the element to remove
      Node pred = first;
      while (pred.next != null && 
             !pred.next.value.equals(element))
      {
          pred = pred.next;
      }

      // pred.next == null OR pred.next.value is element
      if (pred.next == null)
          return false;
      
      // pred.next.value  is element
      pred.next = pred.next.next;    
      
      // Check if pred is now last
      if (pred.next == null)
          last = pred;
      
      return true;       
    }
    
    public void sort(){
        Node current=first;
        Node next_node;
        Node temp=new Node("empty");
        if (current==null){
            last=null;
        }
        else{
            while (current!=null){
                next_node=current.next;
                while (next_node!=null){
                    if(current.value.compareTo(next_node.value)>0){
                        temp.value=current.value;
                        current.value=next_node.value;
                        next_node.value=temp.value;
                    }
                    next_node=next_node.next;
                }
                current=current.next;
            }
        }
        
    }
     
    public void reverse(){
        Node current=first;
        Node next_node;
        Node prev_node=null;
        
        while(current.next!=null){
            next_node=current.next;
            current.next=prev_node;
            prev_node=current;
            current=next_node;
        }
        first=current;
        first.next=prev_node;

    }
    
    
}
