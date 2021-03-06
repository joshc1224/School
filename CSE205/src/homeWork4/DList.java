package homeWork4;
//**************************************************************************************************
// CLASS: DList (DList.java)
//
// DESCRIPTION
// Implements a doubly linked list data structure where each element is an Integer.
//
// AUTHOR
// Kevin R. Burger (burgerk@asu.edu)
// Computer Science & Engineering Program
// Fulton Schools of Engineering
// Arizona State University, Tempe, AZ 85287-8809
// Web: http://www.devlang.com
//**************************************************************************************************

/**
 * Implements a doubly linked list of Integers.
 */
public class DList {

    //==============================================================================================
    // DList Instance Variables
    //==============================================================================================

    /**
     * For a nonempty DList, mHead is a reference to the first Node in the DList. For an empty
     * DList, mHead will be null.
     */
    private Node mHead;

    /**
     * The number of Nodes containing data in this DList. For an empty DList, mSize is 0.
     */
    private int mSize;

    /**
     * For a nonempty DList, mTail is a reference to the last Node in the DList. For an empty DList,
     * mTail will be null.
     */
    private Node mTail;

    //==============================================================================================
    // DList Instance Methods
    //==============================================================================================

    /**
     * Creates an empty DList. For an empty DList, mHead = null, mTail = null, and mSize = 0.
     */
    public DList() {
        setHead(null);
        setTail(null);
        setSize(0);
    }

    /**
     * Creates a new DList with one element containing pData.
     */
    public DList(Integer pData) {
        // Create a new Node storing pData. The ctor makes the mPrev and mNext references null.
        Node newNode = new Node(pData);

        // Make the mHead reference refer to the new node.
        setHead(newNode);

        // Make the mTail reference refer to the new node.
        setTail(newNode);

        // The size of the list is now 1.
        setSize(1);
    }

    /**
     * Inserts a new Node containing pData into this DList at index pIndex. Shifts the element
     * currently at that index (if it exists) and succeeding elements to the right (i.e., adds
     * one to their indices).
     *
     * Note that if pIndex == getSize() the new element is appended to the list.
     *
     * Throws an IndexOutOfBoundsException if pIndex < 0 or pIndex > size of the DList.
     */
   /* public void add(int pIndex, Integer pData) throws IndexOutOfBoundsException {
        // Check for pIndex out of bounds.
        if (pIndex < 0 || pIndex > getSize()) throw new IndexOutOfBoundsException();

        // Are we appending?
        if (pIndex == getSize()) {
            // Create a new Node storing pData. The mNext reference is initialized to null because
            // the new Node will become the tail Node of the DList and the mNext reference of the
            // tail Node is always null. The mPrev reference is initialized to mTail so it will
            // refer to the Node preceding the new Node.
            Node newNode = new Node(pData, getTail(), null);

            // If this DList is empty the new Node becomes the head Node. Otherwise change mNext of
            // the tail Node to refer to the new Node.
            if (isEmpty()) setHead(newNode);
            else getTail().setNext(newNode);

            // In any case, we must change mTail to refer to the new Node that is now at the tail.
            setTail(newNode);
        }

        // We are not appending.
        else {
            // Get a reference to the Node at pIndex. We are inserting a new Node before 'node'.
            Node node = getNodeAt(pIndex);

            // Create a new Node storing pData. Set the mPrev reference of the new Node to refer to
            // the Node preceding 'node'. Set the mNext reference of the new Node to refer to
            // 'node'.
            Node newNode = new Node(pData, node.getPrev(), node);

            // If we are not inserting at pIndex = 0 then we need to change the mNext reference of
            // the Node preceding 'node' to refer to the new Node.
            if (pIndex != 0) node.getPrev().setNext(newNode);

            // Make the mPrev reference of 'node' refer to the new Node. The result of these three
            // operations is to "link" the the new Node into the DList.
            node.setPrev(newNode);

            // Are we inserting at index 0? If so, we need to change the head to refer to the new
            // Node because the new Node is now at head.
            if (pIndex == 0) setHead(newNode);
        }

        // We have added a new Node to the DList. Increment the size of the DList.
        setSize(getSize() + 1);
    }*/
    /**																														//Question 4.4
     * new add method that uses three helper methods to add new nodes
     * @param pIndex  Index to add the node at
     * @param pData Data of the node
     * @throws IndexOutOfBoundsException if the index is outside the bounds of the List(less than 0 or greater than size)
     */
    public void add(int pIndex, Integer pData) throws IndexOutOfBoundsException {
    	 if (pIndex < 0 || pIndex > getSize()) throw new IndexOutOfBoundsException();
    	 
    	 if(pIndex==0) {
    		 addHead(pData);
    	 }else if(pIndex==getSize()) {
    		 addTail(pData);
    	 }else {
    		 addInterior(pData, getNodeAt(pIndex));
    	 }
    }
    /**
     * adds a head node to the List
     * @param pData data to be stored in the new head node
     */
    protected void addHead(Integer pData) {
    	Node newNode = new Node(pData, null, getHead());
    	if(getHead()==null) {
    		setTail(newNode);
    	}else {
    		getHead().setPrev(newNode);
    	}
    	setHead(newNode);
    	setSize(getSize()+1);
    }
    /**
     * adds a tail node to the end of the list
     * @param pData data to be stored in the new tail node
     */
    protected void addTail(Integer pData) {
    	Node newNode = new Node(pData, getTail(), null);
    	if(getTail()==null) {
    		setHead(newNode);
    	}else {
    		getTail().setNext(newNode);
    	}
    	setTail(newNode);
    	setSize(getSize()+1);
    }
    /**
     * adds a node that is not the head or the tail
     * @param pData Data to be stored in the new node
     * @param pNode Node to insert the new Node before
     */
    protected void addInterior(Integer pData, Node pNode) {
    	if(pNode==getHead()) {
    		addHead(pData);
    	}else {
    		Node newNode = new Node(pData, pNode.getPrev(), pNode);
    		pNode.getPrev().setNext(newNode);
    		pNode.setPrev(newNode);
    		setSize(getSize()+1);
    	}
    	
    }																													//Question 4.4 End
    /**																													//Question 4.7
     * Prepends a new Node storing pData to this DList.
     */
    public void prepend(Integer pData) {
        //add(0, pData);
    	addHead(pData);
    }
    /**
     * Appends a new Node storing pData to this DList. Note that passing an index of getSize() to
     * add(int pIndex, Integer pData) will cause add() to perform an append operation.
     */
    public void append(Integer pData) {
        //add(getSize(), pData);
    	addTail(pData);
    }																													//Question 4.7 end

