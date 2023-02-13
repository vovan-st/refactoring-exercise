package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class ChessPiece {
    private final Board board;
    private final PieceColor color;

    public ChessPiece(PieceColor color, Board board, Position position) {
        this.board = board;
        this.color = color;
        board.putPieceAt(this, position);
    }

    public Board getChessboard() {
        return board;
    }

    public Position getPosition() {
        return getChessboard().getPositionOf(this);
    }

    public PieceColor getColor() {
        return color;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public String toString() {
        return getName() + "{" + getColor() + " at: " + getPosition() +"}";
    }

    protected boolean isRankEquals(Position targetPosition) {
        return targetPosition.x == getPosition().x;
    }

    protected boolean isFileEquals(Position targetPosition) {
        return targetPosition.y == getPosition().y;
    }

    protected boolean isTargetSquareAvailable(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        return targetPiece == null || targetPiece.getColor() != getColor();
    }

    protected boolean isFreeToMoveHorizontally(Position targetPosition) {
        return isPathFree(getPosition().getYOffset(), targetPosition.getYOffset(), targetPosition.getXOffset(), true);
    }

    protected boolean isFreeToMoveVertically(Position targetPosition) {
        return isPathFree(getPosition().getXOffset(), targetPosition.getXOffset(), targetPosition.getYOffset(), false); 
    }

    protected boolean isFreeToMoveDiagonally(Position targetPosition) {
        int x = getPosition().x;
        int y = getPosition().y;
        int targetX = targetPosition.x;
        int targetY = targetPosition.y;
        return Math.abs(x - targetX) == Math.abs(y - targetY);
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

}
