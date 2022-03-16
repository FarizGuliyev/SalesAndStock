package com.company.frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.company.complex.SalesContractComplex;
import com.company.complex.StockContractComplex;
import com.company.contract.CostumerContract;
import com.company.contract.WorkerContract;
import com.company.contract.SalesContract;
import com.company.contract.StockContract;
import com.company.contract.ProductContract;
import com.company.dal.CostumerDal;
import com.company.dal.SalesDal;
import com.company.dal.StockDal;
import com.company.dal.ProductDal;
import com.company.interfaces.FeInterfaces;
import com.company.utilities.MenuUtilities;
import com.toedter.calendar.JDateChooser;

public class MainWindowFE extends JFrame implements FeInterfaces {

	public MainWindowFE() {
		initWindow();
	}

	@Override
	public void initWindow() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Ticarət sistemi");
		setSize(600, 400);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenuUtilities.initBar();
		return bar;

	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();

		JPanel stockPanel = new JPanel(new BorderLayout());
		JPanel salesPanel = new JPanel(new BorderLayout());

		/* Stok yaratma hissesi */
		JPanel stockLeftPanel = new JPanel(new BorderLayout());
		JPanel stockLeftUpPanel = new JPanel(new GridLayout(5, 2));
		JPanel stockLeftDownPanel = new JPanel();

		stockLeftPanel.setBorder(BorderFactory.createTitledBorder("Stoka yeni məhsul əlavə edin:"));

		Object[] stockColumns = { "Id", "İşçinin adı", "Məhsulun növü", "Məhsulun miqdarı(ədəd)", "Tarix"  };
		DefaultTableModel model = new DefaultTableModel(stockColumns, 0);
		JTable table = new JTable(model);
		JScrollPane stockTablePane = new JScrollPane(table);
		stockTablePane.setBorder(BorderFactory.createTitledBorder("Stokdakı məhsullar:"));

		for (StockContractComplex contract : new StockDal().getTotalStock()) {
			model.addRow(contract.getData());
		}

		JLabel stockProductLabel = new JLabel("Məhsulun adı:", JLabel.RIGHT);
		stockLeftUpPanel.add(stockProductLabel);
		JComboBox stockProductBox = new JComboBox(new ProductDal().GetAll().toArray());
		stockLeftUpPanel.add(stockProductBox);
		JLabel stockAmountLabel = new JLabel("Məhsulun miqdarı(ədəd):", JLabel.RIGHT);
		stockLeftUpPanel.add(stockAmountLabel);
		JTextField stockAmountField = new JTextField(10);
		stockLeftUpPanel.add(stockAmountField);
		JLabel stockDateLabel = new JLabel("Stoka əlavə olunma tarixi:", JLabel.RIGHT);
		stockLeftUpPanel.add(stockDateLabel);
		JDateChooser stockDate = new JDateChooser();
		stockLeftUpPanel.add(stockDate);

		JButton stockAddButton = new JButton("Əlavə et");
		stockLeftUpPanel.add(stockAddButton);
		JButton stockUpdateButton = new JButton("Yenilə");
		stockLeftUpPanel.add(stockUpdateButton);
		JButton StockTotalButton = new JButton("Stok tarixçəsi");
		stockLeftUpPanel.add(StockTotalButton);

		StockTotalButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelcount = (DefaultTableModel) table.getModel();
				while (modelcount.getRowCount() > 0) {
					modelcount.removeRow(0);
				}

