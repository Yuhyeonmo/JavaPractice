package main.java.document;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static main.java.document.Attributes.TYPE;
import static main.java.document.Attributes.PATIENT;
import static main.java.document.Attributes.BODY;


class ReportImporter implements Importer {
    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(final File file) throws IOException {
        final TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");
        return new Document(attributes);
    }
}

