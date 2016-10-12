package com.secretbiology.dppmanager;

import java.util.ArrayList;
import java.util.List;

public class InitialSetupe {

	public List<Device> getDefaultDevice() {
		List<Device> allDevices = new ArrayList<Device>();
		allDevices.add(getCamera());
		allDevices.add(getShutter());
		allDevices.add(getShutterWheel());
		return allDevices;
	}
	
	public List<DeviceProperty> getDefaultProperties() {
		List<DeviceProperty> allDevicesProp = new ArrayList<DeviceProperty>();
		allDevicesProp.add(new DeviceProperty(getShutterWheel(), "Filter Positions", "6"));
		allDevicesProp.add(new DeviceProperty(getShutterWheel(), "Home-Timeout-(s)", "10.0000"));
		allDevicesProp.add(new DeviceProperty(getShutterWheel(), "LudlDeviceNumberWheel", "1"));
		allDevicesProp.add(new DeviceProperty(getShutterWheel(), "LudlWheelNumber", "2"));
		allDevicesProp.add(new DeviceProperty(getShutter(), "LudlDeviceNumberShutter", "1"));
		allDevicesProp.add(new DeviceProperty(getShutter(), "LudlShutterNumber", "2"));
		return allDevicesProp;
	}

	private Device getCamera() {
		Device cam = new Device();
//		cam.setLabel("Camera");
//		cam.setModuleName("PVCAM");
//		cam.setDeviceName("Camera-1");		
		cam.setLabel("Camera");
		cam.setModuleName("DemoCamera");
		cam.setDeviceName("DCam");
		return cam;
	}
	
	private Device getShutter() {
		Device shutter = new Device();
//		shutter.setLabel("LudlShutter");
//		shutter.setModuleName("Ludl");
//		shutter.setDeviceName("LudlShutter");
		shutter.setLabel("LudlShutter");
		shutter.setModuleName("DemoCamera");
		shutter.setDeviceName("DShutter");
		return shutter;
	}
	
	private Device getShutterWheel() {
		Device shutterWheel = new Device();
//		shutterWheel.setLabel("LudlWheel");
//		shutterWheel.setModuleName("Ludl");
//		shutterWheel.setDeviceName("LudlWheel");
		shutterWheel.setLabel("LudlWheel");
		shutterWheel.setModuleName("DemoCamera");
		shutterWheel.setDeviceName("DWheel");
		return shutterWheel;
	}

	
	
	

}
