import java.util.Random;

// From research, VIN's made up from WMI VDS AND VIS, so used these naming formats for realistic implementation.
public class VINGen {
    private static final String[] WMI_CODES = {"1HG", "2HG", "3HG", "JHM", "JHL"};
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateVIN() {

        // Generate WMI
// This assigns a random WMI, VDS and VIS together and concatenates to create a unqiue VIN
        String vin = generateWMI() +

                // Generate VDS
                generateVDS() +

                // Generate VIS
                generateVIS();

        return vin;
    }

    private static String generateWMI() {
        Random random = new Random();
        return WMI_CODES[random.nextInt(WMI_CODES.length)];
    }

    private static String generateVDS() {
        Random random = new Random();
        StringBuilder vds = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            vds.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return vds.toString();
    }

    private static String generateVIS() {
        Random random = new Random();
        StringBuilder vis = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            vis.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return vis.toString();
    }
}

