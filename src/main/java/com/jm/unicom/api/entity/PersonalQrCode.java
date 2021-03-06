package com.jm.unicom.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <b>Description:</b><br>
 *
 * @author Eric.
 * @version 1.0
 *          <b>ProjectName:</b> unicom
 *          <br><b>PackageName:</b> com.jm.unicom.api.shop.entity
 *          <br><b>Date:</b> 2018/1/4 11:08
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_personal_qrcode")
@JsonIdentityInfo(generator = JSOGGenerator.class)
public class PersonalQrCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "uuid", columnDefinition = "varchar(50) COMMENT '主键'", unique = true)
    @GenericGenerator(name = "personalqrcode-uuid", strategy = "uuid")
    @GeneratedValue(generator = "personalqrcode-uuid")
    private String uuid;

    @JsonBackReference("personal_qrcode")
    @ManyToOne
    private Shop shop;

    @Column(nullable = false, columnDefinition = "int(5) COMMENT '二维码类型  0:微信 1:支付宝'")
    private Integer imgType;

    @Column(nullable = false, columnDefinition = "longtext COMMENT 'base64二维码'")
    private String imgData;

    @Column(columnDefinition = "varchar(200) COMMENT '备注信息'")
    private String description;

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

        PersonalQrCode that = (PersonalQrCode) o;

        return uuid.equals(that.uuid);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + uuid.hashCode();
        return result;
    }
}
