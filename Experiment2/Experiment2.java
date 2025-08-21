// Code implement power function in O(logn) time complexity.


public class Experiment2 {

    static int power(int base, int exp) {
        if (exp == 0)
            return 1;

        int half = power(base, exp / 2);

        if (exp % 2 == 0)
            return half * half;
        else
            return base * half * half;
    }

    public static void main(String[] args) {
        int base = 2;
        int exp = 10;
        int result = power(base, exp);

        System.out.println("Base: " + base);
        System.out.println("Exponent: " + exp);
        System.out.println("Result: " + result);
    }
}
