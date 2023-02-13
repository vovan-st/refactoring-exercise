package ca.attractors.chess;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionValidMove(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }



    private boolean isTargetPositionEmpty(Position position) {
        return getChessboard().getPieceAt(position) == null;
    }

    private boolean isTargetPositionValidMove(Position targetPosition){
        boolean verticalMoveValid = getPosition().isVerticalMovementValid(targetPosition);
        boolean horizontalMoveValid = getPosition().isHorizontalMovementValid(targetPosition);
        return verticalMoveValid || horizontalMoveValid;
    }

}
