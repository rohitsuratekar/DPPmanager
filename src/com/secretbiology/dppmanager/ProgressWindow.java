package com.secretbiology.dppmanager;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Panel;
import java.awt.Button;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.micromanager.api.ScriptInterface;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProgressWindow {

	private JFrame frame;
	private JTextField textField;
	private static ScriptInterface app;

	/**
	 * Launch the application.
	 */
	public void show(ScriptInterface a) {
		app = a;
		try {
			ProgressWindow window = new ProgressWindow();
			window.frame.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public ProgressWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel InitialSetupe = new JPanel();
		frame.getContentPane().add(InitialSetupe, "name_790073414396907");
		InitialSetupe.setLayout(null);

		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(83, 131, 69, 20);
		InitialSetupe.add(lblTime);

		JButton btnAbort = new JButton("Abort");
		btnAbort.setBounds(132, 256, 115, 29);
		InitialSetupe.add(btnAbort);

		final JPanel ProgressWindow = new JPanel();
		frame.getContentPane().add(ProgressWindow, "name_790127043717101");

		textField = new JTextField();
		ProgressWindow.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("New button");
		ProgressWindow.add(btnNewButton);

		btnAbort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// what to do here?
				CardLayout cardLayout = (CardLayout) frame.getContentPane().getLayout();
				cardLayout.next(frame.getContentPane());
				try {
					PluginSetupe s = new PluginSetupe();
					s.setExposure(90);
					s.setGapTime(10);
					s.setInitialAdaptation(20);
					s.setDeviceSetupe(new InitialSetupe());
					new PluginOperation(app, s);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

	}
}
