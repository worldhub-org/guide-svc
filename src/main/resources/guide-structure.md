GUIDE (Root)
├── id (UUID)
├── ownerId / ownerEmail
├── title: "5 Days Ubud: Greenery, Culture & Spirit"
├── description / shortDescription
├── status: "PUBLISHED"
├── version: 2
├── destination / country
├── costType / price / currency
├── averageRate
├── createdOn / updatedOn
└── ITINERARY (List of Days)
│
├── DAY 1: "Monkeys and Luwak"
│   ├── day (Integer)
│   ├── title
│   ├── busyness ("MORNING")
│   ├── short_description
│   ├── estimated_spending (Range: Min/Max)
│   └── ACTIVITIES (List)
│       │
│       ├── ACTIVITY 1: "Taste the Luwak Coffee" (Detailed View)
│       │   ├── id (UUID)
│       │   ├── name
│       │   ├── description
│       │   ├── type ("FOOD_AND_DRINK")
│       │   ├── Operational Data
│       │   │   ├── duration_value / unit (e.g., 3 Hours)
│       │   │   ├── best_time_to_do ("MORNING")
│       │   │   ├── difficulty_level ("EASY")
│       │   │   └── requirements (min_people, min_age)
│       │   │
│       │   ├── Location Data
│       │   │   ├── name ("Kumulilir")
│       │   │   ├── address
│       │   │   ├── url (Google Maps Link)
│       │   │   └── coordinates (Lat/Lng)
│       │   │
│       │   ├── Contacts
│       │   │   ├── whatsapp
│       │   │   └── website
│       │   │
│       │   ├── Instructions (List)
│       │   │   ├── Type: DO (Value: "Arrive before 7:30...")
│       │   │   ├── Type: DONT (Value: "Avoid after 9:00...")
│       │   │   └── Type: SAFETY (Value: "Mosquito repellent...")
│       │   │
│       │   ├── Inclusions
│       │   │   ├── included (List)
│       │   │   └── not_included (List)
│       │   │
│       │   ├── Price Configuration (New!)
│       │   │   ├── type ("PAID")
│       │   │   ├── method ("CASH_OR_CARD")
│       │   │   ├── range (min_cost, max_cost, avg_cost)
│       │   │   ├── tipping_policy
│       │   │   └── breakdown (List of specific items)
│       │   │
│       │   ├── Logistics To Next Activity (New!)
│       │   │   ├── destination_name
│       │   │   ├── distance_km / estimated_time_min
│       │   │   ├── recommended_mode ("TAXI_APP")
│       │   │   ├── provider_options (["Grab", "Gojek"])
│       │   │   ├── estimated_cost_range (Min/Max)
│       │   │   └── note ("Signal is weak here...")
│       │   │
│       │   └── Nearby Recommendations (New!)
│       │       ├── name ("Alas Harum Swing")
│       │       ├── type ("ATTRACTION")
│       │       ├── distance ("500m")
│       │       └── reason ("If you want adrenaline...")
│       │
│       └── ACTIVITY 2: "Sacred Monkey Forest"
│           └── (Repeats same structure as above...)
│
├── DAY 2: "Rice Terraces..."
├── DAY 3: "Lotus Cafe..."
├── DAY 4: "Waterfalls..."
└── DAY 5: "Ridge Walk..."