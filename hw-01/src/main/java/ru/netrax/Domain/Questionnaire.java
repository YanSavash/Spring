package ru.netrax.Domain;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Questionnaire {
    @CsvBindByName
    private String question;
    @CsvBindAndSplitByName(elementType = String.class, collectionType = ArrayList.class, splitOn = "\\|")
    private List<String> options;
    @CsvBindByName
    private String answer;

    @Override
    public String toString() {
        return "Questionnaire{" +
                "question='" + question + '\'' +
                ", option=" + options +
                ", answer='" + answer + '\'' +
                "}\n";
    }
}
