package src.utils;

public class Movement {

    private Position initialPosition;
    private Position finalPosition;
    private boolean oldWasMoved;

    public Movement(Position initialPosition, Position finalPosition, boolean oldWasMoved) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
        this.oldWasMoved = oldWasMoved;
    }

    /*
     * GETTERS AND SETTERS
     */

    public Position getInitialPosition() { return initialPosition; }
    public void setInitialPosition(Position initialPosition) { this.initialPosition = initialPosition; }
    public Position getFinalPosition() { return finalPosition; }
    public void setFinalPosition(Position finalPosition) { this.finalPosition = finalPosition; }
    public boolean getOldWasMoved() { return oldWasMoved; }
    public void setOldWasMoved(boolean oldWasMoved) { this.oldWasMoved = oldWasMoved; }
}
