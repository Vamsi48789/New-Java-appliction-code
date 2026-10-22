package com.mobilehub;

public record Phone(
        String id,
        String brand,
        String name,
        int priceInRupees,
        String storage,
        String color,
        String badge,
        String description) {
}
