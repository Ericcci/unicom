package com.jm.unicom.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User
 *
 * @author Eric
 * @date 2017/12/21
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_user", indexes = {@Index(name = "IDX_USER", columnList = "userName")})
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    private String uuid;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '用户名'")
    private String userName;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '密码'")
    private String password;

    @Column(columnDefinition = "int(5) COMMENT '删除标记  -1:删除 1:有效'")
    private Integer status = 1;

    @JsonManagedReference("shop")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Shop> shopSet = new HashSet<>();

    @ManyToMany(targetEntity = Role.class, cascade = CascadeType.MERGE)//立即从数据库中进行加载数据;
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "user_uuid")}, inverseJoinColumns = {@JoinColumn(name = "role_uuid")})
    private Set<Role> roles = new HashSet<Role>();

    public User(String uuid) {
        this.uuid = uuid;
    }
}

