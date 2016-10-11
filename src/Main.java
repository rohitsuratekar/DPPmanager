
import java.io.File;

import javax.swing.JOptionPane;

import mmcorej.CMMCore;

import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ShortProcessor;

public class Main implements MMPlugin {
	public static final String menuName = "DPPmanager";
	public static final String tooltipDescription = "Displays a simple dialog";

	// Provides access to the Micro-Manager Java API (for GUI control and high-
	// level functions).
	private ScriptInterface app;
	// Provides access to the Micro-Manager Core API (for direct hardware
	// control)
	private CMMCore core;

	String ImageName_Main = "DPPOutput//DexImage";
	double mInterval = 3000;
	boolean red = true;

	@Override
	public void setApp(ScriptInterface app) {
		app = app;
		core = app.getMMCore();
		new File("DPPOutput/").mkdirs();

		try {
			core.loadDevice("COM6", "SerialManager", "COM6");
			core.loadDevice("LudlController", "Ludl", "LudlController");
			core.loadDevice("LudlWheel", "Ludl", "LudlWheel");
			core.loadDevice("LudlShutter", "Ludl", "LudlShutter");
			core.loadDevice("Camera", "PVCAM", "Camera-1");

			core.setProperty("LudlController", "Port", "COM6");
			core.setProperty("LudlWheel", "Fiter Positions", "6");
			core.setProperty("LudlWheel", "Home-Timeout-(s)", "10.0000");
			core.setProperty("LudlWheel", "LudlDeviceNumberWheel", "1");
			core.setProperty("LudlWheel", "LudlWheelNumber", "2");
			core.setProperty("LudlShutter", "LudlDeviceNumberShutter", "1");
			core.setProperty("LudlShutter", "LudlShutterNumber", "2");

			core.initializeAllDevices();
			core.setAutoShutter(false); // disable auto shutter
			core.setProperty("LudlShutter", "State", "1"); // open
			core.waitForDevice("LudlShutter");
			
			for (int i = 0; i < 3; i++) {
				if (red) {
					core.setState("LudlWheel", 4);
				} else {
					core.setState("LudlWheel", 2);
				}
				core.waitForDevice("LudlWheel"); // until it stops moving
				String ImageName = ImageName_Main + String.valueOf(i) + ".tiff";
				core.snapImage();
				ShortProcessor im = new ShortProcessor((int) core.getImageWidth(), (int) core.getImageHeight());
				im.setPixels(core.getImage());
				ImagePlus imp = new ImagePlus(ImageName, im);
				FileSaver fs = new FileSaver(imp);
				fs.saveAsTiff(ImageName);
				core.sleep(mInterval);
				mInterval = mInterval + 3000;
				red = !red;
			}
			
			core.setProperty("LudlShutter", "State", "0"); // Close

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void dispose() {
		// We do nothing here as the only object we create, our dialog, should
		// be dismissed by the user.
	}

	@Override
	public void show() {
		JOptionPane.showMessageDialog(null, "DPP Manager 1.0", "Your analysis is complete.", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public String getInfo() {
		return "Displays a simple greeting.";
	}

	@Override
	public String getDescription() {
		return tooltipDescription;
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getCopyright() {
		return "National Centre for Biological Sceinces, 2016";
	}

	@Override
	public void configurationChanged() {
		// TODO Auto-generated method stub

	}
}