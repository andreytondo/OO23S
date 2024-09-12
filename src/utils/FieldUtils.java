package utils;

import javax.swing.text.MaskFormatter;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.logging.Logger;

public class FieldUtils {

    private static final Logger LOGGER = Logger.getLogger(FieldUtils.class.getName());

    public static MaskFormatter createPriceMask() {
        return createMask("R$ #.###,##");
    }

    public static MaskFormatter createDateMask() {
        return createMask("##/##/####");
    }

    public static BigDecimal parsePrice(String price) {
        return new BigDecimal(
                price
                        .replace("R$", "")
                        .replace(".", "")
                        .replace(",", ".")
                        .replace(" ", "")
        );
    }

    public static MaskFormatter createMask(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
        } catch (java.text.ParseException e) {
            LOGGER.severe("Error creating formatter: " + e.getMessage());
        }
        return formatter;
    }

    public static Date parseDate(String text) {
        String[] parts = text.split("/");
        return Date.valueOf(parts[2] + "-" + parts[1] + "-" + parts[0]);
    }
}
