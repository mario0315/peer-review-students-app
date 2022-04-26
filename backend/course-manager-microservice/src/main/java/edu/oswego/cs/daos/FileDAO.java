package edu.oswego.cs.daos;

import com.ibm.websphere.jaxrs20.multipart.IAttachment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FileDAO {

    private String filename;
    private List<String> csvLines;

    /**
     * Takes form-data from a POST request for a csv file and reconstructs the content within the file
     * @param attachments form-data
     * @return FileDAO Instance
     * @throws Exception File Corruption Exception
     */
    public static FileDAO FileFactory(List<IAttachment> attachments) throws Exception {
        List<String> csvLines = new ArrayList<>();

        for (IAttachment attachment : attachments) {
            if (attachment == null) continue;
            String fileName = attachment.getDataHandler().getName();

            if (fileName != null) {
                InputStream stream = attachment.getDataHandler().getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String line = "";
                try {
                    while ((line = reader.readLine()) != null)
                        if (!line.isEmpty()) csvLines.add(line);
                    if (csvLines.size() == 0) continue;
                    reader.close();

                    csvLines = csvLines.stream()
                            .map( str -> str.replaceAll("[^a-zA-Z0-9_-]", "") )
                            .collect(Collectors.toList());

                    return new FileDAO(fileName, csvLines);
                } catch (IOException ignored) {}
            }
        }
        throw new Exception();
    }

}
