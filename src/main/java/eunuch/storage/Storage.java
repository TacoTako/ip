package eunuch.storage;

import eunuch.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File file;

    public Storage(String fileDirPath) {
        if (fileDirPath.isEmpty()) {
            fileDirPath = "data/eunuch_data.txt";
        }

        this.file = new File(fileDirPath);

        //create directories if needed
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null) {
            parentDirectory.mkdirs();
        }
    }


    public TaskList loadListFromFile() {
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

    public void writeListToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        fw.write(list.toData());
        fw.close();
    }
}
