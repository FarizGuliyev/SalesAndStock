package com.company.update;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

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

import com.company.contract.ProductContract;
import com.company.dal.CategoryDal;
import com.company.dal.ProductDal;
import com.company.interfaces.FeInterfaces;
import com.toedter.calendar.JDateChooser;

public class ProductUpdate extends JDialog implements FeInterfaces {

	public ProductUpdate() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);

		setTitle("Məhsula düzəliş edin");
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

		JLabel newproductLabel = new JLabel("Yeni məhsul adı:", JLabel.RIGHT);
		upPanel.add(newproductLabel);
		JTextField newproductField = new JTextField(15);
		upPanel.add(newproductField);

		JLabel priceLabel = new JLabel("Yeni məhsulun qiyməti:", JLabel.RIGHT);
		upPanel.add(priceLabel);
		JTextField priceField = new JTextField(15);
		upPanel.add(priceField);

		JLabel dateLabel = new JLabel("Yeni məhsulun tarixi:", JLabel.RIGHT);
		upPanel.add(dateLabel);
		JDateChooser date = new JDateChooser();
		upPanel.add(date);

	

		upPanel.setBorder(BorderFactory.createTitledBorder("Məhsulun məlumatlarını daxil edin"));
		
		
		
		JLabel productSearchLabel = new JLabel("Məhsul axtarış:", JLabel.RIGHT);
		upPanel.add(productSearchLabel);
		JTextField productSearchField = new JTextField(15);
		upPanel.add(productSearchField);

		JList productList = new JList();
		productList.setListData(new ProductDal().getSearchProduct(productSearchField.getText()).toArray());
		JScrollPane pane = new JScrollPane(productList);
		pane.setBorder(BorderFactory.createTitledBorder("Dəyişmək istədiyiniz məhsulu seçin"));
	
		

		JPanel updatePanel = new JPanel(new GridLayout(1, 1));
		JButton updateButton = new JButton("Dəyişin");
		updatePanel.add(updateButton);
		
		
		productSearchField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				productList.setListData(new ProductDal().getSearchProduct(productSearchField.getText()).toArray());
				productList.setSelectedIndex(0);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});
		
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductContract contract=new ProductContract();
				ProductContract contract2=(ProductContract) productList.getSelectedValue();
				ProductContract contract3=new ProductDal().productId(contract2);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String formatDate = format.format(date.getDate());
				
				contract.setId(contract3.getId());
				contract.setName(newproductField.getText());
				contract.setCategoryId(contract3.getCategoryId());
				contract.setPrice(Float.parseFloat(priceField.getText()));
				contract.setDate(formatDate);
				
				new ProductDal().Update(contract);
				JOptionPane.showMessageDialog(null, "Seçdiyiniz məhsul "+newproductField.getText()+" olaraq dəyişdirildi!");
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
