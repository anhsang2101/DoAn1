package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.ConnectDB;
import Model.Account;
import Model.Customer;
import Model.Product;
import Model.Receipt;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Calendar;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class MainFrame extends JFrame {

	
	
	
	private JPanel contentPane;
	JTabbedPane tabbedPane;
	private JTable tableProduct; 
	private ConnectDB c = new ConnectDB();
	private JTextField tfSearch;
	private JTextField tfName;
	private JTextField tfImportPrice;
	private JTextField tfExportPrice;
	private JTextField tfBrand;
	private JTextField tfCpu;
	private JTextField tfRam;
	private JTextField tfRom;
	private JTextField tfDisplay;
	private JTextField tfBatery;
	private JTextField tfAmount;
	
	private JLabel tfPrice;
	private JLabel tfProductsName;
	private JLabel tfTotalPrice;
	private JComboBox cbCustomer;
	private JLabel tfEmployeeNameSell;
	private double doubleTotalPrice=0;
	private JComboBox cbPhone;
	JComboBox cbRole;
	JLabel lbEditCustomer;
	
	JLabel lbInsertProduct;
	JLabel lbEditProduct;
	JLabel lbDeleteProduct;
	
	
	
	private JTable tableProductSell;
	private JTable tableOrder;
	private JTextField tfAmountSell;
	
	
	//icon
	ImageIcon icInsert1 = new ImageIcon("Insert1.png");
	ImageIcon icInsert2 = new ImageIcon("Insert2.png");
	ImageIcon icEdit1 = new ImageIcon("Edit1.png");
	ImageIcon icEdit2 = new ImageIcon("Edit2.png");
	ImageIcon icDelete1 = new ImageIcon("Delete1.png");
	ImageIcon icDelete2 = new ImageIcon("Delete2.png");
	ImageIcon icRefresh1 = new ImageIcon("Refresh1.png");
	ImageIcon icRefresh2 = new ImageIcon("Refresh2.png");
	ImageIcon icPrint1 = new ImageIcon("Print1.png");
	ImageIcon icPrint2 = new ImageIcon("Print2.png");
	private JTable tableCustomer;
	private JTextField tfCustomerName;
	private JTextField tfCustomerPhone;
	private JTextField tfCustomerAddress;
	Border border = BorderFactory.createLineBorder(Color.BLACK);
	private JTextField tfUserName;
	private JPasswordField passwordField;
	private JPasswordField rePasswordField;
	private JTextField tfEmployeeName;
	private JTable tableEmployee;
	private JTextField tfReceiptIdSell;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame("ad");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * Create the frame.
	 */
	public MainFrame(String employeeName) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 25));
		tabbedPane.setBounds(0, 0, 1184, 523);
		contentPane.add(tabbedPane);
		
		ImageIcon icHome = new ImageIcon("Home.png");
		JScrollPane tabHome = new JScrollPane();
		tabbedPane.addTab("Home", icHome, tabHome, null);
		
		ImageIcon icProduct = new ImageIcon("Product.png");
		JPanel tabProduct = new JPanel();
		tabbedPane.addTab("Product", icProduct, tabProduct, null);
		tabProduct.setLayout(null);
		
		JScrollPane scrollPaneProduct = new JScrollPane();
		scrollPaneProduct.setBounds(10, 44, 746, 307);
		tabProduct.add(scrollPaneProduct);
		
		tableProduct = new JTable();
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Brand", "CPU", "RAM", "ROM", "Display", "Battery", "Amount", "Import Price", "Export Price"
			}
		));
		scrollPaneProduct.setViewportView(tableProduct);
		tableProduct.addMouseListener(new MouseAdapter() {
			
			public void setTextField(Product p) {
				tfName.setText(p.getName());
				tfBrand.setText(p.getBrand());
				tfCpu.setText(p.getCpu());
				tfRam.setText(p.getRam());
				tfRom.setText(p.getRom());
				tfDisplay.setText(p.getDisplay());
				tfBatery.setText(p.getPin());
				tfAmount.setText(String.valueOf(p.getAmount()));
				tfImportPrice.setText(String.valueOf(p.getImportPrice()));
				tfExportPrice.setText(String.valueOf(p.getExportPrice()));
			
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableProduct.rowAtPoint(e.getPoint());
				String name = tableProduct.getValueAt(row, 0).toString();
				Product p = c.getProductByName(name);
				setTextField(p);
				lbEditProduct.setVisible(true);
				
			}
		});
		fillDataTableProduct();
		
		JLabel lbSearch = new JLabel("Search");
		lbSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbSearch.setBounds(20, 11, 96, 22);
		tabProduct.add(lbSearch);
		ImageIcon icSearch = new ImageIcon("Search.png");
		lbSearch.setIcon(icSearch);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					c.searchProductByName(tableProduct, tfSearch);
				}
			}
		});
		tfSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfSearch.setBounds(115, 11, 256, 22);
		tabProduct.add(tfSearch);
		tfSearch.setColumns(10);
		
		JLabel lbName = new JLabel("Name");
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbName.setBounds(780, 177, 84, 31);
		tabProduct.add(lbName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfName.setBounds(766, 219, 282, 31);
		tabProduct.add(tfName);
		tfName.setColumns(10);
		
		JLabel lbImportPrice = new JLabel("Import price");
		lbImportPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbImportPrice.setBounds(780, 264, 115, 31);
		tabProduct.add(lbImportPrice);
		
		tfImportPrice = new JTextField();
		tfImportPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfImportPrice.setColumns(10);
		tfImportPrice.setBounds(766, 306, 96, 31);
		tabProduct.add(tfImportPrice);
		
		JLabel lbExportPrice = new JLabel("Export Price");
		lbExportPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbExportPrice.setBounds(927, 264, 121, 31);
		tabProduct.add(lbExportPrice);
		
		tfExportPrice = new JTextField();
		tfExportPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfExportPrice.setColumns(10);
		tfExportPrice.setBounds(915, 306, 96, 31);
		tabProduct.add(tfExportPrice);
		
		JLabel lbUSD1 = new JLabel("USD");
		lbUSD1.setBounds(865, 318, 30, 14);
		tabProduct.add(lbUSD1);
		
		JLabel lbUSD2 = new JLabel("USD");
		lbUSD2.setBounds(1012, 318, 36, 14);
		tabProduct.add(lbUSD2);
		
		JLabel lbBrand = new JLabel("Brand");
		lbBrand.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbBrand.setBounds(20, 362, 65, 22);
		tabProduct.add(lbBrand);
		
		JLabel lbCpu = new JLabel("CPU");
		lbCpu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbCpu.setBounds(292, 362, 65, 22);
		tabProduct.add(lbCpu);
		
		JLabel lblRam = new JLabel("RAM");
		lblRam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRam.setBounds(542, 362, 65, 22);
		tabProduct.add(lblRam);
		
		JLabel lblRom = new JLabel("ROM");
		lblRom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRom.setBounds(20, 437, 65, 22);
		tabProduct.add(lblRom);
		
		JLabel lblDisplay = new JLabel("Display");
		lblDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDisplay.setBounds(292, 437, 65, 22);
		tabProduct.add(lblDisplay);
		
		JLabel lblBatery = new JLabel("Batery");
		lblBatery.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblBatery.setBounds(542, 437, 65, 22);
		tabProduct.add(lblBatery);
		
		tfBrand = new JTextField();
		tfBrand.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfBrand.setBounds(10, 395, 209, 31);
		tabProduct.add(tfBrand);
		tfBrand.setColumns(10);
		
		tfCpu = new JTextField();
		tfCpu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfCpu.setColumns(10);
		tfCpu.setBounds(276, 395, 209, 31);
		tabProduct.add(tfCpu);
		
		tfRam = new JTextField();
		tfRam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfRam.setColumns(10);
		tfRam.setBounds(532, 395, 209, 31);
		tabProduct.add(tfRam);
		
		tfRom = new JTextField();
		tfRom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfRom.setColumns(10);
		tfRom.setBounds(10, 470, 209, 31);
		tabProduct.add(tfRom);
		
		tfDisplay = new JTextField();
		tfDisplay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfDisplay.setColumns(10);
		tfDisplay.setBounds(276, 470, 209, 31);
		tabProduct.add(tfDisplay);
		
		tfBatery = new JTextField();
		tfBatery.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfBatery.setColumns(10);
		tfBatery.setBounds(532, 470, 209, 31);
		tabProduct.add(tfBatery);
		
		tfAmount = new JTextField();
		tfAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfAmount.setColumns(10);
		tfAmount.setBounds(766, 395, 209, 31);
		tabProduct.add(tfAmount);
		
		JLabel lbAmount = new JLabel("Amount");
		lbAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbAmount.setBounds(776, 362, 119, 22);
		tabProduct.add(lbAmount);
		
		lbInsertProduct = new JLabel("");
		lbInsertProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbInsertProduct.setIcon(icInsert2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbInsertProduct.setIcon(icInsert1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if(validateFormProduct()) {
						Product p = putToModelProduct();
						if(c.CheckProductExist(p)>0)
							JOptionPane.showMessageDialog(null, "Product's name is existed");
						
						else if(c.InsertProduct(p)>0) {
							JOptionPane.showMessageDialog(null, "Insert successfully");
							fillDataTableProduct();
							fillDataTableProductReceipt();
						}
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Please complete required field");	
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		lbInsertProduct.setBounds(771, 456, 65, 45);
		tabProduct.add(lbInsertProduct);
		lbInsertProduct.setIcon(icInsert1);
		
		lbEditProduct = new JLabel("");
		lbEditProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lbEditProduct.setIcon(icEdit2);
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				
				lbEditProduct.setIcon(icEdit1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				try {
					if(validateFormProduct()) {
						Product p = putToModelProduct();
						if(c.checkUpdateProduct(p.getName())==true) {
							if(c.UpdateProduct(p)>0) {
								JOptionPane.showMessageDialog(null, "Update successfully");
								fillDataTableProduct();
								fillDataTableProductReceipt();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "You mustn't change the name, \n"
									+ "You should insert a new one");
							newTextFieldProduct();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Try again");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
				
			}
		});
		lbEditProduct.setBounds(846, 456, 65, 45);
		tabProduct.add(lbEditProduct);
		lbEditProduct.setIcon(icEdit1);
		lbEditProduct.setVisible(false);
		
		lbDeleteProduct = new JLabel("");
		lbDeleteProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lbDeleteProduct.setIcon(icDelete2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				lbDeleteProduct.setIcon(icDelete1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if(validateFormProduct()) {
						int row = tableProduct.getSelectedRow();
						Product p = new Product();
						p.setName(tableProduct.getValueAt(row, 0).toString());
						
						if(c.DeleteProduct(p)>0) {
							JOptionPane.showMessageDialog(null, "Delete successfully");
							fillDataTableProduct();
							fillDataTableProductReceipt();
							newTextFieldProduct();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Choose again");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		lbDeleteProduct.setBounds(924, 456, 65, 45);
		tabProduct.add(lbDeleteProduct);
		lbDeleteProduct.setIcon(icDelete1);
		
		JLabel lbRefreshProduct = new JLabel("");
		lbRefreshProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {				
				lbRefreshProduct.setIcon(icRefresh2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbRefreshProduct.setIcon(icRefresh1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				newTextFieldProduct();
				fillDataTableProduct();
				fillDataTableProductReceipt();
				lbEditProduct.setVisible(false);
			}
		});
		lbRefreshProduct.setBounds(999, 462, 49, 39);
		tabProduct.add(lbRefreshProduct);
		lbRefreshProduct.setIcon(icRefresh1);
		
		//tab ban hang
		
		JPanel tabSell = new JPanel();
		ImageIcon icReceipt = new ImageIcon("Receipt.png");
		tabbedPane.addTab("Sell", icReceipt, tabSell, null);
		tabSell.setLayout(null);
		
		JScrollPane scrollPaneProductSell = new JScrollPane();
		scrollPaneProductSell.setBounds(10, 42, 746, 248);
		tabSell.add(scrollPaneProductSell);
		TitledBorder titleBorderProduct = BorderFactory.createTitledBorder(border, "Product");
		scrollPaneProductSell.setBorder(titleBorderProduct);
		
		tableProductSell = new JTable();
		tableProductSell.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Name", "Brand", "CPU", "RAM", "ROM", "Display", "Battery", "Amount", "Import Price", "Export Price"
				}
			));
		scrollPaneProductSell.setViewportView(tableProductSell);
		tableProductSell.addMouseListener(new MouseAdapter() {
			
			public void setTextField(Product p) {
				tfProductsName.setText(p.getName());
				tfPrice.setText(String.valueOf(p.getExportPrice()));
			
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableProductSell.rowAtPoint(e.getPoint());
				String name = tableProductSell.getValueAt(row, 0).toString();
				Product p = c.getProductByName(name);
				setTextField(p);
			}
		});
		fillDataTableProductReceipt();
		
		JScrollPane scrollPaneOrder = new JScrollPane();
		scrollPaneOrder.setBounds(10, 327, 746, 180);
		tabSell.add(scrollPaneOrder);
		TitledBorder titleBorderOrder = BorderFactory.createTitledBorder(border, "Order");
		scrollPaneOrder.setBorder(titleBorderOrder);
		
		tableOrder = new JTable();
		tableOrder.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product's name", "Price", "Amount", "Total money", "Employee's name", "Customer's name"
			}
		));
		scrollPaneOrder.setViewportView(tableOrder);
//		tableReceipt.addMouseListener(new MouseAdapter() {
//			
//			public void setTextField(Product p) {
//				tfProductsName.setText(p.getName());
//				tfPrice.setText(String.valueOf(p.getExportPrice()));
//			
//			}
//			
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int row = tableReceipt.rowAtPoint(e.getPoint());
//				String name = tableReceipt.getValueAt(row, 0).toString();
//				Product p = c.getProductByName(name);
//				setTextField(p);
//			}
//		});
		
		JLabel lbCustomerSell = new JLabel("Customer");
		lbCustomerSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCustomerSell.setBounds(766, 26, 77, 25);
		tabSell.add(lbCustomerSell);
		
		cbCustomer = new JComboBox();
		cbCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				fillComboBoxCustomerPhone();
			}
		});
		cbCustomer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbCustomer.setBounds(853, 17, 195, 34);
		tabSell.add(cbCustomer);
		fillComboBoxCustomerName();
		
		JLabel lbEmployeeSell = new JLabel("Employee");
		lbEmployeeSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbEmployeeSell.setBounds(766, 120, 77, 25);
		tabSell.add(lbEmployeeSell);
		
		tfEmployeeNameSell = new JLabel("");
		tfEmployeeNameSell.setBorder(new LineBorder(new Color(0, 0, 0)));
		tfEmployeeNameSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfEmployeeNameSell.setBounds(853, 120, 195, 25);
		tfEmployeeNameSell.setText(" "+employeeName);
		tabSell.add(tfEmployeeNameSell);
		
		JLabel lbPrice = new JLabel("Price");
		lbPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbPrice.setBounds(766, 227, 77, 25);
		tabSell.add(lbPrice);
		
		tfPrice = new JLabel("");
		tfPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfPrice.setBorder(new LineBorder(new Color(0, 0, 0)));
		tfPrice.setBounds(853, 227, 195, 25);
		tabSell.add(tfPrice);
		
		tfProductsName = new JLabel("");
		tfProductsName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfProductsName.setBorder(new LineBorder(new Color(0, 0, 0)));
		tfProductsName.setBounds(853, 173, 195, 25);
		tabSell.add(tfProductsName);
		
		JLabel lbProductsName = new JLabel("Name");
		lbProductsName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbProductsName.setBounds(766, 173, 77, 25);
		tabSell.add(lbProductsName);
		
		JLabel lbAmountSell = new JLabel("Amount");
		lbAmountSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbAmountSell.setBounds(766, 280, 77, 25);
		tabSell.add(lbAmountSell);
		
		tfAmountSell = new JTextField();
		tfAmountSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfAmountSell.setText("1");
		tfAmountSell.setBounds(853, 282, 195, 25);
		tabSell.add(tfAmountSell);
		tfAmountSell.setColumns(10);
		
		JLabel lbTotalPrice = new JLabel("Total price:");
		lbTotalPrice.setForeground(Color.RED);
		lbTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTotalPrice.setBounds(766, 373, 101, 25);
		tabSell.add(lbTotalPrice);
		
		tfTotalPrice = new JLabel("0");
		tfTotalPrice.setForeground(Color.RED);
		tfTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfTotalPrice.setBounds(875, 373, 114, 25);
		tabSell.add(tfTotalPrice);
		
		JLabel lbInsertSell = new JLabel("");
		lbInsertSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbInsertSell.setIcon(icInsert2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbInsertSell.setIcon(icInsert1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				cbCustomer.setEnabled(false);
				cbPhone.setEnabled(false);
				insertOrder();
				for(int row=0; row<tableOrder.getRowCount();row++) {
					String stTotalMoney = tableOrder.getValueAt(row, 3).toString();
					double doubleTotalMoney = Double.parseDouble(stTotalMoney);
					doubleTotalPrice += doubleTotalMoney;
				}
				
				tfTotalPrice.setText(String.valueOf(doubleTotalPrice));
				doubleTotalPrice=0;
			}
		});
		lbInsertSell.setBounds(782, 445, 65, 45);
		tabSell.add(lbInsertSell);
		lbInsertSell.setIcon(icInsert1);
		
		JLabel lbDeleteSell = new JLabel("");
		lbDeleteSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {				
				lbDeleteSell.setIcon(icDelete2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbDeleteSell.setIcon(icDelete1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int row = tableOrder.getSelectedRow();
				
				System.out.println("Delete row" + row);
				
				String totalPrice = tfTotalPrice.getText();
				double doubleTotalPrice = Double.parseDouble(totalPrice);
				String stTotalMoney = tableOrder.getValueAt(row, 3).toString();
				double doubleTotalMoney = Double.parseDouble(stTotalMoney);
				tfTotalPrice.setText(String.valueOf(doubleTotalPrice-doubleTotalMoney));
				
				if(tableOrder.getModel().getRowCount()==1) {
					cbCustomer.setEnabled(true);
					cbPhone.setEnabled(true);
				}
					
				((DefaultTableModel) tableOrder.getModel()).removeRow(row);
				
				
			}
		});
		lbDeleteSell.setBounds(869, 445, 65, 45);
		tabSell.add(lbDeleteSell);
		lbDeleteSell.setIcon(icDelete1);
		
		JLabel lbPrintSell = new JLabel("");
		lbPrintSell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbPrintSell.setIcon(icPrint2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbPrintSell.setIcon(icPrint1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				try {
					if(!tfReceiptIdSell.getText().isEmpty()) {
						Receipt r = new Receipt();
						r.setIdReceipt(tfReceiptIdSell.getText());
						r.setCustomerName(cbCustomer.getSelectedItem().toString());
						r.setEmployeeName(employeeName);
						r.setReceiptPrice(Double.parseDouble(tfTotalPrice.getText()));
						r.setBuyDate(utilToSqlDate(new java.util.Date()));
						
						if(c.UpdateNumOfTimeBuy(cbPhone.getSelectedItem().toString())>0 && c.InsertReceipt(r)>0) {
							fillDataTableCustomer();
							JOptionPane.showMessageDialog(null, "Payment success");
							cbCustomer.setEnabled(true);
							cbPhone.setEnabled(true);
							
							
							// xoa bang
							DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
							model.setRowCount(0);
							
							// in ra file
							FileOutputStream fos = new FileOutputStream(r.getIdReceipt()+".txt");
							fos.write(r.print().getBytes());
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "You haven't type receipt id yet");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		lbPrintSell.setBounds(959, 445, 65, 45);
		tabSell.add(lbPrintSell);
		lbPrintSell.setIcon(icPrint1);
		
		JLabel lbUSD3 = new JLabel("USD");
		lbUSD3.setForeground(Color.RED);
		lbUSD3.setBounds(999, 380, 46, 14);
		tabSell.add(lbUSD3);
		
		JLabel lbPhoneSell = new JLabel("Phone");
		lbPhoneSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbPhoneSell.setBounds(766, 73, 77, 25);
		tabSell.add(lbPhoneSell);
		
		cbPhone = new JComboBox();
		cbPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbPhone.setBounds(853, 64, 195, 34);
		tabSell.add(cbPhone);
		
		JLabel lbReceiptIdSell = new JLabel("Receipt ID");
		lbReceiptIdSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbReceiptIdSell.setBounds(766, 327, 77, 25);
		tabSell.add(lbReceiptIdSell);
		
		tfReceiptIdSell = new JTextField();
		tfReceiptIdSell.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfReceiptIdSell.setColumns(10);
		tfReceiptIdSell.setBounds(853, 329, 195, 25);
		tabSell.add(tfReceiptIdSell);
		fillComboBoxCustomerPhone();
		
		JPanel tabReceipt = new JPanel();
		tabbedPane.addTab("Receipt", null, tabReceipt, null);
		
		JPanel tabCustomer = new JPanel();
		tabbedPane.addTab("Customer", null, tabCustomer, null);
		tabCustomer.setLayout(null);
		
		JScrollPane scrollPaneCustomer = new JScrollPane();
		scrollPaneCustomer.setBounds(36, 51, 589, 435);
		tabCustomer.add(scrollPaneCustomer);
		
		tableCustomer = new JTable();
		tableCustomer.addMouseListener(new MouseAdapter() {
			
			public void setTextField(Customer p) {
				tfCustomerName.setText(p.getName());
				tfCustomerPhone.setText(p.getPhone());
				tfCustomerAddress.setText(p.getAddress());
			
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableCustomer.rowAtPoint(e.getPoint());
				String phone = tableCustomer.getValueAt(row, 1).toString();
				Customer p = c.getCustomerByPhone(phone);
				setTextField(p);
				lbEditCustomer.setVisible(true);
			}
		});
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Phone", "Address", "Number of times buyed"
			}
		));
		scrollPaneCustomer.setViewportView(tableCustomer);
		fillDataTableCustomer();
		
		JLabel lbInsertCustomer = new JLabel("");
		lbInsertCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbInsertCustomer.setIcon(icInsert2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbInsertCustomer.setIcon(icInsert1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if(validateFormCustomer()) {
						Customer p = putToModelCustomer();
						if(c.CheckCustomerExist(p)>0)
							JOptionPane.showMessageDialog(null, "Customer's phone is existed");
						
						else if(c.InsertCustomer(p)>0) {
							JOptionPane.showMessageDialog(null, "Insert successfully");
							fillDataTableCustomer();
							cbCustomer.addItem(p.getName());
						}
					}
					
					else {
						JOptionPane.showMessageDialog(null, "Please complete required field");	
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		lbInsertCustomer.setBounds(657, 416, 65, 45);
		tabCustomer.add(lbInsertCustomer);
		lbInsertCustomer.setIcon(icInsert1);
		
		lbEditCustomer = new JLabel("");
		lbEditCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lbEditCustomer.setIcon(icEdit2);
			} 
			@Override
			public void mouseExited(MouseEvent e) {
				
				lbEditCustomer.setIcon(icEdit1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
				try {
					if(validateFormCustomer()) {
						Customer p = putToModelCustomer();
						if(c.checkUpdateCustomer(p)==true) {
							if(c.UpdateCustomer(p)>0) {
								JOptionPane.showMessageDialog(null, "Update successfully");
								fillDataTableCustomer();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "You mustn't change the phone or name, \n"
									+ "You should insert a new one");
							newTextFieldCustomer();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Try again");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
				
				
			}
		});
		lbEditCustomer.setBounds(751, 416, 65, 45);
		tabCustomer.add(lbEditCustomer);
		lbEditCustomer.setIcon(icEdit1);
		lbEditCustomer.setVisible(false);
		
		JLabel lbDeleteCustomer = new JLabel("");
		lbDeleteCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				
				lbDeleteCustomer.setIcon(icDelete2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
				lbDeleteCustomer.setIcon(icDelete1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if(validateFormCustomer()) {
						int row = tableCustomer.getSelectedRow();
						Customer p = putToModelCustomer();
						p.setPhone(tableCustomer.getValueAt(row, 1).toString());
						
						if(c.DeleteCustomer(p)>0) {
							JOptionPane.showMessageDialog(null, "Delete successfully");
							fillDataTableCustomer();
							newTextFieldCustomer();
							cbCustomer.removeItem(p.getName());
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Choose again");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});
		lbDeleteCustomer.setBounds(851, 416, 65, 45);
		tabCustomer.add(lbDeleteCustomer);
		lbDeleteCustomer.setIcon(icDelete1);
		
		JLabel lbRefreshCustomer = new JLabel("");
		lbRefreshCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {				
				lbRefreshCustomer.setIcon(icRefresh2);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lbRefreshCustomer.setIcon(icRefresh1);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				newTextFieldCustomer();
				fillDataTableCustomer();
				lbEditCustomer.setVisible(false);
			}
		});
		lbRefreshCustomer.setBounds(953, 416, 65, 45);
		tabCustomer.add(lbRefreshCustomer);
		lbRefreshCustomer.setIcon(icRefresh1);
		
		tfCustomerName = new JTextField();
		tfCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCustomerName.setColumns(10);
		tfCustomerName.setBounds(779, 116, 195, 25);
		tabCustomer.add(tfCustomerName);
		
		JLabel lbCustomerName = new JLabel("Name");
		lbCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCustomerName.setBounds(692, 114, 77, 25);
		tabCustomer.add(lbCustomerName);
		
		tfCustomerPhone = new JTextField();
		tfCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCustomerPhone.setColumns(10);
		tfCustomerPhone.setBounds(779, 186, 195, 25);
		tabCustomer.add(tfCustomerPhone);
		
		JLabel lbCustomerPhone = new JLabel("Phone");
		lbCustomerPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCustomerPhone.setBounds(692, 184, 77, 25);
		tabCustomer.add(lbCustomerPhone);
		
		tfCustomerAddress = new JTextField();
		tfCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfCustomerAddress.setColumns(10);
		tfCustomerAddress.setBounds(779, 253, 195, 25);
		tabCustomer.add(tfCustomerAddress);
		
		JLabel lbCustomerAddress = new JLabel("Address");
		lbCustomerAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbCustomerAddress.setBounds(692, 251, 77, 25);
		tabCustomer.add(lbCustomerAddress);
		
		tabbedPane.setEnabledAt(0, false);
		tabbedPane.setSelectedIndex(1);
		
		JPanel tabEmployee = new JPanel();
		tabbedPane.addTab("Employee", null, tabEmployee, null);
		tabEmployee.setLayout(null);
		
		cbRole = new JComboBox();
		cbRole.setModel(new DefaultComboBoxModel(new String[] {"Admin", "Employee"}));
		cbRole.setBounds(778, 56, 237, 22);
		tabEmployee.add(cbRole);
		
		JLabel lbRole = new JLabel("Role");
		lbRole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbRole.setBounds(672, 58, 46, 14);
		tabEmployee.add(lbRole);
		
		JLabel lbUsername = new JLabel("User Name");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setBounds(659, 108, 80, 14);
		tabEmployee.add(lbUsername);
		
		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfUserName.setColumns(10);
		tfUserName.setBounds(776, 103, 239, 25);
		tabEmployee.add(tfUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(662, 157, 67, 14);
		tabEmployee.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(776, 156, 239, 25);
		tabEmployee.add(passwordField);
		
		JLabel lbRePassword = new JLabel("Re-enter Password");
		lbRePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbRePassword.setBounds(636, 208, 130, 14);
		tabEmployee.add(lbRePassword);
		
		rePasswordField = new JPasswordField();
		rePasswordField.setBounds(776, 207, 239, 25);
		tabEmployee.add(rePasswordField);
		
		JLabel lbEmployeeName = new JLabel("Employee Name");
		lbEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbEmployeeName.setBounds(636, 256, 130, 14);
		tabEmployee.add(lbEmployeeName);
		
		tfEmployeeName = new JTextField();
		tfEmployeeName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfEmployeeName.setColumns(10);
		tfEmployeeName.setBounds(776, 251, 239, 25);
		tabEmployee.add(tfEmployeeName);
		
		JLabel btnInsertEmployee = new JLabel("Insert");
		btnInsertEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(tfUserName.getText().isEmpty() || passwordField.getText().isEmpty() || rePasswordField.getText().isEmpty() || tfEmployeeName.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill in the blank");
				}
				else if(!passwordField.getText().equals(rePasswordField.getText())) {
					JOptionPane.showMessageDialog(null, "Wrong password");
				}
				else {
					Account a = new Account(tfUserName.getText(), passwordField.getText(), cbRole.getSelectedItem().toString(), tfEmployeeName.getText());
					if(c.CheckAccountExist(a)>0) {
						JOptionPane.showMessageDialog(null, "Account existed");
					}
					else if(c.SignUp(a)>0) {
						JOptionPane.showMessageDialog(null, "Sign up successful");
						fillDataTableEmployee();
					}
				}
			}
		});
		btnInsertEmployee.setBounds(707, 340, 73, 52);
		tabEmployee.add(btnInsertEmployee);
		
		JLabel btnLayOff = new JLabel("Lay off");
		btnLayOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					int row = tableEmployee.getSelectedRow();
					Account p = new Account();
					p.setUsername(tableEmployee.getValueAt(row, 0).toString());
					
					if(c.DeleteAccount(p)>0) {
						JOptionPane.showMessageDialog(null, "Delete successfully");
						fillDataTableEmployee();
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "You haven't select yet");
				}
			}
		});
		btnLayOff.setBounds(816, 337, 80, 58);
		tabEmployee.add(btnLayOff);
		
		JLabel btnChangePassword = new JLabel("Change password");
		btnChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					int row = tableEmployee.getSelectedRow();
					Account p = new Account();
					p.setUsername(tableEmployee.getValueAt(row, 0).toString());
					p.setPassword(JOptionPane.showInputDialog("Type new password"));
					
					if(c.ChangePassword(p)>0) {
						JOptionPane.showMessageDialog(null, "Change successfully");
						fillDataTableEmployee();
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "You haven't select yet");
				}
			}
		});
		btnChangePassword.setBounds(921, 337, 80, 58);
		tabEmployee.add(btnChangePassword);
		
		JScrollPane scrollPaneEmployee = new JScrollPane();
		scrollPaneEmployee.setBounds(40, 76, 540, 396);
		tabEmployee.add(scrollPaneEmployee);
		
		tableEmployee = new JTable();
		tableEmployee.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"User Name", "Password", "Role", "Employee Name"
			}
		));
		scrollPaneEmployee.setViewportView(tableEmployee);
		fillDataTableEmployee();
		
		
		setResizable(false);
		setUndecorated(false);
		setVisible(true);
	}
	
	
	
	// method
	
	public void newTextFieldProduct() {
		tfName.setText("");
		tfBrand.setText("");
		tfCpu.setText("");
		tfRam.setText("");
		tfRom.setText("");
		tfDisplay.setText("");
		tfBatery.setText("");
		tfAmount.setText("");
		tfImportPrice.setText("");
		tfExportPrice.setText("");
	
	}
	
	public void newTextFieldCustomer() {
		tfCustomerName.setText("");
		tfCustomerPhone.setText("");
		tfCustomerAddress.setText("");
	}
	
	
	
	public void fillDataTableProduct() {
		DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
		model.setRowCount(0);
		
		for(Product p : c.getAllProduct()) {
			Object dataRow[] = new Object[10];
			dataRow[0] = p.getName();
			dataRow[1] = p.getBrand();
			dataRow[2] = p.getCpu();
			dataRow[3] = p.getRam();
			dataRow[4] = p.getRom();
			dataRow[5] = p.getDisplay();
			dataRow[6] = p.getPin();
			dataRow[7] = p.getAmount();
			dataRow[8] = p.getImportPrice();
			dataRow[9] = p.getExportPrice();
			model.addRow(dataRow);
		}
	}
	
	public void fillDataTableProductReceipt() {
		DefaultTableModel model = (DefaultTableModel) tableProductSell.getModel();
		model.setRowCount(0);
		
		for(Product p : c.getAllProduct()) {
			Object dataRow[] = new Object[10];
			dataRow[0] = p.getName();
			dataRow[1] = p.getBrand();
			dataRow[2] = p.getCpu();
			dataRow[3] = p.getRam();
			dataRow[4] = p.getRom();
			dataRow[5] = p.getDisplay();
			dataRow[6] = p.getPin();
			dataRow[7] = p.getAmount();
			dataRow[8] = p.getImportPrice();
			dataRow[9] = p.getExportPrice();
			model.addRow(dataRow);
		}
	}
	
	public void fillDataTableCustomer() {
		DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
		model.setRowCount(0);
		
		for(Customer p : c.getAllCustomer()) {
			Object dataRow[] = new Object[4];
			dataRow[0] = p.getName();
			dataRow[1] = p.getPhone();
			dataRow[2] = p.getAddress();
			dataRow[3] = p.getNumOfTimeBuy();
			model.addRow(dataRow);
		}
	}
	
	public void fillDataTableEmployee() {
		DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();
		model.setRowCount(0);
		
		for(Account p : c.getAllAccount()) {
			Object dataRow[] = new Object[4];
			dataRow[0] = p.getUsername();
			dataRow[1] = p.getPassword();
			dataRow[2] = p.getRole();
			dataRow[3] = p.getName();
			model.addRow(dataRow);
		}
	}
	
	public void insertOrder() {
		DefaultTableModel model = (DefaultTableModel) tableOrder.getModel();
		Object dataRow[] = new Object[6];
		dataRow[0] = tfProductsName.getText();
		dataRow[1] = tfPrice.getText();
		dataRow[2] = tfAmountSell.getText();
		dataRow[3] = String.valueOf(Double.parseDouble(tfPrice.getText())*Double.parseDouble(tfAmountSell.getText()));
		dataRow[4] = tfEmployeeNameSell.getText();
		dataRow[5] = cbCustomer.getSelectedItem().toString();
		model.addRow(dataRow);
	}
	
	public boolean validateFormProduct() {
		if(tfName.getText().isEmpty() || tfBrand.getText().isEmpty() || tfCpu.getText().isEmpty() || tfRam.getText().isEmpty() || tfRam.getText().isEmpty() || tfDisplay.getText().isEmpty() || tfBatery.getText().isEmpty() || tfAmount.getText().isEmpty()|| tfImportPrice.getText().isEmpty() || tfExportPrice.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean validateFormCustomer() {
		if(tfCustomerAddress.getText().isEmpty() || tfCustomerName.getText().isEmpty() || tfCustomerPhone.getText().isEmpty()) {
			return false;
		}
		return true;
	}
	
	
	public Product putToModelProduct() {
		Product p = new Product();
		p.setName(tfName.getText());
		p.setBrand(tfBrand.getText());
		p.setCpu(tfCpu.getText());
		p.setRam(tfRam.getText());
		p.setRom(tfRom.getText());
		p.setDisplay(tfDisplay.getText());
		p.setPin(tfBatery.getText());
		p.setAmount(Integer.parseInt(tfAmount.getText()));
		p.setImportPrice(Double.parseDouble(tfImportPrice.getText()));
		p.setExportPrice(Double.parseDouble(tfExportPrice.getText()));
		
		return p;
	}
	
	public Customer putToModelCustomer() {
		Customer p = new Customer();
		p.setName(tfCustomerName.getText());
		p.setPhone(tfCustomerPhone.getText());
		p.setAddress(tfCustomerAddress.getText());
		
		return p;
	}
	
	public void printReceipt() {
		
	}
	
	public void fillComboBoxCustomerName(){
		for (Customer p : c.getAllCustomer()) {
			cbCustomer.addItem(p.getName());
		}
	}
	
	public void fillComboBoxCustomerPhone(){
		cbPhone.removeAllItems();
		for(String phone : c.getPhonesByName(cbCustomer.getSelectedItem().toString())) {
			cbPhone.addItem(phone);
		}
	}
	
	public Date utilToSqlDate(java.util.Date time){
	    java.sql.Date sqldate = new java.sql.Date(time.getTime());
	    return sqldate;
	}
}
