package com.bug.reporting.system.bugreportingsystem.firebase;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RequiredArgsConstructor
@RequestMapping("/app")
public class FireController {
    private final FirebaseConfiguration firebaseConfiguration;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile) {
        return firebaseConfiguration.upload(multipartFile);
    }
}
