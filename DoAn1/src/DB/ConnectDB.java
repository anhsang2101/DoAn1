package DB;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Model.Account;
import Model.Customer;
import Model.Product;
import Model.Receipt;



public class ConnectDB {
	public static final String connectionURL = "jdbc:sqlserver://SANG\\SQLEXPRESS:1433;databaseName=DOAN1;user=sa;password=1234";
	
	public static Connection getDBConnect() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionURL);
			System.out.println("Successful");
			return con;
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("Where is driver?");
			System.out.println(e.toString());
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		
		return null;
	}
	
	
	public int SignIn(Account a) {
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Account Where username = ? and password = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, a.getUsername());
			sttm.setString(2, a.getPassword());
			rs = sttm.executeQuery();
			if(rs.next())
				return 1;
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public String getRoleInSQL(Account a) {
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Account Where username = ? and password = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, a.getUsername());
			sttm.setString(2, a.getPassword());
			rs = sttm.executeQuery();
			if(rs.next())
				return rs.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		 return null;
	}
	
	public int SignUp(Account a) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Insert into Account (username, password, role, name) values(?,?,?,?)";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, a.getUsername());
			sttm.setString(2, a.getPassword());
			sttm.setString(3, a.getRole());
			sttm.setString(4, a.getName());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Sign Up thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int ChangePassword(Account p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Update Account set password = ? where username = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getPassword());
			sttm.setString(2, p.getUsername());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Update thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int DeleteAccount(Account p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Delete From Account where username = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getUsername());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Delete thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	
	public List<Account> getAllAccount(){
		List<Account> list = new ArrayList<>();
		Connection conn = null;
		Statement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from Account";
			conn = getDBConnect();
			sttm = conn.createStatement();
			rs = sttm.executeQuery(sql);
			while(rs.next()) {
				Account p = new Account();
				p.setUsername(rs.getString(1));
				p.setPassword(rs.getString(2));
				p.setRole(rs.getString(3));
				p.setName(rs.getString(4));
				list.add(p);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public int CheckAccountExist(Account a) {
		
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Account Where username = ? ";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, a.getUsername());
			rs = sttm.executeQuery();
			if(rs.next())
				return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;

	}
	
	public String getEmployeeName(String username) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		String sql = "Select name from Account where username = ?";
		try {
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, username);
			rs = sttm.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	public int CheckProductExist(Product p) {
		
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Product Where name = ? ";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getName());
			rs = sttm.executeQuery();
			if(rs.next())
				return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;

	}
	
	
	public int InsertProduct(Product p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Insert into Product (name, brand, cpu, ram, rom, display, pin, amount, importprice, exportprice) values(?,?,?,?,?,?,?,?,?,?)";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getName());
			sttm.setString(2, p.getBrand());
			sttm.setString(3, p.getCpu());
			sttm.setString(4, p.getRam());
			sttm.setString(5, p.getRom());
			sttm.setString(6, p.getDisplay());
			sttm.setString(7, p.getPin());
			sttm.setInt(8, p.getAmount());
			sttm.setDouble(9, p.getImportPrice());
			sttm.setDouble(10, p.getExportPrice());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Insert thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int UpdateProduct(Product p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Update Product set brand = ?, cpu = ?, ram = ?, rom = ?, display = ?, pin = ?,  amount = ?, importprice = ?, exportprice = ? where name = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getBrand());
			sttm.setString(2, p.getCpu());
			sttm.setString(3, p.getRam());
			sttm.setString(4, p.getRom());
			sttm.setString(5, p.getDisplay());
			sttm.setString(6, p.getPin());
			sttm.setInt(7, p.getAmount());
			sttm.setDouble(8, p.getImportPrice());
			sttm.setDouble(9, p.getExportPrice());
			sttm.setString(10, p.getName());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Update thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int DeleteProduct(Product p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Delete From Product where name = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getName());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Delete thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public boolean checkUpdateProduct(String name) {
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Product Where name = ? ";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, name);
			rs = sttm.executeQuery();
			if(rs.next())
				return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return false;

	}
	
	
	public List<Product> getAllProduct(){
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		Statement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from Product";
			conn = getDBConnect();
			sttm = conn.createStatement();
			rs = sttm.executeQuery(sql);
			while(rs.next()) {
				Product p = new Product();
				p.setName(rs.getString(1));
				p.setBrand(rs.getString(2));
				p.setCpu(rs.getString(3));
				p.setRam(rs.getString(4));
				p.setRom(rs.getString(5));
				p.setDisplay(rs.getString(6));
				p.setPin(rs.getString(7));
				p.setAmount(rs.getInt(8));
				p.setImportPrice(rs.getDouble(9));
				p.setExportPrice(rs.getDouble(10));
				list.add(p);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public Product getProductByName(String name) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		String sql = "Select * from Product where name = ?";
		Product p = new Product();
		try {
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, name);
			rs = sttm.executeQuery();
			if(rs.next()) {
				p.setName(rs.getString(1));
				p.setBrand(rs.getString(2));
				p.setCpu(rs.getString(3));
				p.setRam(rs.getString(4));
				p.setRom(rs.getString(5));
				p.setDisplay(rs.getString(6));
				p.setPin(rs.getString(7));
				p.setAmount(rs.getInt(8));
				p.setImportPrice(rs.getDouble(9));
				p.setExportPrice(rs.getDouble(10));
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	public List<Product> getListProductSearch(String name){
		List<Product> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;

		try {
			String sql = "Select * From Product where name like '%"+name+"%'";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
			
			while(rs.next()) {
				Product p = new Product();
				p.setName(rs.getString(1));
				p.setBrand(rs.getString(2));
				p.setCpu(rs.getString(3));
				p.setRam(rs.getString(4));
				p.setRom(rs.getString(5));
				p.setDisplay(rs.getString(6));
				p.setPin(rs.getString(7));
				p.setAmount(rs.getInt(8));
				p.setImportPrice(rs.getDouble(9));
				p.setExportPrice(rs.getDouble(10));
				list.add(p);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				rs.close();
				//SQL_D.getCon().close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
		return list;
	}
	
	public void searchProductByName(JTable tableProduct, JTextField tfSearch) {
		DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
		model.setRowCount(0);
		
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		
		try {
			String sql = "Select * From Product where name like '%"+tfSearch.getText()+"%'";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			rs = sttm.executeQuery();
					
			if(rs.next()==false) {
				JOptionPane.showMessageDialog(null, "Not found");
			}
			else {
				model.setRowCount(0);
				for(Product p : getListProductSearch(tfSearch.getText())) {
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
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	
	
	
	public int CheckCustomerExist(Customer p) {
		
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Customer Where phone = ? ";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getPhone());
			rs = sttm.executeQuery();
			if(rs.next())
				return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;

	}
	public int InsertCustomer(Customer p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Insert into Customer (name, phone, address, numOfTimeBuy) values(?,?,?,?)";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getName());
			sttm.setString(2, p.getPhone());
			sttm.setString(3, p.getAddress());
			sttm.setInt(4, 0);
			if(sttm.executeUpdate() > 0) {
				System.out.println("Insert thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int UpdateCustomer(Customer p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Update Customer set name = ?, address = ? where phone = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getName());
			sttm.setString(2, p.getAddress());
			sttm.setString(3, p.getPhone());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Update thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public int DeleteCustomer(Customer p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Delete From Customer where phone = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getPhone());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Delete thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public boolean checkUpdateCustomer(Customer p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * From Customer Where phone = ? and name = ? ";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getPhone());
			sttm.setString(2, p.getName());
			rs = sttm.executeQuery();
			if(rs.next())
				return true;
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return false;

	}
	
	
	public List<Customer> getAllCustomer(){
		List<Customer> list = new ArrayList<>();
		Connection conn = null;
		Statement sttm = null;
		ResultSet rs = null;
		try {
			String sql = "Select * from Customer";
			conn = getDBConnect();
			sttm = conn.createStatement();
			rs = sttm.executeQuery(sql);
			while(rs.next()) {
				Customer p = new Customer();
				p.setName(rs.getString(1));
				p.setPhone(rs.getString(2));
				p.setAddress(rs.getString(3));
				p.setNumOfTimeBuy(rs.getInt(4));
				list.add(p);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public Customer getCustomerByPhone(String phone) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		String sql = "Select * from Customer where phone = ?";
		Customer p = new Customer();
		try {
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, phone);
			rs = sttm.executeQuery();
			if(rs.next()) {
				p.setName(rs.getString(1));
				p.setPhone(rs.getString(2));
				p.setAddress(rs.getString(3));
				p.setNumOfTimeBuy(rs.getInt(4));
				return p;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	
	public int UpdateNumOfTimeBuy(String phone) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "UPDATE Customer SET numOfTimeBuy = numOfTimeBuy + 1 WHERE phone = ?";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, phone);
			if(sttm.executeUpdate() > 0) {
				System.out.println("Update thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	public List<String> getPhonesByName(String name) {
		List<String> list = new ArrayList<>();
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement sttm = null;
		String sql = "Select phone from Customer where name = ?";
		try {
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, name);
			rs = sttm.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public int InsertReceipt(Receipt p) {
		Connection conn = null;
		PreparedStatement sttm = null;
		try {
			String sql = "Insert into Receipt (id, customerName, employeeName, price, buyDate) values(?,?,?,?,?)";
			conn = getDBConnect();
			sttm = conn.prepareStatement(sql);
			sttm.setString(1, p.getIdReceipt());
			sttm.setString(2, p.getCustomerName());
			sttm.setString(3, p.getEmployeeName());
			sttm.setDouble(4, p.getReceiptPrice());
			sttm.setDate(5, p.getBuyDate());
			if(sttm.executeUpdate() > 0) {
				System.out.println("Insert thanh cong");
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		} finally {
			try {
				sttm.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return -1;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
//		Connection conn = getDBConnect();
		ConnectDB c = new ConnectDB();
		Product p = new Product("2","apple","2","1","1","1","1",1,1.123,2.333);
		
//		c.InsertProduct(p);
		c.UpdateProduct(p);
//		c.DeleteProduct(p);
	}
}