    /**
     * Removes all of the elements from the DList. After this operation the DList will be empty.
     */
    public void clear() {
        // To clear the list is simple. Simply remove the node at index 0 until the list becomes
        // empty.
        while (!isEmpty()) { remove(0); }
    }

    /**
     * Returns the element at index pIndex.
     *
     * Thows IndexOutOfBoundsException if pIndex < 0 or pIndex >= mSize.
     */
    public Integer get(int pIndex) throws IndexOutOfBoundsException {
        return getNodeAt(pIndex).getData();
    }

    /**
     * Accessor method for the mHead field.
     */
    protected Node getHead() {
        return mHead;
    }

    /**
     * Returns a reference to the Node at index pIndex.
     *
     * Thows IndexOutOfBoundsException if pIndex < 0 or pIndex >= getSize()
     */
    protected Node getNodeAt(int pIndex) throws IndexOutOfBoundsException {
        // Check for pIndex out of bounds and throw exception is necessary.
        if (pIndex < 0 || pIndex >= getSize()) throw new IndexOutOfBoundsException();

        // Since accessing the head and tail nodes is a common operation we check for those cases
        // first.
        if (pIndex == 0) return getHead();
        else if (pIndex == getSize() - 1) return getTail();

        // Otherwise, start at the node at index 1 and walk forward until the node at index pIndex
        // is reached and then return it.
        Node node = getHead().getNext();
        for (int index = 1; index < pIndex; ++index) node = node.getNext();
        return node;
    }

    /**
     * Accessor method for the mSize field.
     */
    public int getSize() {
        return mSize;
    }

    /**
     * Accessor method for the mTail field.
     */
    protected Node getTail() {
        return mTail;
    	
    }

