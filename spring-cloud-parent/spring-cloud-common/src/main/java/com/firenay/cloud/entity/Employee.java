package com.firenay.cloud.entity;

/**
 * <p>Title: Employee</p>
 * Description：
 * date：2020/5/17 15:19
 */
public class Employee {

	private Integer empId;
	private String empName;
	private Double empSalary;

	@Override
	public String toString() {
		return "Employee{" +
				"empId=" + empId +
				", empName='" + empName + '\'' +
				", empSalary=" + empSalary +
				'}';
	}

	public Employee() {}

	public Employee(Integer empId, String empName, Double empSalary) {
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(Double empSalary) {
		this.empSalary = empSalary;
	}
}
