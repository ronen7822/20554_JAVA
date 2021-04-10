package mmn14Q1;

public class Generic <T extends Comparable<T>> {

	//  reducing from the SortedGroup to new group all the elements which equal or lesser than x
    public static <T extends Comparable<T>>   SortedGroup<T> reduce(SortedGroup<T> group,T x){
    	
        SortedGroup<T> Selectedgroup = new SortedGroup<T>();
        int i = 0;
        int size = group.size();
        while(i<size){
        	// if the element i is bigger than x than add all the the elements from i to the end of the group
            if (((Comparable<T>) group.get(i)).compareTo(x) > 0) 
                break;
            i++;
        }
        while(i<size){
        	Selectedgroup.add(group.get(i++));
        }
        return Selectedgroup;
    }
}
