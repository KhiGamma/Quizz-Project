package fr.insa.quizz.ressources;

import fr.insa.quizz.models.Quizz;
import fr.insa.quizz.ressources.dto.QuizzResponse;
import fr.insa.quizz.ressources.dto.QuizzRequest;
import fr.insa.quizz.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quizz")
public class QuizzResource extends CommonResource {

    @Autowired
    QuizzService quizzService;

    @GetMapping
    public QuizzResponse getQuizz(@RequestParam(name = "id") String id) {
        return new QuizzResponse(this.quizzService.getQuizzById(id));
    }

    @PostMapping
    public QuizzResponse registerQuizz(@RequestBody QuizzRequest quizzRequest) {
        return new QuizzResponse(this.quizzService.saveQuizz(quizzRequest));
    }

    @GetMapping("/test/{id}")
    public boolean test(@PathVariable("id") int id) {
        return this.quizzService.test(id);
    }
}
