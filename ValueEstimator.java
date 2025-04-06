public class ValueEstimator {
    public static String estimateValue(Vehicle vehicle) {
        double baseValue = 20000; // Base value for calculation
        double ageFactor = Math.min((2025 - vehicle.getYear()) * 0.02, 0.5); // Adjusted age factor with a 50% max deduction
        double mileageFactor = Math.min(vehicle.getMileage() * 0.00005, 0.3); // Adjusted mileage factor with a 30% max deduction
        double brandFactor = getBrandFactor(vehicle.getMake());
        double totalFactor = ageFactor + mileageFactor;

        // Calculate estimated value
        double estimatedValue = baseValue * (1 - totalFactor) * brandFactor;

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
            case MERCEDES_BENZ: return 1.5;
            case LAMBORGHINI: return 15.0;
            case BUGATTI: return 20.0;
            case FERRARI: return 19.0;
            case POLESTAR: return 2.8;
            case ROLLS_ROYCE: return 15.0;
            case BENTLEY: return 10.0;
            case JAGUAR: return 2.0;
            case TESLA: return 2.0;
            case MCLAREN: return 19.0;
            case LEXUS: return 2.6;
            case MASERATI: return 11.6;
            case PORSCHE: return 16.8;
            case DACIA: return 0.8;
            case BMW: return 1.4;
            case AUDI: return 1.3;
            case VOLKSWAGEN: return 1.2;
            case TOYOTA: return 1.1;
            case HONDA: return 1.1;
            case FORD: return 1.1;
            case NISSAN: return 1.0;
            case HYUNDAI: return 1.0;
            case KIA: return 1.0;
            case FIAT: return 0.9;
            case PEUGEOT: return 0.9;
            case RENAULT: return 0.9;
            case CITROEN: return 0.8;
            default: return 1.0;
        }
    }
}