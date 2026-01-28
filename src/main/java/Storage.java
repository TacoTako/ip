import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String fileDirPath) {
        if (fileDirPath.isEmpty()) {
            fileDirPath = "data/eunuch_data.txt";
        }

        this.file = new File(fileDirPath);

        //create directories if needed
        File parent_directory = file.getParentFile();
        if (parent_directory != null) {
            parent_directory.mkdirs();
        }
    }


    private TaskList loadListFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                lines.add(s.nextLine());
            }
            s.close();

            return new TaskList(lines);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    private void writeListToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        fw.write(list.toData());
        fw.close();
    }
}
