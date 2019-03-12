package com.forecast;

import java.util.List;

public class ResponsePOJONew {

	private int temperature;
	private List<TemperaturePOJO> citynames;
	private int rtcode;
	private String message;
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public List<TemperaturePOJO> getCitynames() {
		return citynames;
	}
	public void setCitynames(List<TemperaturePOJO> citynames) {
		this.citynames = citynames;
	}
	public int getRtcode() {
		return rtcode;
	}
	public void setRtcode(int rtcode) {
		this.rtcode = rtcode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	@Override
	public String toString() {
		String temp="";
		
		temp=this.getTemperature()+" : "+this.getCitynames().toString()+" : "+this.rtcode+" : "+this.getMessage();
		
		
		
		return temp;
	}
}
