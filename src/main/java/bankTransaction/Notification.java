package main.java.bankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Notification {
	private final List<String> errors = new ArrayList<>();
	private String description;
	private String date;
	private String amount;
	public void addError(final String message){
		errors.add(message);
	}
	public boolean hasErrors(){
		return !errors.isEmpty();
	}
	public String errorMessage(){
		return errors.toString();
	}
	public List<String> getErrors(){
		return this.errors;
	}
	
	public Notification validate(){
		final Notification notification = new Notification();
		
		if(this.description.length()>100){
			notification.addError("The description is too long");
		}
		
		final LocalDate parseDate;
		try {
			parseDate = LocalDate.parse(this.date);
			if(parseDate.isAfter(LocalDate.now())){
				notification.addError("date cannot be in the future");
			}
		} catch(DateTimeParseException e) {
			notification.addError("Invalid format for date");
		}
		
		final double amount;
		try {
			amount = Double.parseDouble(this.amount);
		} catch (NumberFormatException e){
			notification.addError("Invalid format for amount");			
		}
		return notification;
	}
}
