package eunuch.storage;

import eunuch.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a data manager that reads and writes any data to the device
 */
public class Storage {
    private final File file;

    /**
     * Constructor for the storage
     * @param fileDirPath directory and file path of the desired file to use as storage
     */
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

    /**
     * Reads the file path stored and converts the contents to a TaskList
     * @return a task lsit with the data from the file loaded
     */
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

    /**
     * Writes and saves a task list contents in string form to the file
     * @param list list to be saved
     * @throws IOException if data failed to be saved
     */
    public void writeListToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file.getPath());
        fw.write(list.toData());
        fw.close();
    }
}
