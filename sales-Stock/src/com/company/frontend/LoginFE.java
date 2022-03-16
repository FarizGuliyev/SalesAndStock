package com.company.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

import com.company.contract.WorkerContract;
import com.company.dal.AccountsDal;
import com.company.dal.WorkerDal;
import com.company.interfaces.FeInterfaces;

public class LoginFE extends JDialog implements FeInterfaces {
	public static JComboBox eMailBox;

	public LoginFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Hesabınıza daxil olun"));
		add(panel);

		setTitle("Hesaba daxil olun");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
		panel.add(emailLabel);
		eMailBox = new JComboBox(new WorkerDal().GetAll().toArray());
		panel.add(eMailBox);
		JLabel passwordLabel = new JLabel("Şifrə:", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);

		JButton loginButton = new JButton("Daxil ol");
		panel.add(loginButton);
		JButton cancelButton = new JButton("İmtina et");
		panel.add(cancelButton);

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WorkerContract contract = (WorkerContract) eMailBox.getSelectedItem();
				if (new AccountsDal().GetWorkerIdPassword(contract.getId(), passwordField.getText()).getId() != 0) {
					new MainWindowFE();
				} else {
					JOptionPane.showMessageDialog(null, "Giriş icazəsi yoxdur!");
				}
			}
		});

		return panel;
	}

	@Override
	public JMenuBar initBar() {
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
