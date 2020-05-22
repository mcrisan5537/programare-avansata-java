package com;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import com.google.gson.*;

public class Info {

    public void country(String countryCode) {

        StringBuilder output = new StringBuilder();
        try {
            URLConnection urlConnection = new URL("http",
                                                  "www.oorsprong.org",
                                                  "/websamples.countryinfo/CountryInfoService.wso/FullCountryInfo/JSON/debug?sCountryISOCode=" + countryCode)
                                                .openConnection();
            urlConnection.connect();

            JsonObject jsonTree = JsonParser.parseString(new String(urlConnection.getInputStream().readAllBytes())).getAsJsonObject();
            output.append("Country name = " + jsonTree.get("sName").getAsString() + "\n");
            output.append("Country capital = " + jsonTree.get("sCapitalCity").getAsString() + "\n");
            output.append("Phone code = " + jsonTree.get("sPhoneCode").getAsString() + "\n");
            String continentCode = jsonTree.get("sContinentCode").getAsString();

            urlConnection = new URL("http",
                                    "www.oorsprong.org",
                                    "/websamples.countryinfo/CountryInfoService.wso/ListOfContinentsByCode/JSON/debug?")
                                    .openConnection();
            JsonArray jsonArray = JsonParser.parseString(new String(urlConnection.getInputStream().readAllBytes()))
                                        .getAsJsonArray();
            for(JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if(jsonObject.get("sCode").getAsString().equalsIgnoreCase(continentCode)) {
                    output.append("Continent = " + jsonObject.get("sName").getAsString() + "\n");
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println(output.toString());
    }

    public void about(Locale locale) {
        StringBuilder sb = new StringBuilder();

        sb.append(getCountry(locale)).append("\n");
        sb.append(getLanguage(locale)).append("\n");
        sb.append(getCurrency(locale)).append("\n");
        sb.append(getWeekDays(locale)).append("\n");
        sb.append(getMonths(locale)).append("\n");
        sb.append(getToday(locale)).append("\n");

        System.out.println(sb.toString());
    }

    private String getCountry(Locale locale) {
        return "Country: " + locale.getDisplayCountry(Locale.US) + " (" + locale.getDisplayCountry(locale) + ")";
    }

    private String getLanguage(Locale locale) {
        return "Language: " + locale.getDisplayLanguage(Locale.US) + " (" + locale.getDisplayLanguage(locale) + ")";
    }

    private String getCurrency(Locale locale) {
        Currency currency = Currency.getInstance(locale);
        return "Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName()  + ")";
    }

    private String getWeekDays(Locale locale) {
        StringBuilder weekDaysReturnString = new StringBuilder();
        String[] weekDays = DateFormatSymbols.getInstance(locale).getWeekdays();

        weekDaysReturnString.append("Week Days: ");
        weekDaysReturnString.append(weekDays[1]);
        for(int i = 2; i < weekDays.length; i++)
            weekDaysReturnString.append(", ").append(weekDays[i]);
        return weekDaysReturnString.toString();
    }

    private String getMonths(Locale locale) {
        StringBuilder monthsReturnString = new StringBuilder();
        String[] months = DateFormatSymbols.getInstance(locale).getMonths();

        monthsReturnString.append("Months: ");
        monthsReturnString.append(months[0]);
        for(int i = 1; i < months.length - 1; i++)
            monthsReturnString.append(", ").append(months[i]);

        return monthsReturnString.toString();
    }

    private String getToday(Locale locale) {
        return "Today: " + DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(new Date())
                + " (" + DateFormat.getDateInstance(DateFormat.LONG, locale).format(new Date()) + ")";
    }

}
