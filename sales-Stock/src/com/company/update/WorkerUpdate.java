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
import com.company.contract.CostumerContract;
import com.company.contract.WorkerContract;
import com.company.dal.CostumerDal;
import com.company.dal.WorkerDal;
import com.company.interfaces.FeInterfaces;

public class WorkerUpdate extends JDialog implements FeInterfaces {

	public WorkerUpdate() {
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
		JPanel upPanel = new JPanel(new GridLayout(3, 2));

		JLabel newWorkerLabel = new JLabel("Yeni işçinin adı və soyadı:", JLabel.RIGHT);
		upPanel.add(newWorkerLabel);
		JTextField newWorkerField = new JTextField(15);
		upPanel.add(newWorkerField);

		JLabel eMailLabel = new JLabel("Yeni işçinin Email ünvanı:", JLabel.RIGHT);
		upPanel.add(eMailLabel);
		JTextField eMailField = new JTextField(15);
		upPanel.add(eMailField);

		JLabel searchLabel = new JLabel("İşçi axtarış:", JLabel.RIGHT);
		upPanel.add(searchLabel);
		JTextField searchField = new JTextField(15);
		upPanel.add(searchField);

		upPanel.setBorder(BorderFactory.createTitledBorder("İşçinin məlumatlarını daxil edin"));

		JList workerList = new JList();
		workerList.setListData(new WorkerDal().getSearchWorker(searchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(workerList);
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
				workerList.setListData(new WorkerDal().getSearchWorker(searchField.getText()).toArray());
				workerList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WorkerContract contract = new WorkerContract();
				WorkerContract contract3 = (WorkerContract) workerList.getSelectedValue();
				WorkerContract contract4 = new WorkerDal().workerId(contract3);

				contract.setId(contract4.getId());
				contract.setNameSurname(newWorkerField.getText());
				contract.setEmail(eMailField.getText());

				new WorkerDal().Update(contract);
				JOptionPane.showMessageDialog(null, "Seçdiyiniz işçinin məlumatları dəyişdirildi! ");
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
