package ru.geekbrains;

import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class Weather {

    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECAST = "forecasts";
    private static final String LOCATIONS = "locations";
    private static final String TOP_CITIES = "topcities";
    private static final String CITIES_NUMBER = "100";
    private static final String API_VERSION = "v1";
    private static final String FORECAST_TYPE = "daily";
    private static final String FORECAST_PERIOD = "5day";
    private static final String MOSCOW_KEY = "294021";
    private static final String API_KEY = "bhAtNApQpTrswjBT70RL4DezpT8bsb3p";

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl urlWeather = new HttpUrl.Builder()
                .scheme("http")
                .host(BASE_HOST)
                .addPathSegment(FORECAST)
                .addPathSegment(API_VERSION)
                .addPathSegment(FORECAST_TYPE)
                .addPathSegment(FORECAST_PERIOD)
                .addPathSegment(MOSCOW_KEY)
                .addQueryParameter("apikey", API_KEY)
                .addQueryParameter("Language", "ru-ru")
                .addQueryParameter("metric", "true")
                .build();

        Request requestWeather = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(urlWeather)
                .build();

        Request requestCity = new Request.Builder()
                .addHeader("accept", "application/json")
                .url(urlWeather)
                .build();

        Response response1 = client.newCall(requestWeather).execute();
        Response response2 = client.newCall(requestCity).execute();
        System.out.println(response1.code());
        System.out.println(response2.code());

        ResponseBody jsonResponse1 = Objects.requireNonNull(client.newCall(requestWeather).execute().body());
        ResponseBody jsonResponse2 = Objects.requireNonNull(client.newCall(requestWeather).execute().body());

        System.out.println(jsonResponse1);
        System.out.println(jsonResponse2);
    }
}
