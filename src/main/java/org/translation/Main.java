package org.translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program.
 * Complete the code according to the "to do" notes.<br/>
 * The system will:<br/>
 * - prompt the user to pick a country name from a list<br/>
 * - prompt the user to pick the language they want it translated to from a list<br/>
 * - output the translation<br/>
 * - at any time, the user can type quit to quit the program<br/>
 */
public class Main {

    /**
     * This is the main entry point of our Translation System!<br/>
     * A class implementing the Translator interface is created and passed into a call to runProgram.
     * @param args not used by the program
     */
    public static void main(String[] args) {
        Translator translator = new JSONTranslator(null);

        runProgram(translator);
    }

    /**
     * This is the method which we will use to test your overall program, since
     * it allows us to pass in whatever translator object that we want!
     * See the class Javadoc for a summary of what the program will do.
     * @param translator the Translator implementation to use in the program
     */
    public static void runProgram(Translator translator) {
        while (true) {
            String q = "quit";
            String country = promptForCountry(translator);
            if (q.equals(country)) {
                break;
            }
            // TODO Task: Once you switch promptForCountry so that it returns the country
            //            name rather than the 3-letter country code, you will need to
            //            convert it back to its 3-letter country code when calling promptForLanguage
            String countryCode = CountryCodeConverter.fromCountry(country);

            String language = promptForLanguage(translator, countryCode);
            if (q.equals(language)) {
                break;
            }

            String languageCode = LanguageCodeConverter.fromLanguage(language);

            // TODO Task: Once you switch promptForLanguage so that it returns the language
            //            name rather than the 2-letter language code, you will need to
            //            convert it back to its 2-letter language code when calling translate.
            //            Note: you should use the actual names in the message printed below though,
            //            since the user will see the displayed message.
            System.out.println(country + " in " + language + " is " + translator.translate(countryCode, languageCode));
            System.out.println("Press enter to continue or quit to exit.");
            Scanner s = new Scanner(System.in);
            String textTyped = s.nextLine();

            if ("quit".equals(textTyped)) {
                break;
            }
        }
    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForCountry(Translator translator) {
        List<String> countryCodes = translator.getCountries();
        List<String> countryNames = new ArrayList<>();

        for (int i = 0; i < countryCodes.size(); i++) {
            String country = countryCodes.get(i);
            countryNames.add(CountryCodeConverter.fromCountryCode(country));
        }

        Collections.sort(countryNames);

        for (int i = 0; i < countryNames.size(); i++) {
            System.out.println(countryNames.get(i));
        }

        System.out.println("select a country from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();

    }

    // Note: CheckStyle is configured so that we don't need javadoc for private methods
    private static String promptForLanguage(Translator translator, String country) {

        // TODO Task: replace the line below so that we sort the languages alphabetically and print\
        //  them out; one per line
        // TODO Task: convert the language codes to the actual language names before sorting

        List<String> countryLanguages = translator.getCountryLanguages(country);
        List<String> languagesList = new ArrayList<>();
        for (int i = 0; i < countryLanguages.size(); i++) {
            String code = countryLanguages.get(i);
            String language = LanguageCodeConverter.fromLanguageCode(code);
            languagesList.add(language);
        }
        Collections.sort(languagesList);

        for (int i = 0; i < languagesList.size(); i++) {
            System.out.println(languagesList.get(i));
        }

        System.out.println("select a language from above:");

        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }
}
