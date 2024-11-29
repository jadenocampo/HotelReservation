// Hotel.java
import java.util.HashMap;

public class Hotel {
    private HashMap<Integer, Room> rooms;

    public Hotel(int totalRooms) {
        rooms = new HashMap<>();
        for (int i = 1; i <= totalRooms; i++) {
            rooms.put(i, new Room(i));
        }
    }

    public void bookRoom(int roomNumber, String guestName) throws RoomBookingException {
        if (!rooms.containsKey(roomNumber)) {
            throw new RoomBookingException("Room " + roomNumber + " does not exist!");
        }
        rooms.get(roomNumber).bookRoom(guestName);
    }

    public void cancelBooking(int roomNumber) throws RoomBookingException {
        if (!rooms.containsKey(roomNumber)) {
            throw new RoomBookingException("Room " + roomNumber + " does not exist!");
        }
        rooms.get(roomNumber).cancelBooking();
    }

    public void displayRooms() {
        System.out.println("Room Status:");
        for (Room room : rooms.values()) {
            System.out.println(room);
        }
    }

    public String getRoomsStatus() {
        StringBuilder status = new StringBuilder("Room Status:\n");
        for (Room room : rooms.values()) {
            status.append(room).append("\n");
        }
        return status.toString();
    }
}
