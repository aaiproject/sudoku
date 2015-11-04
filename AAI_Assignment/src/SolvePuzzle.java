
public class SolvePuzzle {
	int[][] puzzle;
	int[][] solve_puzzle = new int[9][9];
	int N;
	public SolvePuzzle(int[][] puzzle) {
		super();
		this.puzzle = puzzle;

		N = puzzle.length;

	}
	
	private boolean checkRow(int row,int num){
		boolean isvalid=true;
		for(int i=0; i<N; i++){
			if(num==puzzle[row][i]){
				isvalid = false;
			}
		}
		
		return isvalid;
		
	}
	private boolean checkCol(int col, int num){
		boolean isvalid=true;
		for(int i=0; i<N; i++){
			if(num==puzzle[i][col]){
				isvalid = false;
			}
		}
		
		return isvalid;
		
	}
	private boolean checkGrid(int row, int col,int num){
		/*boolean isvalid=true;
		row = row%3;
		col = col%3;
		int start_row=row, start_col=col;
		if(row%3==1){
			start_row = row-1;
		} else {
			if(row%3==2){
				start_row = row-2;
			}
		}
		if(col%3==1){
			start_col = col-1;
		} else {
			if(col%3==2){
				start_col = col-2;
			}
		}
		
		for(int i=0; i<2; i++){
			for(int j =0; j<2;j++){
				if(num==puzzle[start_row+i][start_col+j]){
					isvalid = false;
				}
			}
		}
		
		return isvalid;*/
		 row = (row / 3) * 3 ;
	     col = (col / 3) * 3 ;

	      for( int r = 0; r < 3; r++ )
	         for( int c = 0; c < 3; c++ )
	         if( puzzle[row+r][col+c] == num )
	            return false ;

	      return true ;
	}
	
	public void solve( int row, int col ) {

		if (row>8){
			//solution found

			PrintGrid();
			//solve_puzzle = puzzle;

		
		}else{
			if(puzzle[row][col]!=0){
				//dont need to work out solution for filled cell
				//get the next vaule 
				next(row,col);
			}else {
				//work out soln
				
				//only valid numbers are 1 to 9 
				for(int i=1; i<10; i++){
					if(checkRow(row,i)&&checkCol(col,i)&&checkGrid(row,col,i)){
						//this is a valid number 
						puzzle[row][col] = i;
						//have to move to the next cell
						next(row,col);
					}
				}
				puzzle[row][col] = 0 ;
			}
		}
	}
	public void next( int row, int col ) 
	   {
	      if( col < 8 )
	         solve( row, col + 1 ) ;
	      else
	         solve( row + 1, 0 ) ;
	   }
	public void PrintGrid(){
		for(int i=0; i<N; i++){
			for(int j =0; j<N; j++){
				//System.out.print(puzzle[i][j]+ " ");
				solve_puzzle[i][j] = puzzle[i][j];
			}
			//System.out.println();
		}
		//solve_puzzle = puzzle;
	}
	
}
