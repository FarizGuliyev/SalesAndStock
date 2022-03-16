package com.company.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.company.contract.WorkerContract;
import com.company.dal.WorkerDal;
import com.company.interfaces.FeInterfaces;

public class WorkerFE extends JDialog implements FeInterfaces {

	public WorkerFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("İşçi əlavə edin:"));
		add(panel);
		setTitle("İşçi əlavə edin");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {

		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel nameSurnameLabel = new JLabel("Adı Soyadı: ", JLabel.RIGHT);
		panel.add(nameSurnameLabel);
		JTextField nameSurnameField = new JTextField(10);
		panel.add(nameSurnameField);
		JLabel eMailLabel = new JLabel("Email: ", JLabel.RIGHT);
		panel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add(saveButton);
		JButton cancelButton = new JButton("İmtina et");
		panel.add(cancelButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WorkerContract contract = new WorkerContract();
				contract.setNameSurname(nameSurnameField.getText());
				contract.setEmail(eMailField.getText());
				new WorkerDal().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getNameSurname() + " adlı işçi əlavə olundu!");

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
