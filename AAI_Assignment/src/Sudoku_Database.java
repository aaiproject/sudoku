import java.io.File;
import java.sql.*;

/**
 * Creates a connection with the database using an external class called org.sqlite.JDBC
 * 
 */
public class Sudoku_Database {
	//creates a connection instance
	static Connection connect = null;
	
	public static Connection tableDB(){
		try{
			//the class "org.sqlite.JDBC" is dynamically loaded at runtime
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection("jdbc:sqlite:src/Database.sqlite");
			
			/*String create_table = "create table \"Table1\" (\n`1` INTERGER ,\n`2` INTEGER,\n`3` INTEGER,\n`4` INTEGER)";
			//String personTableQuery = "create table \"Person\" (\n`Person_ID` INTEGER PRIMARY KEY,\n`Person_Name` TEXT,\n`Mother_ID` INTEGER,\n`Father_ID` INTEGER,\n`Genome` TEXT,\n`Reference` TEXT\n)";
			String insert_table = "INSERT INTO TABLE1 VALUES(4,5,3,5) "; 
			String insert_table1 = "INSERT INTO TABLE1 VALUES(5,11,3,57) "; 
			String insert_table2= "INSERT INTO TABLE1 VALUES(8,0,5,7) "; 
			String insert_table3 = "INSERT INTO TABLE1 VALUES(10,14,3,2) "; 
			Statement state = connect.createStatement();
			state.execute(create_table);
			state.execute(insert_table);
			state.execute(insert_table1);
			state.execute(insert_table2);
			state.execute(insert_table3);
			state.close();*/
			return connect;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	

}
