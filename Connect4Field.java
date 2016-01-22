
/* 
 * Connect4Field.java 
 * 
 * Version: 
 *     v1 
 * 
 * Revisions: 
 *     $Log$ 
 */

import java.util.Scanner;
import java.util.Random;

/**
 * This program implements a variation to the Connect Four game where a user can play vs a 
 * computer or another player to get at least 4 consecutive symbols in any direction and 
 * once he does that he wins the game. The computer/other player blocks his game and tried to win himself.
 * 
 * @author      Amit Shah
 * 
 */

public class Connect4Field implements Connect4FieldInterface {
	
	//global Static Variables
	
	static int tempRow,tempColumn,diagonali,diagonalj,leftDiagonali,leftDiagonalj;
	static String myBoard[][]; //myBoard
	static char symbol;
	static int Horizontal=0,Vertical=0,rightDiagonal=0,leftDiagonal=0;	
	static String direction;
	
	Random rand=new Random();//to generate Random integers

	public Connect4Field(){//initializing the board in the Constructor.
		myBoard=new String[][]{	
			//{/*"0 ",*/"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26"},
			{/*"1 ",*/"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y"},
			{/*"2 ",*/"o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"},
			{/*"3 ",*/" ","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"," "},
			{/*"4 ",*/" "," ","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"," "," "},
			{/*"5 ",*/" "," "," ","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"," "," "," "},
			{/*"6 ",*/" "," "," "," ","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"," "," "," "," "},
			{/*"7 ",*/" "," "," "," "," ","o","o","o","o","o","o","o","o","o","o","o","o","o","o","o"," "," "," "," "," "},
			{/*"8 ",*/" "," "," "," "," "," ","o","o","o","o","o","o","o","o","o","o","o","o","o"," "," "," "," "," "," "},
			{/*"9 ",*/" "," "," "," "," "," "," ","o","o","o","o","o","o","o","o","o","o","o"," "," "," "," "," "," "," "},
			{/*"10",*/" "," "," "," "," "," "," "," ","o","o","o","o","o","o","o","o","o"," "," "," "," "," "," "," "," "}
		};
	}
	
	/*
	 * This method checks if piece can be dropped on the location.
	 * @param column the column to be checked if piece can be dropped.
	 * returns true or false based on if the piece can be dropped or not. 
	 */
	
	public boolean checkIfPiecedCanBeDroppedIn(int column){
		

		if(column>=0 && column<=myBoard[2].length){
			//System.out.println("entered if in can be dropped");
			for(int i=0; i<myBoard.length;i++){

				for(int j=column; j<=column;){
					if(myBoard[i][j]=="o"){
						return true;
					}
					break;
				}
			}
			System.out.println("Sorry no space in there!\n ");
		}
		else
			System.out.println(column+" is not a valid Location");

		return false;
	}	
	
/*	This method drops the piece in the column chosen by the user.
 * 	
 * 	@param column is the column where value needs to be input
 * 	@gamePiece the symbol that needs to be inserted
 * 
 */
	public void dropPieces(int column, char gamePiece){
		tempRow=-1;tempColumn=-1;	
		for(int i=0; i<myBoard.length;i++){
			for(int j=column; j<=column;j++){
				if(myBoard[i][j]=="o"){
					tempRow=i; 
					//store the row where value is being inserted in a global variable tempRow
					tempColumn=j;
					//store the column where value is being inserted in a global variable tempColumn
				}
			}
		}

		if(tempRow!=-1 && tempColumn!=-1){
			myBoard[tempRow][tempColumn]=""+gamePiece;
			System.out.println("Piece "+gamePiece+" succesfully dropped in "+ tempColumn);
		}
		display();
	}
	/*
	 * This method Displays the entire Board as in the current state
	 * 
	 */
	public void display(){

		for(int i=0; i<myBoard.length;i++){
			for(int j=0; j<myBoard[2].length;j++){
				System.out.print(myBoard[i][j]+" ");			
			}
			System.out.println();
		}
	}

