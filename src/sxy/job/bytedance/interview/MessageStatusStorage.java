package sxy.job.bytedance.interview;

/**
 * 设计题：设计一个群消息已读/未读的存储机制。
 * 
 * 用户A发送一条群消息，用户A可以实时查看该消息的阅读情况，即群内阅读过该消息的是已读组，群内未阅读过该消息的是是未读组。需要考虑用户退群、新用户进群、
 * 用户退群又进入等情况，且需要额外空间开的尽量小。
 * 
 * 思路：布隆过滤器
 * 
 * @author Kevin
 * 
 */
public class MessageStatusStorage {

}
