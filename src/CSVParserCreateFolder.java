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

        Integer countFolders = 0;
        Integer countSubfolders = subfolderArray.length;

        try {

            reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    csvFile), StandardCharsets.UTF_8));

            while ((line = reader.readLine()) != null) {

                String[]country = line.split(cvsSplitBy);

                String value = country[numberOfColumn];

                Path path = Paths.get(output + "/" + value);
                Files.createDirectories(path);

                countFolders += 1;

                for (String name : subfolderArray) {
                    name = name.trim();
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

        System.out.println("\nAll folders were created in this folder: " + output);
        System.out.println("\nA total of " + countFolders + " folders with " + countSubfolders + " subfolders each were created.\nThat makes a total of " + (countFolders * countSubfolders) + " folders. These are located in\nthe folder \"output\", which is in the same path as the .csv file.\n\nHave fun!");
        System.out.println("\nIf you needed 10 seconds for each folder, if you had created it\nmanually, you would have saved " + ((countFolders * countSubfolders * 10)/60) + " minutes or " + ((countFolders * countSubfolders * 10)/3600) + " hours!");

    }

    public static void createFolder(Path path, String name) throws IOException {
        Path pathTemp = Paths.get(path + "/" + name);
        Files.createDirectories(pathTemp);
    }
}
