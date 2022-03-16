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

import com.company.contract.PermitContract;
import com.company.dal.PermitDal;
import com.company.dal.WorkerDal;
import com.company.interfaces.FeInterfaces;

public class PermitUpdate extends JDialog implements FeInterfaces {

	public PermitUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("Vəzifəyə düzəliş edin");
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

		JLabel newPermitLabel = new JLabel("Yeni vəzifəni daxil edin:", JLabel.RIGHT);
		upPanel.add(newPermitLabel);
		JTextField newPermitField = new JTextField(10);
		upPanel.add(newPermitField);

		JLabel searchLabel = new JLabel("Vəzifə axtarış:", JLabel.RIGHT);
		upPanel.add(searchLabel);
		JTextField searchField = new JTextField(10);
		upPanel.add(searchField);

		JList permitList = new JList();
		permitList.setListData(new PermitDal().getSearchPermit(searchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(permitList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz vəzifəni seçin"));

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
				permitList.setListData(new PermitDal().getSearchPermit(searchField.getText()).toArray());
				permitList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PermitContract contract = new PermitContract();
				PermitContract contract3 = (PermitContract) permitList.getSelectedValue();
				PermitContract contract4 = new PermitDal().permitId(contract3);

				contract.setId(contract4.getId());
				contract.setName(newPermitField.getText());
				

				new PermitDal().Update(contract);
				JOptionPane.showMessageDialog(null,"Seçdiyiniz vəzifənin məlumatları dəyişdirildi! ");
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
