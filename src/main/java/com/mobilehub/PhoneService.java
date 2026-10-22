package com.mobilehub;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PhoneService {
    private final List<Phone> phones = List.of(
            new Phone("iphone16", "Apple", "iPhone 16", 69900, "128 GB", "Ultramarine", "Popular",
                    "A versatile everyday phone with a bright display and capable cameras."),
            new Phone("iphone16pro", "Apple", "iPhone 16 Pro", 119900, "256 GB", "Natural Titanium", "Premium",
                    "A premium option for photography and demanding tasks."),
            new Phone("galaxy-s25", "Samsung", "Galaxy S25", 80999, "256 GB", "Navy", "Popular",
                    "A compact flagship with a vivid screen and useful everyday features."),
            new Phone("galaxy-a56", "Samsung", "Galaxy A56 5G", 41999, "256 GB", "Graphite", "Value pick",
                    "A practical choice for everyday use and entertainment."),
            new Phone("pixel-9a", "Google", "Pixel 9a", 49999, "128 GB", "Obsidian", "Camera pick",
                    "A clean Android experience with helpful software and camera tools."),
            new Phone("oneplus-13r", "OnePlus", "OnePlus 13R", 42999, "256 GB", "Nebula Noir", "Performance",
                    "A responsive phone for multitasking and gaming."),
            new Phone("nothing-3a", "Nothing", "Phone (3a)", 24999, "128 GB", "Black", "Fresh design",
                    "A distinctive design with a straightforward interface."),
            new Phone("redmi-note-14", "Xiaomi", "Redmi Note 14 5G", 18999, "128 GB", "White", "Budget pick",
                    "A budget-friendly option for everyday communication."),
            new Phone("vivo-v50", "vivo", "V50", 34999, "128 GB", "Rose Red", "Style pick",
                    "A stylish device focused on portraits and daily convenience.")
    );

    public List<Phone> search(String query, String brand, Integer maxPrice) {
        String q = query == null ? "" : query.trim().toLowerCase(Locale.ROOT);
        String b = brand == null ? "" : brand.trim();
        return phones.stream()
                .filter(p -> q.isEmpty() || (p.brand() + " " + p.name()).toLowerCase(Locale.ROOT).contains(q))
                .filter(p -> b.isEmpty() || b.equalsIgnoreCase("All brands") || p.brand().equalsIgnoreCase(b))
                .filter(p -> maxPrice == null || p.priceInRupees() <= maxPrice)
                .toList();
    }

    public Phone findById(String id) {
        return phones.stream().filter(p -> p.id().equals(id)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Phone not found"));
    }
}
