package com.kosmo.funfunhaejwo.jpa.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main_FriendVo {
    private String profileImg;
    private String username;
    private long friendId;
}
