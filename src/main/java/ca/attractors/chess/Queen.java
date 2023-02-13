package ca.attractors.chess;

public class Queen extends ChessPiece {

    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */
    public boolean moveTo(Position targetPosition) {
        if (isValidMovement(targetPosition)) {
            getChessboard().movePieceTo(this, targetPosition);
            return true;
        }
        return false;
    }

    private boolean isValidMovement(Position position) {
        if (!isTargetSquareAvailable(position)) {
            return false;
        }
        
        if (position.y == getPosition().y && !isFreeToMoveVertically(position)) {
            return false;
        }
        
        if (position.x == getPosition().x && !isFreeToMoveHorizontally(position)) {
            return false;
        }

        if (position.x != getPosition().x && position.y != getPosition().y && !isFreeToMoveDiagonally(position)) {
            return false;
        }

        return true;
    }

}