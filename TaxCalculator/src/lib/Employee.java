package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee extends data{

	private data pegawai;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(data pegawai,  int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		this.setEmployeeId(pegawai.getEmployeeId());
		this.setFirstName(pegawai.getFirstName());
		this.setLastName(pegawai.getLastName());
		this.setIdNumber(pegawai.getIdNumber());
		this.setAddress(pegawai.getAddress());
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	private final int grade_1 = (int) 3000000;
	private final int grade_2 = (int) 5000000;
	private final int grade_3 = (int) 7000000;
	private final int foreign_1 = (int) 4500000;
	private final int foreign_2 = (int) 7500000;
	private final int foreign_3 = (int) 10500000;

	
	public void setMonthlySalary(int grade) {	
		if (grade == grade_1) {
			monthlySalary = grade_1;
			if (isForeigner) {
				monthlySalary = foreign_1;
			}
		}else if (grade == grade_2) {
			monthlySalary = grade_2;
			if (isForeigner) {
				monthlySalary = foreign_2;
			}
		}else if (grade == grade_3) {
			monthlySalary = grade_3;
			if (isForeigner) {
				monthlySalary = foreign_3;
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
