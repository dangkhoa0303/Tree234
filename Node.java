package Tree234;

public class Node {
	// maximum children a node can hold
	private static final int ORDER = 4;
	// number of keys a node holds
	private int numItems;
	// parent node
	private Node parent;
	// array of child nodes a node can have
	private Node childArray[] = new Node[ORDER];
	// array of keys a node has
	private DataItem itemArray[] = new DataItem[ORDER-1];
	
	// connect child to this node
	public void connectChild(int childNum, Node child) {
		// childNum is the order of child
		childArray[childNum] = child;
		if (child != null)
			child.parent = this;
	}
	
	// disconnect child from this node, return it
	public Node disconnectChild(int childNum) {
		Node tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}
	
	// get the (childNum) (1st, 2nd, 3rd or 4th) child node
	public Node getChild(int childNum) {
		return childArray[childNum];
	}
	
	// return the parent node
	public Node getParent() {
		return parent;
	}
	
	// check a node is a leaf node or not
	public boolean isLeaf() {
		// if a node does not have any children, return true, otherwise return false;
		return (childArray[0]==null) ? true : false;
	}
	
	// get number of keys a node holds
	public int getNumItems() {
		return numItems;
	}
	
	// get a specific key at an index within a node 
	public DataItem getItem(int index) {
		return itemArray[index];
	}
	
	// check if a node is full or not
	public boolean isFull() {
		return (numItems==ORDER-1) ? true : false;
	}
	
	// search for an item within a node, which has sub nodes
	// return index (within a node) if key item is found
	// otherwise, return -1
	public int findItem(long key) {
		for (int j=0; j<ORDER-1; j++) {
			if (itemArray[j] == null)
				break;
			else if (itemArray[j].dData == key)
				return j;
		}
		return -1;
	}
	
	// insert Key
	public int insertItem(DataItem newItem) {
		// assumes node is not full
		numItems ++;
		long newKey = newItem.dData;
		
		// find the most suitable place to insert newItem
		// starting from the right, go left one cell each loop
		for (int j=ORDER-2; j>=0; j--) {
			if (itemArray[j] == null)
				continue;
			else {
				long itsKey = itemArray[j].dData;
				if (newKey < itsKey)
					itemArray[j+1] = itemArray[j];
				else {
					itemArray[j+1] = newItem;
					return j+1;
				}
			}
		}
		itemArray[0] = newItem;
		return 0;
	}
	
	// remove the largest key
	public DataItem removeItem() {
		DataItem temp = itemArray[numItems - 1];
		itemArray[numItems - 1] = null;
		numItems --;
		return temp;
	}
	
	public void displayNode() {
		for (int j=0; j<numItems; j++) {
			itemArray[j].displayItem();
		}
		System.out.println("/");
	}
}
