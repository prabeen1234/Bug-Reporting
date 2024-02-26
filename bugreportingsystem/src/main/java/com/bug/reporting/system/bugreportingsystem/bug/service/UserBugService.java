package com.bug.reporting.system.bugreportingsystem.bug.service;


import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.bug.reporting.system.bugreportingsystem.bug.repository.BugRepository;
import com.bug.reporting.system.bugreportingsystem.firebase.FirebaseConfiguration;
import com.bug.reporting.system.bugreportingsystem.shared.MessageConstant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBugService {
    private final BugRepository bugRepository;
    private final ModelMapper modelMapper;
    private final FirebaseConfiguration firebaseConfiguration;

    public ResponseEntity<?> addBugByUser(BugDto bugDto, MultipartFile photo, MultipartFile video) {
       String fileName= firebaseConfiguration.upload(photo);
       String fileName1= firebaseConfiguration.upload(video);
        Bug bug = modelMapper.map(bugDto, Bug.class);
        bug.setTimeStamp(new Date(System.currentTimeMillis()));
        bug.setPhoto(fileName);
        bug.setVideo(fileName1);
        modelMapper.map(bugRepository.save(bug), BugDto.class);
        return ResponseEntity.ok().body(MessageConstant.successMessage);
    }

    public ResponseEntity<?> getAllBugs(Integer pageNumber, Integer pageSize) {
        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by("timeStamp"));
        Page<Bug> page = bugRepository.findAll(p);
        List<Bug> bugList = page.getContent();
        List<BugDto> bugDtoList = BugDto.mapToBugDto(bugList);
        if (bugDtoList.isEmpty()) {
            return ResponseEntity.badRequest().body("bug is not found");
        }
        return ResponseEntity.ok(bugDtoList);
    }
}
