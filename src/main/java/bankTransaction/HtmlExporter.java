package main.java.bankTransaction;

// Exporter 인터페이스 구현
public class HtmlExporter implements Exporter{

	@Override
	public String export(final SummaryStatistics summaryStatistics) {
		// TODO Auto-generated method stub
		String result = "<!doctype html>";
		result +="<html lang='ko'>";
		result +="<head> <title>Bank Transaction Report</title></head>";
		result +="<body>";
		result +="<ul>";
		result +="<li><strong>합계는</strong>:" + summaryStatistics.getSum() + "</li>";
		result +="<li><strong>평균은</strong>:" + summaryStatistics.getAverage() + "</li>";
		result +="<li><strong>최대는</strong>:" + summaryStatistics.getMax() + "</li>";
		result +="<li><strong>최소는</strong>:" + summaryStatistics.getMin() + "</li>";
		result +="</ul>";
		result +="</body>";
		result +="</html>";
		return null;
	}
	
}
