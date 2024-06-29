package uz.pdp.quiz_first_app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.quiz_first_app.dto.OptionDTO;
import uz.pdp.quiz_first_app.entity.Option;
import uz.pdp.quiz_first_app.repo.OptionRepo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionUtil {

    private final OptionRepo optionRepo;

    public List<OptionDTO> getOptionsByQuestionId(Integer questionId) {
        List<Option> options = optionRepo.findAllByQuestionId(questionId);
        List<OptionDTO> optionDTOs = new ArrayList<>();
        for (Option option : options) {
            OptionDTO optionDTO = OptionDTO.builder()
                    .id(option.getId())
                    .text(getTextFromLanguage(option))
                    .isCorrect(option.isCorrect())
                    .build();
            optionDTOs.add(optionDTO);
        }
        Collections.shuffle(optionDTOs);
        optionDTOs.get(0).setOption('A');
        optionDTOs.get(1).setOption('B');
        optionDTOs.get(2).setOption('C');
        return optionDTOs;
    }

    private String getTextFromLanguage(Option option) {
        String language = LocaleContextHolder.getLocale().getLanguage();
        return switch (language){
            case "uz" -> option.getTextUz();
            case "ru" -> option.getTextRu();
            default -> option.getTextEn();
        };
    }

}
