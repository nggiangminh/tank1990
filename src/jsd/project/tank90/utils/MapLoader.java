package jsd.project.tank90.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {
    private List<String> mapData;
    private final String folderPath = "src/jsd/project/tank90/resources/map_stage/";

    public MapLoader() {
        this.mapData = new ArrayList<>();
    }

    // Load map from a file
    public void loadMap(int mapLevel) {
        try (BufferedReader reader = new BufferedReader(new FileReader(getMap(mapLevel)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                mapData.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
        }
    }

    // Get map data
    public List<String> getMapData() {
        return mapData;
    }

    public String getMap(int mapLevel) {
        String fileName = "map_" + mapLevel + ".txt";
        return folderPath + fileName;
    }
}
