import java.awt.*;        
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;     
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
 

public class Sudoku extends JFrame {

   static int n = 9;    
   int SubSize = n/3; 
 
   int CellSize = 60;   
   int Width  = CellSize * n;
   int Height = CellSize * n;
 
   Color TextColour = Color.BLACK;
   static Font font = new Font("Monospaced", Font.BOLD, 20);
 
   private JTextField[][] board = new JTextField[n][n];
   private JTextField[][] Mainboard = new JTextField[n][n];

   private static int[][] puzzle =new int[n][n];/* =
	   	   {{0, 6, 0, 1, 0, 4, 0, 5, 0},
            {0, 0, 8, 3, 0, 5, 6, 0, 0},
            {2, 0, 0, 0, 0, 0, 0, 0, 1},
            {8, 0, 0, 4, 0, 7, 0, 0, 6},
            {0, 0, 6, 0, 0, 0, 3, 0, 0},
            {7, 0, 0, 9, 0, 1, 0, 0, 4},
            {5, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 7, 2, 0, 6, 9, 0, 0},
            {0, 4, 0, 5, 0, 8, 0, 7, 0}};*/
 

   public Sudoku(boolean check, JFrame Mainframe) {
	 
      Container cp = getContentPane();
      cp.setLayout(new GridLayout(SubSize, SubSize));  
     
      JPanel panel0 = new JPanel();
      JPanel panel1 = new JPanel();
      JPanel panel2 = new JPanel();
      JPanel panel3 = new JPanel();
      JPanel panel4 = new JPanel();
      JPanel panel5 = new JPanel();
      JPanel panel6 = new JPanel();
      JPanel panel7 = new JPanel();
      JPanel panel8 = new JPanel();
      
      int StartI,StartJ;
   
      JPanel[] panel = {panel0,panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8};
      for(int k=0;k<n;k++){
    	  
    	  cp.add(panel[k]);
    	  panel[k].setLayout(new GridLayout(SubSize,SubSize));
    	  panel[k].setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    	  
    	  //Check Current SubGrid
    	  if(k == 0 || k == 1 || k ==2){
    		  StartI =0;
    	  }
    	  else if(k == 3 || k == 4 || k ==5){
    		  StartI =3;
    	  }
    	  else{
    		  StartI =6;
    	  }
    	  
    	  //Check Current SubGrid
    	  if(k == 0 || k == 3 || k ==6){
    		  StartJ =0;
    	  }
    	  else if(k == 1 || k == 4 || k ==7){
    		  StartJ =3;
    	  }
    	  else{
    		  StartJ =6;
    	  }
    	  
    	  for (int i = 0; i < SubSize; i++) {
    		  int temp =StartJ;
    		  for (int j = 0; j < SubSize; j++) {
    			  board[i][j] = new JTextField();
    			  Mainboard[StartI][StartJ] = board[i][j];
    			  
    			  panel[k].add(board[i][j]); 
    			  if (check == false){
    				  board[i][j].setText("");    
    				  board[i][j].setEditable(true);
    				  board[i][j].setBackground(Color.CYAN);
    			  }
    			  else if (puzzle[StartI][StartJ] == 0) {
    				  board[i][j].setText("");    
    				  board[i][j].setEditable(false);
    				  board[i][j].setBackground(Color.CYAN);
    			  } else {
    				  board[i][j].setText(puzzle[StartI][StartJ] + "");
    				  board[i][j].setEditable(false);
    				  board[i][j].setForeground(TextColour);
    				  board[i][j].setBackground(Color.CYAN);
    			  }
    			  board[i][j].setHorizontalAlignment(JTextField.CENTER);
    			  board[i][j].setFont(font);
    			  
    			  StartJ++;
    		 }
    		 StartJ = temp;
    		 StartI++;
    	  }
      }
    
      cp.setPreferredSize(new Dimension(Width, Height));

      ((JComponent)cp).setBorder(BorderFactory.createMatteBorder(2,2, 2, 2,Color.BLACK));
      pack();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      setTitle("Sudoku");


      JMenuBar menuBar = new JMenuBar();
      Font f = new Font("sans-serif", Font.PLAIN, 20);
      UIManager.put("Menu.font", f);

      
      JMenu NewMenu = new JMenu("New Puzzle");
      //NewMenu.setMnemonic(KeyEvent.VK_F);
      menuBar.add(NewMenu);

      JMenu SolveMenu = new JMenu("Solve");
      menuBar.add(SolveMenu);
      
      JMenu MainMenu = new JMenu("Return to Main Menu");
      menuBar.add(MainMenu);
      
      SolveMenu.addMenuListener(new MenuListener() {
  		
  		@Override
  		public void menuSelected(MenuEvent e) {
  			// TODO Auto-generated method stub
  			
  			//If user manu
  			if(check == false){
  				for(int i =0; i<n;i++){
  	  				for(int j =0; j<n;j++){
  	  					if(Mainboard[i][j].getText().equals("")){
  	  						puzzle[i][j] = 0;
  	  					} 
  	  					else{
  	  						//System.out.print(Mainboard[i][j].getText());
  	  						puzzle[i][j] = Integer.parseInt(Mainboard[i][j].getText());
  	  					}
  	  				}
  	  			}
  			}
  			

  			
  			
  			
  			System.out.println("Original puzzle");
  			for(int i =0; i<9;i++){
  				for(int j =0; j<9;j++){
  					System.out.print(puzzle[i][j]+ " ");
  				}
  				System.out.println();
  			}
  			SolvePuzzle sol = new SolvePuzzle(puzzle);
  			try {
  				sol.solve(0, 0);
  			} catch (Exception e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}  			
  			System.out.println("Solved Puzzle");
  			
  			for(int i =0; i<9;i++){
  				for(int j =0; j<9;j++){
  					System.out.print(sol.solve_puzzle[i][j]+ " ");
  				}
  				System.out.println();
  			}
  			//board[2][3].setText("A");
  			for(int i =0; i<9;i++){
  				for(int j =0; j<9;j++){
  					Mainboard[i][j].setText(Integer.toString(sol.solve_puzzle[i][j]));
  				}
  			}

  			SolveMenu.setSelected(false);
  			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			robot.keyPress(KeyEvent.VK_ESCAPE);
  			
  
  		}
  		
  		@Override
  		public void menuDeselected(MenuEvent e) {
  			// TODO Auto-generated method stub
  			menuBar.setSelected(panel8);
  			
  		}
  		
  		@Override
  		public void menuCanceled(MenuEvent e) {
  			// TODO Auto-generated method stub
  			
  		}
        });
           
      
      
      
      
      MainMenu.addMenuListener(new MenuListener() {
		
		@Override
		public void menuSelected(MenuEvent e) {
			// TODO Auto-generated method stub
			 Mainframe.setVisible(true);
			 dispose();
		}
		
		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub
			
		}
      });
      
	  
      setJMenuBar(menuBar);
      setVisible(true);
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
   }
   
   public static void Analysis(String Difficulty, JTextArea Area ){
	   	int data[];
	   	long total =0;
	   	
	   	//import data
	   	if(!Difficulty.equals("All")){
	   		data = new int[10];
		   	Area.setText("Graph of analysis on "+Difficulty+ " Puzzles\n"+"Table Number \t Nano Time \n");
		   	for(int i = 0; i<10;i++){
		   		Import_Table tbl = new Import_Table("Table"+Difficulty+Integer.toString(i+1));
		   		puzzle = tbl.getTable();
		   		SolvePuzzle sol = new SolvePuzzle(puzzle);

		   		double StartTime = System.nanoTime();
		   		sol.solve(0, 0);
				double EndTime = System.nanoTime();
				data[i] = (int) (EndTime -StartTime);
				System.out.print(",");
				System.out.print(EndTime -StartTime);
				long time =(long) (EndTime -StartTime);
				Area.setText(Area.getText()+"Table:"+i+"\t \t"+time+"\n");
			
		   	}
	   	}
	   	else{
		   	Area.setText("Graph of analysis on all difficulty puzzles\n"+"Puzzle: \t Nano Time: \n");
	   		data = new int[4];
	   		for(int i=0;i<4;i++){
	   			if(i==0) Difficulty ="Easy";
	   			if(i==1) Difficulty ="Medium";
	   			if(i==2) Difficulty ="Difficult";
	   			if(i==3) Difficulty ="SuperDifficult";
	   			for(int j=0;j<10;j++){
	   				Import_Table tbl = new Import_Table("Table"+Difficulty+Integer.toString(j+1));
	   				puzzle = tbl.getTable();
	   				SolvePuzzle sol = new SolvePuzzle(puzzle);

	   				double StartTime = System.nanoTime();
	   				sol.solve(0, 0);
	   				double EndTime = System.nanoTime();
	   				
	   				long time = (long) (EndTime -StartTime);
	   				
	   				total = total +time;
	   				System.out.print(","+time);
	   				
	   			}
	   			System.out.println("");
	   			long num = total/10;
	   			data[i] = (int) num;
	   			Area.setText(Area.getText()+Difficulty+""+"\t "+num+"\n");
	   			total=0;
	   			Difficulty ="All";
	   		}
	   	}
	   	
		System.out.println("");
		
		//Create frame
		JFrame Graph = new JFrame("Graph of analysis on "+Difficulty+ " puzzles");
		Graph.add(new GraphingData(data));
		Graph.setSize(400,400);
		Graph.setLocation(200,200);
		Graph.setVisible(true);
   } 
   

   public static void main(String[] args) {

	   
	   JFrame Startframe = new JFrame();
	   Startframe.setSize(600, 400);
	   Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	   Startframe.setLocation(dim.width/2-Startframe.getSize().width/2, dim.height/2-Startframe.getSize().height/2);
	   
	   JButton btnInput = new JButton("Manually insert sudoku grid");
	   JButton btnRandom = new JButton("Random generated sudoku grid");
	   JButton btnAnalysis = new JButton("Generate Analysis");
	   JLabel lblSudoku = new JLabel("Sudoku");
	  
	   btnInput.setBounds(190, 140, 230,50 );
	   btnRandom.setBounds(190, 220, 230,50);
	   btnAnalysis.setBounds(190, 300, 230,50);
	   lblSudoku.setBounds(230, 50, 200,50 );
	   lblSudoku.setFont(new Font("Serif", Font.BOLD, 40));
	   
	   btnRandom.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int randomNum = rand.nextInt((10 -1) + 1) + 1;
				Import_Table tbl = new Import_Table("TableMedium"+Integer.toString(randomNum));
				puzzle = tbl.getTable();
				JFrame frameRandom = new Sudoku(true,Startframe);
			    frameRandom.setSize(600, 600);
			    Startframe.setVisible(false);
			}
	   });
	   
	   btnInput.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFrame frameInput = new Sudoku(false,Startframe);
			    frameInput.setSize(600, 600);
			    Startframe.setVisible(false);
			}
	   });
	   
	   btnAnalysis.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Startframe.setVisible(false);
				JFrame AnalysisFrame = new JFrame("Sudoku Analysis");
				
				JTextArea Display = new JTextArea();
				Display.setFont(font);
			    Display.setBounds(50, 50, 600, 500);
			    Display.setBackground(null);
			   //Display.setEnabled(false);
				
				JMenuBar menuBar1 = new JMenuBar();
			    Font f = new Font("sans-serif", Font.PLAIN, 20);
			    UIManager.put("Menu.font", f);
  
			    JMenu AnalysisMenu = new JMenu("Perform Analysis");
			    menuBar1.add(AnalysisMenu);
			    
			    JMenu MainMenu = new JMenu("Return to Main Menu");
			    menuBar1.add(MainMenu);
			    
			    JMenuItem EasyItem= new JMenuItem(new AbstractAction("Analysis on Easy Puzzle") {
			        public void actionPerformed(ActionEvent e) {
			            Analysis("Easy",Display);
			        }
			    });
			    EasyItem.setFont(new Font("sans-serif", Font.PLAIN, 18));
			    
			    JMenuItem MediumItem = new JMenuItem(new AbstractAction("Analysis on Medium Puzzle") {
			        public void actionPerformed(ActionEvent e) {
			        	Analysis("Medium",Display);
			        }
			    });
			    MediumItem.setFont(new Font("sans-serif", Font.PLAIN, 18));
			    
			    JMenuItem DifficultItem = new JMenuItem(new AbstractAction("Analysis on Difficult Puzzle") {
			        public void actionPerformed(ActionEvent e) {
			        	Analysis("Difficult",Display);
			        }
			    });
			    DifficultItem.setFont(new Font("sans-serif", Font.PLAIN, 18));
			    
			    JMenuItem SuperDifficultItem = new JMenuItem(new AbstractAction("Analysis on Super Difficult Puzzle") {
			        public void actionPerformed(ActionEvent e) {
			        	Analysis("SuperDifficult",Display);
			        }
			    });
			    SuperDifficultItem.setFont(new Font("sans-serif", Font.PLAIN, 18));

			    JMenuItem CompareItem= new JMenuItem(new AbstractAction("Comparison of all Puzzles") {
			        public void actionPerformed(ActionEvent e) {
			        	Analysis("All",Display);
			        }
			    });
			    CompareItem.setFont(new Font("sans-serif", Font.PLAIN, 18));
			    
			    AnalysisMenu.add(EasyItem);
			    AnalysisMenu.add(MediumItem);
			    AnalysisMenu.add(DifficultItem);
			    AnalysisMenu.add(SuperDifficultItem);
			    AnalysisMenu.add(CompareItem);
			    
			    MainMenu.addMenuListener(new MenuListener() {
					
					@Override
					public void menuSelected(MenuEvent e) {
						// TODO Auto-generated method stub
						 Startframe.setVisible(true);
						 AnalysisFrame.dispose();
					}
					
					@Override
					public void menuDeselected(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void menuCanceled(MenuEvent e) {
						// TODO Auto-generated method stub
						
					}
			      });
			    
			    
			    
			    AnalysisFrame.getContentPane().setLayout(null);
			    AnalysisFrame.setJMenuBar(menuBar1);
			    AnalysisFrame.getContentPane().add(Display);
			    //AnalysisFrame.pack();
			    AnalysisFrame.setSize(600, 600);
			    AnalysisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			    AnalysisFrame.setVisible(true);
			    
			
				
			}
	   });
	   
	   Startframe.setLayout(null);
	   Startframe.getContentPane().add(btnInput);
	   Startframe.getContentPane().add(btnRandom);
	   Startframe.getContentPane().add(btnAnalysis);
	   Startframe.getContentPane().add(lblSudoku);
	   Startframe.setVisible(true);
	   
       
   }
   
}