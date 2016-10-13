package com.secretbiology.dppmanager;

public class PluginSetupe {

	int exposure;
	int gapTime;
	int initialAdaptation;
	InitialSetupe deviceSetupe;
	public PluginSetupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PluginSetupe(int exposure, int gapTime, int initialAdaptation, InitialSetupe deviceSetupe) {
		super();
		this.exposure = exposure;
		this.gapTime = gapTime;
		this.initialAdaptation = initialAdaptation;
		this.deviceSetupe = deviceSetupe;
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
	public InitialSetupe getDeviceSetupe() {
		return deviceSetupe;
	}
	public void setDeviceSetupe(InitialSetupe deviceSetupe) {
		this.deviceSetupe = deviceSetupe;
	}

	
}
