package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.text.DateFormatter;
//KISS(Keep in short and simple) 
//응용 프로그램 코드를 한 개의 클래스로 구현
// 입출금 내역 분석기 
public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";
    
    
    //특정 파서와의 결합제거
    public void analyze(final String fileName, final BankStatementParser bankStatementParser) throws IOException{
    	
    	final Path path = Paths.get(RESOURCES+fileName);
    	final List<String> lines = Files.readAllLines(path);
    	
    	final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
    	
    	final BankstatementProcessor bankstatementProcessor = new BankstatementProcessor(bankTransactions);
    	
    	collectSummary(bankstatementProcessor);
    	
    }
    
    private static final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

    public static void main(final String[] args) throws IOException {
            final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
            final List<String> lines = Files.readAllLines(path);
            double total = 0;
            
            final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
            final BankstatementProcessor bankstatementProcessor = new BankstatementProcessor(bankTransactions);
            
            collectSummary(bankstatementProcessor);
    }
    
    public static void collectSummary(final BankstatementProcessor bankstatementProcessor){
    	final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	final LocalDate startDate = LocalDate.parse("01-01-2017", DATE_PATTERN);
    	final LocalDate endDate = LocalDate.parse("01-01-2018", DATE_PATTERN);
    	System.out.println("모든 거래내역의 합 :" + bankstatementProcessor.caculateTotalAmount());
    	System.out.println("1월 거래내역의 합 : "+ bankstatementProcessor.caculateInMonth(Month.JANUARY));
    	System.out.println("2월 거래내역의 합:"+ bankstatementProcessor.caculateInMonth(Month.FEBRUARY));
    	System.out.println("Salary received is : "+ bankstatementProcessor.calculateTotalForCategory("Salary"));
    	System.out.println("2017~2018년 거래내역 중 가장 큰 지출내역 "+ bankstatementProcessor.calculateMinInPeriod(startDate, endDate));
    	System.out.println("2017~2018년 거래내역 중 가장 큰 입금내역 "+ bankstatementProcessor.calculateMaxInPeriod(startDate, endDate));
    }
}