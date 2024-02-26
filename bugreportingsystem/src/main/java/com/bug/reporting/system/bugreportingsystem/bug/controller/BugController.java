package com.bug.reporting.system.bugreportingsystem.bug.controller;


import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.service.UserBugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/bug")
@RequiredArgsConstructor
public class BugController {
    private final UserBugService userBugService;

    @PostMapping("/save")
    public ResponseEntity<?> bugSave(@RequestPart BugDto bugDto, @RequestPart MultipartFile multipartFile, @RequestPart MultipartFile multipartFile1) {
        return userBugService.addBugByUser(bugDto, multipartFile, multipartFile1);
    }
    @GetMapping("/get_all_bug")
    public ResponseEntity<?> getAllBug(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return userBugService.getAllBugs(pageNumber, pageSize);
    }
}
