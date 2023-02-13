package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Rook extends ChessPiece {
    protected Rook(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    /**
     *
     * @param targetPosition - the position that we would like to move to
     * @return true if we were able to complete the move. false otherwise
     */

    @Override
    public boolean isValidMove(Position targetPosition) {
        if (!validRookMove(targetPosition)) return false;
        if (getPositionOccupiedWithSameColor(targetPosition)) return false;
        if (checkXMoveInvalid(targetPosition)) return false;
        if (checkYMoveInvalid(targetPosition)) return false;
        return true;
    }

    private boolean checkXMoveInvalid(Position targetPosition) {
        if (targetPosition.y == getPosition().y) {
            int start = getPosition().getXOffset();
            int end = targetPosition.getXOffset();
            int increment = getIncrement(targetPosition);
            List<Position> positions = getMoveXPositions(targetPosition, start, end, increment);
            if (moveBlocked(positions)) return true;
        }
        return false;
    }

    private boolean checkYMoveInvalid(Position targetPosition) {
        if (targetPosition.x == getPosition().x) {
            int start = getPosition().getYOffset();
            int end = targetPosition.getYOffset();
            int increment = getIncrement(targetPosition);
            List<Position> positions = getMoveYPositions(targetPosition, start, end, increment);
            if (moveBlocked(positions)) return true;
        }
        return false;
    }

    private boolean moveBlocked(List<Position> positions) {
        for (Position position: positions) {
            if (getChessboard().getPieceAt(position) != null) {
                return true;
            }
        }
        return false;
    }

    private static List<Position> getMoveYPositions(Position targetPosition, int start, int end, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int y = start + increment; y != end; y = y + increment) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        return positions;
    }

    private static List<Position> getMoveXPositions(Position targetPosition, int start, int end, int increment) {
        List<Position> positions = new ArrayList<>();
        for (int x = start + increment; x != end; x = x + increment) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }
        return positions;
    }

    private int getIncrement(Position targetPosition) {
        int start = getPosition().getXOffset();
        int end = targetPosition.getXOffset();
        if (start > end)
            return -1;
        else
            return 1;
    }

    private boolean validRookMove(Position targetPosition) {
        return targetPosition.x == getPosition().x || targetPosition.y == getPosition().y;
    }

    private boolean getPositionOccupiedWithSameColor(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (targetPiece != null) {
            if (targetPiece.getColor() == getColor())
                return true;
        }
        return false;
    }

}
