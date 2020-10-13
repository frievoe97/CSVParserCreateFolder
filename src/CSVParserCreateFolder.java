import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVParserCreateFolder {

    public static void main(String[] args) throws IOException {

        String csvFile = "/Users/friedrich/file.csv";
        BufferedReader reader = null;
        String line = null;
        String cvsSplitBy = ";";
        Integer numberOfColumn = 2;

        Path parentDir = Paths.get(csvFile).getParent();
        Path output = Paths.get(parentDir + "/output");
        Files.createDirectories(output);

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    csvFile), StandardCharsets.UTF_8));

            while ((line = reader.readLine()) != null) {

                String[]country = line.split(cvsSplitBy);

                System.out.println(country[numberOfColumn]);

                Path path = Paths.get(output + "/" + country[numberOfColumn]);
                Files.createDirectories(path);

                createFolder(path, "Unterorder 1");
                createFolder(path, "Bela");
                createFolder(path, "Friedrich");

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void createFolder(Path path, String name) throws IOException {
        Path pathTemp = Paths.get(path + "/" + name);
        Files.createDirectories(pathTemp);
    }
}
