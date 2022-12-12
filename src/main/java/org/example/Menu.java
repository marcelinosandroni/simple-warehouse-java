package org.example;

import java.util.Scanner;

public class Menu {
    public String title;
    public String[] options;
    Menu(String title, String[] options) {
        this.title = title;
        this.options = options;
    }

    public int render() {
        System.out.printf("%n%s%n", title);
        for (int index = 0; index < options.length; index++) {
            System.out.printf("%d - %s%n", index + 1, options[index]);
        }
        System.out.print("Insert a option (0 for exit): ");
        var scanner = new Scanner(System.in);
        var selectedOption = scanner.nextInt();
        System.out.println();
        return selectedOption;
    }
}
