package Model;

public class Product {
	
	private String name;
	private String brand;
	private String cpu;
	private String ram;
	private String rom;
	private String display;
	private String pin;
	private int amount;
	private double importPrice;
	private double exportPrice;
	
	
	public Product() {
	}



	public Product(String name, String brand, String cpu, String ram, String rom, String display, String pin,
			int amount, double importPrice, double exportPrice) {
		this.name = name;
		this.brand = brand;
		this.cpu = cpu;
		this.ram = ram;
		this.rom = rom;
		this.display = display;
		this.pin = pin;
		this.amount = amount;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getCpu() {
		return cpu;
	}



	public void setCpu(String cpu) {
		this.cpu = cpu;
	}



	public String getRam() {
		return ram;
	}



	public void setRam(String ram) {
		this.ram = ram;
	}



	public String getRom() {
		return rom;
	}



	public void setRom(String rom) {
		this.rom = rom;
	}



	public String getDisplay() {
		return display;
	}



	public void setDisplay(String display) {
		this.display = display;
	}



	public String getPin() {
		return pin;
	}



	public void setPin(String pin) {
		this.pin = pin;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public double getImportPrice() {
		return importPrice;
	}



	public void setImportPrice(double importPrice) {
		this.importPrice = importPrice;
	}



	public double getExportPrice() {
		return exportPrice;
	}



	public void setExportPrice(double exportPrice) {
		this.exportPrice = exportPrice;
	}


	
	
	
}
