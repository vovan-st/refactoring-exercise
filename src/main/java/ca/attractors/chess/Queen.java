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
        boolean verticalMoveValid = isVerticalMovementValid(targetPosition);
        boolean horizontalMoveValid = isHorizontalMovementValid(targetPosition);
        boolean diagonalMoveValid = isDiagonalMoveValid(targetPosition);
        return verticalMoveValid || horizontalMoveValid || diagonalMoveValid;
    }

    private boolean isVerticalMovementValid(Position targetPosition){
        boolean isSameFile = targetPosition.x == getPosition().x;
        boolean isSameRank = targetPosition.y == getPosition().y;
        return  (isSameRank && !isSameFile) || (!isSameRank && isSameFile);
    }

    private boolean isHorizontalMovementValid(Position targetPosition){
        boolean isSameFile = targetPosition.x == getPosition().x;
        boolean isSameRank = targetPosition.y == getPosition().y;
        return  (isSameRank && !isSameFile) || (!isSameRank && isSameFile);
    }

    private boolean isDiagonalMoveValid(Position targetPosition){
        int movementX = Math.abs(targetPosition.x - this.getPosition().x);
        int movementY = Math.abs(targetPosition.y - this.getPosition().y);
        return movementY == movementX;
    }
}
