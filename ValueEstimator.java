public class ValueEstimator {
    public static String estimateValue(Vehicle vehicle) {
        double baseValue = 20000; // Base value for calculation
        double ageFactor = Math.min((2025 - vehicle.getYear()) * 0.02, 0.5); // Adjusted age factor with a 50% max deduction
        double mileageFactor = Math.min(vehicle.getMileage() * 0.00005, 0.3); // Adjusted mileage factor with a 30% max deduction
        double brandFactor = getBrandFactor(vehicle.getMake());
        double totalFactor = ageFactor + mileageFactor + brandFactor;


        // Ensure total factor doesn't exceed 1 (100%)
        totalFactor = Math.min(totalFactor, 1.0);

        // Calculate estimated value
        double estimatedValue = baseValue * (1 - totalFactor);

        // Ensure the value doesn't go negative and round to the nearest full number
        estimatedValue = Math.max(estimatedValue, 0);
        int roundedValue = (int) Math.round(estimatedValue);


        if (vehicle instanceof Motorbike) {
            roundedValue *= 0.5;
        }

        // Prefix with "£"
                return "£" + roundedValue;
            }

    private static double getBrandFactor(Make make) {
        switch (make) {
            case MERCEDES_BENZ: return 0.32;
            case DACIA: return 0.1;
            case BMW: return 0.18;
            case AUDI: return 0.17;
            case VOLKSWAGEN: return 0.16;
            case TOYOTA: return 0.14;
            case HONDA: return 0.13;
            case FORD: return 0.12;
            case NISSAN: return 0.11;
            case HYUNDAI: return 0.10;
            case KIA: return 0.09;
            case FIAT: return 0.08;
            case PEUGEOT: return 0.07;
            case RENAULT: return 0.06;
            case CITROEN: return 0.05;
            default: return 0.15;
        }
    }
}