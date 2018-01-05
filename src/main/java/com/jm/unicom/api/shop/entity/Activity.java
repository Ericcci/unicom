package com.jm.unicom.api.shop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Activity
 *
 * @author Eric
 * @date 2018/1/3
 */
@Data
@Entity
@Table(name = "t_activity")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Activity {
    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "activity-uuid", strategy = "uuid")
    @GeneratedValue(generator = "activity-uuid")
    private String uuid;

    private String title;

    private Date startTime;

    private Date endTime;

    @Column(columnDefinition = "int(5) default 1 COMMENT '活动状态 1:有效  -1:无效'")
    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.REMOVE)
    private Set<Prize> prizeSet = new HashSet<>();
}
