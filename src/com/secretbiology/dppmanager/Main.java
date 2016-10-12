package com.secretbiology.dppmanager;

import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;

public class Main implements MMPlugin {
	public static final String menuName = "DPPmanager";
	public static final String tooltipDescription = "Manages DPP experiment";

	@Override
	public void setApp(ScriptInterface app) {
		new APPWindow().run(app);

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
