package kakaopay.problem.aipservice.filereader;

import java.util.List;

public interface FileReader {
    List<Record> getRecodes(String filePath);
}
