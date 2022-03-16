 package com.company.frontend;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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

import com.company.contract.CategoryContract;
import com.company.dal.CategoryDal;
import com.company.interfaces.FeInterfaces;

public class CategoryFE extends JDialog implements FeInterfaces {

	public CategoryFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Kateqoriyanı daxil edin:"));
		add(panel);

		setTitle("Kateqoriya əlavə edin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel nameLabel = new JLabel("Kateqoriyanın adı: ",JLabel.RIGHT);
		panel.add(nameLabel);
		JTextField nameField = new JTextField(10);
		panel.add(nameField);
		JLabel categoryLabel = new JLabel("Kateqoriya seçin:", JLabel.RIGHT);
		panel.add(categoryLabel);
		JComboBox categoryBox = new JComboBox(new CategoryDal().GetAllParentId().toArray());
		panel.add(categoryBox);
		categoryBox.insertItemAt("-Kateqoriyanızı seçin-", 0);
		categoryBox.setSelectedIndex(0);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryContract contract = new CategoryContract();
				if (categoryBox.getSelectedIndex() != 0) {
					CategoryContract castContract = (CategoryContract) categoryBox.getSelectedItem();

					contract.setName(nameField.getText());
					contract.setParentId(castContract.getId());

					new CategoryDal().Insert(contract);
					JOptionPane.showMessageDialog(saveButton, contract.getName() +  " adlı kateqoriya əlavə olundu");

				} else {

					contract.setName(nameField.getText());
					contract.setParentId(categoryBox.getSelectedIndex());

					new CategoryDal().Insert(contract);
					JOptionPane.showMessageDialog(saveButton, contract.getName() + " adlı kateqoriya əlavə olundu");



				}

			}
		});

		JButton cancelButton = new JButton("İmtina et");
		panel.add(cancelButton);

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
