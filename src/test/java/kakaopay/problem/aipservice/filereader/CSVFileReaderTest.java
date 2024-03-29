package kakaopay.problem.aipservice.filereader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CSVFileReaderTest {

    @Autowired
    private CSVFileReader csvFileReader;

    @Test
    @DisplayName("FileReader로 읽은 Recode 확인")
    void getRecode() {
        Record record = csvFileReader.getRecodes("static/csv/data.csv").get(0);

        assertThat(record.getRegion()).isEqualTo("강릉시");
        assertThat(record.getTarget()).isEqualTo("강릉시 소재 중소기업으로서 강릉시장이 추천한 자");
        assertThat(record.getUsage()).isEqualTo("운전");
        assertThat(record.getLimit()).isEqualTo("추천금액 이내");
        assertThat(record.getRate()).isEqualTo("3%");
        assertThat(record.getInstitute()).isEqualTo("강릉시");
        assertThat(record.getMgmt()).isEqualTo("강릉지점");
        assertThat(record.getReception()).isEqualTo("강릉시 소재 영업점");
    }
}