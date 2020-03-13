package net.canway.demo.mvc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class User {

    @NotBlank(message = "id不能为空", groups = UpdateValidated.class)
    private String id;

    private String fullname;

    private String username;

    /**
     * 忽略字段序列化和反序列化，双向的。
     * 如果希望在一方忽略，那就在对应的get、set方法上标注
     */
    @JsonIgnore
    private String password;

    /**
     * 自定时间序列化格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Email(message = "邮箱校验不通过", groups = {UpdateValidated.class, SaveValidated.class})
    @NotNull(message = "邮箱不能为空", groups = {SaveValidated.class})
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public interface SaveValidated {
    }

    public interface UpdateValidated {
    }

}
