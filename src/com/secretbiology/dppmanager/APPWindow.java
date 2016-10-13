package com.secretbiology.dppmanager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.micromanager.api.ScriptInterface;

public class APPWindow {

	private JFrame frame;
	private static ScriptInterface app;

	/**
	 * Launch the application.
	 */
	public void run(ScriptInterface a) {
		app = a;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					APPWindow window = new APPWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public APPWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JFormattedTextField exposureText, gapTimeText, redAdaptation;
	private JTextField textField;

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setName("DPP Manager");
		frame.setBounds(100, 100, 450, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton btnNewButton = new JButton("Start");
		btnNewButton.setBounds(171, 265, 93, 29);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int exposure, gapTime, redAdaptationTime;
					try {
						exposure = Integer.parseInt(exposureText.getText());
						gapTime = Integer.parseInt(gapTimeText.getText());
						redAdaptationTime = Integer.parseInt(redAdaptation.getText());
						if (exposure < 1 || gapTime < 0 || redAdaptationTime < 0) {
							JOptionPane.showMessageDialog(null, "Time can not be negative or exposure can not be zero");
						} else {
							//frame.setVisible(false);
							PluginSetupe s = new PluginSetupe();
							s.setExposure(exposure);
							s.setGapTime(gapTime);
							s.setInitialAdaptation(redAdaptationTime);
							s.setDeviceSetupe(new InitialSetupe());
							new PluginOperation(app, s);
						}

					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Time should be integer");
					}

					//
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		frame.getContentPane().setLayout(null);
		JLabel lblDppManager = new JLabel("DPP Manager 1.0");
		lblDppManager.setForeground(Color.GRAY);
		lblDppManager.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblDppManager.setBounds(130, 16, 146, 20);
		lblDppManager.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblDppManager);
		frame.getContentPane().add(btnNewButton);

		JLabel lblExposure = new JLabel("Blue Light Exposure (ms)");
		lblExposure.setHorizontalAlignment(SwingConstants.TRAILING);
		lblExposure.setBounds(82, 92, 208, 29);
		frame.getContentPane().add(lblExposure);
		lblExposure.setPreferredSize(new Dimension(194, 20));
		lblExposure.setMinimumSize(new Dimension(194, 20));
		lblExposure.setMaximumSize(new Dimension(194, 20));
		lblExposure.setForeground(Color.BLACK);
		lblExposure.setToolTipText("Exposure time will be used for camera (not shutter)");
		lblExposure.setBorder(new EmptyBorder(10, 10, 10, 10));

		exposureText = new JFormattedTextField();
		exposureText.setBounds(305, 92, 108, 29);
		frame.getContentPane().add(exposureText);
		exposureText.setHorizontalAlignment(SwingConstants.CENTER);
		exposureText.setText("90");

		JLabel lblInitialRedLight = new JLabel("Initial Red Light Adaptation (min)");
		lblInitialRedLight.setHorizontalAlignment(SwingConstants.TRAILING);
		lblInitialRedLight.setBounds(29, 52, 261, 29);
		frame.getContentPane().add(lblInitialRedLight);
		lblInitialRedLight.setToolTipText("Dark Adaptation");
		lblInitialRedLight.setForeground(Color.BLACK);
		lblInitialRedLight.setBorder(new EmptyBorder(10, 10, 10, 10));

		redAdaptation = new JFormattedTextField();
		redAdaptation.setBounds(305, 52, 108, 29);
		frame.getContentPane().add(redAdaptation);
		redAdaptation.setText("1");
		redAdaptation.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNoLightAfter = new JLabel("No Light after first flash (s)");
		lblNoLightAfter.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNoLightAfter.setToolTipText("Gap after illumination");
		lblNoLightAfter.setPreferredSize(new Dimension(194, 20));
		lblNoLightAfter.setMinimumSize(new Dimension(194, 20));
		lblNoLightAfter.setMaximumSize(new Dimension(194, 20));
		lblNoLightAfter.setForeground(Color.BLACK);
		lblNoLightAfter.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblNoLightAfter.setBounds(49, 137, 241, 29);
		frame.getContentPane().add(lblNoLightAfter);

		gapTimeText = new JFormattedTextField();
		gapTimeText.setText("10");
		gapTimeText.setHorizontalAlignment(SwingConstants.CENTER);
		gapTimeText.setBounds(305, 138, 108, 29);
		frame.getContentPane().add(gapTimeText);

		JLabel lblOutputFolder = new JLabel("Output Folder");
		lblOutputFolder.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblOutputFolder.setBounds(15, 193, 131, 29);
		frame.getContentPane().add(lblOutputFolder);

		textField = new JTextField();
		textField.setBounds(15, 223, 273, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		final JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setCurrentDirectory(new java.io.File("."));
		f.setDialogTitle("Select Output Folder");
		textField.setText(f.getCurrentDirectory().getAbsolutePath());
		final JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int v = f.showOpenDialog(btnSelect);
				if (v == JFileChooser.APPROVE_OPTION) {
					textField.setText(f.getCurrentDirectory().getPath());
					System.out.println("Selceted ====" + f.getCurrentDirectory());
				}
			}
		});
		btnSelect.setBounds(305, 222, 98, 29);
		frame.getContentPane().add(btnSelect);
	}
	
	private void startProgress() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnAbort = new JButton("Abort");
		btnAbort.setBounds(170, 287, 115, 29);
		frame.getContentPane().add(btnAbort);

		JLabel lblTimeRemaining = new JLabel("Time Remaining");
		lblTimeRemaining.setBounds(15, 50, 151, 20);
		frame.getContentPane().add(lblTimeRemaining);

		JLabel lblSec = new JLabel("0 sec");
		lblSec.setBounds(260, 50, 69, 20);
		frame.getContentPane().add(lblSec);
	}
}
