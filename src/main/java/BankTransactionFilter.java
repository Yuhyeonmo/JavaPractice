package main.java;
/*
 * 함수형 인터페이스 @FunctionalInterface을 통해 의도를 명확하게 표현
 * BankTransaction의 선택조건을 결정
 */
@FunctionalInterface
public interface BankTransactionFilter {
	boolean test(BankTransaction bankTransaction);
}
