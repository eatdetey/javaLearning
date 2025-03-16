package calc.operation;

public class Multiplicator {
    
    private int prod;
    public Multiplicator() { prod=1; }
    public Multiplicator(int a) { this.prod=a; }

    public void mult(int a) {
        prod*=a;
    }

    public int getProd() { return prod; }
}
