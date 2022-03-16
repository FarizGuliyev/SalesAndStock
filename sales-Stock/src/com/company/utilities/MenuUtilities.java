package com.company.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import com.company.contract.WorkerContract;
import com.company.dal.AccountsDal;
import com.company.frontend.CityFE;
import com.company.frontend.CategoryFE;
import com.company.frontend.LoginFE;
import com.company.frontend.CostumerFE;
import com.company.frontend.WorkerFE;
import com.company.frontend.AccountsFE;
import com.company.frontend.ProductFE;
import com.company.frontend.PermitFE;
import com.company.update.AccountsUpdate;
import com.company.update.CategoryUpdate;
import com.company.update.CityUpdate;
import com.company.update.CostumerUpdate;
import com.company.update.PermitUpdate;
import com.company.update.ProductUpdate;
import com.company.update.WorkerUpdate;

public class MenuUtilities {
	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		JMenu settingsMenu = new JMenu("Ayarlar");
		bar.add(settingsMenu);
		JMenuItem settingsItem = new JMenuItem("Çıxış");
		settingsMenu.add(settingsItem);

		JMenu productMenu = new JMenu("Məhsullar");
		bar.add(productMenu);
		JMenuItem productAddItem = new JMenuItem("Məhsul əlavə edin");
		productMenu.add(productAddItem);
		JMenuItem categoryAddItem = new JMenuItem("Kateqoriya əlavə edin");
		productMenu.add(categoryAddItem);
		productMenu.addSeparator();
		JMenuItem productUpdateItem = new JMenuItem("Məhsula düzəliş edin");
		productMenu.add(productUpdateItem);
		JMenuItem categoryUpdateItem = new JMenuItem("Kateqoriyaya düzəliş edin");
		productMenu.add(categoryUpdateItem);

		productUpdateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new ProductUpdate();

					}
				});

			}
		});

		categoryUpdateItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new CategoryUpdate();

					}
				});

			}
		});

		//Account HissÉ™si
		JMenu workerMenu = new JMenu("İşçilər");
		bar.add(workerMenu);
		JMenuItem workerAddItem = new JMenuItem("İşçi əlavə edin");
		workerMenu.add(workerAddItem);
		JMenuItem permitAddItem = new JMenuItem("Vəzifə əlavə edin");
		workerMenu.add(permitAddItem);
		JMenuItem accountAddItem = new JMenuItem("Hesab yaradın");
		workerMenu.add(accountAddItem);
		workerMenu.addSeparator();

		JMenuItem workerUpdateItem = new JMenuItem("İşçilərə düzəliş edin");
		workerMenu.add(workerUpdateItem);
		JMenuItem permitUpdateItem = new JMenuItem("Vəzifəyə düzəliş edin");
		workerMenu.add(permitUpdateItem);
		JMenuItem accountUpdateItem = new JMenuItem("Hesaba düzəliş edin");
		workerMenu.add(accountUpdateItem);

		workerUpdateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
				new WorkerUpdate();
					
				}
			});
				
			}
		});
		
		permitUpdateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
					new PermitUpdate();
						
					}
				});
				
			}
		});
		
		accountUpdateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
SwingUtilities.invokeLater(new Runnable() {
	
	@Override
	public void run() {
	new AccountsUpdate();
		
	}
});				
			}
		});
	

		/* Musteri Menyusu Duzeltme */
		JMenu costumerMenu = new JMenu("Müştərilər");
		bar.add(costumerMenu);
		JMenuItem costumerAddItem = new JMenuItem("Müştəri əlavə edin");
		costumerMenu.add(costumerAddItem);
		JMenuItem cityAddItem = new JMenuItem("Şəhər əlavə edin");
		costumerMenu.add(cityAddItem);
		costumerMenu.addSeparator();

		JMenuItem costumerUpdateItem = new JMenuItem("Müştərilərə düzəliş edin");
		costumerMenu.add(costumerUpdateItem);
		JMenuItem cityUpdateItem = new JMenuItem("Şəhərə düzəliş edin");
		costumerMenu.add(cityUpdateItem);
		WorkerContract contract = (WorkerContract) LoginFE.eMailBox.getSelectedItem();
		if (new AccountsDal().GetPermitId(contract.getId()).getPermitId() == 2) {
			workerMenu.hide();
		} else if (new AccountsDal().GetPermitId(contract.getId()).getPermitId() != 1) {
			workerMenu.hide();
			costumerMenu.hide();
			productMenu.hide();
		}


		costumerUpdateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
				new CostumerUpdate();
					
				}
			});
				
			}
		});
		
		
		cityUpdateItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new CityUpdate();

					}
				});
			}
		});
		
		// É™lavÉ™ metodlarÄ±
		cityAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CityFE();

			}
		});

		productAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new ProductFE();

					}
				});

			}
		});

		categoryAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CategoryFE();
			}
		});

		workerAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new WorkerFE();

					}
				});

			}
		});
		permitAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new PermitFE();

					}
				});
				;

			}
		});
		accountAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AccountsFE();

			}
		});

		costumerAddItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new CostumerFE();

					}
				});

			}
		});
		
		

		return bar;
	}
}
