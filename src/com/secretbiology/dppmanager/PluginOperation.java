package com.secretbiology.dppmanager;

import org.micromanager.api.ScriptInterface;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ShortProcessor;
import mmcorej.CMMCore;

public class PluginOperation {
	private CMMCore core = new CMMCore();
	private PluginSetupe s = new PluginSetupe();
	int IMAGE_NO = 1;

	int RED_LIGHT = 4;
	int BLUE_LIGHT = 2;
	int NO_LIGHT = 1;

	public PluginOperation(ScriptInterface app, PluginSetupe s) {
		core = app.getMMCore();

		try {
			// core.loadDevice("COM6", "SerialManager", "COM6");
			// core.loadDevice("LudlController", "Ludl", "LudlController");
			// core.loadDevice("LudlWheel", "Ludl", "LudlWheel");
			// core.loadDevice("LudlShutter", "Ludl", "LudlShutter");
			// core.loadDevice("Camera", "PVCAM", "Camera-1");
			//
			// core.setProperty("LudlController", "Port", "COM6");
			// core.setProperty("LudlWheel", "Fiter Positions", "6");
			// core.setProperty("LudlWheel", "Home-Timeout-(s)", "10.0000");
			// core.setProperty("LudlWheel", "LudlDeviceNumberWheel", "1");
			// core.setProperty("LudlWheel", "LudlWheelNumber", "2");
			// core.setProperty("LudlShutter", "LudlDeviceNumberShutter", "1");
			// core.setProperty("LudlShutter", "LudlShutterNumber", "2");

			core.loadDevice("Camera", "DemoCamera", "DCam");
			core.loadDevice("Emission", "DemoCamera", "DWheel");
			core.loadDevice("Shutter", "DemoCamera", "DShutter");

			core.initializeAllDevices();
			core.setAutoShutter(false); // disable auto shutter
			// core.setProperty("LudlShutter", "State", "1"); // open
			// core.waitForDevice("LudlShutter");

			core.setProperty("Shutter", "State", "1");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void capture(int number) {
		String ImageName = "DPPOutput//Image_" + String.valueOf(number) + ".tiff";
		try {
			core.snapImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ShortProcessor im = new ShortProcessor((int) core.getImageWidth(), (int) core.getImageHeight());
		try {
			im.setPixels(core.getImage());
			ImagePlus imp = new ImagePlus(ImageName, im);
			FileSaver fs = new FileSaver(imp);
			fs.saveAsTiff(ImageName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void exposeAndCapture(int light, long time) {
		try {

			if (light != NO_LIGHT) {

				core.setState("LudlWheel", light);
				core.sleep(time);
				if (light != RED_LIGHT) {
					capture(IMAGE_NO);
					IMAGE_NO = IMAGE_NO + 1;
				}
			} else {
				core.setProperty("LudlShutter", "State", "0"); // Close
				core.waitForDevice("LudlShutter");
				core.sleep(time);
				core.setProperty("LudlShutter", "State", "1"); // Close
				core.waitForDevice("LudlShutter");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
