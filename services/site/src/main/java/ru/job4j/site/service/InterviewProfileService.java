package ru.job4j.site.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.job4j.site.dto.InterviewProfileDTO;

import java.util.List;

@Service
public class InterviewProfileService {

    public List<InterviewProfileDTO> getByType(int type, ProfilesService profilesService) throws JsonProcessingException {
        var text = new RestAuthCall(String.format("http://localhost:9912/interviews/%d", type)).get();
        var mapper = new ObjectMapper();
        List<InterviewProfileDTO> interviewProfile = mapper.readValue(text, new TypeReference<>() {
        });
        for (InterviewProfileDTO profile : interviewProfile) {
            profile.setProfileUsername(profilesService.getProfileById(profile.getSubmitterId()).get().getUsername());
        }
        return interviewProfile;
    }
}