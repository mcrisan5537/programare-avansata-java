package app;

import com.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplorer {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        properties.load(new FileReader("Commands.properties"));
        String setLocaleCommand = properties.getProperty("setlocale.command");
        String displayLocalesCommand = properties.getProperty("displaylocales.command");
        String currentLocaleCommand = properties.getProperty("currentlocale.command");
        String infoCommand = properties.getProperty("info.command");
        String countryCommand = properties.getProperty("country.command");

        Locale.setDefault(new Locale("ro", "RO"));
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Messages");
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            System.out.print(resourceBundle.getString("prompt") + " ");
            input = scanner.nextLine();

            if(input.equalsIgnoreCase(displayLocalesCommand)) {
                System.out.println(resourceBundle.getString("locales"));
                new DisplayLocales().execute();
            } else if(input.equalsIgnoreCase(currentLocaleCommand)) {
                String pattern = resourceBundle.getString("locale.set");
                String message = MessageFormat.format(pattern, Locale.getDefault().toString());
                System.out.println(message);
            } else if(input.equalsIgnoreCase(infoCommand)) {
                String pattern = resourceBundle.getString("info");
                String message = MessageFormat.format(pattern, Locale.getDefault().toString());
                System.out.println(message);
                new Info().about(Locale.getDefault());
            } else if(input.equalsIgnoreCase("exit")) {
                break;
            } else {
                String[] splitInput = input.split(" ");
                if(splitInput[0].equalsIgnoreCase(setLocaleCommand)) {
                    new SetLocale().execute(new Locale(splitInput[1], splitInput[2]));
                    resourceBundle = ResourceBundle.getBundle("Messages");
                } else if(splitInput[0].equalsIgnoreCase(countryCommand)) {
                    new Info().country(splitInput[1]);
                } else {
                    System.out.println(resourceBundle.getString("invalid"));
                }
            }

        }while(true);

    }

}
