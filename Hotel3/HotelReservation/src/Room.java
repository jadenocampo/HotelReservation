// Room.java
public class Room {
    private int roomNumber;
    private boolean isBooked;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
        this.guestName = "";
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public String getGuestName() {
        return guestName;
    }

    public void bookRoom(String guestName) throws RoomBookingException {
        if (isBooked) {
            throw new RoomBookingException("Room " + roomNumber + " is already booked!");
        }
        this.isBooked = true;
        this.guestName = guestName;
    }

    public void cancelBooking() throws RoomBookingException {
        if (!isBooked) {
            throw new RoomBookingException("Room " + roomNumber + " is not booked!");
        }
        this.isBooked = false;
        this.guestName = "";
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " - " + (isBooked ? "Booked by " + guestName : "Available");
    }
}
