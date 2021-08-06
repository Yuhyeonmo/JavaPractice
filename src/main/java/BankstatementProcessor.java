package main.java;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
 * BankstatementAnalyzerSRP 의 응집도 개선 
 * -> 정적 메서드로 계산을 수행 중인 부분을 BankstatementProcessor라는 별도의 클래스로 추출한다. 또한 모든 연산에서 메서드 인수인 입출금 내역 목록을 공유하므로, 이를 클래스의 필드로 생성
 */
public class BankstatementProcessor {
	private final List<BankTransaction> bankTransactions;
	
	public BankstatementProcessor(final List<BankTransaction> bankTransactions){
		this.bankTransactions = bankTransactions;
	}
	
	public double caculateTotalAmount(){
    	double total = 0d;
    	for(final BankTransaction bankTransaction : bankTransactions){
    		total+=bankTransaction.getAmount();
    	}
    	return total;
	}
	
    public List<BankTransaction> caculateInMonth(final Month month){
    	final List<BankTransaction> bankTransactionsInMonth = new ArrayList<BankTransaction>();
    	for(BankTransaction bankTransaction : bankTransactions){
    		if(bankTransaction.getDate().getMonth()==month){
    			bankTransactionsInMonth.add(bankTransaction);
    		}
    	}
    	return bankTransactionsInMonth;
    }
	
    public double calculateTotalForCategory(final String category){
    	double total = 0;
    	for(BankTransaction bankTransaction : bankTransactions){
    		if(bankTransaction.getDescription().equals(category)){
    			total+=bankTransaction.getAmount();
    		}
    	}
    	return total;
    }
    
    public BankTransaction calculateMaxInPeriod(LocalDate start, LocalDate end){
    	
    	double maxValue = Double.MIN_VALUE; 
    	BankTransaction maxTransaction = null;
    	for(BankTransaction bankTransaction : bankTransactions){
    		if(bankTransaction.getDate().isAfter(start) && bankTransaction.getDate().isBefore(end)){
    			if(maxValue < bankTransaction.getAmount()){
    				maxTransaction = bankTransaction;
    				maxValue = bankTransaction.getAmount();
    			}
    		}
    	}
    	
    	return maxTransaction;
    }
    
	public BankTransaction calculateMinInPeriod(LocalDate start, LocalDate end){
	    	
			double minValue = Double.MAX_VALUE; 
			BankTransaction minTransaction = null;
	    	for(BankTransaction bankTransaction : bankTransactions){
	    		if(bankTransaction.getDate().isAfter(start) && bankTransaction.getDate().isBefore(end)){
	    			if(minValue > bankTransaction.getAmount()){
	    				minTransaction = bankTransaction;
	    				minValue = bankTransaction.getAmount();
	    			}
	    		}
	    	}
	    	
	    	return minTransaction;
	    }
    
    
    
}
