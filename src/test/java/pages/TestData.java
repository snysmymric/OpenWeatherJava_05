package pages;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "TopMenuTestData")
    public static Object[][] topMenuTestDataProvider() {

        return new Object[][]{
                {0, "Logo", "/", "https://openweathermap.org/", "Сurrent weather and forecast - OpenWeatherMap"},
                {1, "Guide", "/guide", "https://openweathermap.org/guide", "OpenWeatherMap API guide - OpenWeatherMap"},
                {2, "API", "/api", "https://openweathermap.org/api", "Weather API - OpenWeatherMap"},
                {3, "Dashboard", "/weather-dashboard", "https://openweathermap.org/weather-dashboard", "Weather dashboard - OpenWeatherMap"},
                {4, "Marketplace", "https://home.openweathermap.org/marketplace", "https://home.openweathermap.org/marketplace", "Marketplace: History Bulk, History Forecast Bulk, Historical Weather Data by State for all ZIP codes, USA - OpenWeather"},
                {5, "Pricing", "/price", "https://openweathermap.org/price", "Pricing - OpenWeatherMap"},
                {6, "Maps", "/weathermap", "https://openweathermap.org/weathermap", "Interactive weather maps - OpenWeatherMap"},

                {7, "Our Initiatives", "/our-initiatives", "https://openweathermap.org/our-initiatives", "Our Initiatives - OpenWeatherMap"},
                {8, "Partners", "/examples", "https://openweathermap.org/examples", "Partners and solutions - OpenWeatherMap"},
                {9, "Blog", "https://openweather.co.uk/blog/category/weather", "https://openweather.co.uk/blog/category/weather", "Blog - OpenWeatherMap"},
                {10, "For Business", "https://openweather.co.uk", "https://openweather.co.uk/", "OpenWeather for business - OpenWeatherMap"},
                {11, "Sign In", "/users/sign_in", "https://home.openweathermap.org/users/sign_in", "Members"},
        };
    }

    @DataProvider(name = "ApiIconsMainPage")
    public static Object[][] apiIconsTestDataProvider() {

        return new Object[][]{
                {0, "current\nweather", "(current)", "/current", "https://openweathermap.org/current", "Current weather data - OpenWeatherMap"},
                {1, "hourly\nforecast", "(4 days)", "/api/hourly-forecast", "https://openweathermap.org/api/hourly-forecast", "Hourly Weather Forecast 4 days - OpenWeatherMap"},
                {2, "daily\nforecast", "(16 days)", "/forecast16", "https://openweathermap.org/forecast16", "Daily Forecast 16 Days - OpenWeatherMap"},
                {3, "climatic\nforecast", "(30 days)", "/api/forecast30", "https://openweathermap.org/api/forecast30", "Climate forecast for 30 days - OpenWeatherMap"},
                {4, "historical\nweather", "(1 month, 1 year)", "/history", "https://openweathermap.org/history", "Historical weather API - OpenWeatherMap"}
        };
    }
}
