//Nandi Hawkins, Jahseim Merritt, Jaden Ocampo
//Dr. Yu
//COMP 360 Project 3: Hotel Reservations
// November 29, 2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HotelReservationSystemGUI {
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Greensboro Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextArea roomStatusArea = new JTextArea(20, 40);
        roomStatusArea.setEditable(false);
        roomStatusArea.setText(hotel.getRoomsStatus());
        JScrollPane scrollPane = new JScrollPane(roomStatusArea);
        panel.add(scrollPane);

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel roomNumberLabel = new JLabel("Room Number:");
        JTextField roomNumberField = new JTextField(5);
        JLabel guestNameLabel = new JLabel("Guest Name:");
        JTextField guestNameField = new JTextField(15);
        
        String[] dates = {"November 26", "November 27"};
        JComboBox<String> dateComboBox = new JComboBox<>(dates);

        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberField);
        inputPanel.add(guestNameLabel);
        inputPanel.add(guestNameField);
        inputPanel.add(new JLabel("Date:"));
        inputPanel.add(dateComboBox);
        panel.add(inputPanel);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton reserveButton = new JButton("Reserve Room");
        JButton cancelButton = new JButton("Cancel Reservation");
        JButton refreshButton = new JButton("Refresh Status");

        reserveButton.addActionListener(e -> {
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String guestName = guestNameField.getText().trim();
                if (guestName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a guest name",
                                               "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int dayIndex = dateComboBox.getSelectedIndex();
                
                hotel.reserveRoom(roomNumber, guestName, dayIndex);
                roomStatusArea.setText(hotel.getRoomsStatus());
                JOptionPane.showMessageDialog(frame, "Room " + roomNumber + " reserved for " + guestName + 
                                           " on " + dates[dayIndex]);
            } catch (NoRoomException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "No Rooms Available", 
                                           JOptionPane.ERROR_MESSAGE);
            } catch (RoomBookingException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), 
                                           "Booking Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid room number.", 
                                           "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            try {
                int roomNumber = Integer.parseInt(roomNumberField.getText());
                String guestName = guestNameField.getText().trim();
                if (guestName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter the guest name who made the reservation",
                                               "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int dayIndex = dateComboBox.getSelectedIndex();
                
                hotel.cancelReservation(roomNumber, guestName, dayIndex);
                roomStatusArea.setText(hotel.getRoomsStatus());
                JOptionPane.showMessageDialog(frame, "Reservation cancelled for Room " + 
                                           roomNumber + " on " + dates[dayIndex]);
            } catch (RoomBookingException ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), 
                                           "Cancellation Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid room number.", 
                                           "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        refreshButton.addActionListener(e -> roomStatusArea.setText(hotel.getRoomsStatus()));

        buttonPanel.add(reserveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel);

        frame.add(panel);
        frame.setVisible(true);
    }
}