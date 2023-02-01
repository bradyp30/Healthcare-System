package userclasses;

import java.time.LocalDate;

public class TestData {
	
	private String ssn;
	private int pressureHigh;
	private int pressureLow;
	private int pulse;
	private double temperature;
	private int oxygen;
	private int glucose;
	private double weight;
	private String date;
	
	//all fields
	public TestData(String ssn, int pressureHigh, int pressureLow, int pulse, double temperature, int oxygen, int glucose,
			double weight, String date) {
		this.ssn = ssn;
		this.pressureHigh = pressureHigh;
		this.pressureLow = pressureLow;
		this.pulse = pulse;
		this.temperature = temperature;
		this.oxygen = oxygen;
		this.glucose = glucose;
		this.weight = weight;
		this.date = date;
	}
	
	//no ssn
	public TestData(int pressureHigh, int pressureLow, int pulse, double temperature, int oxygen, int glucose, double weight,
			String date) {
		this.pressureHigh = pressureHigh;
		this.pressureLow = pressureLow;
		this.pulse = pulse;
		this.temperature = temperature;
		this.oxygen = oxygen;
		this.glucose = glucose;
		this.weight = weight;
		this.date = date;
	}
	
	//only ssn and date
	public TestData(String ssn, String date) {
		this.ssn = ssn;
		this.date = date;
	
		
	}


	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getPressureHigh() {
		return pressureHigh;
	}
	public void setPressureHigh(int pressureHigh) {
		this.pressureHigh = pressureHigh;
	}
	public int getPressureLow() {
		return pressureLow;
	}
	public void setPressureLow(int pressureLow) {
		this.pressureLow = pressureLow;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getOxygen() {
		return oxygen;
	}
	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}
	public int getGlucose() {
		return glucose;
	}
	public void setGlucose(int glucose) {
		this.glucose = glucose;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
