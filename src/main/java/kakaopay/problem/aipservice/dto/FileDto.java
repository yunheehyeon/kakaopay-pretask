package kakaopay.problem.aipservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
