package com.jm.unicom.api.shop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * Prize
 *
 * @author Eric
 * @date 2018/1/3
 */
@Data
@Entity
@Table(name = "t_prize")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class Prize {
    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'")
    @GenericGenerator(name = "prize-uuid", strategy = "uuid")
    @GeneratedValue(generator = "prize-uuid")
    private String uuid;

    @ManyToOne
    private Activity activity;

    @Column(columnDefinition = "int(10) default 0 COMMENT '奖品类型 0:虚拟物品 1:实物'")
    private Integer type;

    @Column(columnDefinition = "varchar(200) COMMENT '奖品名称'")
    private String prizeName;

    @Column(columnDefinition = "int(5) COMMENT '奖品等级 -1:谢谢惠顾 0:特等奖 1:一等奖 2:二等奖 3:三等奖 4:纪念奖'")
    private Integer level;

    @Column(columnDefinition = "decimal(2,2) COMMENT '中奖率'")
    private Double percentage;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Prize prize = (Prize) o;

        return uuid.equals(prize.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
