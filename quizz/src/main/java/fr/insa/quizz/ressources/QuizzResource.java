package fr.insa.quizz.ressources;

import fr.insa.quizz.ressources.dto.QuizzQuestionsResponse;
import fr.insa.quizz.ressources.dto.QuizzResponse;
import fr.insa.quizz.ressources.dto.QuizzRequest;
import fr.insa.quizz.ressources.dto.UserAnswersRequest;
import fr.insa.quizz.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("quizz")
public class QuizzResource extends CommonResource {

    @Autowired
    QuizzService quizzService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/names")
    public String getNames() {
        return String.format("I'm %s listening on port : %s", applicationName, serverPort);
    }

    @GetMapping
    public QuizzResponse getQuizz(@RequestParam(name = "id") String id) {
        return new QuizzResponse(this.quizzService.getQuizzById(id));
    }

    @PostMapping
    public QuizzResponse registerQuizz(@RequestBody QuizzRequest quizzRequest) {
        return new QuizzResponse(this.quizzService.saveQuizz(quizzRequest));
    }

    @DeleteMapping
    public ResponseEntity deleteQuizz(@RequestParam(name = "quizzId") String quizzId) {
        this.quizzService.deleteQuizz(quizzId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuizzQuestionsResponse>> questions() {
        return ResponseEntity.ok(
                new ArrayList<>(
                        this.quizzService.getQuestions().stream().map(QuizzQuestionsResponse::new).collect(Collectors.toList())
                ));
    }

    @GetMapping("/quizzQuestions")
    public ResponseEntity<List<QuizzResponse>> quizzQuestions() {
        return ResponseEntity.ok(
                new ArrayList<>(
                        this.quizzService.getRandomQuestions().stream().map(QuizzResponse::new).collect(Collectors.toList())
                ));
    }

    @GetMapping("/check-answers")
    public int checkAnswers(@RequestBody List<UserAnswersRequest> userAnswersRequests) {
        return this.quizzService.checkAnswers(userAnswersRequests);
    }

}
