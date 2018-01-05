package com.jm.unicom.api.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "t_user")
public class User extends Basic implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    private String uuid;


    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String age;

    @Column(columnDefinition = "int(10) COMMENT '用户状态'")
    private Integer status;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.MERGE)//立即从数据库中进行加载数据;
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "user_uuid")}, inverseJoinColumns = {@JoinColumn(name = "role_uuid")})
    private Set<Role> roles = new HashSet<Role>();
}

