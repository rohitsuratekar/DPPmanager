package com.secretbiology.dppmanager;

public class DeviceProperty {

	private Device device;
	private String propName;
	private String propValue;
	
	public DeviceProperty(Device device, String propName, String propValue) {
		super();
		this.device = device;
		this.propName = propName;
		this.propValue = propValue;
	}

	public Device getDevice() {
		return device;
	}

	public String getPropName() {
		return propName;
	}

	public String getPropValue() {
		return propValue;
	}




}
