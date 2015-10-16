package chessGame;

public class ChessMonitoringSystem {

	private static ChessPiece[] allChessPieces;
	private static ChessMonitoringSystem instance;

	private ChessMonitoringSystem() {
		// TODO - implement ChessMonitoringSystem.ChessMonitoringSystem
		throw new UnsupportedOperationException();
	}

	public static ChessMonitoringSystem getInstance() {
		return instance;
	}

	public void initializeChessBoard() {
		// TODO - implement ChessMonitoringSystem.initializeChessboard
		throw new UnsupportedOperationException();
	}

	public void initializeChessPieces(ChessPlayer player1, ChessPlayer player2) {
		// TODO - implement ChessMonitoringSystem.initializeChessPieces
		allChessPieces = new ChessPiece[32];
		ChessPlayer[] chessPlayerList = {player1, player2};
		
		int allChessYPos = 1;
		int pawnYPos = 2;
		
		for (int i = 0; i<2; i++)
		{
			allChessPieces[i*16] = new Rook(chessPlayerList[i], "a"+allChessYPos);
			allChessPieces[i*16+1] = new Knight(chessPlayerList[i], "b"+allChessYPos);
			allChessPieces[i*16+2] = new Bishop(chessPlayerList[i], "c"+allChessYPos);
			allChessPieces[i*16+3] = new King(chessPlayerList[i], "d"+allChessYPos);
			allChessPieces[i*16+4] = new Queen(chessPlayerList[i], "e"+allChessYPos);
			allChessPieces[i*16+5] = new Bishop(chessPlayerList[i], "f"+allChessYPos);
			allChessPieces[i*16+6] = new Knight(chessPlayerList[i], "g"+allChessYPos);
			allChessPieces[i*16+7] = new Rook(chessPlayerList[i], "h"+allChessYPos);
			
			//8 pawns		
			int xPosCharCode = 97;
			for (int j = 0; j<8; j++)
			{
				String pawnXPos= ""+(char)xPosCharCode; //x-coordinate of pawns' position
				allChessPieces[i*16+8+j] = new Pawn(chessPlayerList[i], pawnXPos+pawnYPos);
				xPosCharCode++;
			}
			
			pawnYPos = 7; //inverted y-coordinate for Pawns
			allChessYPos = 8; //invert y-coordinate for oponent's chess pieces
		}
		
		throw new UnsupportedOperationException();
	}

	public void showAllChessPiecesPosition() {
		// TODO - implement ChessMonitoringSystem.showAllChessPiecesPosition
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
			xPosInNum = (int)tempPos.charAt(0)-96;
			yPosInNum = (int)tempPos.charAt(1);
			chessPiecesPosition[yPosInNum-1][xPosInNum-1] = c.toString(); //a1 on chess board->[0][0] in array
		}
		
		System.out.println(String.format("%2s%2s%2s%2s%2s%2s%2s%2s","a", "b", "c", "d", "e", "f", "g", "h"));

		for (int i = 0; i<8; i++)
		{
			System.out.print((8-i)+" ");
			for (int j = 0; j<8; j++)
			{
				System.out.print(chessPiecesPosition[8-i-1][j-1]+" "); //yPos: 8,7,6,...->7,6,5,...  
																		//xPos: a,b,c,...->0,1,2,...
			}
			System.out.println(8-i);
		}
		
		System.out.println(String.format("%2s%2s%2s%2s%2s%2s%2s%2s","a", "b", "c", "d", "e", "f", "g", "h"));
		
		throw new UnsupportedOperationException();
	}

	public void moveChessPiece(ChessPlayer player,String oldPos,String newPos) {
		/*----------------------------------------------
		 * BELOW CODE DO ONLY ILLUSTRATE THE CONCEPT
		 *----------------------------------------------*/
		
		
		ChessPiece movingChess = getChessPiece(oldPos);
		/*ERROR-CATCHING : MAY CHANGE TO TRY-CATCH CLAUSE*/
		if(movingChess == null)
			System.out.println("Chesspiece not found!");
		else if(movingChess.getPlayer()!=player)
      			System.out.println("Selected piece does not belong to you!");
		else if(movingChess.moveIsAvailable(newPos)) {
			if(getChessPiece(newPos)!=null)
				removeChessPiece(newPos);
			movingChess.updatePosition(newPos);
		}
        	else
          		System.out.println("The move is invalid.");
		
		//updated the newPos of chessPiece
		throw new UnsupportedOperationException();
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
		// TODO - implement ChessMonitoringSystem.removeChessPiece
		ChessPiece target = getChessPiece(position);
		if(target instanceof King)
			checkGameResult(target.getPlayer());
			
		allChessPieces.remove(target); //IF USE ARRAYLIST
	}

	public void checkGameResult(ChessPlayer winner) {
		// TODO - Possible Gamemode conditions
		//if(getGameMode());
		System.out.printf("Player %s won!", winner.toString());

	}

	public boolean isKingCaptured() {
		// TODO Auto-generated method stub
		return false;
	}

	public void getGameResult(int mode) {
		// TODO Auto-generated method stub
		
	}

}
