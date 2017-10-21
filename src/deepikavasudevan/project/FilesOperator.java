package deepikavasudevan.project;

import java.io.*;

public class FilesOperator {

    private String fileName;

    public FilesOperator(String filename) {
        this.fileName = filename;
    }

    public  void writeToFile(String requestContents) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(requestContents + ",");
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public String getContentsFromFile() throws IOException {
        String line, contents = "[";
        File file = new File(fileName);
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
        return contents;
    }
}
