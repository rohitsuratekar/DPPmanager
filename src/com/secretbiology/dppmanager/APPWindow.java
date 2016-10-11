package com.secretbiology.dppmanager;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class APPWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void run() {
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setText("Clicked");
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		JLabel lblDppManager = new JLabel("DPP Manager 1.0");
		lblDppManager.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblDppManager, BorderLayout.NORTH);
	}

}
