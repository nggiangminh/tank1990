package jsd.project.tank90.model.tanks;

public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;

        // Add a method to get the opposite direction
        public Direction opposite() {
                switch (this) {
                        case UP:
                                return Direction.DOWN;
                        case DOWN:
                                return Direction.UP;
                        case LEFT:
                                return Direction.RIGHT;
                        case RIGHT:
                                return Direction.LEFT;
                        default:
                                return this;
                }
        }
}