package com.gerousia;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        UserInterface program = new UserInterface(in);

        program.start();
    }

}
