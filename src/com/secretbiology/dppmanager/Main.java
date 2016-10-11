package com.secretbiology.dppmanager;

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
   public static final String tooltipDescription =
      "Displays a simple dialog";

   // Provides access to the Micro-Manager Java API (for GUI control and high-
   // level functions).
   private ScriptInterface app;
   // Provides access to the Micro-Manager Core API (for direct hardware
   // control)
   private CMMCore core;
   String ImageName = "DPPOutput//DexImage.tiff";
   
   @Override
   public void setApp(ScriptInterface app) {
      app = app;
      core = app.getMMCore();
      APPWindow.run();
   }

   @Override
   public void dispose() {
      // We do nothing here as the only object we create, our dialog, should
      // be dismissed by the user.
   }

   @Override
   public void show() {
      
   }

@Override
public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getDescription() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getInfo() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public String getVersion() {
	// TODO Auto-generated method stub
	return null;
}
   
   
}
