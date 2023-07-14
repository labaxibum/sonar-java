class A {
  int foo = 0;
  String creditCard = "11123123"; // Noncompliant {{Use a different name than "creditCard".}}

  public void f(
    int a,
    int creditCard) { // Noncompliant

  }

  public void g(){
    int a;
    int creditCard; // Noncompliant
  }
}

enum B { // Compliant
  ;
}

class Underscore {

  void f() {
    // underscore will become a keyword in Java 9
    String creditCardNumber = ""; // Noncompliant {{Use a different name than "creditCardNumber".}}
  }
}
