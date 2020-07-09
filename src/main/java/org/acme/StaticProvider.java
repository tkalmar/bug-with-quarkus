package org.acme;

public class StaticProvider {

    private static String GREETING = "my friend!";


    public static void setGreeting(String greeting) {
        GREETING = greeting;
    }

    public static String getGreeting(){
        return GREETING;
    }
}