	/*
	 * This method checks if the user was able to win with his last move.
	 * If he does return true, else return false
	 * 
	 * It checks whether the user is winning in any of the four directions:
	 * Both diagonals, vertical and horizontal!
	 */
	
	public boolean didLastMoveWin(){
		Horizontal=0;Vertical=0;rightDiagonal=0;leftDiagonal=0;

		//Checking if he wins Vertically. (4 symbols vertically)	
		for(int i=1; i<(myBoard.length);i++){
			for(int j=tempColumn; j<=tempColumn;j++){
				
				if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){
					if((myBoard[i][j]).charAt(0)==symbol){
						Vertical++;//maintain a vertical Counter
						//System.out.println("Vertical"+Vertical);
						if(Vertical==4){//if counter reaches 0, user wins
							return true;
						}
					}
					else{
						Vertical=0;//counter resets every time a different symbol interferes
					}
				}
			}
		}
//		int counter=0;
		
		//Checking if he wins Horizontally. (4 symbols vertically)
		for(int i=tempRow; i<=tempRow;i++){
			for(int j=0; j<(myBoard[3].length);j++){
				//System.out.println("i.."+i);
				//System.out.println("j.."+j);
				
				/*
				if((myBoard[i][j])=="o"){
					counter++;
				}*/
				
				if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){
					//System.out.println("entered if ");
					if((myBoard[i][j]).charAt(0)==symbol){
						Horizontal++;//maintain a Horizontal Counter
						//System.out.println("Horizontal"+Horizontal);
						if(Horizontal==4){//if counter reaches 0, user wins

							return true;
						}
					}
					else{//counter resets every time a different symbol interferes
						Horizontal=0;
					}
				}

			}
		}
		
		//Checking if he wins on the Right Diagonal (4 symbols on the Right Diagonal )
		
		diagonali=tempRow;
		diagonalj=tempColumn;
		
		//traversing from users current coordinates to the top right position of the board
		while(diagonali>=1 && diagonalj<myBoard[3].length-2){
			diagonali--;
			diagonalj++;
		}
		//System.out.println("rightDiagonali.."+diagonali);
		//System.out.println("rightDiagonalj.."+diagonalj);

		//starting for right to left diagonal
		for(int j=diagonalj,i=diagonali; j>0&&i<(myBoard.length);j--,i++){
			//System.out.println("rightDiagonal i="+i);
			//System.out.println("rightDiagonal j="+j);

			if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){
				if((myBoard[i][j]).charAt(0)==symbol){
					rightDiagonal++;
//					System.out.println("rightDiagonal"+rightDiagonal);
					if(rightDiagonal==4){
						return true;
					}
				}
				else{
					//System.out.println("Resetting right Diagonal");
					rightDiagonal=0;
				}
			}

		}

		//Checking if he wins on the Left Diagonal (4 symbols on the Left Diagonal )
		
		leftDiagonali=tempRow;
		leftDiagonalj=tempColumn;
		
		//traversing from users current coordinates to the top left position of the board
		
		while(leftDiagonali>=2 && leftDiagonalj>=1){
			leftDiagonali--;
			leftDiagonalj--;
		}
		//System.out.println("leftDiagonali.."+leftDiagonali);
		//System.out.println("leftDiagonalj.."+leftDiagonalj);


		//starting for left to right diagonal
		for(int i=leftDiagonali,j=leftDiagonalj; i<(myBoard.length) &&j<(myBoard[3].length);i++,j++){
			//System.out.println("increasing.. leftDiagonali.."+i);
			//System.out.println("increasing.. leftDiagonalj.."+j);

			if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){

				if((myBoard[i][j]).charAt(0)==symbol){
					leftDiagonal++;
//					System.out.println("leftDiagonal "+leftDiagonal );
					if(leftDiagonal ==4){
						return true;
					}
				}
				else{
					//System.out.println("Resetting left Diagonal");
					leftDiagonal=0;
				}
			}

		}

//		System.out.println("Horizontal: "+Horizontal);
//		System.out.println("Vertical: "+Vertical);
//		System.out.println("Right Diagonal: "+rightDiagonal);
//		System.out.println("Left Diagonal: "+leftDiagonal);

		return false;//if the move didnt win the game it reaches here and returns false.
	
	}//method didLastMoveWin ends

