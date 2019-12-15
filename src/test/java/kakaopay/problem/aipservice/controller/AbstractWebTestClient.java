package kakaopay.problem.aipservice.controller;

import org.junit.jupiter.api.BeforeEach;
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

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
public class AbstractWebTestClient {

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
    }

    protected WebTestClient.BodyContentSpec getRequest(String uri) {
        return webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }

    protected <T> WebTestClient.BodyContentSpec postRequest(String uri, T jsonBody) {
        return webTestClient.post()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(jsonBody), jsonBody.getClass())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }

    protected <T> WebTestClient.BodyContentSpec putRequest(String uri, T jsonBody) {
        return webTestClient.put()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(jsonBody), jsonBody.getClass())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }

    protected <T> WebTestClient.BodyContentSpec deleteRequest(String uri) {
        return webTestClient.delete()
                .uri(uri)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();
    }
}

