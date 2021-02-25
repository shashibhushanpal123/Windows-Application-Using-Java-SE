import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class frontend1 implements ActionListener
{
	
JFrame frame;
JTextField txt1,txt2,txt3;
JLabel l1,l2,l3;
JPanel p1,p2;
JButton b1,b2,b3,b4,b5;
Statement st;
PreparedStatement pst;
Connection con;
ResultSet rs;

void firstfun()
{
	frame=new JFrame("2nd Windows App");
	//frame.getContentPane().setBackground(Color.red);
	l1=new JLabel("Enter Name");
	l2=new JLabel("Enter Roll Number");
	l3=new JLabel("Enter City");
	
	txt1=new JTextField(15);
	txt2=new JTextField(10);
	txt3=new JTextField(10);
	
	p1=new JPanel();
	
	b1=new JButton("Insert");
	b2=new JButton("Update");
	b3=new JButton("Delete");
	b4=new JButton("Search");
	b5=new JButton("Clear All");
	
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);

	FlowLayout orientation = new FlowLayout();
	frame.setLayout(orientation);
	
	frame.add(l1);
	frame.add(txt1);
	frame.add(l2);
	frame.add(txt2);
	frame.add(l3);
	frame.add(txt3);
	p1.add(b1);
	p1.add(b2);
	p1.add(b3);
	p1.add(b4);
    p1.add(b5);
	frame.add(p1);
	
	p1.setBorder(BorderFactory.createTitledBorder("OPERATIONS"));
	
	
	
	frame.setSize(700,500);
	frame.setVisible(true);
	
	
  JOptionPane.showMessageDialog(frame, "Welcome To Student Management System"); 
  
  JOptionPane.showMessageDialog(frame, "Please Enter Details As Mentioned Above"); 
  
  JOptionPane.showMessageDialog(frame, "For Searching A Record You Have To Enter Roll Number");
  
}


public void JDBC()
{
   //Step 1:->Load the Driver.
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	//Step 2:->Establish connectivity b/w front end and back end.
	
	try {
		con=DriverManager.getConnection("jdbc:mysql://localhost/windowsapp", "root", "q1w2e3r4");
	   //Step 3:->Prepare a message for mysql database.
	
	String message="select * from windowsapp.sms";
	
	
	//Step 4:->We need object to transfer message from front end to back end.
	
		st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	 
	//Step 5:->Fire the query and store the table in return.
	
		rs=st.executeQuery(message);
	
}
	
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
}

public void filltextboxes()
{
	
	try
	{
		
	txt1.setText(rs.getString(2));
	txt2.setText(Integer.toString(rs.getInt(1)));
	txt3.setText(rs.getString(3));
	
	}
	
	catch(SQLException e)
	{
		
		e.printStackTrace();
	}
	
}


@Override
public void actionPerformed(ActionEvent args) {
	
	if (args.getActionCommand() == "Insert") {

		 insert();

	}
		 
		 else if(args.getActionCommand() == "Update")
		 {
			 	//JOptionPane.showMessageDialog(frame,"Inside update");
	
			 update();
			 
		 }
		 
		 
		 else if(args.getActionCommand() == "Delete")
		 {
			 
			 delete();
			 
		 }
		 
		 
		 else if(args.getActionCommand() == "Search")
		 {
			 search();
			 JDBC();
		 }
	

		 else if(args.getActionCommand() == "Clear All")
		 {
			 txt1.setText(" ");
			 txt2.setText(" ");
			 txt3.setText(" ");
			 
		 }
		 
		 
}


private void insert() {
	
	//Storing values entered by user in text boxes.
	
	String name=txt1.getText();
	int roll=Integer.parseInt(txt2.getText());
	String city=txt3.getText();
	
	try {
		rs.moveToInsertRow();
		rs.updateInt(1,roll);
		rs.updateString(2,name);
		

	rs.updateString(3,city);

	rs.insertRow();
	JOptionPane.showMessageDialog(frame,"Record Gets Inserted");
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}



private void update() {
	
	

	String name=txt1.getText();
	int roll=Integer.parseInt(txt2.getText());
	String city=txt3.getText();
	
	
	try
	{
		
		rs.first();
		
		while (rs.getInt(1) != Integer.parseInt(txt2.getText()))
		{
			if (rs.isLast())
			{
				JOptionPane.showMessageDialog(frame, "record is not in our table record");	
				break;
			}
			else 
			{
			rs.next();
			}
		}
		if (rs.getInt(1) == Integer.parseInt(txt2.getText()))
		{

		//rs.updateInt(1,roll);
	
		//JOptionPane.showMessageDialog(frame,"After 1st update of Roll");

		
	rs.updateString(2,name);
	
	//JOptionPane.showMessageDialog(frame,"After 2nd update of name");

	
	rs.updateString(3,city);
	
	//JOptionPane.showMessageDialog(frame,"Before commit");

	
	rs.updateRow();
	
	JOptionPane.showMessageDialog(frame,"Record Updated Succesfully");
		}
	}
	
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	
}



private void delete() {
	
	try {
//int concurrency=rs.getConcurrency();
		
	//	JOptionPane.showMessageDialog(frame,concurrency);
		rs.first();
		while (rs.getInt(1) != Integer.parseInt(txt2.getText()))
		{
			if (rs.isLast())
			{
				JOptionPane.showMessageDialog(frame, "record is not in our table record");	
				break;
			}
			else 
			{
			rs.next();
			}
		}
		if (rs.getInt(1) == Integer.parseInt(txt2.getText()))
				{
			rs.deleteRow();
		JOptionPane.showMessageDialog(frame,"1 Record Deleted");
				}
	} 
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}




private void search() {
	
	
	
	if(txt2.getText().trim().isEmpty())
	{
		JOptionPane.showMessageDialog(frame, "Roll Number is needed to perform search");
	
	}
	
	else
	{
		
	int roll=Integer.parseInt(txt2.getText().trim());
	
	
	try
	{
	
		
		
		
	pst=con.prepareStatement("select * from sms where roll=?");
	
	pst.setInt(1,roll);
	
	ResultSet rssearch;
	rssearch = pst.executeQuery();
	
	rs=pst.executeQuery();
	
	if(rs.next())
	{
		filltextboxes();
		
	}
	
	
	
	else
		
	{
		JOptionPane.showMessageDialog(frame, roll+" is not in our table record");
		
	}
	
	}
	
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
}

}
	
}

public class SWA {

	public static void main(String[] args) {
		
		frontend1 oya=new frontend1();
		oya.firstfun();
		oya.JDBC();
	}

}
