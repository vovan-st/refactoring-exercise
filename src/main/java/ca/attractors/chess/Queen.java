package ca.attractors.chess;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    @Override
    public boolean isValidMove(Position targetPosition) {
        if (!validRookMove(targetPosition) )
//                ||
//                getPositionOccupiedWithSameColor(targetPosition) ||
//                checkXMoveInvalid(targetPosition) ||
//                checkYMoveInvalid(targetPosition))
            return false;
        return true;
    }


    private boolean validRookMove(Position targetPosition) {
        return targetPosition.x == getPosition().x || targetPosition.y == getPosition().y;
    }
}
