package com.bug.reporting.system.bugreportingsystem.bug.controller;

import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.service.UserBugService;
import com.bug.reporting.system.bugreportingsystem.shared.ApiURL;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BugController {
    private final UserBugService userBugService;

    @PostMapping(ApiURL.USER_SAVE_BUG)
    public UserResponse bugSave(@RequestPart BugDto bugDto, @RequestPart MultipartFile photo, @RequestPart MultipartFile multipartFile1) {
         return userBugService.addBugByUser(bugDto, photo, multipartFile1);
    }

    @GetMapping(ApiURL.ADMIN_GET_BUG)
    public  List<BugDto> getAllBug(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return userBugService.getAllBugs(pageNumber, pageSize);
    }
}
