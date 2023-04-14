package BLL.Util;

import java.util.UUID;

public class QRCodeStringGenerator {
    private String generatedString;


    public QRCodeStringGenerator() {
        generateString();
    }

    private void generateString(){
        UUID uuid = UUID.randomUUID();
        generatedString = uuid.toString();
    }

    public String getGeneratedString() {
        return generatedString;
    }
}
