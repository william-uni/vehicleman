public class ValueEstimator {
    public static double estimateValue(Vehicle vehicle) {
        double baseValue = 20000; // Base value for calculation
        double ageFactor = (2025 - vehicle.getYear()) * 0.03; // Adjusted age factor
        double mileageFactor = vehicle.getMileage() * 0.0001; // Adjusted mileage factor
        double brandFactor = getBrandFactor(vehicle.getMake());
        return baseValue * (1 - (ageFactor + mileageFactor + brandFactor));
    }

    private static double getBrandFactor(Make make) {
        switch (make) {
            case MERCEDES_BENZ: return 0.2;
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