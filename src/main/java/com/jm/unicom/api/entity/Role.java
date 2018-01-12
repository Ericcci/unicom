package com.jm.unicom.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
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
@NoArgsConstructor
@Entity
@Table(name = "t_role")
@JsonIdentityInfo(generator = JSOGGenerator.class)
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

    @JsonBackReference("role")
    @ManyToMany
    @JoinTable(name = "t_user_role", joinColumns = {@JoinColumn(name = "role_uuid")}, inverseJoinColumns = {@JoinColumn(name = "user_uuid")})
    private Set<User> userSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "role_uuid")}, inverseJoinColumns = {@JoinColumn(name = "permission_uuid")})
    private Set<Permission> permissionSet = new HashSet<Permission>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Role role = (Role) o;

        return uuid.equals(role.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
