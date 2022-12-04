package ru.qmbo.mc3.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Message
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 02.12.2022
 */
@Accessors(chain = true)
@Data
public class Message {
    private Integer id;
    private Integer sessionId;
    private Date mc1Timestamp;
    private Date mc2Timestamp;
    private Date mc3Timestamp;
    private Date endTimestamp;
}
