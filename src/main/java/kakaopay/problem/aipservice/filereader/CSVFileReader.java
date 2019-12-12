package kakaopay.problem.aipservice.filereader;

import kakaopay.problem.aipservice.filereader.exception.CSVParserCreateException;
import kakaopay.problem.aipservice.filereader.exception.ReaderCreatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CSVFileReader implements FileReader {
    private final Reader reader;

    public CSVFileReader(String filePath) {
        reader = createReader(filePath);
    }

    private java.io.FileReader createReader(String filePath) {
        try {
            return new java.io.FileReader(filePath);
        } catch (Exception e) {
            throw new ReaderCreatException();
        }
    }

    public List<Record> getRecode() {
        CSVParser parser = createCSVParser();
        List<String> headerNames = parser.getHeaderNames();

        return StreamSupport.stream(parser.spliterator(), false)
                .map(record -> createRecord(record, headerNames))
                .collect(Collectors.toList());
    }

    private Record createRecord(CSVRecord record, List<String> headerNames) {
        List<String> recordItems = headerNames.stream()
                .map(record::get)
                .collect(Collectors.toList());

        return new Record(recordItems);
    }

    private CSVParser createCSVParser() {
        try {
            return CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        } catch (Exception e) {
            throw new CSVParserCreateException();
        }
    }
}
