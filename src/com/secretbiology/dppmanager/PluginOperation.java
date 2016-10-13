package com.secretbiology.dppmanager;

import javax.swing.JOptionPane;

import org.micromanager.api.ScriptInterface;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;
import mmcorej.CMMCore;

public class PluginOperation {
	private CMMCore core = new CMMCore();
	Device shutter, camera, wheel;
	int IMAGE_NO = 1;

	int RED_LIGHT = 4;
	int BLUE_LIGHT = 2;
	int NO_LIGHT = 1;

	public PluginOperation(ScriptInterface app, PluginSetupe s) {
		core = app.getMMCore();
		InitialSetupe i = s.getDeviceSetupe();
		shutter = i.getShutter();
		camera = i.getCamera();
		wheel = i.getShutterWheel();
		try {
			for (Device d : i.getDefaultDevice()) {
				core.loadDevice(d.getLabel(), d.getModuleName(), d.getDeviceName());
			}
			// Always load properties AFTER loading device

			for (DeviceProperty p : i.getDefaultProperties()) {
				core.setProperty(p.getDevice().getLabel(), p.getPropName(), p.getPropValue());
			}

			core.initializeAllDevices();
			core.setAutoShutter(false); // disable auto shutter
			core.setProperty(shutter.getLabel(), "State", "1");
			core.waitForDevice(shutter.getLabel());
			
			exposeAndCapture(RED_LIGHT, 1000);
			exposeAndCapture(BLUE_LIGHT, 1000);
			exposeAndCapture(NO_LIGHT, 1000);

			core.setProperty(shutter.getLabel(), "State", "0");
			JOptionPane.showConfirmDialog(null, "Analysis is comeplete", "DPPmanager 1.0", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
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
		long byteDepth = core.getBytesPerPixel();
		ImageProcessor im;
		if (byteDepth == 1) {
			im = new ByteProcessor((int) core.getImageWidth(), (int) core.getImageHeight());
		} else {
			im = new ShortProcessor((int) core.getImageWidth(), (int) core.getImageHeight());
		}
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
				core.setState(wheel.getLabel(), light);
				core.sleep(time);
				if (light != RED_LIGHT) {
					capture(IMAGE_NO);
					IMAGE_NO = IMAGE_NO + 1;
				}
			} else {
				core.setProperty(shutter.getLabel(), "State", "0"); // Close
				core.waitForDevice(shutter.getLabel());
				core.sleep(time);
				core.setProperty(shutter.getLabel(), "State", "1"); // Close
				core.waitForDevice(shutter.getLabel());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
