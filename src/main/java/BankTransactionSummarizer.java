package main.java;

@FunctionalInterface
public interface BankTransactionSummarizer {
	double summarize(double accmulator, BankTransaction bankTransaction);
}
