package com.evaluation;

public class Evaluation {
   public void add(int x, int y){
      System.out.println("Result is:");
      System.out.print("x + y = ");
      System.out.println(x+y);
   }
   public void minus(int x, int y) {
      System.out.println("Result is:");
      System.out.print("x - y = ");
      System.out.println(x - y);
   }
   public void multiply(int x, int y) {
      System.out.println("Result is:");
      System.out.print("x * y = ");
      System.out.println(x * y);
   }
   public void divide(int x, int y) {
      System.out.println("Result is:");
      System.out.print("x / y = ");
      System.out.println(x / y);
   }
   public void remainder(int h) {
      System.out.println("Result is:");
      System.out.print("h % 2 = ");
      System.out.println(h % 2);
   }
   public void compareto0(int x) {
      System.out.println("Result is:");
      System.out.print("x == 0 = ");
      System.out.println(x == 0);
   }
   public void texttoconsole(String txt ) {
      System.out.print("Text to console:");
      System.out.println(txt);
   }
}
