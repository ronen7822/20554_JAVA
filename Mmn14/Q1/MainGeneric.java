package mmn14Q1;

public class MainGeneric {

	// main method to run tests for the generic sorted group
	public static void main(String[] args) {
		
		// some students
		Student first = new Student("ronen", 232323, 98);
		Student second = new Student("avi", 12312312, 88);
		Student third = new Student("yosi", 23423532, 78);
		Student fourth = new Student("ada", 465775, 60);
		Student fifth = new Student("yeael", 1232214, 22);
		
		SortedGroup<Student> group = new SortedGroup<Student>();
		// adding the Students in random order
		group.add(fifth);
		group.add(second);
		group.add(third);
		group.add(fourth);
		group.add(first);		
		System.out.println("the original group:\n"+group+ "\n");
		
		Student newStudent = new Student("aviel", 4534534, 55);
		Student newStudent2 = new Student("rotem", 9834534, 78);
		group.add(newStudent);
		group.add(newStudent2);
		System.out.println("after adding 2 students:\n"+group+ "\n");
		
		// removing  Students with grade equal to 78
		group.remove(third);
		System.out.println("after removing students with grade of 78:\n"+group+ "\n");
		
		// new group of students with grades greater than 60
		SortedGroup<Student> Selectedgroup = Generic.reduce(group, new Student("a", 1, 60));
		System.out.println("after reduce:\n" +Selectedgroup+ "\n");
		
	}

}
