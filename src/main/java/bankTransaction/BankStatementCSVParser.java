package main.java.bankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/*
 * CSV Parser(simple)
 */
public class BankStatementCSVParser implements BankStatementParser{
	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	
	private BankTransaction preseFromCSV(final String line){
		final String[] columns = line.split(",");
		
		final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		final double amount = Double.parseDouble(columns[1]);
		final String description = columns[2];
		
		return new BankTransaction(date, amount, description);
	}
	
	public List<BankTransaction> parseLinesFromCSV(final List<String> lines){
		final List<BankTransaction> bankTranscations = new ArrayList<>();
		for(final String line : lines){
			bankTranscations.add(preseFromCSV(line));
		}
		return bankTranscations;
	}

	@Override
	public BankTransaction parseFrom(String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);

        return new BankTransaction(date, amount, columns[2]);
	}

	@Override
	public List<BankTransaction> parseLinesFrom(List<String> lines) {
		final List<BankTransaction> bankTranscations = new ArrayList<>();
		for(final String line : lines){
			bankTranscations.add(preseFromCSV(line));
		}
		return bankTranscations;
	}


}
