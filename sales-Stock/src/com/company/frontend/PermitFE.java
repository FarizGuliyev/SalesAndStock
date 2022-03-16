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

import com.company.contract.PermitContract;
import com.company.dal.PermitDal;
import com.company.interfaces.FeInterfaces;

public class PermitFE extends JDialog implements FeInterfaces {

	public PermitFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Vəzifənin təyin olunması "));
		add(panel);
		setTitle("Vəzifə verin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JLabel nameLabel = new JLabel("Vəzifənin(İcazənin) növü:", JLabel.RIGHT);
		panel.add(nameLabel);
		JTextField nameField = new JTextField(10);
		panel.add(nameField);

		JButton saveButton = new JButton("Yadda saxla");
		panel.add(saveButton);
		JButton cancelButton = new JButton("İmtina et");
		panel.add(cancelButton);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			PermitContract contract=new PermitContract();
			contract.setName(nameField.getText());
			new PermitDal().Insert(contract);
			JOptionPane.showMessageDialog(null,contract.getName()+" adlı icazə verildi!") ;
				
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
