//Nandi Hawkins, Jahseim Merritt, Jaden Ocampo
//Dr. Yu
//COMP 360 Project 3: Hotel Reservations
// November 29, 2024

import java.util.HashMap;
public class Hotel {
    private HashMap<Integer, Room> rooms;
    private static final int TOTAL_ROOMS = 5;
    private static final int TOTAL_DAYS = 2;

    public Hotel() {
        rooms = new HashMap<>();
        for (int i = 1; i <= TOTAL_ROOMS; i++) {
            rooms.put(i, new Room(i));
        }
    }

    public boolean isHotelFull(int dayIndex) {
        return rooms.values().stream()
                   .noneMatch(room -> !room.isBooked(dayIndex));
    }

    public void reserveRoom(int roomNumber, String guestName, int dayIndex) 
            throws RoomBookingException, NoRoomException {
        if (isHotelFull(dayIndex)) {
            throw new NoRoomException("No rooms available for November " + (26 + dayIndex));
        }
        if (!rooms.containsKey(roomNumber)) {
            throw new RoomBookingException("Room " + roomNumber + " does not exist!");
        }
        Room room = rooms.get(roomNumber);
        room.bookRoom(guestName, dayIndex);
        System.out.println("Room " + roomNumber + " reserved for " + guestName + 
                         " on November " + (26 + dayIndex));
    }

    public void cancelReservation(int roomNumber, String guestName, int dayIndex) throws RoomBookingException {
        if (!rooms.containsKey(roomNumber)) {
            throw new RoomBookingException("Room " + roomNumber + " does not exist!");
        }
        rooms.get(roomNumber).cancelBooking(guestName, dayIndex);
        System.out.println("Reservation cancelled for Room " + roomNumber + 
                         " on November " + (26 + dayIndex));
    }

    public String getRoomsStatus() {
        StringBuilder status = new StringBuilder("Hotel Status:\n");
        for (Room room : rooms.values()) {
            status.append(room).append("\n");
        }
        return status.toString();
    }
}