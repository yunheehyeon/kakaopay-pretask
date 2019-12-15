package kakaopay.problem.aipservice.controller;

import kakaopay.problem.aipservice.dto.FileDto;
import kakaopay.problem.aipservice.dto.OrderLimitSearchDto;
import kakaopay.problem.aipservice.dto.RegionSearchDto;
import kakaopay.problem.aipservice.dto.SupportRuleDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.RestDocumentationContextProvider;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

class SupportRuleControllerTest extends AbstractWebTestClient {

    @Override
    @BeforeEach
    protected void setUp(RestDocumentationContextProvider restDocumentation) {
        super.setUp(restDocumentation);

        getRequest("/api/fileread");
    }

    @Test
    @DisplayName("지원하는 지자체 페이지 조회")
    void read() throws IOException {
        getRequest("/api/support?page=1&size=3&direction=ASC")
                .consumeWith(document("support/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @DisplayName("지자체 이름으로 조회")
    void readByName() throws UnsupportedEncodingException {
        byte[] bytes = postRequest("/api/support/", new RegionSearchDto("성남시"))
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

        byte[] bytes = putRequest("/api/support/", supportRuleDto)
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
        postRequest("/api/support/limit/order", new OrderLimitSearchDto(5))
                .consumeWith(document("support/limit/post",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @Test
    @DisplayName("가장 작은 이차보전 조회")
    void readMinRate() {
        getRequest("/api/support/minRate")
                .consumeWith(document("support/minRate/get",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())
                ));
    }

    @AfterEach
    void tearDown() {
        // 모두 삭제
        deleteRequest("/api/support/");
    }
}