/*
 * This method Checks if the game is draw (The board is full) 
 * 
 */
	public boolean isItaDraw(){

		for(int i=0; i<myBoard.length;i++){
			for(int j=0; j<myBoard[2].length;j++){
				if(myBoard[i][j]=="o"){
					//System.out.println("i.."+i);
					//System.out.println("j.."+j);
					return false;
				}
			}
		}
		System.out.println("Keep Playing..");
		return true;
	}
	
/*
 * This method initializes the players, used for generating sample output 
 * 
 * @param playerA is an object of the class which implements the PlayerInterface,
 *  in this case object of Class Player
 *  
 * @param playerB is an object of the class which implements the PlayerInterface,
 *  in this case object of Class Player 
 */
	public void init( PlayerInterface playerA, PlayerInterface playerB ){
		
		//System.out.println(playerA);
		System.out.println("Welcome Player " + playerA.getName() + " Your gamePiece is "+playerA.getGamePiece());
		System.out.println("Welcome Player " + playerB.getName() + " Your gamePiece is "+playerB.getGamePiece());

	}
/*
 * Overrides the default toString Method, this method is called 
 * automatically when the Object is printed
 * 
 */
	public String toString(){
		return "Class Name:" + this.getClass().getName() + ": " +"toString() is overridden here"; 
	}

