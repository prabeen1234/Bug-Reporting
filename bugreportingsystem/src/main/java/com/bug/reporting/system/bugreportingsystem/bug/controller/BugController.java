package com.bug.reporting.system.bugreportingsystem.bug.controller;

import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.service.UserBugService;
import com.bug.reporting.system.bugreportingsystem.shared.ApiURL;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BugController {
    private final UserBugService userBugService;

    @PostMapping(value = ApiURL.USER_SAVE_BUG)
    public ResponseEntity<UserResponse> addBug(@RequestPart BugDto bugDto, @RequestPart(name = "photo", required = false) MultipartFile photo, @RequestPart(name = "photo", required = false) MultipartFile video) {
        UserResponse response = userBugService.addBugByUser(bugDto, photo, video);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(ApiURL.ADMIN_GET_BUG)
    public List<BugDto> getAllBug() {
        return userBugService.getAllBugs();
    }

    @DeleteMapping(ApiURL.ADMIN_GET_BUG)
    public void deleteBug(@RequestParam Integer id) {
        userBugService.deleteBugDetailAfterAdminPick(id);
    }
}
