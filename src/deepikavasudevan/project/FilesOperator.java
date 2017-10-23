package deepikavasudevan.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

public class FilesOperator {

    private String fileName;
    static Logger logger = LogManager.getLogger("FilesOperator");

    public FilesOperator(String filename) {
        this.fileName = filename;
    }

    public void writeToFile(String requestContents) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(requestContents + ",");
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            logger.error("Could not write to the file");
        }
    }

    public String getContentsFromFile() {
        String line, contents = "[";
        File file = new File(fileName);
        try {
            FileInputStream stream = new FileInputStream(file);
            DataInputStream data = new DataInputStream(stream);
            InputStreamReader bream = new InputStreamReader(data);
            BufferedReader reader = new BufferedReader(bream);

            while ((line = reader.readLine()) != null) {
                contents += line;
            }

            if (contents.endsWith(",")) {
                contents = contents.substring(0, contents.length() - 1) + "]";
            }

            data.close();
            bream.close();
            stream.close();
        } catch(IOException ex) {
            logger.error("Could not read from the file");
        }

        return contents;
    }
}
