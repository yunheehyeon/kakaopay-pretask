package kakaopay.problem.aipservice.filereader;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CSVFileReaderTest {

    @Test
    void getRecode() {
        List<String> expected = Lists.newArrayList();
        expected.add("1");
        expected.add("강릉시");
        expected.add("강릉시 소재 중소기업으로서 강릉시장이 추천한 자");
        expected.add("운전");
        expected.add("추천금액 이내");
        expected.add("3%");
        expected.add("강릉지점");
        expected.add("강릉시 소재 영업점");

        CSVFileReader csvFileReader = new CSVFileReader("./src/main/resources/static/csv/problem1.csv");
        Record record = csvFileReader.getRecode().get(0);

        assertThat(record.values()).hasSameSizeAs(expected);
        assertThat(record.values()).hasSameElementsAs(expected);
    }
}