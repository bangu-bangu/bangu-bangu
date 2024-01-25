package com.github.bbooong.bangumall.member.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping
    public ResponseEntity<Void> createMember() {
        final Long memberId = 777L;

        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }
}
