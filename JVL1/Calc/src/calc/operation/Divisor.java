package calc.operation;

public class Divisor {
    
    private float qout;
    public Divisor() { qout=1; }
    public Divisor(float a) {
        if (a!=0) {
            this.qout=a;
        } 
    }

    public void div(float a){
        qout/=a;
    }

    public float getQuot() { return qout; }

}
