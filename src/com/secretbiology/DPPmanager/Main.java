package com.secretbiology.DPPmanager;
import java.io.PrintStream;

import mmcorej.CMMCore;

import org.micromanager.api.MMPlugin;
import org.micromanager.api.ScriptInterface;
import javax.swing.JOptionPane;

public class Main implements MMPlugin {
    public static final String menuName = "Hello World";
    public static final String tooltipDescription =
            "Displays a simple dialog";

    // Provides access to the Micro-Manager Java API (for GUI control and high-
    // level functions).
    private ScriptInterface app_;
    // Provides access to the Micro-Manager Core API (for direct hardware
    // control)
    private CMMCore core_;

    public static void main(String[] args) {

        // write your code here
    }


    @Override
    public void setApp(ScriptInterface app) {
        app_ = app;
        core_ = app.getMMCore();
    }

    @Override
    public void dispose() {
        // We do nothing here as the only object we create, our dialog, should
        // be dismissed by the user.
    }

    @Override
    public void show() {
        JOptionPane.showMessageDialog(null, "Hello, world!", "Hello world!",
                JOptionPane.PLAIN_MESSAGE);
    }

    @Override
    public String getInfo () {
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
        return "University of California, 2015";
    }
}

