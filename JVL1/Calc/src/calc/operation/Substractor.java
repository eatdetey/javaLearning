package calc.operation;

public class Substractor {

    private int diff;
    public Substractor() { diff=0; }
    public Substractor(int a) { this.diff=a; }

    public void sub(int a){
        diff-=a;
    }

    public int getDiff() { return diff; }

    
}
