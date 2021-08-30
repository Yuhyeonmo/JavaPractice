package main.java.document;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class DocumentManageSystem {
	 private final Map<String, Importer> extensionToImporter = new HashMap<>();
	 private final ArrayList<Document> documents = new ArrayList<>();
	 private final List<Document> documentsView = unmodifiableList(documents);
	    public DocumentManageSystem() {
	        extensionToImporter.put("letter", new LetterImporter());
	        extensionToImporter.put("report", new ReportImporter());
	        extensionToImporter.put("jpg", new ImageImporter());
	    }
	    
	    public void importFile(final String path) throws IOException{
	    	final File file = new File(path);
	    	if(!file.exists()) {
	    		throw new FileNotFoundException();
	    	}
	    	
	    	final int separatorIndex = path.lastIndexOf('.');
	    	if(separatorIndex!= - 1) {
	    		if(separatorIndex == path.length()){
	    			throw new UnknownFileTypeException("해당 파일 경로에서 확장자를 찾을 수 없습니다.");
	    		}
	    		
	    		final String extension = path.substring(separatorIndex+1);
	    		final Importer importer = extensionToImporter.get(extension);
	    		if(importer==null) {
	    			throw new UnknownFileTypeException("not Importer Type");
	    		}
	    		final Document document = importer.importFile(file);
	    		documents.add(document);
	    	} else {
	    		throw new UnknownFileTypeException("파일에 확장자가 없습니다.");
	    	}
	    }
	    
	    public List<Document> contents() {
	        return documentsView;
	    }

	    public List<Document> search(final String query) {
	        return documents.stream()
	                        .filter(Query.parse(query))
	                        .collect(Collectors.toList());
	    }
}
