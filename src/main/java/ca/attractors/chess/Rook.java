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
    public boolean moveTo(Position targetPosition) {
        ChessPiece targetPiece = getChessboard().getPieceAt(targetPosition);
        if (!isTargetPositionOnSameRankOrFile(targetPosition)) return false;
        if (isTargetPieceSameColour(targetPiece)) return false;
        if (isTargetPathValid(targetPosition)) return false;
        getChessboard().movePieceTo(this, targetPosition);
        return true;
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

    private boolean isTargetPieceSameColour(ChessPiece targetPiece) {
        if (targetPiece != null) {
            return targetPiece.getColor() == getColor();
        }
        return false;
    }

    private boolean isTargetPositionOnSameRankOrFile(Position targetPosition) {
        boolean isSameFile = targetPosition.x == getPosition().x;
        boolean isSameRank = targetPosition.y == getPosition().y;
        return  (isSameRank && !isSameFile) || (!isSameRank && isSameFile);
    }

}
