package fr.insa.game.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insa.game.resources.dto.QuizzResponse;
import fr.insa.game.resources.dto.UserAnswersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/sample")
    public String getSample() {
        String response = restTemplate.getForObject("http://quizz-service/quizz-managment/quizz/names", String.class);
        return response;
    }

    @RequestMapping("/pick-questions")
    public List<QuizzResponse> pickQuestions() {
        List<QuizzResponse> quizzResponses = restTemplate.getForObject("http://quizz-service/quizz-managment/quizz/quizz-questions", List.class);
        return quizzResponses;
    }

    @PostMapping("/check-answers")
    public int checkAnswers(@RequestBody List<UserAnswersRequest> userAnswersRequests) throws JsonProcessingException {
        Integer score = -1;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<UserAnswersRequest>> entity = new HttpEntity<>(userAnswersRequests, headers);
        score = restTemplate.postForObject("http://quizz-service/quizz-managment/quizz/check-answers", entity, int.class);

        return score;
    }

}
