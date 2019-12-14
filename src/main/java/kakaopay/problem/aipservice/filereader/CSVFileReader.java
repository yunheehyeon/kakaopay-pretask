package kakaopay.problem.aipservice.filereader;

import kakaopay.problem.aipservice.filereader.exception.CSVParserCreateException;
import kakaopay.problem.aipservice.filereader.exception.ReaderCreatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CSVFileReader implements FileReader {
    private final Reader reader;

    public CSVFileReader(File file) {
        reader = createReader(file);
    }

    private java.io.FileReader createReader(File file) {
        try {
            return new java.io.FileReader(file);
        } catch (Exception e) {
            throw new ReaderCreatException();
        }
    }

    @Override
    public List<Record> getRecodes() {
        CSVParser parser = createCSVParser();

        return StreamSupport.stream(parser.spliterator(), false)
                .map(this::createRecord)
                .collect(Collectors.toList());
    }

    private Record createRecord(CSVRecord record) {
        return new Record(record.toMap());
    }

    private CSVParser createCSVParser() {
        try {
            return CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);
        } catch (Exception e) {
            throw new CSVParserCreateException();
        }
    }
}
