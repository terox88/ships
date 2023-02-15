import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Ship {
    private String name;
    private List<String> coordinates;
    private int size;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        coordinates = new LinkedList<>();
    }
    public void addCoordinates(String coordinate) {
        coordinates.add(coordinate);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }
    public void removeCoordinate(int index) {
        coordinates.remove(index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (size != ship.size) return false;
        if (!Objects.equals(name, ship.name)) return false;
        return Objects.equals(coordinates, ship.coordinates);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + size;
        return result;
    }
}
