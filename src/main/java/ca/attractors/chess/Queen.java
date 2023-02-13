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
        return isPathFree(getPosition().getYOffset(), targetPosition.getYOffset(), targetPosition.getXOffset(), true);
    }

    private boolean isFreeToMoveVertically(Position targetPosition) {
        return isPathFree(getPosition().getXOffset(), targetPosition.getXOffset(), targetPosition.getYOffset(), false); 
    }

    private boolean isPathFree(int start, int end, int offset, boolean isVertical) {
        int increment = start > end ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int p = start + increment; p != end; p = p + increment) {
            if (isVertical) {
                positions.add(Position.getPositionFor(offset, p));
            } else {
                positions.add(Position.getPositionFor(p, offset));
            }
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