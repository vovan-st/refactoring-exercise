package ca.attractors.chess;

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

    protected boolean isTargetPieceSameColour(ChessPiece targetPiece) {
        if (targetPiece != null) {
            return targetPiece.getColor() == getColor();
        }
        return false;
    }

    protected int getDirection(int current, int target){
        if(current == target){
            return 0;
        }
        return current > target ? -1 : 1;
    }

    protected boolean isTargetPathValid(Position targetPosition) {
        Position currentPosition = getPosition();
        while(currentPosition.getXOffset() != targetPosition.getXOffset() && currentPosition.getYOffset() != targetPosition.getYOffset()){
            int xDirection = getDirection(currentPosition.getXOffset(), targetPosition.getXOffset());
            int yDirection = getDirection(currentPosition.getYOffset(), targetPosition.getYOffset());
            currentPosition =  Position.getPositionFor(currentPosition.x + xDirection, currentPosition.y + yDirection);
            if(!board.isTargetPositionEmpty(currentPosition)){
                return false;
            }
        }
        return true;
    }
}
