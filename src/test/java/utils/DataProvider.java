package utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class DataProvider {

    private static final String CSV_QUESTIONS_PATH_FILE = "Questions.csv";

    public static Stream<Arguments> csvQuestionData(){
        List<String[]> data = CSVUtils.loadCSVData(CSV_QUESTIONS_PATH_FILE);

        return data.stream()
                .filter(values -> values.length == 2)
                .map(values -> Arguments.of(values[0], values[1]));
    }
}
