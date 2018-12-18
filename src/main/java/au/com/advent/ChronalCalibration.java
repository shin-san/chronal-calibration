package au.com.advent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChronalCalibration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalCalibration.class);

    public static void main(String[] args) throws Exception{
        final File frequencies = new File("src/main/resources/input.txt");
        List<Integer> frequencyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(frequencies))) {
            while (br.ready()) {
                frequencyList.add(Integer.parseInt(br.readLine()));
            }

            List<Integer> frequencyTotal = new ArrayList<>();
            int totalValue = 0;
            int counter = 0;
            boolean isFrequencyFound = false;
            while (!isFrequencyFound) {
                if (counter > frequencyList.size()-1) {
                    counter = 0;
                }
                totalValue += frequencyList.get(counter);
                for (int value: frequencyTotal) {
                    if (value == totalValue) {
                        LOGGER.info("First frequency reached twice: {}", totalValue);
                        isFrequencyFound = true;
                        break;
                    }
                }
                frequencyTotal.add(totalValue);
                counter++;
            }
            LOGGER.info("Resulting frequency: {}", totalValue);
        } catch (Exception ex) {
            LOGGER.error("Exception occurred: {}", ex);
            throw new Exception("Exception occurred");
        }
    }
}
