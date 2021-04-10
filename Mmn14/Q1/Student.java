package mmn14Q1;

public class Student implements Comparable<Student> {
	
	private String name;
	private long id;
	private int grade;
	
	// builder for new students 
	public Student (String name,long id,int grade) {
		this.name = name;
		this.id = id;
		this.grade = grade;				
	}
	//return the grade of the student 
	public int getGrade() {
		return grade;
	}
	public int compareTo (Student other) {
		return grade - other.getGrade();
	}
	
	// overriding equals method to compare  students
	// student are equal if and only if their grades are equal
	public boolean equals(Object other) {
		if ( ! (other instanceof Student))
			System.out.println("Eror - comparing 2 diffrent types of objects");
		
		Student temp = (Student) other;
		return (grade == temp.getGrade()) ;
	}
	
	public String toString() {
		String str ="The name is: "+ name+" the id is: "+ id+ " and the grade is: "+grade+"\n";
		return str;
	}

}
