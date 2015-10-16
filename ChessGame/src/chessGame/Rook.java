package chessGame;

public class Rook extends ChessPiece {
	private final int score=0;
	
	public Rook(ChessPlayer player, String position) {
		super(player, position);	
	}

	public boolean moveIsAvailable(String moveTo) {
		// TO-DO: add Exception out of the board scope 
		// throw new UnsupportedOperationException();
		// TO-DO: Exception own chess
		
		ChessMonitoringSystem CMS = ChessMonitoringSystem.getInstance();
		if(CMS.getChessPiece(moveTo)==null || 
				CMS.getChessPiece(moveTo).getPlayer()!=this.player)
		{
			
			
			
			
			
			
		}
		
		
		
		
		
		
		//a1a2 vertical move
		if((int)position.charAt(0)==(int)move.charAt(1))
			if((int)position.charAt(1)-(int)move.charAt(2)>0)
				int k=(int)position.charAt(1)-(int)move.charAt(2);
				for(int i=0;i<k;i++)
					.getChessPiece(nearest position);//need to implement in chessMonitoringSystem
				//compare whether you can move there
			else if ((int)position.charAt(1)-(int)move.charAt(2)<0)
				int k=(int)position.charAt(2)-(int)move.charAt(1);
				for(int i=0;i<k;i++)
					.getChessPiece(nearest position);//need to implement in chessMonitoringSystem
				//compare whether you can move there
			else// dont move the chessPiece
				return false;
		
		//a1b1 horizontal move
		if((int)position.charAt(1)==(int)move.charAt(2))
			if((int)position.charAt(0)-(int)move.charAt(1)>0)
				int k=(int)position.charAt(0)-(int)move.charAt(1);
				for(int i=0;i<k;i++)
					.getChessPiece(nearest position);//need to implement in chessMonitoringSystem
				//compare whether you can move there
			else if ((int)position.charAt(0)-(int)move.charAt(1)<0)
				int k=(int)position.charAt(1)-(int)move.charAt(0);
				for(int i=0;i<k;i++)
					.getChessPiece(nearest position);//need to implement in chessMonitoringSystem
				//compare whether you can move there
			else// dont move the chessPiece
				return false;

		else
			return false;
			
			
		
		
		
		
	}

	@Override
	public int getScore() {
		return score;
	}
	
	@Override
	public String toString(){
		if (player.getPlayerId()==1)
			return "R";
		else if (player.getPlayerId()==2)
			return "r";
		else
			return null;
	}

}
