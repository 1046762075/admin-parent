package com.firenay.leave.test;

import java.util.List;
import java.util.Map;

public class Student {
	
	private Integer stuId;
	private String stuName;
	private Address address;
	private List<Subject> subjectList;
	private Map<String, String> map;

	public Student() {

	}

	public Student(Integer stuId, String stuName, Address address, List<Subject> subjectList, Map<String, String> map) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.address = address;
		this.subjectList = subjectList;
		this.map = map;
	}

	@Override
	public String toString() {
		return "Student [stuId=" + stuId + ", stuName=" + stuName + ", address=" + address + ", subjectList="
				+ subjectList + ", map=" + map + "]";
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
}
