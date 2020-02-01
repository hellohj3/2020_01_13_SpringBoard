package com.springboard.user;

import java.util.Date;

/**
 * Class for holding user information
 * @author MyHyem
 * @since 2020.01.20
 * @version 1.0
 * @see
 *
 * <pre>
 * << Modification Information >>
 *
 *   Modified date       Modifier              Modifications
 *  --------------      ----------       ---------------------------
 *  2020.01.20          MyHyem              Initial Creation
 *  </pre>
 */
public class UserVO {
    private int idx;
    private String id;
    private String password;
    private String name;
    private Date regDate;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "idx=" + idx +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", regDate='" + regDate + '\'' +
                '}';
    }
}
