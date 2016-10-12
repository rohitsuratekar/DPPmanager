package com.secretbiology.dppmanager;

import java.util.List;

public class PluginSetupe {

	int exposure;
	int gapTime;
	int initialAdaptation;
	List<Device> deviceList;
	List<DeviceProperty> initialProperties;

	public PluginSetupe(int exposure, int gapTime, int initialAdaptation, List<Device> deviceList,
			List<DeviceProperty> initialProperties) {
		super();
		this.exposure = exposure;
		this.gapTime = gapTime;
		this.initialAdaptation = initialAdaptation;
		this.deviceList = deviceList;
		this.initialProperties = initialProperties;
	}

	public PluginSetupe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getExposure() {
		return exposure;
	}

	public void setExposure(int exposure) {
		this.exposure = exposure;
	}

	public int getGapTime() {
		return gapTime;
	}

	public void setGapTime(int gapTime) {
		this.gapTime = gapTime;
	}

	public int getInitialAdaptation() {
		return initialAdaptation;
	}

	public void setInitialAdaptation(int initialAdaptation) {
		this.initialAdaptation = initialAdaptation;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public List<DeviceProperty> getInitialProperties() {
		return initialProperties;
	}

	public void setInitialProperties(List<DeviceProperty> initialProperties) {
		this.initialProperties = initialProperties;
	}

}
