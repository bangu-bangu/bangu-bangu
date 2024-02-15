package com.github.bbooong.bangumall.core;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("/validation/request-body")
    public void validationRequestBody(@Valid @RequestBody final TestValidationRequest request) {}

    @GetMapping("/validation/path-variable/{id}")
    public void validationPathVariable(@PathVariable @Positive final long id) {}

    @GetMapping("/validation/request-param")
    public void validationRequestParam(@RequestParam("id") @Positive final long id) {}
}
