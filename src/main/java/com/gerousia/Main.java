package com.gerousia;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException,
            CsvRequiredFieldEmptyException,
            CsvDataTypeMismatchException {
         new UserInterface(new Scanner(System.in)).start();
    }

}
