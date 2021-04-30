package com.order.order.entity;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Search {
    private final String name;
    private final String cityName;
    private final int cityLat;
    private final int cityLong;

    private Search(SearchBuilder builder) {
        name = builder.name;
        cityName = builder.cityName;
        cityLat = builder.cityLat;
        cityLong = builder.cityLong;
    }

    public List<Order> result(List<Order> orders) {
        return orders.stream()
                .filter(predicateName()
                        .and(predicateCityName())
                        .and(predicateCityLat())
                        .and(predicateCityLong())
                )
                .collect(Collectors.toList());
    }

    public Predicate<Order> predicateName() {
        return order -> order.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT));
    }

    public Predicate<Order> predicateCityName() {
        return order -> order.getCity() != null && order.getCity().toLowerCase(Locale.ROOT).contains(cityName.toLowerCase(Locale.ROOT));
    }

    public Predicate<Order> predicateCityLat() {
        return order -> cityLat == 9999 || order.getLattitude() == cityLat;
    }

    public Predicate<Order> predicateCityLong() {
        return order -> cityLong == 9999 || order.getLattitude() == cityLong;
    }

    public static class SearchBuilder {
        private String name;
        private String cityName;
        private int cityLat;
        private int cityLong;


        public SearchBuilder() {
            name = "";
            cityName = "";
            cityLat = 9999;
            cityLong = 9999;
        }

        public SearchBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SearchBuilder cityName(String cityName) {
            this.cityName = cityName;
            return this;
        }

        public SearchBuilder cityLat(int cityLat) {
            this.cityLat = cityLat;
            return this;
        }

        public SearchBuilder cityLong(int cityLong) {
            this.cityLong = cityLong;
            return this;
        }

        public Search build() {
            Search search =  new Search(this);
            return search;
        }
    }
}