    /**
     * Returns true if this DList is empty.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Removes the element at index pIndex from this DList. Shifts any succeeding elements to
     * the left (i.e., subtracts one from their indices). Returns the element that was removed from
     * the list.
     *
     * Throws an IndexOutOfBoundsException is pIndex < 0 || pIndex >= getSize().
     */
    /*public Integer remove(int pIndex) throws IndexOutOfBoundsException {
        Node node = getNodeAt(pIndex);

        // Are we removing the only element in a list with one element?
        if (getSize() == 1) {
            setHead(null);
            setTail(null);
        }

        // Else are we removing the head node in a list with more than one element (note: we will
        // not get here if the list has only one element)?
        else if (pIndex == 0) {
            // Change the prev reference of the next node to null because the next node will now
            // be the head node in the list.
            node.getNext().setPrev(null);

            // Since we removed the head node, we have to change the head reference to refer to the
            // next node succeeding the one that was just removed.
            setHead(node.getNext());
        }

        // Else are we removing the tail node in a list with more than one element (note: we will
        // not get here if the list has only one element)?
        else if (pIndex == getSize() - 1) {
            // Change the next reference of the previous node to null because the previous node will
            // now be the tail node in the list.
            node.getPrev().setNext(null);

            // Since we removed the tail node, we have to change the tail reference to refer to the
            // previous node preceding the one that was just removed.
            setTail(node.getPrev());
        }

        // We are not removing the head or tail node.
        else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }

        // We have removed a Node so decrement the size of the list.
        setSize(getSize() - 1);

        // Return the data stored at the removed Node.
        return node.getData();
    }*/
    /**																									//Question 4.3
     * new remove method that incorporates modular helper methods
     * @param pIndex index to remove node at
     * @return data value of node removed
     * @throws IndexOutOfBoundsException if the index is outside the size of the list
     */
    public Integer remove(int pIndex) throws IndexOutOfBoundsException{
    	if(pIndex<0||pIndex>getSize()) {
    		throw new IndexOutOfBoundsException();
    	}
    	Node node = getNodeAt(pIndex);
    	Integer data = node.getData();
    	if(getHead()==node) {
    		removeHead();
    	}else if(getTail()==node) {
    		removeTail();
    	}else {
    		removeInterior(node);
    	}
    	return data;
    }
    /**
     * removes the head node of the list
     * @return the data of the head before removing it
     */
    protected Integer removeHead() {
    	Integer data = getHead().getData();
    	if(getSize()==1) {
    		setHead(null);
    		setTail(null);
    	}else {
    		getHead().getNext().setPrev(null);
    		setHead(getHead().getNext());
    	}
    	setSize(getSize()-1);
    	return data;
    }
    /**
     * remove the tail node of the list
     * @return the value of the tail before removing it
     */
    protected Integer removeTail() {
    	Integer data = getTail().getData();
    	if (getSize()==1) {
    		setTail(null);
    		setHead(null);
    	}else {
    		getTail().getPrev().setNext(null);
    		setTail(getTail().getPrev());
    	}
    	setSize(getSize()-1);
    	return data;
    }
    /**
     * removes an interior node in the list
     * @param pNode node to be removed
     * @return the value of the node
     */
    protected Integer removeInterior(Node pNode) {
    	Integer data = pNode.getData();
    	pNode.getNext().setPrev(pNode.getPrev());
    	pNode.getPrev().setNext(pNode.getNext());
    	setSize(getSize()-1);
    	return data;
    }																									//Question 4.3 End

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Returns the element that was previously stored at pIndex.
     */
    public Integer set(int pIndex, Integer pData) throws IndexOutOfBoundsException {
        Node node = getNodeAt(pIndex);
        Integer original = node.getData();
        node.setData(pData);
        return original;
    }

    /**
     * Mutator method for the mHead field.
     */
    protected void setHead(Node pHead) {
        mHead = pHead;
    }

    /**
     * Mutator method for the mSize field.
     */
    protected void setSize(int pSize) {
        mSize = pSize;
    }

    /**
     * Mutator method for the mTail field.
     */
    protected void setTail(Node pTail) {
        mTail = pTail;
    }

