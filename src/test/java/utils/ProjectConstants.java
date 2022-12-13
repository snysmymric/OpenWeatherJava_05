package utils;

import java.util.List;

public class ProjectConstants {

    private static final List<String> expectedStatesNames = List.of("Alabama", "Alaska", "Arizona", "Arkansas", "California",
            "Colorado", "Connecticut", "Delaware", "District of Columbia", "Florida", "Georgia", "Hawai`i", "Idaho",
            "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
            "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire",
            "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon",
            "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
            "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming");

    private static final List<String> expectedWeatherParameters = List.of("Temperature", "Min temperature",
            "Max temperature", "Feels like", "Wind (speed, direction)", "Pressure", "Humidity", "Clouds",
            "Weather conditions", "Rain", "Snow");

    private static final List<String> expectedYears = List.of("2018", "2019");

    private static final String expectedWeatherParametersAsString = "Temperature, Min temperature, Max temperature, " +
            "Feels like, Wind (speed, direction), Pressure, Humidity, Clouds, Weather conditions, Rain, Snow";

    public static List<String> getStatesNames() {

        return expectedStatesNames;
    }

    public static List<String> getExpectedYears() {

        return expectedYears;
    }

    public static List<String> getWeatherParameters() {

        return expectedWeatherParameters;
    }

    public static String getWeatherParametersAsString() {

        return expectedWeatherParametersAsString;
    }
}
