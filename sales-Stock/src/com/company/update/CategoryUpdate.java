package com.company.update;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
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

import com.company.contract.CategoryContract;
import com.company.dal.CategoryDal;
import com.company.interfaces.FeInterfaces;

public class CategoryUpdate extends JDialog implements FeInterfaces {

	public CategoryUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("Kateqoriyaya düzəliş edin");
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

		JLabel upCategoryLabel = new JLabel("Ana kateqoriya adı:", JLabel.RIGHT);
		upPanel.add(upCategoryLabel);
		JComboBox upCategoryBox = new JComboBox(new CategoryDal().GetAllParentId().toArray());
		upPanel.add(upCategoryBox);

		JLabel newCategoryLabel = new JLabel("Yeni kateqoriya adı:", JLabel.RIGHT);
		upPanel.add(newCategoryLabel);
		JTextField newCategoryField = new JTextField(10);
		upPanel.add(newCategoryField);

		JLabel categorySearchLabel = new JLabel("Kateqoriya axtarış:", JLabel.RIGHT);
		upPanel.add(categorySearchLabel);
		JTextField categorySearchField = new JTextField(10);
		upPanel.add(categorySearchField);

		upPanel.setBorder(BorderFactory.createTitledBorder("Kateqoriyanı daxil edin"));

		JList categoryList = new JList();
		categoryList.setListData(new CategoryDal().getSearchCategory(categorySearchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(categoryList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz kateqoriyani seçin"));

		JPanel updatePanel = new JPanel(new GridLayout(1, 1));
		JButton updateButton = new JButton("Dəyişin");
		updatePanel.add(updateButton);

		categorySearchField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				categoryList.setListData(new CategoryDal().getSearchCategory(categorySearchField.getText()).toArray());
				categoryList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CategoryContract contract = new CategoryContract();
				CategoryContract mainContract = (CategoryContract) upCategoryBox.getSelectedItem();
				CategoryContract contract2 = (CategoryContract) categoryList.getSelectedValue();
				CategoryContract contract3=new CategoryDal().categoryId(contract2);

				contract.setId(contract3.getId());
				contract.setName(newCategoryField.getText());
				contract.setParentId(mainContract.getId());
				categoryList.setSelectedIndex(0);

				new CategoryDal().Update(contract);
				JOptionPane.showMessageDialog(null, "Seçdiyiniz kateqoriya "+ newCategoryField.getText() +" olaraq dəyişdirildi!");
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
