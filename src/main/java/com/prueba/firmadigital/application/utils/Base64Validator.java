package com.prueba.firmadigital.application.utils;

import org.apache.commons.lang3.StringUtils;
import java.util.Base64;

public class Base64Validator {

    public static boolean validate(String inputBase64) {
        if (StringUtils.isBlank(inputBase64)) {

            return false;
        }

        try {
            byte[] decoded = Base64.getDecoder().decode(inputBase64);
            String encoded = Base64.getEncoder().encodeToString(decoded);

            return encoded.equals(inputBase64.replaceAll("\\s+", ""));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isPdf(String inputBase64) {
        if (StringUtils.isBlank(inputBase64)) {

            return false;
        }

        try {
            inputBase64 = inputBase64.replaceAll("\\s+", "");
            byte[] decoded = Base64.getDecoder().decode(inputBase64);
            String header = new String(decoded, 0, Math.min(decoded.length, 5));

            return header.startsWith("%PDF");
        } catch (Exception e) {

            return false;
        }
    }
}
