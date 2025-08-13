package org.practice;

import org.practice.entities.User;
import org.practice.services.UserBookingService;
import org.practice.services.TrainService;
import org.practice.util.UserServiceUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import org.practice.entities.Train;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running TicketBooking System");
        Scanner sc = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService;
        TrainService trainService;
        User user = null;
        try {
            userBookingService = new UserBookingService();
            trainService = new TrainService();

            while (option != 7) {
                System.out.println("Please enter your option");
                System.out.println("1. Create a new user");
                System.out.println("2. Login");
                System.out.println("3. Fetch Bookings");
                System.out.println("4. Search Trains");
                System.out.println("5. Book a seat");
                System.out.println("6. Cancel Booking");
                System.out.println("7. Exit");
                option = sc.nextInt();
                switch (option) {
                    case 1:
                        System.out.println("Please enter UserName to SignUp");
                        String userName = sc.next();
                        System.out.println("Please enter Password to SignUp");
                        String password = sc.next();
                        User userSignUp = new User(userName, password,
                                UserServiceUtil.hashPassword(password),
                                new ArrayList<>(),
                                UUID.randomUUID().toString());
                        userBookingService.signUp(userSignUp);
                        break;
                    case 2:
                        System.out.println("Please enter UserName to Login");
                        String nameToLogin = sc.next();
                        System.out.println("Please enter Password to Login");
                        String passwordToLogin = sc.next();
                        user = new User(nameToLogin, passwordToLogin,
                                UserServiceUtil.hashPassword(passwordToLogin),
                                new ArrayList<>(),
                                UUID.randomUUID().toString());
                        if (userBookingService.loginUser  (user)) {
                            System.out.println("Login successful");
                        } else {
                            System.out.println("Invalid username or password");
                        }
                        break;
                    case 3:
                        if (user != null) {
                            userBookingService.fetchBooking();
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 4:
                        System.out.println("Please enter source station");
                        String source = sc.next();
                        System.out.println("Please enter destination station");
                        String destination = sc.next();
                        List<Train> trains = trainService.searchTrains(source, destination);
                        if (trains.isEmpty()) {
                            System.out.println("No trains found");
                        } else {
                            for (Train train : trains) {
                                System.out.println(train);
                            }
                        }
                        break;
                    case 5:
                        if (user != null) {
                            System.out.println("Please enter train ID");
                            String trainId = sc.next();
                            System.out.println("Please enter seat number");
                            int seatNumber = sc.nextInt();
                            if (userBookingService.bookSeat(user, trainId, seatNumber)) {
                                System.out.println("Seat booked successfully");
                            } else {
                                System.out.println("Failed to book seat");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                    case 6:
                        if (user != null) {
                            System.out.println("Please enter ticket ID");
                            String ticketId = sc.next();
                            if (userBookingService.cancelTicket(ticketId)) {
                                System.out.println("Booking cancelled successfully");
                            } else {
                                System.out.println("Failed to cancel booking");
                            }
                        } else {
                            System.out.println("Please login first");
                        }
                        break;
                }
            }
        } catch (IOException e) {
//            System.out.println("Something went wrong " + e.getMessage());
            e.printStackTrace();
        }
    }
}