package com.forecast;

public class TemperaturePOJO {
	
	private int time;
	private String city;
	private int temperature;
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	@Override
	public String toString() {
		String temp="";
		
		temp="time: "+this.getTime()+" city: "+this.getCity()+" temperature: "+this.getTemperature();
		return super.toString();
	}
	
	
	
}