				for (StockContractComplex contractComplex : new StockDal().getAllStock()) {
					model.addRow(contractComplex.getData());
				}
			}
		});
		stockUpdateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelcount = (DefaultTableModel) table.getModel();
				while (modelcount.getRowCount() > 0) {
					modelcount.removeRow(0);
				}

				for (StockContractComplex contractComplex : new StockDal().getTotalStock()) {
					model.addRow(contractComplex.getData());
				}
			}
		});

		stockAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StockContract contract = new StockContract();
				ProductContract pContract = (ProductContract) stockProductBox.getSelectedItem();
				WorkerContract wContract = (WorkerContract) LoginFE.eMailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stockDate.getDate());

				contract.setWorkerId(wContract.getId());
				contract.setProductId(pContract.getId());
				contract.setDate(date);
				contract.setAmount(Integer.parseInt(stockAmountField.getText()));
				new StockDal().Insert(contract);
				JOptionPane.showMessageDialog(null, pContract.getName() + " adlı məhsul stoka əlavə olundu");

				DefaultTableModel modelcount = (DefaultTableModel) table.getModel();
				while (modelcount.getRowCount() > 0) {
					modelcount.removeRow(0);
				}

				for (StockContractComplex contractComplex : new StockDal().getTotalStock()) {
					model.addRow(contractComplex.getData());

				}

			}
		});

		/* Satış yaratma hissəsi */
		JPanel salesRightPanel = new JPanel(new BorderLayout());
		JPanel salesRightUpPanel = new JPanel(new GridLayout(6, 2));
		JPanel salesRightDownPanel = new JPanel();

		salesRightPanel.setBorder(BorderFactory.createTitledBorder("Satılan məhsulu daxil edin:"));

		Object[] salesColumns = { "Id", "İşçinin adı", "Müştərinin adı", "Məhsulun növü", "Məhsulun miqdarı(ədəd)",
				"Tarix" };
		DefaultTableModel salesModel = new DefaultTableModel(salesColumns, 0);
		JTable salesTable = new JTable(salesModel);
		JScrollPane salesTablePane = new JScrollPane(salesTable);
		salesTablePane.setBorder(BorderFactory.createTitledBorder("Satılan məhsullar:"));
		
		for (SalesContractComplex contractComplex : new SalesDal().getTotalSales()) {
			salesModel.addRow(contractComplex.getData());
		}

		JLabel costumerLabel = new JLabel("Müştərinin adı:", JLabel.RIGHT);
		salesRightUpPanel.add(costumerLabel);
		JComboBox costumerBox = new JComboBox(new CostumerDal().GetAll().toArray());
		salesRightUpPanel.add(costumerBox);
		JLabel salesProductLabel = new JLabel("Məhsulun adı:", JLabel.RIGHT);
		salesRightUpPanel.add(salesProductLabel);
		JComboBox salesProductBox = new JComboBox(new ProductDal().GetAll().toArray());
		salesRightUpPanel.add(salesProductBox);
		JLabel salesAmountLabel = new JLabel("Məhsulun miqdarı(ədəd):", JLabel.RIGHT);
		salesRightUpPanel.add(salesAmountLabel);
		JTextField salesAmountField = new JTextField(10);
		salesRightUpPanel.add(salesAmountField);
		JLabel salesDateLabel = new JLabel("Məhsulun satılma tarixi:", JLabel.RIGHT);
		salesRightUpPanel.add(salesDateLabel);
		JDateChooser salesDate = new JDateChooser();
		salesRightUpPanel.add(salesDate);

		JButton salesAddButton = new JButton("Əlavə et");
		salesRightUpPanel.add(salesAddButton);
		JButton salesUpdateButton = new JButton("Yenilə");
		salesRightUpPanel.add(salesUpdateButton);
		JButton salesTotalButton=new JButton("Satış tarixçəsi");
		salesRightUpPanel.add(salesTotalButton);
		salesTotalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel modelcount = (DefaultTableModel) salesTable.getModel();
				while (modelcount.getRowCount() > 0) {
					salesModel.removeRow(0);
				}

				for (SalesContractComplex contractComplex : new SalesDal().getAllSales()) {
					salesModel.addRow(contractComplex.getData());
				}
			}
		});
		salesUpdateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelcount = (DefaultTableModel) salesTable.getModel();
				while (modelcount.getRowCount() > 0) {
					salesModel.removeRow(0);
				}

				for (SalesContractComplex contractComplex : new SalesDal().getTotalSales()) {
					salesModel.addRow(contractComplex.getData());
				}
				
			}
		});

		salesAddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SalesContract contract = new SalesContract();
				CostumerContract contract2 = (CostumerContract) costumerBox.getSelectedItem();
				ProductContract contract3 = (ProductContract) salesProductBox.getSelectedItem();
				WorkerContract contract4 = (WorkerContract) LoginFE.eMailBox.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(salesDate.getDate());

				contract.setCostumerId(contract2.getId());
				contract.setWorkerId(contract4.getId());
				contract.setProductId(contract3.getId());
				contract.setAmount(Integer.parseInt(salesAmountField.getText()));
				contract.setDate(date);

				new SalesDal().Insert(contract);
				
				StockContract contract5=new StockContract();
				contract5.setWorkerId(contract4.getId());
				contract5.setProductId(contract3.getId());
				contract5.setAmount(-Integer.parseInt(salesAmountField.getText()));
				contract5.setDate(date);
				
				new StockDal().Insert(contract5);
			
				JOptionPane.showMessageDialog(null, "Satış olundu!");
			
				DefaultTableModel modelcount = (DefaultTableModel) salesTable.getModel();
				while (modelcount.getRowCount() > 0) {
					salesModel.removeRow(0);
			}

			for (SalesContractComplex contractComplex : new SalesDal().getTotalSales()) {
				salesModel.addRow(contractComplex.getData());
			}
		}
		});

		stockLeftPanel.add(stockLeftUpPanel, BorderLayout.NORTH);
		stockLeftPanel.add(stockLeftDownPanel, BorderLayout.SOUTH);
		salesRightPanel.add(salesRightDownPanel, BorderLayout.SOUTH);
		salesRightPanel.add(salesRightUpPanel, BorderLayout.NORTH);

		stockPanel.add(stockLeftPanel, BorderLayout.WEST);
		stockPanel.add(stockTablePane, BorderLayout.CENTER);
		salesPanel.add(salesRightPanel, BorderLayout.WEST);
		salesPanel.add(salesTablePane, BorderLayout.CENTER);

		ImageIcon icon = new ImageIcon("icons/StockIcon.png");
		ImageIcon icon2 = new ImageIcon("icons/SalesIcon.png");
		pane.addTab("Stoklar ", icon, stockPanel);
		pane.addTab("Satışlar ", icon2, salesPanel);
		return pane;
	}

}
