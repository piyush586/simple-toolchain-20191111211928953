package com.revolve;

public class RawMaterial {

	String name;
	double hike;
	int scarcity;
	
	
	public RawMaterial(String name, double hike, int scarcity) {
		super();
		this.name = name;
		this.hike = hike;
		this.scarcity = scarcity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHike() {
		return hike;
	}
	public void setHike(double hike) {
		this.hike = hike;
	}
	public int getScarcity() {
		return scarcity;
	}
	public void setScarcity(int scarcity) {
		this.scarcity = scarcity;
	}
	
}
