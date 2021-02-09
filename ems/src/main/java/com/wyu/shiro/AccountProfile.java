package com.wyu.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountProfile implements Serializable {

    private String userId;

    private String username;

    private String type;

    private String department;

}
