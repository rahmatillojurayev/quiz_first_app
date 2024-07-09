package uz.pdp.quiz_first_app.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {

    private String fileName;

    private String url;

    private String content;

}
