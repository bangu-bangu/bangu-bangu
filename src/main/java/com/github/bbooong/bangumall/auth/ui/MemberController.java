package com.github.bbooong.bangumall.auth.ui;

import com.github.bbooong.bangumall.auth.dto.MemberCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody final MemberCreateRequest request) {
        final Long memberId = 777L;

        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }
}
