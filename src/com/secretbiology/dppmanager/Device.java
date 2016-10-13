package com.secretbiology.dppmanager;

public class Device {
	
	private String label;
	private String moduleName;
	private String deviceName;

	public Device() {
		// TODO Auto-generated constructor stub
	}

	public Device(String label, String moduleName, String deviceName) {
		super();
		this.label = label;
		this.moduleName = moduleName;
		this.deviceName = deviceName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
	

}
