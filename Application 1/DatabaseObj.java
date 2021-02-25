package Database;

import java.sql.ResultSet;

import java.sql.*;

public class DatabaseObj {

	public void Connection()
	
	{
		
	//JDBC BEGINS
	//STEP 1-> LOAD THE DRIVER.
	try
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");//Here we give reference of package eg->"com.mysql.jdbc.Driver");
	}
	catch(ClassNotFoundException e)//if bytecode not found
	{
		e.printStackTrace();
	}
	
	//STEP 2->Establish Connectivity between front end in swing and backend in mysql/oracle..
	//Here we need to create object of Connection Class.
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@:1521","system","q1w2e3r4")//get connection  is factory method.


 //Step 3->
			String msg="select * from one";//string for java but query for mysql.

	//handover that above message to 
	
	//Step 4-> We need an object to transfer msg from front end to back end.
	//Object of statement class->
	
	stat=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet_UPDATABLE);//createStatement is Factory method of connection class statement which makes object of statement class.takes message and takes table from mysql to java.
	
	//After this RS is now updatable.
	
	//now we need to make variable to store table location in java

 //Step 5->Fire the query and store the table in return.
	
	rs=stat.executeQuery(msg);//Result set is a class represent actual physical table of mysql hence called Virtual table. Backend se table is placed in Result set.
	//By default rs points to column name.
	
	rs.next();//pointer moves to next row.
	//Step 6->showing contents of table into text boxes.

	//After that we comment this rs.next(); when not dealing with SOP.
	
    System.out.println(rs.getIn("carid"));//car id column se value fetch and show on command prompt.
    System.out.println(rs.getStgring("carname"));
    System.out.println(rs.getDouble("carprice"));
    
	  //Now instead of showing on command prompt we display value in text boxes.
    
	 //SO commenting that SOP statements.
    
    //txtf1.getText(rs.getIn(11));//Java takes string and show string
	
//    txtf1.getText(IntegertoString(rs.getIn(1))); Instead of writing this code again and again we make function filltextbox() to reuse it.
    
  //  txtf2.getText(rs.getIn(2);
    
    //txtf3.getText(DoubletoString(rs.getIn(3)));
}





public void filltextbox()
{
		txtf1.getText(IntegertoString(rs.getIn(1)));
	    
	    txtf2.getText(rs.getIn(2));
	    
	    txtf3.getText(Double toString(rs.getIn(3)));
		
	
  	
}