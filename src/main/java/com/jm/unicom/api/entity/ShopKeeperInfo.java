package com.jm.unicom.api.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ShopKeeperInfo
 *
 * @author Eric
 * @date 2018/1/9
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_shopkeeper_info")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class ShopKeeperInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "shopkeeper-uuid", strategy = "uuid")
    @GeneratedValue(generator = "shopkeeper-uuid")
    private String uuid;

    @Column(columnDefinition = "varchar(200) COMMENT '登录账号'")
    private String userName;

    @Column(columnDefinition = "varchar(200) COMMENT '登录密码'")
    private String password;

    @JsonManagedReference("shopKeeperInfo")
    @OneToMany(mappedBy = "shopKeeperInfo", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private Set<Shop> shopQrCodeSet = new HashSet<>();
}
