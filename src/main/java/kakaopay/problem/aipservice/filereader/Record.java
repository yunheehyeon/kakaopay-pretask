package kakaopay.problem.aipservice.filereader;

import java.util.ArrayList;
import java.util.List;

public class Record {
    private final List<String> recordItems;

    public Record(List<String> recordItems) {
        this.recordItems = recordItems;
    }

    public List<String> getRecordItems() {
        return new ArrayList<>(recordItems);
    }

    @Override
    public String toString() {
        return "Record{" +
                "recordItems=" + recordItems +
                '}';
    }
}
