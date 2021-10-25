package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author PF.Tian
 * @since 2021/10/24
 */
@Data
public class UserInfo {

    @Id
    private Long id;

    private Integer age;

    private String name;

}
