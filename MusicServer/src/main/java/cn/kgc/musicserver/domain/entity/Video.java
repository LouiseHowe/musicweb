package cn.kgc.musicserver.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("vedio") // 注意表名vedio可能有拼写错误，应与数据库完全一致
public class Video {
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("v_name")
    private String title;

    @TableField("v_author")
    private String author;

    @TableField("v_pic")
    private String coverUrl;

    @TableField("regionid")
    private Integer regionId;

    @TableField("versionid")
    private Integer versionId;

    @TableField("v_createtime")
    private Date createTime;
}
