import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CSVParserCreateFolder {

    public static void main(String[] args) throws IOException {

        String csvFile = args[1];
        BufferedReader reader = null;
        String line = null;
        String cvsSplitBy = ";";
        Integer numberOfColumn = Integer.valueOf(args[0]);
        String subfolder = args[2];
        String[] subfolderArray = subfolder.split(",");

        Path parentDir = Paths.get(csvFile).getParent();
        Path output = Paths.get(parentDir + "/output");
        Files.createDirectories(output);

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    csvFile), StandardCharsets.UTF_8));

            while ((line = reader.readLine()) != null) {

                String[]country = line.split(cvsSplitBy);

                String value = country[numberOfColumn];

                Path path = Paths.get(output + "/" + value);
                Files.createDirectories(path);

                for (String name : subfolderArray) {
                    name = name.trim();
                    System.out.println(name);
                    createFolder(path, name);
                }
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

        System.out.println("All folders were created!");

    }

    public static void createFolder(Path path, String name) throws IOException {
        Path pathTemp = Paths.get(path + "/" + name);
        Files.createDirectories(pathTemp);
    }
}
