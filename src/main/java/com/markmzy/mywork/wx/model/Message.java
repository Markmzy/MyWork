package com.markmzy.mywork.wx.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @title: Message
 * @Author Zhiyue Ma
 * @Date: 7/22/21 17:19
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Message对象", description = "消息表")
@Document(collection = "message")
public class Message implements Serializable
{
    @Id
    private String _id;

    @Indexed
    private Integer senderId;

    private String senderPhoto = "https://zhiyuem1-1257317681.cos.ap-shenzhen-fsi.myqcloud.com/system.jpg";

    private String senderName;

    private String msg;

    @Indexed
    private Date sendTime;
}
