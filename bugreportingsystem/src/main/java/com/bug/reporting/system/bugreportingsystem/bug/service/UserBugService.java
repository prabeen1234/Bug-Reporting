package com.bug.reporting.system.bugreportingsystem.bug.service;


import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.auth.repository.UserRepository;
import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.repository.BugRepository;
import com.bug.reporting.system.bugreportingsystem.firebase.FirebaseConfiguration;
import com.bug.reporting.system.bugreportingsystem.shared.MessageConstant;
import com.bug.reporting.system.bugreportingsystem.shared.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBugService {
    private final BugRepository bugRepository;
    private final ModelMapper modelMapper;
    private final FirebaseConfiguration firebaseConfiguration;
    private final UserRepository userRepository;
    public UserResponse addBugByUser(BugDto bugDto, MultipartFile photo, MultipartFile video) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = userRepository.findByEmail(authentication.getName());
        Timestamp userBugPostTime = new Timestamp(System.currentTimeMillis());
        String fileName = firebaseConfiguration.upload(photo);
        String fileName1 = firebaseConfiguration.upload(video);
        Bug bug = modelMapper.map(bugDto, Bug.class);
        bug.setTimeStamp(new Date(System.currentTimeMillis()));
        bug.setPhoto(fileName);
        bug.setVideo(fileName1);
        bug.setUser(optionalUser.get());
        bug.setTimeStamp(userBugPostTime);
        modelMapper.map(bugRepository.save(bug), BugDto.class);
        return new UserResponse(MessageConstant.SUCCESSFULLY_SAVE);
    }

    public List<BugDto> getAllBugs(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by("timeStamp").descending());
        Page<Bug> page = bugRepository.findAll(p);
        List<Bug> bugList = page.getContent();
        return BugDto.mapToBugDto(bugList);
    }
}
