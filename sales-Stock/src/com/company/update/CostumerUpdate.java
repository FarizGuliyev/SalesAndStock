package com.company.update;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.company.contract.CityContract;
import com.company.contract.CostumerContract;
import com.company.dal.CityDal;
import com.company.dal.CostumerDal;
import com.company.dal.ProductDal;
import com.company.interfaces.FeInterfaces;

public class CostumerUpdate extends JDialog implements FeInterfaces {

	public CostumerUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("Müştəriyə düzəliş edin");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(5, 2));

		JLabel newCostumerLabel = new JLabel("Yeni müştərinin adı:", JLabel.RIGHT);
		upPanel.add(newCostumerLabel);
		JTextField newCostumerField = new JTextField(15);
		upPanel.add(newCostumerField);

		JLabel telephoneLabel = new JLabel("Yeni müştərinin telefonu:", JLabel.RIGHT);
		upPanel.add(telephoneLabel);
		JTextField telephoneField = new JTextField(15);
		upPanel.add(telephoneField);

		JLabel adressLabel = new JLabel("Yeni müştərinin ünvanı:", JLabel.RIGHT);
		upPanel.add(adressLabel);
		JTextField adressField = new JTextField(15);
		upPanel.add(adressField);

		JLabel cityLabel = new JLabel("Yeni müştərinin şəhəri:", JLabel.RIGHT);
		upPanel.add(cityLabel);
		JComboBox cityBox = new JComboBox(new CityDal().GetAll().toArray());
		upPanel.add(cityBox);

		JLabel searchLabel = new JLabel("Müştəri axtarış:", JLabel.RIGHT);
		upPanel.add(searchLabel);
		JTextField searchField = new JTextField(15);
		upPanel.add(searchField);

		upPanel.setBorder(BorderFactory.createTitledBorder("Müştərinin məlumatlarını daxil edin"));

		JList costumerList = new JList();
		costumerList.setListData(new CostumerDal().getSearchCostumer(searchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(costumerList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz müştərini seçin"));

		JPanel updatePanel = new JPanel(new GridLayout(1, 1));
		JButton updateButton = new JButton("Dəyişin");
		updatePanel.add(updateButton);

		searchField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				costumerList.setListData(new CostumerDal().getSearchCostumer(searchField.getText()).toArray());
				costumerList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CostumerContract contract = new CostumerContract();
				CityContract contract2 = (CityContract) cityBox.getSelectedItem();
				CostumerContract contract3 = (CostumerContract) costumerList.getSelectedValue();
				CostumerContract contract4 = new CostumerDal().costumerId(contract3);

				contract.setId(contract4.getId());
				contract.setNameSurname(newCostumerField.getText());
				contract.setTelephone(telephoneField.getText());
				contract.setAdress(adressField.getText());
				contract.setCityId(contract2.getId());

				new CostumerDal().Update(contract);
				JOptionPane.showMessageDialog(null, "Seçdiyiniz müştərinin məlumatları dəyişdirildi! ");
			}
		});

		panel.add(upPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(updatePanel, BorderLayout.SOUTH);

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
