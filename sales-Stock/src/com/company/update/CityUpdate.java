package com.company.update;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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
import com.company.dal.CityDal;
import com.company.interfaces.FeInterfaces;

public class CityUpdate extends JDialog implements FeInterfaces {

	public CityUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("Şəhərə düzəliş edin");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(2, 2));

		upPanel.setBorder(BorderFactory.createTitledBorder("Şəhəri daxil edin"));

		JLabel cityLabel = new JLabel("Şəhərin yeni adı:", JLabel.RIGHT);
		upPanel.add(cityLabel);
		JTextField cityField = new JTextField(10);
		upPanel.add(cityField);

		JLabel citySearchLabel = new JLabel("Şəhər axtarın:", JLabel.RIGHT);
		upPanel.add(citySearchLabel);
		JTextField citySearchField = new JTextField(10);
		upPanel.add(citySearchField);

		JList cityList = new JList();
		cityList.setListData(new CityDal().getSearchCity(citySearchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(cityList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz şəhəri seçin"));

		JPanel updatePanel = new JPanel(new GridLayout(1, 1));
		JButton updateButton = new JButton("Dəyişin");
		updatePanel.add(updateButton);

		citySearchField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				cityList.setListData(new CityDal().getSearchCity(citySearchField.getText()).toArray());
				cityList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CityContract contract = new CityContract();
				CityContract contract2 = (CityContract) cityList.getSelectedValue();
				CityContract contract3 = new CityDal().cityId(contract2);

				contract.setId(contract3.getId());
				contract.setName(cityField.getText());

				new CityDal().Update(contract);
				JOptionPane.showMessageDialog(null, "Seçdiyiniz şəhər "+ cityField.getText()+" olaraq dəyişdirildi!");
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
