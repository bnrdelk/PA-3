import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// class to read student data from randomly generated student txt file
public class DataReader {
        public static List<Student> fetchStudentsAsArrayList(String fileName) {
            List<Student> studentsList = new ArrayList<>();

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
                String studentData;
                // read until the end, split data to variables
                while ((studentData = bufferedReader.readLine()) != null) {
                    String[] data = studentData.split(",");
                    String name = data[0];
                    String lastName = data[1];
                    int ID = Integer.parseInt(data[2]);
                    String departmentData = data[3];
                    String facultyData = data[4];

                    // create student object with read data & add to list
                    Student student = new Student(name, lastName, ID, departmentData, facultyData);
                    studentsList.add(student);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return studentsList;
        }

    public static List<Integer> fetchNumbersAsArrayList(String fileName) {
        List<Integer> primeNumbersList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String numberData;
            int primeNum;

            // read until the end, split data to variables (each line has 10 numbers)
            while ((numberData = bufferedReader.readLine()) != null) {
                String[] data = numberData.split(" ");
                for(int i=0; i<10; i++){
                    primeNum = Integer.parseInt(data[i]);
                    primeNumbersList.add(primeNum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return primeNumbersList;
    }
    }
