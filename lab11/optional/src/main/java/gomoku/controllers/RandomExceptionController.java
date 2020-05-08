package gomoku.controllers;

import gomoku.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RandomExceptionController {

    @GetMapping("randomException")
    public void throwException() {
        throw new ApiException("random exception thrown");
    }

}
