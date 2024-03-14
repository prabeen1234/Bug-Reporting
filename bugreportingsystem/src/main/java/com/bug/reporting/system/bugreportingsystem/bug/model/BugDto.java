package com.bug.reporting.system.bugreportingsystem.bug.model;

import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BugDto {
    public  Integer bugId;
    @JsonAlias("bug_title")
    private String bugTitle;
    @JsonAlias("bug_description")
    private String bugDescription;
    private String photo;
    private String video;
    private Date timeStamp;
    private Integer userId;
    private String email;
    public static List<BugDto> mapToBugDto(List<Bug> bugList) {
        List<BugDto> bugDtoList = new ArrayList<>();
        bugList.forEach(val -> {
            bugDtoList.add(bugDto(val));
        });
        return bugDtoList;
    }

    public static BugDto bugDto(Bug bug) {
        return BugDto
                .builder()
                .bugId(bug.getBugId())
                .userId(bug.getUser().getUserId())
                .bugTitle(bug.getBugTitle())
                .bugDescription(bug.getBugDescription())
                .timeStamp(bug.getTimeStamp())
                .photo(bug.getPhoto())
                .video(bug.getVideo())
                .email(bug.getUser().getEmail())
                .build();
    }


}
