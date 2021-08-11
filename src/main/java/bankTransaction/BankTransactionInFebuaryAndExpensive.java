package main.java.bankTransaction;

import java.time.Month;

/*
 * 요구사항에 맞는 필터 구현
 * findTransactions() 메서드의 인수로 필터의 인스턴스를 사용한다.
 */

public class BankTransactionInFebuaryAndExpensive implements BankTransactionFilter {

	@Override
	public boolean test(BankTransaction bankTransaction) {
		// TODO Auto-generated method stub
		
		return bankTransaction.getDate().getMonth()==Month.FEBRUARY 
				&& bankTransaction.getAmount()>=1_000;
	}
	

}
