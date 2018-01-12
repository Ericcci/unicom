package com.jm.unicom.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Permission
 *
 * @author Eric
 * @date 2017/12/27
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_permission")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "permission-uuid", strategy = "uuid")
    @GeneratedValue(generator = "permission-uuid")
    private String uuid;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '链接地址'")
    private String urlAddress;

    @Column(columnDefinition = "varchar(200) COMMENT '链接描述'")
    private String urlDescription;

    @Column(nullable = false, columnDefinition = "varchar(200) COMMENT '具备的权限'")
    private String permissionInit;

    @Column(nullable = false, columnDefinition = "int(10) COMMENT '排序'")
    private Integer sort;

    @JsonBackReference("permission")
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission", joinColumns = @JoinColumn(name = "permission_uuid"), inverseJoinColumns = @JoinColumn(name = "role_uuid"))
    private Set<Role> roleSet = new HashSet<>();

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

        Permission that = (Permission) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
