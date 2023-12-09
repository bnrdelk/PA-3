import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        StudentDataReader studentDataReader = new StudentDataReader();
        List<Student> studentsList = studentDataReader.fetchStudentsAsArrayList("students.txt");
        ChainingTableClass chainingTable = new ChainingTableClass(4001);

        // fetch the students from the ArrayList,
        // then hash and allocate them into the table
        for(Student student: studentsList){
            chainingTable.add(student);
        }

        // search and bring hundred random students from the table.
        List<Student> randomStudentList = bringRandomStudentsFromTable(100, chainingTable);
        System.out.println(randomStudentList);
    }

    private static List<Student> bringRandomStudentsFromTable(int i, ChainingTableClass chainingTable) {
        List<Student> randomStudentList = new ArrayList<>();
        Student randomStudent = new Student("null","null",0,"null","null");

        for (int count = 0; count < i; count++) {
            // loop until find a valid student
            do{
                int randomStudentID = getRandomStudentID();
                randomStudent = chainingTable.find(randomStudentID);
            } while(randomStudent == null);

            // add to the list
            randomStudentList.add(randomStudent);
        }
        return randomStudentList;
    }

    private static int getRandomStudentID() {
        Random random = new Random();
        // get random number between 1-230909100
        return random.nextInt(230909100) + 1;
    }

}
