package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.RegionSearchDto;
import kakaopay.problem.aipservice.dto.OrderLimitSearchDto;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
class SupportRuleControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    protected void setUp(RestDocumentationContextProvider restDocumentation) {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .defaultHeader("Content-Type", "application/json;charset=UTF-8")
                .filter(documentationConfiguration(restDocumentation))
                .build();

        //저장
        webTestClient.post()
                .uri("/api/filesave")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    @DisplayName("지원하는 지자체 페이지 조회")
    void read() throws IOException {
        webTestClient.get()
                .uri("/api/support?page=1&size=3")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("support/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @DisplayName("지자체 이름으로 조회")
    void readByName() throws UnsupportedEncodingException {
        byte[] bytes = webTestClient.post()
                .uri("/api/support/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new RegionSearchDto("성남시")), RegionSearchDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("support/post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                )).returnResult()
                .getResponseBody();

        assertThat(new String(bytes, "utf-8")).contains("성남시");
    }

    @Test
    @DisplayName("지자체 정보 업데이트")
    void update() throws UnsupportedEncodingException {
        SupportRuleDto supportRuleDto = new SupportRuleDto(
                "성남시",
                "성남시 소재 중소기업으로서 성남시장의 추천을 받은 자",
                "정보 변경",
                "5억원 이내",
                "1.80%",
                "성남시",
                "정보 변경",
                "전 영업점"
        );

        byte[] bytes = webTestClient.put()
                .uri("/api/support/")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(supportRuleDto), SupportRuleDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("support/put",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                )).returnResult()
                .getResponseBody();

        assertThat(new String(bytes, "utf-8")).contains("정보 변경");
    }

    @Test
    @DisplayName("지원금액, 이차보전 순으로 K개 조회")
    void readByNumber() {
        webTestClient.post()
                .uri("/api/support/search")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(new OrderLimitSearchDto(10)), OrderLimitSearchDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("support/search/post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @DisplayName("가장 작은 이차보전 조회")
    void readMinRate() {
        webTestClient.get()
                .uri("/api/support/minRate")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(document("support/minRate/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @AfterEach
    void tearDown() {
        // 모두 삭제
        webTestClient.delete()
                .uri("/api/support/")
                .exchange()
                .expectStatus()
                .isOk();
    }
}