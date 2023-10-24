package org.example.core.useCase.costumer;

public class EmailUtils {

    public static String obscureEmail(String email) {
        if (email == null) {
            return null;
        }

        String[] parts = email.split("@");
        if (parts.length != 2) {
            return email;
        }

        String obscuredLocalPart = obscureLocalPart(parts[0]);
        return obscuredLocalPart + "@" + parts[1];
    }

    private static String obscureLocalPart(String localPart) {
        int length = localPart.length();
        if (length <= 2) {
            return "**";
        }

        int halfLength = length / 2;
        return localPart.substring(0, halfLength) + "**";
    }
}