package app;

import com.*;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplorer {

    public static void main(String[] args) {

        Locale.setDefault(new Locale("ro", "RO"));

        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print(resourceBundle.getString("prompt") + " ");
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("display locales")) {
                System.out.println(resourceBundle.getString("locales"));
                new DisplayLocales().execute();
            } else if(input.equalsIgnoreCase("current locale")) {
                String pattern = resourceBundle.getString("locale.set");
                String message = MessageFormat.format(pattern, new Object[]{Locale.getDefault().toString()});
                System.out.println(message);
            } else if(input.equalsIgnoreCase("info")) {
                String pattern = resourceBundle.getString("info");
                String message = MessageFormat.format(pattern, new Object[]{Locale.getDefault().toString()});
                System.out.println(message);
                new Info().about(Locale.getDefault());
            } else if(input.equalsIgnoreCase("exit")) {
                break;
            } else {
                String[] splitInput = input.split(" ");
                if(splitInput[0].equalsIgnoreCase("setlocale")) {
                    new SetLocale().execute(new Locale(splitInput[1], splitInput[2]));
                    resourceBundle = ResourceBundle.getBundle("Messages");
                } else {
                    System.out.println(resourceBundle.getString("invalid"));
                }
            }

        }while(true);

    }

}
