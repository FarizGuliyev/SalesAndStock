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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.company.contract.AccountsContract;
import com.company.contract.PermitContract;
import com.company.contract.WorkerContract;
import com.company.dal.AccountsDal;
import com.company.dal.PermitDal;
import com.company.dal.WorkerDal;
import com.company.interfaces.FeInterfaces;

public class AccountsUpdate extends JDialog implements FeInterfaces {

	public AccountsUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("İşçiyə düzəliş edin");
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel upPanel = new JPanel(new GridLayout(4, 2));

		JLabel permitLabel = new JLabel("İşçinin vəzifəsi:", JLabel.RIGHT);
		upPanel.add(permitLabel);
		JComboBox permitBox = new JComboBox(new PermitDal().GetAll().toArray());
		upPanel.add(permitBox);

		JLabel passwordLabel = new JLabel("Yeni şifrə:", JLabel.RIGHT);
		upPanel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(10);
		upPanel.add(passwordField);

		JLabel password2Label = new JLabel("Şifrə təkrar:", JLabel.RIGHT);
		upPanel.add(password2Label);
		JPasswordField password2Field = new JPasswordField(10);
		upPanel.add(password2Field);

		JLabel searchLabel = new JLabel("İşçi axtarış:", JLabel.RIGHT);
		upPanel.add(searchLabel);
		JTextField searchField = new JTextField(10);
		upPanel.add(searchField);

		upPanel.setBorder(BorderFactory.createTitledBorder("İşçinin məlumatlarını daxil edin"));

		JList accountList = new JList();
		accountList.setListData(new AccountsDal().getSearchWorker(searchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(accountList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz işçini seçin"));

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
				accountList.setListData(new AccountsDal().getSearchWorker(searchField.getText()).toArray());
				accountList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AccountsContract contract = new AccountsContract();
				PermitContract permitContract = (PermitContract) permitBox.getSelectedItem();
				WorkerContract contract3 = (WorkerContract) accountList.getSelectedValue();
				WorkerContract contract4 = new AccountsDal().workerId(contract3);
				AccountsContract accountsContract = new AccountsDal().accountId(contract4);

				if (passwordField.getText().equals(password2Field.getText())) {
					contract.setId(accountsContract.getId());
					contract.setPassword(passwordField.getText());
					contract.setPermitId(permitContract.getId());
					contract.setWorkerId(contract4.getId());

					new AccountsDal().Update(contract);
					JOptionPane.showMessageDialog(null, "Seçdiyiniz işçinin hesab məlumatları dəyişdirildi! ");
				} else {
					JOptionPane.showMessageDialog(null, "Daxil etdiyiniz şifrələr fərqlidir! Yenidən cəhd edin");
				}
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
