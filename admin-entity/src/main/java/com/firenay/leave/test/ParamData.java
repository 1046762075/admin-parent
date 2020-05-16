package com.firenay.leave.test;

import java.util.List;

public class ParamData {
	
	private List<Integer> array;
	
	public ParamData() {
		
	}

	public ParamData(List<Integer> array) {
		super();
		this.array = array;
	}

	@Override
	public String toString() {
		return "ParamData [array=" + array + "]";
	}

	public List<Integer> getArray() {
		return array;
	}

	public void setArray(List<Integer> array) {
		this.array = array;
	}
}
