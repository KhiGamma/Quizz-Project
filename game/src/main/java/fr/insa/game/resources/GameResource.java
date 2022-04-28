package fr.insa.game.resources;

import fr.insa.game.resources.dto.QuizzResponse;
import fr.insa.game.resources.dto.UserAnswersRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/check-answers")
    public int checkAnswers(@RequestBody List<UserAnswersRequest> userAnswersRequests) {
        Integer score = restTemplate.exchange("http://quizz-service/quizz-managment/quizz/check-answers", HttpMethod.GET, new HttpEntity<>(userAnswersRequests), Integer.class).getBody();
        //Integer score = restTemplate.getForObject("http://quizz-service/quizz-managment/quizz/check-answers", Integer.class, userAnswersRequests);
        return score;
    }

}
