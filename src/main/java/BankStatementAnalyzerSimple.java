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
// 입출금 내역 분석기 (Simple)
public class BankStatementAnalyzerSimple {

    private static final String RESOURCES = "src/main/resources/";

    public static void main(final String[] args) throws IOException {
            final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
            final List<String> lines = Files.readAllLines(path);
            double total = 0;
            final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            for(final String line: lines) {
                String[] columns = line.split(",");
                final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
                if(date.getMonth()==Month.JANUARY){
                	double amount = Double.parseDouble(columns[1]);
                    total += amount;
                }
                
            }

            System.out.println("모든 거래내역의 합 : " + total);
    }
}