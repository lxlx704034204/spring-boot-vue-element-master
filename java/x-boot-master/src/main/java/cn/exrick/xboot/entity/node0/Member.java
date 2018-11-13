package cn.exrick.xboot.entity.node0;

import cn.exrick.xboot.base.XbootBaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author liangxin
 * @since 02/09/2018
 */
@Data
@Entity
@Table(name = "node0_user")
@TableName("node0_user")
public class Member extends XbootBaseEntity {


    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Temporal(TemporalType.DATE)
    public Date date = new Date();

    public String name;

    public Integer sex;

    public String address;


}
