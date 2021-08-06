package test.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import main.java.BankStatementCSVParser;
import main.java.BankStatementParser;
import main.java.BankTransaction;

import org.junit.Assert;
import org.junit.Test;

// 테스트 클래스명에는 Test라는 접미어를 붙이는 것이 관습
public class BankStatementCSVParserTest {
	
	private final BankStatementParser statementParser = new BankStatementCSVParser();
	// TEST annotaion을 통해 해당 메서드가 유닛 테스트 실행 대상임을 지정한다.
	@Test
	public void shouldParseOneCorrectLine() throws Exception {
		//Assert.fail("Not yet implemented");
		
		final String line = "30-01-2017,-50,Tesco";
		
		final BankTransaction result = statementParser.parseFrom(line);
		final BankTransaction expected = new BankTransaction(LocalDate.of(2017,Month.JANUARY, 30), -50, "Tesco");
		
		final double tolerance = 0.0d;
		
		Assert.assertEquals(expected.getDate(), result.getDate());
		Assert.assertEquals(expected.getAmount(), result.getAmount(), tolerance);
		Assert.assertEquals(expected.getDescription(), result.getDescription());
	}
	
	@Test
	public void shouldParseMultiCorrectLine() throws IOException{
		final String RESOURCES = "src/main/resources/";
		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
		final List<BankTransaction> expected = new LinkedList<BankTransaction>();
		
		final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
        for(String line : lines){
        	String columns[] = line.split(",");
        	  final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        	expected.add(new BankTransaction(date, Double.parseDouble(columns[1]), columns[2]));
        }
        
        
        final List<BankTransaction> result = statementParser.parseLinesFrom(lines);
        Assert.assertEquals(expected.toString(), result.toString());
        
	}

}
