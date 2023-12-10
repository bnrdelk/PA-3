public class ProbingFunctions {
    private HashFunctions h1;
    private HashFunctions h2;

    public ProbingFunctions(HashFunctions h1, HashFunctions h2) {
        this.h1 = h1;
        this.h2 = h2;
    }

    public int probe(int x, int i) {
        return (h1.hash(x) + i * h2.hash(x)) % h1.getM();
    }
}