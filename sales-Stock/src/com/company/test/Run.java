package com.company.test;

import javax.swing.SwingUtilities;

import com.company.frontend.LoginFE;

public class Run {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				new LoginFE();
			}
		});
	}
 
}
