package org.springframework.debug.bean;

import lombok.ToString;

/**
 * @create 2023/11/11 11:58
 * @description
 */
@ToString
public class User {
	private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
