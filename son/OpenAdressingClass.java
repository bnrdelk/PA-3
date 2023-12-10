public class OpenAdressingClass {
    private Student[] openAdressingTable;
    private int numberOfStudents;
    private int capacity;
    private HashFunctions hashing;
    private ProbingFunctions probing;
    private int[] primeNumbers;

    public OpenAdressingClass(int initialCapacity, int[] primeNumbers) {
        capacity = initialCapacity;
        openAdressingTable = new Student[capacity];
        this.primeNumbers = primeNumbers;
        numberOfStudents = 0;
        hashing = new HashFunctions(5, 7, capacity);
        probing = new ProbingFunctions(new HashFunctions(9, 11, capacity), new HashFunctions(5, 7, capacity));
    }

    // Method to insert a student into the hash table using linear probing
    public void add(Student student) {
        // check is capacity is greater or equal than %80 of table capacity
        if (numberOfStudents >= capacity * 0.8) {
            // if it is, resize table with doubling
            resizeTable();
        }

        // get index with hashing
        int index = hashing.hash(student.getID());

        int i = 0;
        // loop until find empty lot in table with probing
        while (openAdressingTable[index] != null) {
            index = (index + probing.probe(student.getID(), i)) % capacity;
            i++;
        }

        // add student to empty lot, increase the student size
        openAdressingTable[index] = student;
        numberOfStudents++;
    }

    // Method to resize the table by doubling its capacity and rehashing all elements
    private void resizeTable() {
        // double the capacity -> with prime numbers
        int newCapacity = getNextPrime(capacity * 2);

        // swap the tables
        Student[] firstTable = openAdressingTable;
        openAdressingTable = new Student[newCapacity];

        // we create a new table so initialize 0 the numberOfStudents & add every student
        capacity = newCapacity;
        numberOfStudents = 0;
        for (Student student : firstTable) {
            if (student != null) {
                add(student);
            }
        }
    }

    private int getNextPrime(int i) {
        for (int k = 0; k < primeNumbers.length; k++) {
            if ((int) primeNumbers[k] >= i) {
                return (int) primeNumbers[k];
            }
        }
        return -1;
    }

    public Student find(int studentID){
        //get initial index using hashing
        int index = hashing.hash(studentID);
        int i = 0;

        while(openAdressingTable[index] != null){
            if(openAdressingTable[index].getID() == studentID){
                return openAdressingTable[index];
            }

            index = (index + probing.probe(studentID, i)) % capacity;
            i++;
        }

        return null;
    }
}