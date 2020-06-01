package com.zjl.washer.DAO;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ManagerInfo {

    @Id
    private String managerId;

    private String managerName;

    private String password;

    private String openId;
}
