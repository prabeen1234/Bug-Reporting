package com.bug.reporting.system.bugreportingsystem.bug.model;

import com.bug.reporting.system.bugreportingsystem.bug.entity.Bug;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BugDto {
    @JsonProperty("bug_title")
    private String bugTitle;
    @JsonProperty("bug_description")
    private String bugDescription;
    private String photo;
    private String video;
    private Date timeStamp;

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
                .bugTitle(bug.getBugTitle())
                .bugDescription(bug.getBugDescription())
                .timeStamp(bug.getTimeStamp())
                .photo(bug.getPhoto())
                .video(bug.getVideo())
                .build();
    }

    public Bug toBug() {
        Bug bug = new Bug();
        bug.setBugTitle(bug.getBugTitle());
        bug.setBugDescription(bug.getBugDescription());
        return bug;
    }
}
