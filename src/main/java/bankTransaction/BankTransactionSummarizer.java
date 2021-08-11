package main.java.bankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
	double summarize(double accmulator, BankTransaction bankTransaction);
}
