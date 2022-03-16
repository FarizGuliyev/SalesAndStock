package com.company.frontend;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

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
import com.company.contract.ProductContract;
import com.company.dal.CategoryDal;
import com.company.dal.ProductDal;
import com.company.interfaces.FeInterfaces;
import com.toedter.calendar.JDateChooser;

public class ProductFE extends JDialog implements FeInterfaces {

	public ProductFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Məhsulun məlumatlarını daxil edin"));
		add(panel);
		setTitle("Məhsul əlavə edin ");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));
		JLabel nameLabel = new JLabel("Məhsulun adı:", JLabel.RIGHT);
		panel.add(nameLabel);
		JTextField nameField = new JTextField(10);
		panel.add(nameField);
		JLabel categoryLabel = new JLabel("Məhsulun kateqoriyası:", JLabel.RIGHT);
		panel.add(categoryLabel);
		JComboBox categoryBox = new JComboBox(new CategoryDal().GetAll().toArray());
		panel.add(categoryBox);
		JLabel dateLabel = new JLabel("Məhsulun tarixi:", JLabel.RIGHT);
		panel.add(dateLabel);
		JDateChooser date = new JDateChooser();
		panel.add(date);
		JLabel priceLabel = new JLabel("Məhsulun qiyməti:", JLabel.RIGHT);
		panel.add(priceLabel);
		JTextField priceField = new JTextField(10);
		panel.add(priceField);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductContract contract = new ProductContract();

				CategoryContract castContract = (CategoryContract) categoryBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String formatDate = format.format(date.getDate());
				contract.setName(nameField.getText());
				contract.setCategoryId(castContract.getId());
				contract.setDate(formatDate);
				contract.setPrice(Float.parseFloat(priceField.getText()));
				new ProductDal().Insert(contract);
				JOptionPane.showMessageDialog(saveButton, contract.getName() + " adlı məhsul əlavə olundu!");

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
