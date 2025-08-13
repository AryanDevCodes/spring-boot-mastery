package org.practice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.practice.entities.Train;
import org.practice.entities.User;
import org.practice.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private static final String USERS_DIRECTORY = "user.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService() throws IOException {
        getUserList();
    }

    public UserBookingService(User user) throws IOException {
        this.user = user;
        getUserList();
    }

    public List<User> getUserList() throws IOException {
        File users = new File(USERS_DIRECTORY);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
        return userList;
    }

    public Boolean loginUser(User user) {
        Optional<User> foundUser = userList.stream().filter(u -> {
            return u.getName().equals(user.getName())
                    && UserServiceUtil.checkPassword(user.getPassword(), u.getHashPassword());
        }).findFirst();
        if (foundUser.isPresent()) {
            this.user = foundUser.get();
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public Boolean signUp(User user) throws IOException {
        userList.add(user);
        saveUserListToFile();
        return Boolean.TRUE;
    }

    private void saveUserListToFile() throws IOException {
        File userFile = new File(USERS_DIRECTORY);
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBooking() {
        if (user != null) {
            user.printTickets();
        } else {
            System.out.println("Please login first");
        }
    }

    public Boolean cancelTicket(String ticketID) throws IOException {
        if (user != null) {
            if (user.cancelTicket(ticketID)) {
                saveUserListToFile();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            System.out.println("Please login first");
            return Boolean.FALSE;
        }
    }

    public Boolean bookSeat(User user, String trainId, int seatNumber) throws IOException {
        if (user != null) {
            if (user.bookSeat(trainId, seatNumber)) {
                saveUserListToFile();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            System.out.println("Please login first");
            return Boolean.FALSE;
        }
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (IOException e) {
            System.out.println("Error getting trains: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}