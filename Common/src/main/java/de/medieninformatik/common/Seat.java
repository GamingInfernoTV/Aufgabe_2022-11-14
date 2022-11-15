package de.medieninformatik.common;

public record Seat(int row, int number) implements java.io.Serializable {
    @Override
    public String toString() {
        return "Seat [" + "row: " + row + ", number: " + number + ']';
    }
}
