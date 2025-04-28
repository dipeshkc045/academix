package org.techalix.identifierservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/uuid")
public class UuidController {

    @GetMapping("/generate")
    public Map<String, String> generateUuid() {
        String uuid = UUID.randomUUID().toString();
        return Map.of("uuid", uuid);
    }
}
