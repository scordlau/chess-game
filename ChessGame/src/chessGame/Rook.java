package chessGame;

public class Rook extends ChessPiece {

	/**
	 * 
	 * @param id
	 */
	public Rook(String id) {
		// TODO - implement Rook.Rook
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param move
	 */
	public boolean moveIsAvailable(String move) {
		// TODO - implement Rook.moveIsAvailable
		throw new UnsupportedOperationException();
		
		if .....means possible to move there
		//a1a2 vertical move
		(int)position.charAt(0)==(int)move.charAt(1)
		//a1b1 horizontal move
		(int)position.charAt(1)==(int)move.charAt(2)
			if no obstacles //check any chessPiece in the way
				//vertice
				if((int)position.charAt(1)-(int)move.charAt(2)>0)
					int k=(int)position.charAt(1)-(int)move.charAt(2);
					for(int i=0;i<k;i++)
						.getChessPiece(nearest position);//need to implement in chessMonitoringSystem
				
				
			
			else
				//obstacles in halfway
		
				
		else
			return false;
			
			
		
		
		
		
	}

}