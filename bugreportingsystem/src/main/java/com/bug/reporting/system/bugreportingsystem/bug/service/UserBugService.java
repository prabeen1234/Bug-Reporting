package com.bug.reporting.system.bugreportingsystem.bug.service;


import com.bug.reporting.system.bugreportingsystem.auth.entity.Role;
import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.auth.repository.UserRepository;
import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.model.PickDto;
import com.bug.reporting.system.bugreportingsystem.bug.repository.BugRepository;
import com.bug.reporting.system.bugreportingsystem.exception.UserNotFoundException;
import com.bug.reporting.system.bugreportingsystem.firebase.FirebaseConfiguration;
import com.bug.reporting.system.bugreportingsystem.shared.MessageConstant;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBugService {
    private final BugRepository bugRepository;
    private final FirebaseConfiguration firebaseConfiguration;
    private final UserRepository userRepository;


    public UserResponse addBugByUser(BugDto bugDto, MultipartFile photo, MultipartFile video) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
            if (optionalUser.isEmpty()) {
                throw new UserNotFoundException(MessageConstant.USER_NOT_FOUND);
            }
            Timestamp userBugPostTime = new Timestamp(System.currentTimeMillis());
            String fileName = firebaseConfiguration.upload(photo);
            String fileName1 = firebaseConfiguration.upload(video);
            Bug bug = new Bug();
            bug.setTimeStamp(new Date(System.currentTimeMillis()));
            bug.setPhoto(fileName);
            bug.setVideo(fileName1);
            bug.setBugTitle(bugDto.getBugTitle());
            bug.setBugDescription(bugDto.getBugDescription());
            bug.setUser(optionalUser.get());
            bug.setTimeStamp(userBugPostTime);
            bugRepository.save(bug);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return new UserResponse(MessageConstant.SUCCESSFULLY_SAVE);
    }

    public List<BugDto> getAllBugs() {
        List<Bug> bug = bugRepository.findAll();
        return BugDto.mapToBugDto(bug);
    }

    public void deleteBugDetailAfterAdminPick(Integer bugId) {
        Optional<Bug> optionalBug = bugRepository.findById(bugId);
        optionalBug.ifPresent(bugRepository::delete);
    }



}
