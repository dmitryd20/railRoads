package ru.vsu.demyanov.view;

import ru.vsu.demyanov.models.entity.Time;
import ru.vsu.demyanov.view.resources.Strings;

import java.util.*;

public class InputHandler {

    private Set<String> yesOptions = new TreeSet<>();
    private Set<String> noOptions = new TreeSet<>();

    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
        yesOptions.addAll(Arrays.asList("Y", "y", "Yes", "yes"));
        noOptions.addAll(Arrays.asList("N", "n", "No", "no"));
    }

    public InputHandler() {
        this(new Scanner(System.in));
    }

    public Boolean readChoice() {
        var input = scanner.nextLine();
        if (yesOptions.contains(input)) {
            return true;
        }
        if (noOptions.contains(input)) {
            return false;
        }
        return null;
    }

    public Integer readInt() {
        try {
            return Integer.valueOf(scanner.nextLine());
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    public String readString() {
        return scanner.nextLine();
    }

    public Time readTime(boolean needDays) {
        Integer days = 0;
        if (needDays) {
            System.out.println(Strings.Input.ENTER_DAYS);
            days = readInt();
        }
        System.out.println(Strings.Input.ENTER_HOURS);
        var hours = readInt();
        System.out.println(Strings.Input.ENTER_MINUTES);
        var minutes = readInt();
        return new Time(days, hours, minutes);
    }
}
