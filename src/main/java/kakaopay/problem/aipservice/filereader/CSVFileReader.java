package kakaopay.problem.aipservice.filereader;

import kakaopay.problem.aipservice.filereader.exception.CSVParserCreateException;
import kakaopay.problem.aipservice.filereader.exception.ReaderCreatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CSVFileReader implements FileReader {
    private final ResourceLoader resourceLoader;

    public CSVFileReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<Record> getRecodes(String filePath) {
        CSVParser parser = createCSVParser(filePath);

        return StreamSupport.stream(parser.spliterator(), false)
                .map(this::createRecord)
                .collect(Collectors.toList());
    }

    private CSVParser createCSVParser(String filePath) {
        try {
            InputStream in = resourceLoader.getResource("classpath:" + filePath).getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);

            return new CSVParser(inputStreamReader, CSVFormat.RFC4180.withFirstRecordAsHeader());
        } catch (Exception e) {
            throw new CSVParserCreateException();
        }
    }

    private Record createRecord(CSVRecord record) {
        return new Record(record.toMap());
    }
}
