package com.bug.reporting.system.bugreportingsystem.bug.service;

import com.bug.reporting.system.bugreportingsystem.auth.entity.User;
import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.bug.reporting.system.bugreportingsystem.bug.model.BugDto;
import com.bug.reporting.system.bugreportingsystem.bug.repository.BugRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class UserBugServiceTest {
    @Mock
    private BugRepository bugRepository;

    @InjectMocks
    private UserBugService userBugService;
    @Test
    void getAllBugs() {
        List<Bug> sampleBug=Arrays.asList(
                new Bug(1,"Bug1","Description",new Date(),"photo1.png","video.png",new User()),
                new Bug(2, "Bug2", "Description2", new Date(), "photo2.jpg", "video2.mp4", new User())
        );
        when(bugRepository.findAll()).thenReturn(sampleBug);
        var bugList=userBugService.getAllBugs();
        assertThat(bugList).isNotNull();


    }
}