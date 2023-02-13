package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

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

    private boolean isTargetSquareAvailable(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        return targetPiece == null || targetPiece.getColor() != getColor();
    }

    private boolean isFreeToMoveHorizontally(Position targetPosition) {
        int start = getPosition().getYOffset();
        int end = targetPosition.getYOffset();
        int increment = start > end ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int y = start + increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFreeToMoveVertically(Position targetPosition) {
        //Next - Get all the cells between the source and the target and ensure that they are empty.
        // if this is a vertical move we need to increment the x coordinate until it is the same as the target's x
        // the increment might be positive or negative.
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        int increment = start > end ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int x = start+increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return false;
            }
        }
        return true;
    }

    private boolean isFreeToMoveDiagonally(Position targetPosition) {
        int x = getPosition().x;
        int y = getPosition().y;
        int targetX = targetPosition.x;
        int targetY = targetPosition.y;
        return Math.abs(x - targetX) == Math.abs(y - targetY);
    }

}