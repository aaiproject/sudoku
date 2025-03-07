import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Import_Table {
	private String Tablename;
	private int[][] table;
	
	public Import_Table(String TableName) {
		this.Tablename = TableName;
		Connection conn = Sudoku_Database.tableDB();
		String query = "select * from "+Tablename;
		PreparedStatement pst;
		try {
			pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			int nCol = rs.getMetaData().getColumnCount();
			
			//int rows = rs.getMetaData().getr
			/*System.out.println("rows: "+rows+" coln: "+ nCol);
			int[][] table = new int[rows][nCol];
			int  i=0;
			while( (rs.next())&&(i<rows)) {
				
			    int[] row = new int[nCol];
			    for( int iCol = 1; iCol <= nCol; iCol++ ){
			        row[iCol-1] = Integer.parseInt(rs.getObject( iCol ).toString());
			        System.out.println(row[iCol-1]);
			    }
			    table[i]= row ;
			    i++;
			}*/
		    //int nCol = result.getMetaData().getColumnCount();
		    ArrayList<String[]> tble = new ArrayList<>();
		    while( rs.next()) {
		        String[] row = new String[nCol];
		        for( int iCol = 1; iCol <= nCol; iCol++ ){
		            row[iCol-1] = (rs.getObject( iCol ) != null) ? rs.getObject( iCol ).toString(): "0";
		        }
		        tble.add( row );
		    }
		    int[][] table = new int[tble.size()][nCol];
		    for (int i =0 ; i<tble.size(); i++){
		    	for (int j =0; j<nCol; j++){
		    		//if (tble.get(i)[j].equals(" ")){
		    			//table[i][j] = 0;
		    		//} else {
		    			table[i][j] = Integer.parseInt(tble.get(i)[j]);
		    		//}
		    	}
		    }
			this.table = table;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
	public int[][] getTable(){
		return this.table;
	}
	public void DisplayTable(){
		for(int i =0; i<table[1].length; i++){
			for (int j=0 ; j<table[1].length; j++){
				System.out.print(table[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
