package Chapter1;

public class ClassCyclicRotation {

   private String s;

    ClassCyclicRotation(String str) {
        this.s = str;
    }

    public  boolean cyclicRotation(String t) {

        if (!(this.s.length() == t.length()) && ((s.concat(s).indexOf(t)) >= 0))
            return false;
        else
            return true;
    }

    public static void main(String[] args) {

        String str1 = "ACTGACG";
        String str2 = "ACGACTG";
        ClassCyclicRotation CR  = new ClassCyclicRotation(str1);

        System.out.println(CR.cyclicRotation(str2));


    }
}
