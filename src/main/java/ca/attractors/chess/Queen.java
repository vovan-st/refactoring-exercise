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

    private boolean isTargetPathValid(Position targetPosition) {
        Position start = getPosition();

        int horizontalPosition = start.getXOffset();
        int verticalPosition = start.getYOffset();
        while(horizontalPosition != targetPosition.getXOffset() && verticalPosition != targetPosition.getYOffset()){
            int xDirection = getDirection(horizontalPosition, targetPosition.getXOffset());
            int yDirection = getDirection(verticalPosition, targetPosition.getYOffset());

            horizontalPosition = horizontalPosition + xDirection;
            verticalPosition = verticalPosition + yDirection;

            Position currentMovePosition = Position.getPositionFor(horizontalPosition, verticalPosition);

            if(isTargetPositionNotEmpty(currentMovePosition)){
                return false;
            }
        }

        return true;
    }

    private boolean isTargetPositionNotEmpty(Position position) {
        return getChessboard().getPieceAt(position) != null;
    }

    private boolean isTargetPositionValidMove(Position targetPosition){
        boolean verticalMoveValid = getPosition().isVerticalMovementValid(targetPosition);
        boolean horizontalMoveValid = getPosition().isHorizontalMovementValid(targetPosition);
        boolean diagonalMoveValid = getPosition().isDiagonalMoveValid(targetPosition);
        return verticalMoveValid || horizontalMoveValid || diagonalMoveValid;
    }
}
