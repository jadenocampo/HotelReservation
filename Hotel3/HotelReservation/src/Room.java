public class Room {
    private int roomNumber;
    private boolean[] isBooked;
    private String[] guestName;
    private static final int TOTAL_DAYS = 2;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = new boolean[TOTAL_DAYS];
        this.guestName = new String[TOTAL_DAYS];
        for (int i = 0; i < TOTAL_DAYS; i++) {
            guestName[i] = "";
        }
    }

    public boolean isBooked(int dayIndex) {
        return isBooked[dayIndex];
    }

    public String getGuestName(int dayIndex) {
        return guestName[dayIndex];
    }

    public void bookRoom(String guestName, int dayIndex) throws RoomBookingException {
        if (isBooked[dayIndex]) {
            throw new RoomBookingException("Room " + roomNumber + " is already booked for " + getDateString(dayIndex));
        }
        this.isBooked[dayIndex] = true;
        this.guestName[dayIndex] = guestName;
    }

    public void cancelBooking(String guestName, int dayIndex) throws RoomBookingException {
        if (!isBooked[dayIndex]) {
            throw new RoomBookingException("Room " + roomNumber + " is not booked for " + getDateString(dayIndex));
        }
        if (!this.guestName[dayIndex].equals(guestName)) {
            throw new RoomBookingException("Only " + this.guestName[dayIndex] + " can cancel this reservation");
        }
        this.isBooked[dayIndex] = false;
        this.guestName[dayIndex] = "";
    }

    private String getDateString(int dayIndex) {
        return "November " + (26 + dayIndex);
    }

    @Override
    public String toString() {
        StringBuilder status = new StringBuilder("Room " + roomNumber + ":\n");
        for (int i = 0; i < TOTAL_DAYS; i++) {
            status.append(getDateString(i)).append(": ")
                 .append(isBooked[i] ? "Booked by " + guestName[i] : "Available")
                 .append("\n");
        }
        return status.toString();
    }
}