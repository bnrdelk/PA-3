public class HashFunctions {
    private int a;
    private int b;
    private int m;

    public HashFunctions(int a, int b, int m) {
        this.a = a;
        this.b = b;
        this.m = m;
    }

    public int hash(int x) {
        int result = ((a * x + b) % 1001) % m;
        // check to avoid negative indexes
        result = check(result);
        return result;
    }

    private int check(int result) {
        if(result <= 0){
            return result + m;
        }
        return result;
    }

    public int getM() {
        return m;
    }
}


