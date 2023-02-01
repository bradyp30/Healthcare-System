package userclasses;

public class NewTest {
	
	private int id;
	private String name;
	private double upperBound;
	private double lowerBound;
	private String unit;
	
	public NewTest(int id, String name, double upperBound, double lowerBound, String unit) {
		super();
		this.id = id;
		this.name = name;
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
		this.unit = unit;
	}
	
	public NewTest(String name, double upperBound, double lowerBound, String unit) {
		super();
		this.name = name;
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
		this.unit = unit;
	}
	
	public NewTest(double upperBound, double lowerBound) {
		this.upperBound = upperBound;
		this.lowerBound = lowerBound;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUpperBound() {
		return upperBound;
	}
	public void setUpperBound(double upperBound) {
		this.upperBound = upperBound;
	}
	public double getLowerBound() {
		return lowerBound;
	}
	public void setLowerBound(double lowerBound) {
		this.lowerBound = lowerBound;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	

}
