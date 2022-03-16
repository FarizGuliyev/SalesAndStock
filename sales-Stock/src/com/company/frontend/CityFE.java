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
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.company.contract.CityContract;
import com.company.dal.CityDal;
import com.company.dal.CategoryDal;
import com.company.interfaces.FeInterfaces;

public class CityFE extends JDialog implements FeInterfaces {

	public CityFE() {
		initWindow();
	}

	@Override
	public void initWindow() {

		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Şəhər daxil edin:"));
		add(panel);

		setTitle("Şəhərlər");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel nameLabel = new JLabel("Şəhərin adı: ");
		panel.add(nameLabel);
		JTextField nameField = new JTextField(10);
		panel.add(nameField);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add(saveButton);
		JButton cancelButton=new JButton("İmtina et");
		panel.add(cancelButton);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			CityContract contract=new CityContract();
		contract.setName(nameField.getText());
		new CityDal().Insert(contract);
				JOptionPane.showMessageDialog(null, "Şəhər əlavə olundu!");
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
