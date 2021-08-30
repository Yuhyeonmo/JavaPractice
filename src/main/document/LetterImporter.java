package main.java.document;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import static main.java.document.Attributes.PATIENT;
import static main.java.document.Attributes.ADDRESS;
import static main.java.document.Attributes.BODY;
import static main.java.document.Attributes.TYPE;

class LetterImporter implements Importer {
    private static final String NAME_PREFIX = "Dear ";

    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);

        final int lineNumber = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNumber + 1, (line) -> line.startsWith("regards,"), BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
   
}
