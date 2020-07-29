package by.tsikunov.day8.model.util;

import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private static final String SPLITTER = ",";

    public DataParser(){

    }
    public List<String> getAuthor(String data) {
        List<String> authors = new ArrayList<>();
        String[] lines = data.split(SPLITTER);
        for (String line : lines) {
            authors.add(line.trim());
        }
        return authors;
    }
}
