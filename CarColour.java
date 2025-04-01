public enum CarColour {
    BLACK, WHITE, GREY, SILVER, BLUE, RED, BROWN, GREEN, BEIGE, YELLOW,
    GOLD, ORANGE, PURPLE, PINK;
    public static CarColour searchColour(String searchKey) {
        for (CarColour CarColour : CarColour.values()) {
            if (CarColour.toString().toLowerCase().contains(searchKey.toLowerCase())) {
                return CarColour;
            }
        }
        throw new IllegalArgumentException("Invalid colour. Please enter a valid colour."); // to account for user input error
    }
}