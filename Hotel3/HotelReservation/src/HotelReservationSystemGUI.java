// HotelReservationSystemGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystemGUI {
    private static Hotel hotel = new Hotel(5); // Hotel with 5 rooms

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Create panels for layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create room status display area
        JTextArea roomStatusArea = new JTextArea();
        roomStatusArea.setEditable(false);
        roomStatusArea.setText(hotel.getRoomsStatus());
        JScrollPane scrollPane = new JScrollPane(roomStatusArea);
        panel.add(scrollPane);

        // Create input fields for booking/canceling
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField(10);
        JLabel guestNameLabel = new JLabel("Guest Name:");
        JTextField guestNameField = new JTextField(10);

        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(guestNameLabel);
        inputPanel.add(guestNameField);
        panel.add(inputPanel);

        // Create buttons for booking, canceling, and viewing status
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton bookButton = new JButton("Book Room");
        JButton cancelButton = new JButton("Cancel Booking");
        JButton refreshButton = new JButton("Refresh Status");

        // Action for booking room
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    String guestName = guestNameField.getText();
                    hotel.bookRoom(roomNumber, guestName);
                    roomStatusArea.setText(hotel.getRoomsStatus());
                    JOptionPane.showMessageDialog(frame, "Room " + roomNumber + " successfully booked for " + guestName);
                } catch (RoomBookingException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid room number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for canceling room booking
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int roomNumber = Integer.parseInt(roomNumberField.getText());
                    hotel.cancelBooking(roomNumber);
                    roomStatusArea.setText(hotel.getRoomsStatus());
                    JOptionPane.showMessageDialog(frame, "Room " + roomNumber + " booking canceled.");
                } catch (RoomBookingException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Cancellation Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid room number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action for refreshing room status
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomStatusArea.setText(hotel.getRoomsStatus());
            }
        });

        // Add buttons to the panel
        buttonPanel.add(bookButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel);

        // Add the panel to the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}
