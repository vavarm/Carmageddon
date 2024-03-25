package org.acme.common;

public class Calculation {

  private Calculation() {
    throw new IllegalStateException("Utility class");
  }

  static final int ZERO = 0;

  public static int calculate(String method) {
    switch (method) {
      case "moins":
        return moins();
      case "method1":
        return method1();
      case "zero":
        return ZERO;
      case "plus":
        return plus();
      default:
        return 0;
    }
  }
  public static int moins() {
    return -1;
  }
  public static int method1() {
    return +1+1-1+1;
  }
  public static int plus() {
    return +1;
  }
}
