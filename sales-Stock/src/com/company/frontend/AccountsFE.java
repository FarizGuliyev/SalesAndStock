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
import com.company.contract.AccountsContract;
import com.company.contract.WorkerContract;
import com.company.contract.PermitContract;
import com.company.dal.AccountsDal;
import com.company.dal.WorkerDal;
import com.company.dal.PermitDal;
import com.company.interfaces.FeInterfaces;

public class AccountsFE extends JDialog implements FeInterfaces {

	public AccountsFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yeni şifrə təyin edin!"));

		add(panel);
		setTitle("Şifrə təyin olunması");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel workerLabel = new JLabel("İşçi:", JLabel.RIGHT);
		panel.add(workerLabel);
		JComboBox workerBox = new JComboBox(new WorkerDal().GetAll().toArray());
		panel.add(workerBox);
		JLabel permitLabel = new JLabel("Vəzifə təyin edin:", JLabel.RIGHT);
		panel.add(permitLabel);
		JComboBox permitBox = new JComboBox(new PermitDal().GetAll().toArray());
		panel.add(permitBox);
		JLabel passwordLabel = new JLabel("Yeni şifrə: ", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(10);
		panel.add(passwordField);
		JLabel password2Label = new JLabel("Şifrəni təkrarlayın:", JLabel.RIGHT);
		panel.add(password2Label);
		JPasswordField password2Field = new JPasswordField(10);
		panel.add(password2Field);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add (saveButton);
		JButton cancelButton = new JButton("Imtina et");
		panel.add(cancelButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract = new AccountsContract();
				WorkerContract wContract = (WorkerContract) workerBox.getSelectedItem();
				PermitContract pContract = (PermitContract) permitBox.getSelectedItem();
				if (passwordField.getText().equals(password2Field.getText())) {
					contract.setWorkerId(wContract.getId());
					contract.setPermitId(pContract.getId());
					contract.setPassword(passwordField.getText());

					new AccountsDal().Insert(contract);
					JOptionPane.showMessageDialog(null, "Qeydiyyat uğurla tamamlandı!");
				} else {
					JOptionPane.showMessageDialog(null, "Daxil etdiyiniz şifrələr fərqlidir! Yenidən cəhd edin");
				}

			}
		});

		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
