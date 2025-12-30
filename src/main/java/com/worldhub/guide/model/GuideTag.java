package com.worldhub.guide.model;

public enum GuideTag {

    SOLO_TRAVELERS("Solo Travelers", "Suitable for people traveling alone"),
    COUPLES("Couples", "Great for couples and romantic trips"),
    FAMILIES("Families", "Recommended for families"),
    KIDS_FRIENDLY("Kids Friendly", "Activities suitable for children"),
    SENIORS("Seniors", "Comfortable for older travelers"),
    ADVENTURE_SEEKERS("Adventure Seekers", "Perfect for thrill and adventure lovers"),
    BUDGET_TRAVELERS("Budget Travelers", "Good for low-cost trips"),
    LUXURY_TRAVELERS("Luxury Travelers", "High-end and premium experiences"),
    PHOTOGRAPHERS("Photographers", "Great for photography spots"),
    HIKERS("Hikers", "Good for hiking enthusiasts"),
    NIGHT_LIFE("Night Life", "Night clubs, bars and nightlife experiences"),
    FOOD_LOVERS("Food Lovers", "Recommended for lovers of food experiences"),
    CULTURE_LOVERS("Culture Lovers", "Great for cultural and historical activities"),
    PET_FRIENDLY("Pet Friendly", "Suitable for people traveling with pets");

    private final String title;
    private final String description;

    GuideTag(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
