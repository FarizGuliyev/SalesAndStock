package com.company.frontend;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.company.contract.CityContract;
import com.company.contract.CostumerContract;
import com.company.dal.CityDal;
import com.company.dal.CostumerDal;
import com.company.interfaces.FeInterfaces;

public class CostumerFE extends JDialog implements FeInterfaces {

	public CostumerFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Müştəri əlavə edin:"));
		add(panel);
		setTitle("Müştəri əlavə edin");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel fieldPanel = new JPanel(new GridLayout(5, 2));
		JPanel buttonJPanel = new JPanel(new GridLayout(1, 2));
		JLabel nameSurnameLabel = new JLabel("Müştərinin adı və soyadı:", JLabel.RIGHT);
		fieldPanel.add(nameSurnameLabel);
		JTextField nameSurnameField = new JTextField(15);
		fieldPanel.add(nameSurnameField);
		JLabel telephoneLabel = new JLabel("Müştərinin telefon nömrəsi:", JLabel.RIGHT);
		fieldPanel.add(telephoneLabel);
		JTextField telephoneField = new JTextField(15);
		fieldPanel.add(telephoneField);
		JLabel cityLabel = new JLabel("Müştərinin yaşadığı şəhər:", JLabel.RIGHT);
		fieldPanel.add(cityLabel);
		JComboBox cityBox = new JComboBox(new CityDal().GetAll().toArray());
		fieldPanel.add(cityBox);
		JLabel adressLabel = new JLabel("Müştərinin yaşadığı ünvan:", JLabel.RIGHT);
		fieldPanel.add(adressLabel);
		JTextArea adressArea = new JTextArea(4, 2);
		JScrollPane pane = new JScrollPane(adressArea);
		pane.setBorder(BorderFactory.createTitledBorder("Müştərinin ünvanını əlavə edin:"));
		JButton saveButton = new JButton("Yadda saxla");
		buttonJPanel.add(saveButton);
		JButton cancelButton = new JButton("İmtina et");
		buttonJPanel.add(cancelButton);
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonJPanel, BorderLayout.SOUTH);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CostumerContract contract = new CostumerContract();
				CityContract castContract = (CityContract) cityBox.getSelectedItem();

				contract.setNameSurname(nameSurnameField.getText());
				contract.setTelephone(telephoneField.getText());
				contract.setCityId(castContract.getId());
				contract.setAdress(adressArea.getText());

				new CostumerDal().Insert(contract);
				JOptionPane.showMessageDialog(null, "Müştəri əlavə olundu!");

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
