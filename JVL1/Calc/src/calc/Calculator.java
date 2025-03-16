package calc;
import calc.operation.Adder;
import calc.operation.Divisor;
import calc.operation.Invertor;
import calc.operation.Multiplicator;
import calc.operation.Substractor;
public class Calculator{
    public int sum(int... a){
        Adder adder = new Adder();
        for (int i:a){
            adder.add(i);
        }
        return adder.getSum();
    }

    public int sub(int... a){
        Substractor substractor = new Substractor(a[0]);
        for (int i=1; i<a.length; i=i+1){
            substractor.sub(a[i]);
        }
        return substractor.getDiff();
    }

    public int mult(int... a){
        Multiplicator multiplicator = new Multiplicator();
        for (int i:a){
            multiplicator.mult(i);
        }
        return multiplicator.getProd();
    }

    public float div(float... a){
        Divisor divisor = new Divisor(a[0]);
        for (int i=1; i<a.length; i=i+1){
            divisor.div(a[i]);
        }
        return divisor.getQuot();
    }

    public int invert(int a){
        Invertor invertor = new Invertor();
        return invertor.invert(a);
    }
}