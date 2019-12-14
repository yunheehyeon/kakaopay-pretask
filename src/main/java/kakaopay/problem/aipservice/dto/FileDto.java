package kakaopay.problem.aipservice.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@NoArgsConstructor
@Getter
@ToString
public class FileDto {
    private File file;

    public FileDto(File file) {
        this.file = file;
    }
}
