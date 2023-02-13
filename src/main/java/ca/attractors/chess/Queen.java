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
        int xDirection = start.x > targetPosition.x ? -1 : 1;
        int yDirection = start.y > targetPosition.y ? -1 : 1;
        List<Position> positions = new ArrayList<>();
        for (int y = start.y; y != targetPosition.y; y += yDirection) {
            positions.add(Position.getPositionFor(targetPosition.getXOffset(), y));
        }
        for (int x = start.x; x != targetPosition.x; x += xDirection) {
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
}
