package com.jm.unicom.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Role
 *
 * @author Eric
 * @date 2017/12/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "role-uuid", strategy = "uuid")
    @GeneratedValue(generator = "role-uuid")
    private String uuid;

    @Column(columnDefinition = "varchar(100) COMMENT '角色名称'")
    private String roleName;

    @Column(columnDefinition = "varchar(100) COMMENT '角色描述'")
    private String roleDescription;

    /**
     * 角色的权限
     */
    @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "role_uuid")}, inverseJoinColumns = {@JoinColumn(name = "permission_uuid")})
    private Set<Permission> permissions = new HashSet<Permission>();
}
