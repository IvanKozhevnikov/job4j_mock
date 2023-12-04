package ru.job4j.site.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.site.dto.CategoryTotalInterviewDTO;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryTotalInterviewService {
    private final TopicsService topicsService;
    private final InterviewProfileService interviewProfileService;

    public List<CategoryTotalInterviewDTO> getPopularFromDesc() throws JsonProcessingException {
        var text = new RestAuthCall("http://localhost:9902/categories/most_pop").get();
        var mapper = new ObjectMapper();
        return mapper.readValue(text, new TypeReference<>() {
        });
    }

    public List<CategoryTotalInterviewDTO> getMostPopular() throws JsonProcessingException {
        var categoriesTotalInterviewDTO = getPopularFromDesc();
        for (var categoryTotalInterviewDTO : categoriesTotalInterviewDTO) {
            categoryTotalInterviewDTO.setTopicsSize(topicsService.getByCategory(categoryTotalInterviewDTO.getId()).size());
            categoryTotalInterviewDTO.setTotalInterview(interviewProfileService.getByType(1).size());
        }
        return categoriesTotalInterviewDTO;
    }
}
