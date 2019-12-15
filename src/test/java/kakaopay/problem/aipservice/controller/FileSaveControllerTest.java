package kakaopay.problem.aipservice.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

class FileSaveControllerTest extends AbstractWebTestClient {

    @Test
    @DisplayName("파일 읽기")
    void read() throws IOException {
        getRequest("/api/fileread")
                .consumeWith(document("fileread",
                        preprocessRequest(prettyPrint())
                ));
        deleteRequest("/api/support");
    }

}