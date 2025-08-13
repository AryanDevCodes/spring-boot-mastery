package org.practice.util;

import org.practice.entities.Train;

import java.util.List;

public class TrainServiceUtil {

    public static boolean isTrainAvailable(Train train, String source, String destination) {
        return train.getStation().contains(source) && train.getStation().contains(destination);
    }

    public static String getDepartureTime(Train train, String station) {
        return train.getStationTime().get(station);
    }

    public static String getArrivalTime(Train train, String station) {
        int index = train.getStation().indexOf(station);
        if (index == train.getStation().size() - 1) {
            return train.getStationTime().get(train.getStation().get(index));
        } else {
            return train.getStationTime().get(train.getStation().get(index + 1));
        }
    }

    public static int getAvailableSeats(Train train) {
        int availableSeats = 0;
        for (List<Integer> row : train.getSeats()) {
            for (int seat : row) {
                if (seat == 0) {
                    availableSeats++;
                }
            }
        }
        return availableSeats;
    }

    public static void bookSeat(Train train, int row, int seat) {
        train.getSeats().get(row).set(seat, 1);
    }

    public static void cancelSeat(Train train, int row, int seat) {
        train.getSeats().get(row).set(seat, 0);
    }
}