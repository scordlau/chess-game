package chessGame;

import java.util.ArrayList;

public class ChessMonitoringSystem {

	private static ArrayList<ChessPiece> allChessPieces;
	private static ChessMonitoringSystem instance;
	private GameMode mode;

	private ChessMonitoringSystem() {}

	//for JUnit testing, may consider for setting up chess problem
	public void setAllChessPieces(ArrayList<ChessPiece> allChessPieces) {
		ChessMonitoringSystem.allChessPieces = allChessPieces;
	}
	
	public static ChessMonitoringSystem getInstance() {
		if (instance==null)
			instance = new ChessMonitoringSystem();
		
		return instance; 
	}
	
	public void initializeChessBoard() {
	
	}

	public void initializeChessPieces(ChessPlayer player1, ChessPlayer player2) {
		allChessPieces=new ArrayList<>();
		ChessPlayer[] chessPlayerList = {player1, player2};
		
		int allChessYPos = 1;
		int pawnYPos = 2;
		
		for (int i = 0; i<2; i++)
		{
			allChessPieces.add(new Rook(chessPlayerList[i], "a"+allChessYPos));
			allChessPieces.add(new Knight(chessPlayerList[i], "b"+allChessYPos));
			allChessPieces.add(new Bishop(chessPlayerList[i], "c"+allChessYPos));
			allChessPieces.add(new King(chessPlayerList[i], "d"+allChessYPos));
			allChessPieces.add(new Queen(chessPlayerList[i], "e"+allChessYPos));
			allChessPieces.add(new Bishop(chessPlayerList[i], "f"+allChessYPos));
			allChessPieces.add(new Knight(chessPlayerList[i], "g"+allChessYPos));
			allChessPieces.add(new Rook(chessPlayerList[i], "h"+allChessYPos));
			
			//8 pawns		
			int xPosCharCode = 97;
			for (int j = 0; j<8; j++)
			{
				String pawnXPos= ""+(char)xPosCharCode; //x-coordinate of pawns' position
				allChessPieces.add(new Pawn(chessPlayerList[i], pawnXPos+pawnYPos));
				xPosCharCode++;
			}
			
			pawnYPos = 7; //inverted y-coordinate for Pawns
			allChessYPos = 8; //invert y-coordinate for oponent's chess pieces
		}
	}

	public void showAllChessPiecesPosition() {
		String [][] chessPiecesPosition = new String[8][8]; //virtual chess board
		String tempPos = null;
		int xPosInNum = 0;
		int yPosInNum = 0;
		
		//initialize chess board
		for (int i = 0; i<8; i++)
			for (int j = 0; j<8; j++)
				chessPiecesPosition[i][j] = "O"; //or "\u25A1", a white square
		
		for (ChessPiece c: allChessPieces)
		{
			tempPos = c.getPosition();
			if (tempPos==null)
				chessPiecesPosition[yPosInNum-1][xPosInNum-1]="O";
			else
			{
				xPosInNum = tempPos.charAt(0)-96;
				yPosInNum = tempPos.charAt(1)-48;	
			}
			chessPiecesPosition[yPosInNum-1][xPosInNum-1] = c.toString(); //a1 on chess board->[0][0] in array
		}
		
		System.out.println(String.format("%3s%2s%2s%2s%2s%2s%2s%2s","a", "b", "c", "d", "e", "f", "g", "h"));

		for (int i = 0; i<8; i++)
		{
			System.out.print((8-i)+" ");
			for (int j = 0; j<8; j++)
			{
				System.out.print(chessPiecesPosition[8-i-1][j]+" "); //yPos: 8,7,6,...->7,6,5,...  
																		//xPos: a,b,c,...->0,1,2,...
			}
			System.out.println(8-i);
		}
		
		System.out.println(String.format("%3s%2s%2s%2s%2s%2s%2s%2s","a", "b", "c", "d", "e", "f", "g", "h"));
	}

	public boolean moveChessPiece(ChessPlayer player,String oldPos,String newPos) {
		ChessPiece movingChess = getChessPiece(oldPos);
		/*ERROR-CATCHING : MAY CHANGE TO TRY-CATCH CLAUSE*/
		/*if(movingChess == null){
			System.out.println("Chesspiece not found!");
			return false;
		}
		else if(movingChess.getPlayer()!=player){
      			System.out.println("Selected piece does not belong to you!");
      			return false;
		}
		else if(movingChess.isValidMove(newPos)) {
			boolean move=move(player, newPos, movingChess);
			return move;
		}
        else{
          		System.out.println("The move is invalid.");
          		return false;
        }*/
		if (movingChess == null){
			System.out.println("Chesspiece not found!");
			return false;
		}
		if(movingChess.isValidMove(newPos)){
			boolean move=move(player, newPos, movingChess);
			return move;
		}
		else {
			if (movingChess.getPlayer()!=player)
				System.out.println("Selected piece does not belong to you!");
			else
				System.out.println("The move is invalid.");
      		return false;
		}
	}

	private boolean move(ChessPlayer player, String newPos, ChessPiece movingChess) {
		if(getChessPiece(newPos)!=null) {
			if (getChessPiece(newPos).getPlayer()!=player) //capturing enemy
			{
				int rank = compareScore(movingChess, getChessPiece(newPos));
				int[] score={getChessPiece(newPos).getScore(), rank};
				mode.addScore(player, score);
				removeChessPiece(newPos);
			}
			else //capturing own chess
			{
		  		System.out.println("You cannot capture own pieceÂ¡I");
		  		return false;
			}
		}
		movingChess.updatePosition(newPos);
		return true;
	}

	private int compareScore(ChessPiece move, ChessPiece old){
		if (move.getScore() > old.getScore())
			return 0;
		else if (old.getScore() > move.getScore())
			return 1;
		else
			return 2;
	}
	
	public ChessPiece getChessPiece(String position){
		String tempChessPos = null;
		for (ChessPiece c: allChessPieces)
		{
			tempChessPos = c.getPosition();
			if (tempChessPos.equals(position))
				return c;
		}
		return null;
	}
	
	
	
	/*Stub
	private boolean isChessPieceCaptured(ChessPiece origin,String position) {
		// TODO - implement ChessMonitoringSystem.chessPieceIsCaptured
		ChessPiece target = getChessPiece(position);
		
		if(origin.getPlayer()!=target.getPlayer())
			return true;
		else
			return false;
	}
	*/

	private void removeChessPiece(String position) {
		ChessPiece target = getChessPiece(position);
		//target.updatePosition(null);	
		allChessPieces.remove(target); //IF USE ARRAYLIST
	}

	public boolean endGame() {
		return this.mode.isEndGame();
	}

	public void getResult(ChessPlayer player1, ChessPlayer player2) {
		//player.getPlayerScore()
		//player.getPlayerName()
		System.out.println(mode.printResult(player1, player2));
	}

	public void startGame(int mode) {
		if (mode == 1){
        	this.mode = new ClassicMode();
        }
        else if (mode == 2){
        	this.mode = new ScoringMode();
        }
        else if (mode ==3 ){
        	this.mode = new RankScoringMode();
        }
	}

	public ChessPlayer isKingCaptured() {
		ChessPlayer winner = null;
		int counter=0;
		for (ChessPiece c: allChessPieces){
			if (c instanceof King){
				winner = c.getPlayer();
				counter++;
			}
		}
		if (counter ==2)
			return null;
		return winner;
	}
}
