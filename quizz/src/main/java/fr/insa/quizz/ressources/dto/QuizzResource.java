package fr.insa.quizz.ressources.dto;

import fr.insa.quizz.services.QuizzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quizz")
public class QuizzResource extends CommonResource {

    @Autowired
    QuizzService quizzService;

    @GetMapping
    public QuizzResponse getQuizz(@RequestParam(num = "num") int num) {
        return new QuizzResponse(this.quizzService.getQuizzByNumber(num))
    }
}
