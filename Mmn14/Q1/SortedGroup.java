package mmn14Q1;

import java.util.ArrayList;
import java.util.Collections;

// group of generic sorted objects
public class SortedGroup <T extends Comparable<T>> {
	
	private ArrayList <T> group ;
	
	public SortedGroup () {
		group = new ArrayList<T>();
	}
	
	// add new member to the sorted group
	public void add (T obj) {
		group.add(obj);
		Collections.sort(group); // sorts the array list		
	}
	
	// remove the all member which are equal to obj and return the
	//amount of objects that have been removed
	public  int remove (T obj) {
		// creating an array to pass as argument to the function removeAll
		ArrayList <T> remove = new ArrayList <T>();
		remove.add(obj);
		
		int before = group.size(); // how many objects are in the array before
		group.removeAll(remove);
		int after = group.size();
			
		return before - after ; // amount of objects that have been removed
	}
	
	// return the the object int the index place
	public <T> T get(int indx){
		return (T) group.get(indx);
	}
	// return the size of the group
	public int size() {
		return group.size();
	}
	
	// return string representation of the group
	public String toString() {
	     String s = "";
	     for (int i=0;i< this.size();i++){
	         s += this.get(i);
	     }
	     return s;
	    }
}
