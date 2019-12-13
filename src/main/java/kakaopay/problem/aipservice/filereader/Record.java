package kakaopay.problem.aipservice.filereader;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Record {
    private final Map<String, String> recordItems;

    public Record(Map<String, String> recordItems) {
        this.recordItems = recordItems;
    }

    public Set<String> keySet() {
        return recordItems.keySet();
    }

    public Set<String> values() {
        return new HashSet<>(recordItems.values());
    }

    public String getRecordItem(String key) {
        return recordItems.get(key);
    }

    @Override
    public String toString() {
        return "Record{" + recordItems + '}';
    }
}
