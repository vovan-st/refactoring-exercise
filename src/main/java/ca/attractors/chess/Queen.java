package ca.attractors.chess;

import java.util.ArrayList;
import java.util.List;

public class Queen extends ChessPiece {
    public Queen(PieceColor color, Board board, Position position) {
        super(color, board, position);
    }

    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionValidMove(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (!isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
    }

    private boolean isTargetPieceSameColour(ChessPiece targetPiece) {
        if (targetPiece != null) {
            return targetPiece.getColor() == getColor();
        }
        return false;
    }

    private boolean isTargetPathValid(Position targetPosition) {
        Position start = getPosition();
        int xDirection = getDirection(getPosition().getXOffset(), targetPosition.getXOffset());
        int yDirection = getDirection(getPosition().getYOffset(), targetPosition.getYOffset());
        List<Position> positions = new ArrayList<>();
        for (int y = start.getYOffset(); y != targetPosition.getYOffset(); y += yDirection) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        for (int x = start.getXOffset(); x != targetPosition.getXOffset(); x += xDirection) {
            positions.add(Position.getPositionFor(x, targetPosition.getYOffset()));
        }

        return positions
                .stream()
                .anyMatch(this::isTargetPositionNotEmpty);
    }

    private boolean isTargetPositionNotEmpty(Position position) {
        return getChessboard().getPieceAt(position) != null;
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

    private int getDirection(int current, int target){
        return Integer.compare(current, target);
    }
}
