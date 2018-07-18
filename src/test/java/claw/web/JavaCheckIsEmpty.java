package claw.web;

public class JavaCheckIsEmpty {
    public static void main(String[] args) {
        String a="";
        String b=null;
        System.out.println("a = " + a.isEmpty());
        System.out.println("b.isEmpty() = " + "".equals(b));
        System.out.println("b.isEmpty() = " + b.equals(""));
        System.out.println("b.isEmpty() = " + b.isEmpty());
    }
}
