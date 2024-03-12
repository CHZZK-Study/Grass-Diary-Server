package chzzk.grassdiary.web.controller.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/api/example")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String example() {
        return "ok";
    }
}