    /**
     * Returns a string representation of this DList where we define string representation be the
     * string representation of each of the Nodes.
     */
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < getSize(); ++i) string += get(i) + " ";
        return string;
    }
    /**
     * removes all nodes with the data entered in pData
     * @param pData data of nodes to be removed
     */
    public void removeAll(Integer pData) {													//Question 4.1
    	int i =0;
    	while (i<getSize()) {
    		if (get(i)==pData) {
    			remove(i);
    		}
    		i++;
    	}
    }																						//Question 4.1 end
    /**																						//Question 4.8
     * adds a number while keeping the order of the list in tact
     * @param pData number to be added into the sorted list
     */
    public void orderedAdd(Integer pData) {
    	int origSize=getSize();
    	if(isEmpty()) {
    		addHead(pData);
    		//System.out.println("Head");
    	}else if(getSize()<2) {
    		//System.out.println("Tail");
    		if(pData<getHead().getData()) {
    			addHead(pData);
    		}else {addTail(pData);}
    	}else {
    		for(int i=0;i<origSize;i++) {
    			Node node = getNodeAt(i);
    			//System.out.println("The Node: "+node);
    			if(node.getNext()==null) {
    				append(pData);
    				//System.out.println("Appended");
    			}else if(node.getData()<pData&&node.getNext().getData()>pData)
    			{
    				add(i+1,pData);
    				//System.out.println("added");
    			}
    			//System.out.println(this);
    		}
    	}
    }//Question 4.8 End

    
    public boolean inOrder(Node pNode) {
    	if(pNode==null||pNode.getNext()==null) {
    		return true;
    	}
    	if(pNode.getData()>=pNode.getNext().getData()) {
    		return false;
    	}else {return inOrder(pNode.getNext());}	
    	
    	
    	
    }
    //**********************************************************************************************
    // Static Nested Class: Node
    //**********************************************************************************************

    /**
     * The data for each element of the DList is stored in a Node object. A Node object contains
     * three instance variables: (1) mData is a reference to the data stored in the Node; (2) mNext
     * is a reference to the succeeding Node in the DList; and (3) mPrev is a reference to the
     * preceding Node in the DList.
     *
     * Note that Node is declared as protected so it is not visible to other classes but it is
     * accessible to subclasses of DList.
     */
    protected static class Node {

        //==========================================================================================
        // Node Instance Variables
        //==========================================================================================

        /**
         * The data stored in this Node.
         */
        Integer mData;

        /**
         * A reference to the succeeding Node in the DList.
         */
        Node mNext;

        /**
         * A reference to the preceding Node in the DList.
         */
        Node mPrev;

        //==========================================================================================
        // Node Instance Methods
        //==========================================================================================

        /**
         * Creates a new Node storing no data and with mNext and mPrev set to null.
         */
        public Node() {
            this(null);
        }

        /**
         * Creates a new Node storing pData as the data and with mNext and mPrev set to null.
         */
        public Node(Integer pData) {
            setData(pData);
            setNext(null);
            setPrev(null);
        }

        /**
         * Creates a new Node storing pData as the data, mPrev initialized to pPrev, and mNext
         * initialized to pNext.
         */
        public Node(Integer pData, Node pPrev, Node pNext) {
            setData(pData);
            setPrev(pPrev);
            setNext(pNext);
        }

        /**
         * Returns true if this Node and pNode are equal to each other where equal is defined as:
         *
         * 1. If pNode is null, returns false.
         * 2. If mNode == pNode is true, returns true.
         * 3. If the instance variables of this Node are equal to the instance variables of pNode
         *    returns true.
         * 4. Otherwise, returns false.
         */
        @Override
        public boolean equals(Object pNode) {
            Node node = (Node)pNode;
            if (node == null) return false;
            if (this == node) return true;
            if (getData() == node.getData() && getNext() == node.getNext() &&
            getPrev() == node.getPrev()) return true;
            return false;
        }

        /**
         * Accessor method for the mData instance variable.
         */
        public Integer getData() {
            return mData;
        }

       /**
         * Accessor method for the mNext instance variable.
         */
        public Node getNext() {
            return mNext;
        }

       /**
         * Accessor method for the mPrev instance variable.
         */
        public Node getPrev() {
            return mPrev;
        }

        /**
         * Mutator method for the mData instance variable.
         */
        public void setData(Integer pData) {
            mData = pData;
        }

        /**
         * Mutator method for the mNext instance variable.
         */
        public void setNext(Node pNext) {
            mNext = pNext;
        }

        /**
         * Mutator method for the mPrev instance variable.
         */
        public void setPrev(Node pPrev) {
            mPrev = pPrev;
        }

        /**
         * Returns a string representation of this Node where we define the string representation
         * to be the string representation of the data stored in this Node.
         */
        @Override
        public String toString() {
            return "" + getData();
        }
    }
}
