/**
 * 
 */
package com.domker.marmot.watcher.sms;

/**
 * 短信实体类
 * 
 * @author Maison
 *
 */
public class SmsEntity {
	public String uid = null;
	/**
	 * 发件人地址，手机号
	 */
	public String number;
	/**
	 * 发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
	 */
	public String name;
	/**
	 * 短信内容
	 */
	public String body;
	/**
	 * 日期，long型，如1346988516，可以对日期显示格式进行设置
	 */
	public long date;
	/**
	 * protocol：协议0 SMS_RPOTO短信，1 MMS_PROTO彩信
	 */
	public int protocol;
	/**
	 * 是否阅读0未读，1已读
	 */
	public int read;
	/**
	 * type：短信类型1是接收到的，2是已发出
	 */
	public int type;
	/**
	 * 短信中心号码
	 */
	public String serviceCenter;
	
	@Override
	public String toString() {
		return "[number=" + number + ", name=" + name + ", body="
				+ body + ", date=" + date + ", protocol=" + protocol
				+ ", read=" + read + ", type=" + type + ", serviceCenter="
				+ serviceCenter + "]";
	}
	
}
