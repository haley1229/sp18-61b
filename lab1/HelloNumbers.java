/** 
    Variable and Loops
    Static Typing
*/

public class HelloNumbers {
    public static void main (String[] args) {

        int x = 0;
        while (x < 10) {
            System.out.print(x + " ");
            x = x + 1;
        }

        System.out.println(5 + "10");
    }
}


/*
1. Before Java variables can be used, they must be declared.
2. Java variable must have a specific type.
3. Java variable types can never change.
4. Types are verifiied before the code even runs. (BIG difference between python & java)
*/


/*
--------------- Static Typing ---------------
1. The compiler ensures that all types are compatible, making it easier for the programmer to debug their code.
2. Since the code is guaranteed to be free of type errors, users of your compiled programs will never run into type errors. For example, Android apps are written in Java, and are typically distributed only as .class files, i.e. in a compiled format. As a result, such applications should never crash due to a type error.
3. Every variable, parameter, and function has a declared type, making it easier for a programmer to understand and reason about code.
*/