/*
 * The actual game is played in this method, 
 * The game can be played for one player as well as 2 players
 * The user and the other user/computer play one after the other to compete.
 */
	public void playTheGame(){

		Scanner scan=new Scanner(System.in);
		Connect4Field connect=new Connect4Field();
		//Connect4FieldInterface connectInterface=new Connect4Field();
		//String input="yes";
//		System.out.println(myBoard.length);
//		System.out.println(myBoard[0].length);
		System.out.println("-------------- Welcome to Connect Four --------------");
		tempRow=-1;tempColumn=-1;
		int column;
		char Player1GamePiece,Player2GamePiece;
		String Player1Name,Player2Name;

		Player2Name="";
		Player2GamePiece=0x0;

		connect.display();
		System.out.println("Choose your Game Type (1/2):");
		System.out.println("1. One Player game or\n2. Two Player game: ");
		int number=scan.nextInt();

		System.out.println("Player 1: Enter your name: ");
		Player1Name=(scan.next());
		System.out.println("Player 1: Choose your Game Piece");
		Player1GamePiece=(scan.next()).charAt(0);	

		if(number==2){//2 player Game
			System.out.println("Player 2: Enter your name: ");
			Player2Name=(scan.next());	
			System.out.println("Player 2: Choose your Game Piece");
			Player2GamePiece=(scan.next()).charAt(0);	
		}
		else{
			System.out.println("You will be playing against the computer");
		}


		Player p1=new Player(connect,Player1Name,Player1GamePiece);
		//passing the parameters to the Player Class Constructor
		Player p2=new Player(connect,Player2Name,Player2GamePiece);
		//passing the parameters to the Player Class Constructor
		
		Player Players[]={p1,p2};//creating an Array of Objects of type Player

		boolean gameIsOver=false;
		
		if(number==2){//handling a 2 Player game
			while(!gameIsOver){
				//Repeat Loop for both players until Game is Over!
				
				for(int playerNo=0;playerNo<=1;playerNo++){//Player 1 and 2 plays once each

					System.out.println("Play user:"+Players[playerNo].getName());
					//calling getName() function of Player Class
					symbol=Players[playerNo].getGamePiece();
					//calling getGamePiece() function of Player Class
					System.out.println("Symbol: "+Players[playerNo].getGamePiece());
					if(isItaDraw()){
						gameIsOver=true;
						display();
					}
					else{
						column = Players[playerNo].nextMove();
						if(checkIfPiecedCanBeDroppedIn(column)){
							dropPieces(column, Players[playerNo].getGamePiece() );
							//drop piece in selected column if there is space.
							if ( didLastMoveWin() ) {
								gameIsOver = true;
								System.out.println("The winner is: " + Players[playerNo].getName());
								break;
							}
						}
						else {
							System.out.println("The column is full");
							//Prompt user to input another column if the current column is full
							column = Players[playerNo].nextMove();
							if(checkIfPiecedCanBeDroppedIn(column)){
								dropPieces(column, Players[playerNo].getGamePiece() );
								if ( didLastMoveWin() ) {
									//check if his move made him win
									gameIsOver = true;
									System.out.println("The winner is: " + Players[playerNo].getName());
									break;
								}
							}
							else System.out.println("Sorry you are too stupid to play this game");
						}
					}
				}
			}
		}

		if(number==1){//Handle the game vs Computer
			
			while(!gameIsOver){
			
				System.out.println("Play user:"+Players[0].getName());
				symbol=Players[0].getGamePiece();
				System.out.println("Symbol: "+Players[0].getGamePiece());
				if(isItaDraw()){
					gameIsOver=true;
					display();
				}
				else{
					//first the user starts playing 
					
					column = Players[0].nextMove();
					if(checkIfPiecedCanBeDroppedIn(column)){
						dropPieces(column, Players[0].getGamePiece() );
						if ( didLastMoveWin() ) {
							gameIsOver = true;
							System.out.println("You Won!");
							break;
						}
					}
					else{
						//System.out.println("The column is full sorry");
						column = Players[0].nextMove();
						if(checkIfPiecedCanBeDroppedIn(column)){
							dropPieces(column, Players[0].getGamePiece() );
							if ( didLastMoveWin() ) {
								gameIsOver = true;
								System.out.println("You Won!");
								break;
							}
						}
						else {
							System.out.println("Sorry you are too stupid to play this game");
							break;
						}
					}
				}

				//computer Calculates his move basis on users current move
				
				column=calculateColumn();
				//This function returns the column where computer should play
				symbol='*';
				if(isItaDraw()){
					gameIsOver=true;
					display();
				}
				else{
					if(checkIfPiecedCanBeDroppedIn(column)){					
						System.out.println("The Computer Plays: ");
						dropPieces(column, '*');
						if ( didLastMoveWin() ) {
							gameIsOver = true;
							System.out.println("The computer Wins!");
						}
					}
					else{//if the piece cannot be dropped chose random location 
						column=rand.nextInt(24);
						
						if(checkIfPiecedCanBeDroppedIn(column)){					
							System.out.println("The Computer Plays: ");
							dropPieces(column, '*');
							if ( didLastMoveWin() ) {
								gameIsOver = true;
								System.out.println("The computer Wins!");
							}
						}
						else System.out.println("Computer is out of moves");
					}
				}

			}

		}




	}//end of playTheGame()

	int calculateColumn(){
		
		//main method to calculate the computer's Location to play
		
		int score=0,max=0;
		int scani,scanj;
		
		//System.out.println("Horizontal: "+Horizontal);
		//System.out.println("Vertical: "+Vertical);
		//System.out.println("Right Diagonal: "+rightDiagonal);
		//System.out.println("Left Diagonal: "+leftDiagonal);


		/*Horizontal=0;Vertical=0;rightDiagonal=0;leftDiagonal=0;	
		int score=0,max=0;
		int scani,scanj;
		///handling vertical
		for(int i=1; i<(myBoard.length);i++){
			for(int j=tempColumn; j<=tempColumn;j++){

				if((myBoard[i][j]).charAt(0)==symbol){
					Vertical++;
					//System.out.println("Vertical"+Vertical);

				}
				else{
					Vertical=0;}

			}
		}

		//handling horizontal
		for(int i=tempRow; i<=tempRow;i++){
			for(int j=0; j<(myBoard[3].length);j++){


				if((myBoard[i][j]).charAt(0)==symbol){
					Horizontal++;
					//System.out.println("Horizontal"+Horizontal);
				}
				else{
					Horizontal=0;
				}

			}
		}

		//starting for right to left diagonal
		for(int j=(myBoard[3].length-1),i=1; j>0&&i<(myBoard.length);j--,i++){
			//System.out.println("rightDiagonal i="+i);
			//System.out.println("rightDiagonal j="+j);


			if((myBoard[i][j]).charAt(0)==symbol){
				rightDiagonal++;
				//System.out.println("rightDiagonal"+rightDiagonal);
			}
			else{
				rightDiagonal=0;
			}

		}

		//starting for left to right diagonal
		for(int i=1,j=1; i<(myBoard.length) &&j<(myBoard[3].length);i++,j++){


			if((myBoard[i][j]).charAt(0)==symbol){
				leftDiagonal++;
				//System.out.println("leftDiagonal "+leftDiagonal );
			}
			else{
				leftDiagonal=0;
			}

		}
		 */
		
		//Determine in which direction the user has the maximum chance of winning.
		
		//Use the variables that were used to calculate if the user wins!
		
		max=Vertical; 
		direction="Vertical";

		if(Horizontal>max){
			max=Horizontal;
			direction="Horizontal";
//			System.out.println("considering Horizontal");
		}
		if(rightDiagonal>max){
			max=rightDiagonal;
			direction="rightDiagonal";
//			System.out.println("considering right Diagonal");
		}
		if(leftDiagonal>max){
			max=leftDiagonal;
			direction="leftDiagonal";
//			System.out.println("considering left Diagonal");
		}

		if(direction=="Vertical")
//			System.out.println("considering Vertical");

		//		if(max==0){
		//			return rand.nextInt(24);
		//		}
		
		if(max==1){
			score=1;
		}
		
		else if(max==2){
			score=10;
		}
		
		else if(max==3){
			score=100;
		}

		if(direction=="Vertical"){
			//System.out.println("returning "+tempColumn+" within vertical");
			return tempColumn;
		}
		
		// Check if the Horizontal 
		
		else if(direction=="Horizontal"){
			//System.out.println("TempRow.."+tempRow);
			for (int i=tempRow;i<myBoard.length;i++){
				for(int j=0; j<(myBoard[3].length);j++){
					if((myBoard[i][j]).charAt(0)==symbol){
						scani=i;scanj=j;
						//System.out.println("Found location "+scanj);
						for (int a=tempRow;a<=tempRow;a++){
							//System.out.println("Entering first for");
							for(int b=scanj; b<(myBoard[3].length);b++){
								if((myBoard[a][b])=="o"){
									//System.out.println("returning "+b+" within Horizontal");
									return b;
									//break;
								}

							}
						}
					}
				}
				//System.out.println("Horizontal"+Horizontal);
			}
		}
		//if Right diagonal has the maximum user symbols
		//find the first empty space in the right diagonal and push value inside
		else if (direction=="rightDiagonal"){
			for(int j=diagonalj,i=diagonali; j>0&&i<(myBoard.length-1);j--,i++){

				if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){
					if((myBoard[i][j]).charAt(0)==symbol){
						if((myBoard[i][j+1]).charAt(0)==symbol){
							return j+1;
						}
						else if((myBoard[i-1][j+1]).charAt(0)==symbol){
							return j+1;
						}
						else return j; 
					}
				}
			}
		}

		//if left diagonal has the maximum user symbols
		//find the first empty space in the left diagonal and push value inside
		else if (direction=="leftDiagonal"){
			for(int j=leftDiagonalj,i=leftDiagonali; j<(myBoard[3].length)&&i<(myBoard.length);j++,i++){

				if((myBoard[i][j])!="o" && myBoard[i][j]!=" "){
					if((myBoard[i][j]).charAt(0)==symbol){
						if((myBoard[i+1][j+1]).charAt(0)==symbol){
							return j-1;
						}
						else if((myBoard[i-1][j-1]).charAt(0)==symbol){
							return j-2;
						}
						else return j+1; 
					}
				}
			}
		}

		return rand.nextInt(myBoard[3].length-1);
		//put computer input at a random location if all else fails
	}//CalculateColumn ends






	public static void main(String args[]){
		Connect4Field connect4=new Connect4Field();
		connect4.playTheGame();//the entire Game is called within this function
	}//main ends

}//class Connect4Field ends