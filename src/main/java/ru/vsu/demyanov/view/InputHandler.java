package ru.vsu.demyanov.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public InputHandler() {
        scanner = new Scanner(System.in);
    }

    public Integer readInt() {
        try {
            return Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException exception) {
            return null;
        }
    }
}
