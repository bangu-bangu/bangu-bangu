package com.github.bbooong.bangumall.core;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @PostMapping("/validation")
    public void validation(@Valid @RequestBody TestValidationRequest request) {}
}
