package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionValidMove(targetPosition)) return false;
//        if (isTargetPieceSameColour(targetPiece)) return false;
//        if (!isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isTargetPositionValidMove(Position targetPosition){
        boolean verticalMoveValid = true;
        boolean horizontalMoveValid = true;
        boolean diagonalMoveValid = true;
        return verticalMoveValid && horizontalMoveValid && diagonalMoveValid;
    }
}
