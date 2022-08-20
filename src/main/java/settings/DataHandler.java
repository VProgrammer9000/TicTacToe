package settings;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataHandler {
    Settings settings;
    /**
     * reads the company from the JSON-file
     */
    private void readSettingsJSON() {
        try {
            byte[] jsonData = Files.readAllBytes(
                    Paths.get("data/Company.json")
            );
            ObjectMapper objectMapper = new ObjectMapper();
            settings = objectMapper.readValue(jsonData,Settings.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the company to the JSON-file
     */
    /*public void writeSettingsJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream;
        Writer fileWriter;

        String companyPath = "data/Company.json";
        try {
            fileOutputStream = new FileOutputStream(companyPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, company);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }*/
}
