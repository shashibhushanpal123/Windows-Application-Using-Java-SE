package mainhai;
import GUI.userinterface;
import Database.DatabaseObj;

public class mainfunc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		userinterface ui=new userinterface();//test it whether running or not by making object of it
		ui.CreateEnterface();
		
		DatabaseObj db=new DatabaseObj();//test it whether running or not by making object of it
		db.Connection();
	}

}
