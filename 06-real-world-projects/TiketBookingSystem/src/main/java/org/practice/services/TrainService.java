package org.practice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.practice.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TrainService {
    private static final String TRAINS_DIRECTORY = "trains.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final List<Train> trainList;

    public TrainService() throws IOException {
        File trains = new File(TRAINS_DIRECTORY);
        trainList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> train.getStation().contains(source) && train.getStation().contains(destination))
                .toList();
    }
}