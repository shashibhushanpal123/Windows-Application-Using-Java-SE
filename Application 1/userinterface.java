package GUI;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class userinterface implements ActionListener {// Object variables are
														// placed here in this
														// class

	// JDBC

	Connection con;
	Statement stat;
	PreparedStatement pstat;
	ResultSet rs; // rs follows disconnected architecture.

	// Containers and Subcontainers.

	JFrame frame; // JFrame is predefined class.
	JPanel panelcrud, panelnavig;// These all are stack allocation.They are not
									// available until we make their heap
									// allocation. Heap allocation is done
									// inside function.

	// Components for frame.

	JLabel lb1, lb2, lb3;

	JTextField txtf1, txtf2, txtf3;

	// Controls for curd panel.

	JButton btnupd, btndel, btnsear, btnclr, btnsel, btninst;

	// Controls for navig panel.

	JButton btnfir, btnlast, btnpre, btnnext;

	// For menu->

	JMenuBar menu;

	JMenu menucurd, menunavig;

	// Menu items for Menucurd.

	JMenuItem minst, mret, mupd, mdel, mclr;

	// Menu items for MenuNavig.

	JMenuItem mfirst, mlast, mnext, mpre;

		
	  		
     	public void CreateEnterface() {

		frame = new JFrame();
		frame.setTitle("Car Inventory Record Keeping");

		// work for curd menu begins.

		menu = new JMenuBar();

		menucurd = new JMenu("Crud Menu");

		minst = new JMenuItem("Menu Item Insert");
		mret = new JMenuItem("Menu Item Retrieve");
		mupd = new JMenuItem("Menu Item Update");
		mdel = new JMenuItem("Menu Item Delete");
		mclr = new JMenuItem("Menu Item Clear");

		// adding menuitem to menu.

		menucurd.add(minst);
		menucurd.add(mret);
		menucurd.add(mupd);
		menucurd.add(mdel);
		menucurd.add(mclr);

		// now we place menucurd to menu.

		menu.add(menucurd);

		// placing menu to frame

		frame.add(menu);

		// work for navig menu begins.

		menunavig = new JMenu("Navig Menu");

		mfirst = new JMenuItem("Menu Item First");
		mlast = new JMenuItem("Menu Item Last");
		mpre = new JMenuItem("Menu Item Previous");
		mnext = new JMenuItem("Menu Item Next");

		// adding menuitem to menu.

		menunavig.add(mfirst);
		menunavig.add(mlast);
		menunavig.add(mpre);
		menunavig.add(mlast);

		// now we place menucurd to menu.

		menu.add(menunavig);

		// placing menu to frame

		frame.add(menu);

		lb1 = new JLabel("Car id");

		lb2 = new JLabel();
		lb2.setText("Car Name");

		lb3 = new JLabel();
		lb3.setText("Car ");

		txtf1 = new JTextField(5);
		txtf2 = new JTextField(20);
		txtf3 = new JTextField(20);

		// Declaring the layout

		FlowLayout decorate = new FlowLayout();// put components to container
												// from ,left to right

		// GridLayout decorate =new GridLayout();

		frame.setLayout(decorate);

		// place the controls into the container/frame.

		frame.add(lb1);
		frame.add(txtf1);
		frame.add(lb2);
		frame.add(txtf2);
		frame.add(lb3);
		frame.add(txtf3);

		// crud panel->

		panelcrud = new JPanel();

		panelcrud.setBorder(BorderFactory.createTitledBorder("CURD PANEL"));

		// button object crud panel

		btninst = new JButton("Insert");
		btninst.addActionListener(this);

		btnupd = new JButton("Update");
		btnupd.addActionListener(this);

		btndel = new JButton("Delete");
		btndel.addActionListener(this);

		btnsear = new JButton("Search");
		btnsear.addActionListener(this);

		btnclr = new JButton("Clear");
		btnclr.addActionListener(this);

		panelcrud.add(btninst);
		panelcrud.add(btnupd);
		panelcrud.add(btndel);
		panelcrud.add(btnsear);
		panelcrud.add(btnclr);

		frame.add(panelcrud);

		// Navigation panel->

		panelnavig = new JPanel();

		panelnavig.setBorder(BorderFactory.createTitledBorder("NAVIG PANEL"));

		// button object Navigation panel

		btnfir = new JButton("<<");

		// Subscription of button for deegation and Delegation->On click
		// button's reference is delegated to Action Listner.
		btnfir.addActionListener(this);

		btnlast = new JButton("<");
		btnlast.addActionListener(this);

		btnpre = new JButton(">");
		btnpre.addActionListener(this);

		btnnext = new JButton(">>");
		btnnext.addActionListener(this);

		panelnavig.add(btnfir);
		panelnavig.add(btnlast);
		panelnavig.add(btnpre);
		panelnavig.add(btnnext);

		frame.add(panelnavig);

		frame.setSize(700, 500);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ref)//On click refernce comes to ref 
	
	{
		if(ref.getActionCommand()=="Insert")
		{
			rcrinsert();
			//JOptionPane.showMessageDialog(frame,"Insert got clicked" );
		}else if(ref.getActionCommand()=="Update")
		{
			rcrupdate();
			
			//JOptionPane.showMessageDialog(frame,"Update got clicked" );
		}
		
		
		else if(ref.getActionCommand()=="Delete")
		{
			rcrdelete();
		//JOptionPane.showMessageDialog(frame,"Insert got clicked" );
	
		}
		
		else if(ref.getActionCommand()=="Clear")
		{
			//JOptionPane.showMessageDialog(frame,"Clear got clicked" );
		
			txtf1.setText(" ");
			txtf2.setText(" ");
			txtf3.setText(" ");
		}
		
			else if(ref.getActionCommand()=="<<")
			{
				rcrfirst();
			}
				//JOptionPane.showMessageDialog(frame,"Clear got clicked" );

			else if(ref.getActionCommand()=="Last")
			{
				rcrlast();
				//JOptionPane.showMessageDialog(frame,"Clear got clicked" );

			}
			
			else if(ref.getActionCommand()=="Previous")
			{
				rcrprevious();
			}
				//JOptionPane.showMessageDialog(frame,"Clear got clicked" );

			else if(ref.getActionCommand()=="Next")
			{
				rcrnext();
			
				//JOptionPane.showMessageDialog(frame,"Clear got clicked" );

			}
			
			else if(ref.getActionCommand()=="Search")
			{
				rcrsearch();
				
				//JOptionPane.showMessageDialog(frame,"Search got clicked" );
			}
			
		}
		
	

	private void rcrsearch() {
	
		if(txtf1.getText().trim().isEmpty())//trim is used to avoid SQL Injection.
		{
			JOptionPane.showMessageDialog(frame,"App need car id");
		}
		
		else
		{
			int id=Integer.parseInt(txtf1.getText().trim());
			

			
			try {
				pstat=con.prepareStatement("select * from one  where carid=?");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {
				pstat.setInt(1,id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //since for integer column(carid).
			
			//now firing query at runtime which is done only by prepared statement and not by statement.
			
			try {
				rs=pstat.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if(rs.next());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//since normally pointer is at column heading and if moved to searched id then fillTextBox() is called.
			
			{
				fillTextBox();
			}
			
				
			
		}
		
	}
	
	private void fillTextBox() {
		// TODO Auto-generated method stub
		
	}

	private void rcrnext() {

	
		if (rs.next()) 
		{	
            fillTextBox(); //if clicked on next then we called filltextbox function.
		}
		
		else
		
		{
			rs.previous();
			JOptionPane.showMessageDialog(frame,"You are at last");
		
		}
		
		}
	

	private void rcrinsert() {

		//taking value from text box into a variable
		
		int cid=Integer.parseInt(txtf1.getText()); //Typecasted.
		
		String cname=txtf2.getText();
		
		double cprice=Double.parseDouble(txtf3.getText());
		

		
		try {
			rs.moveToInsertRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Predefined

		try {
			rs.updateInt(1,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Predefined
		
		try {
			rs.updateString(2,cname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //But by default Result set is Read Only so it gives error.
		
		try {
			rs.updateDouble(3,cprice);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//To create RS updatable-> We should deal with only one table,There should be atleast 1 P.Key
		
	    try {
			rs.insertRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //Predefined ->It inserts updated result set(Virtual table) into final physical table.
		
	}
		
		

	private void rcrupdate() {
		
        int cid=Integer.parseInt(txtf1.getText()); //Typecasted.
		
		String cname=txtf2.getText();
		
		double cprice=Double.parseDouble(txtf3.getText());
		
		try {
			rs.updateInt("carid",cid);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} //that carid row is updated with cid.
	
	    try {
			rs.updateString("carname",cname);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
	    try {
			rs.updateDouble("carprice",cprice);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    //now comitting result in final physical table.

		try {
			rs.updateRow();
				
			{
				fillTextBox();
			}
			
			
				
		} 
	 
	   
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Predefined.
	    
	    JOptionPane.showMessageDialog(frame, "Record gets Deleted");
	    
	}

	private void rcrdelete() {
		
			try {
				rs.deleteRow();
					
				{
					fillTextBox();
				}
				
				
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(frame,"Record Deleted");
		

	}

	private void rcrfirst() {
		// TODO Auto-generated method stub

				
				try {
					if(rs.first())
					{
						fillTextBox();
					}
					
					else
					{
						JOptionPane.showMessageDialog(frame,"You are at last");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		
	}

	private void rcrlast() {
		// TODO Auto-generated method stub
		
		try {
			if(rs.last())//predefined function rs.last();
			{
				fillTextBox();
			}
			
			else
			{
				JOptionPane.showMessageDialog(frame,"You are at last");
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void rcrprevious() {
	
		
		try {
			if (rs.next()) 
			{	
			    fillTextBox(); //if clicked on next then we called filltextbox function.
			}
			
			else
			
			{
				rs.previous();
				JOptionPane.showMessageDialog(frame,"You are at last");		
			}
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
		}