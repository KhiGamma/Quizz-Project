package fr.insa.quizz.ressources;

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
    public QuizzResponse getQuizz(@RequestParam(name = "num") int num) {
        return new QuizzResponse(this.quizzService.getQuizzByNumber(num));
    }
}
