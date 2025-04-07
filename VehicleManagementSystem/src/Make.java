public enum Make {
    ALFA_ROMEO, ASTON_MARTIN, AUDI, BENTLEY,DACIA, BMW, BUGATTI, CITROEN, FERRARI, FIAT, FORD, HONDA, HYUNDAI, JAGUAR, JEEP, KIA, LAMBORGHINI, LAND_ROVER, LEXUS, LOTUS, MASERATI, MG, MAZDA, MCLAREN, MERCEDES_BENZ, MINI, MITSUBISHI, NISSAN, PEUGEOT, POLESTAR, PORSCHE, RENAULT, ROLLS_ROYCE, SAAB, SUBARU, SUZUKI, TESLA, TOYOTA, VOLKSWAGEN, VOLVO, VAUXHALL, DUCATI, KAWASAKI, YAMAHA, KTM, HARLEY_DAVIDSON, TRIUMPH;

    @Override
    public String toString() {
        switch (this) {
            case BMW:
            case MG:
            case KTM:
                return name();
            default:
                String name = name().replace('_', ' ').toLowerCase();
                String[] words = name.split(" ");
                StringBuilder titleCase = new StringBuilder();
                for (String word : words) {
                    titleCase.append(Character.toUpperCase(word.charAt(0)))
                            .append(word.substring(1))
                            .append(" ");
                }
                return titleCase.toString().trim();
        }
    }
    public static Make searchMake(String searchKey) {
        for (Make make : Make.values()) {
            if (make.toString().toLowerCase().contains(searchKey.toLowerCase())) {
                return make;
            }
        }
        throw new IllegalArgumentException("Invalid make. Please enter a valid make."); // to account for user input error
    }

}