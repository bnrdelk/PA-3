public class ChainingTableClass {
    private Node[] chainingTable;
    private int size;
    private HashFunctions hashing = new HashFunctions(5,7,4001);

    // const
    public ChainingTableClass(int tableSize) {
        chainingTable = new Node[tableSize];
        size = tableSize;
    }

    // method to add a student into table
    public void add(Student student) {
        // get index with hashing
        int index = hashing.hash(student.getID());

        // if index is empty, add
        if (chainingTable[index] == null) {
            chainingTable[index] = new Node(student);
        } else {
            // if not, do chaining in the index
            Node point = chainingTable[index];
            // go to last node & add
            while (point.next != null) {
                point = point.next;
            }
            point.next = new Node(student);
        }
    }

    // method to find student in table
    public Student find(int studentID) {
        // get index with hashing
        int index = hashing.hash(studentID);

        Node point = chainingTable[index];
        while (point != null) {
            if (point.student.getID() == studentID) {
                // student has found
                return point.student;
            }
            point = point.next;
        }
        // if not found
        return null;
    }

    public void displayChaining() {
        for (int i = 0; i < chainingTable.length; i++) {
            System.out.print("Index " + i + ": ");

            Node node = chainingTable[i];
            while (node != null) {
                System.out.print(node.student.getID() + " -> ");
                node = node.next;
            }
            // if empty, mark them null
            System.out.println("null");
        }
    }
}