package kakaopay.problem.aipservice.filereader;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Record {
    private enum RecordDelimiter {
        REGION("지자체명(기관명)"), TARGET("지원대상"),

        USAGE("용도"), LIMIT("지원한도"),

        RATE("이차보전"), INSTITUTE("추천기관"),

        MGMT("관리점"), RECEPTION("취급점");

        private String name;

        RecordDelimiter(String name) {
            this.name = name;
        }
    }

    private final Map<String, String> recordItems;

    public Record(Map<String, String> recordItems) {
        this.recordItems = recordItems;
    }

    public Set<String> values() {
        return new HashSet<>(recordItems.values());
    }

    public String getRegion() {
        return recordItems.get(RecordDelimiter.REGION.name);
    }

    public String getTarget() {
        return recordItems.get(RecordDelimiter.TARGET.name);
    }

    public String getUsage() {
        return recordItems.get(RecordDelimiter.USAGE.name);
    }

    public String getLimit() {
        return recordItems.get(RecordDelimiter.LIMIT.name);
    }

    public String getRate() {
        return recordItems.get(RecordDelimiter.RATE.name);
    }

    public String getInstitute() {
        return recordItems.get(RecordDelimiter.INSTITUTE.name);
    }

    public String getMgmt() {
        return recordItems.get(RecordDelimiter.MGMT.name);
    }

    public String getReception() {
        return recordItems.get(RecordDelimiter.RECEPTION.name);
    }

    @Override
    public String toString() {
        return "Record{" + recordItems.values() + '}';
    }
}
