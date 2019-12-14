package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.FileDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SupportRuleControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @DisplayName("csv 파일 저장")
    void saveCSVFile() throws IOException {
        File file = new File("./src/main/resources/static/csv/problem1.csv");
        FileDto fileDto = new FileDto(file);

        webTestClient.post()
                .uri("/api/support")
                .body(Mono.just(fileDto), File.class)
                .exchange()
                .expectStatus()
                .isOk();
    }
}