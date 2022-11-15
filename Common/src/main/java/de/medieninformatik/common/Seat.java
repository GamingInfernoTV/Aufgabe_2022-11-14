package de.medieninformatik.common;

/**
 * Einfache Wrapper für die Übertragung von Sitz-Daten
 *
 * @param row    Die Reihe des Sitzes in einem Saal
 * @param number Die Nummer des Sitzes in einer Reihe
 * @author Malte Kasolowsky <code>m30114</code>
 */
public record Seat(int row, int number) implements java.io.Serializable {

    /**
     * Überschreibt {@link Object#toString()}
     *
     * @return Eine Zeichenkette, die den Sitz beschreibt
     */
    @Override
    public String toString() {
        return "Seat [" + "row: " + row + ", number: " + number + ']';
    }
}
