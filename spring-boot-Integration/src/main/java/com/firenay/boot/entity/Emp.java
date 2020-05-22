package com.firenay.boot.entity;

/**
 * <p>Title: Emp</p>
 * Description：
 * date：2020/5/17 11:58
 */
public class Emp {
	private Integer empId;
	private String empName;
	private Integer empAge;

	@Override
	public String toString() {
		return "Emp{" +
				"empId=" + empId +
				", empName='" + empName + '\'' +
				", empAge=" + empAge +
				'}';
	}
}
