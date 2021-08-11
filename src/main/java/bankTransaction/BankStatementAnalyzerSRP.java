package main.java.bankTransaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;
//KISS(Keep in short and simple) 
//응용 프로그램 코드를 한 개의 클래스로 구현
// 입출금 내역 분석기 (SRP)
public class BankStatementAnalyzerSRP {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws IOException {
           
            final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();
            
            //final String fileName = args[0];
            final String fileName = "bank-data-simple.csv";
            final Path path = Paths.get(RESOURCES+fileName);
            final List<String> lines = Files.readAllLines(path);
            
            final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
            
            

            System.out.println("모든 거래내역의 합 : " + calculateTotalAmount(bankTransactions));
            System.out.println("1월 거래내역 : "+ selectMonth(bankTransactions, Month.JANUARY));
    }
    
    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions){
    	double total = 0d;
    	for(final BankTransaction bankTransaction : bankTransactions){
    		total+=bankTransaction.getAmount();
    	}
    	return total;
    }
    public static List<BankTransaction> selectMonth(final List<BankTransaction> bankTransactions, final Month month){
    	final List<BankTransaction> bankTransactionsInMonth = new ArrayList<BankTransaction>();
    	for(BankTransaction bankTransaction : bankTransactions){
    		if(bankTransaction.getDate().getMonth()==month){
    			bankTransactionsInMonth.add(bankTransaction);
    		}
    	}
    	return bankTransactionsInMonth;
    }
}