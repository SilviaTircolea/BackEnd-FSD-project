package com.twitter.app.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCommon {

    @JsonIgnoreProperties({"sltBbId", "nic", "mobile", "landPhone", "userAttempts", "status"})
    private User user;

}
