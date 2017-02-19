/**
 * 
 */
package com.domker.marmot.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 配置管理中心
 * 
 * @author wanlipeng
 * @date 2017年1月4日 下午6:12:56
 */
public final class ConfigManager implements ConfigConst {
	private static ConfigManager manager = null;
	
	private SharedPreferences.Editor mEditor = null;
	private SharedPreferences mSharedPref = null;
	
	/**
	 * 需要最先调用这个方法，建议在Application中
	 * 
	 * @param context 建议传递ApplicationContext
	 */
	public static void init(Context context) {
		if (manager == null) {
			manager = new ConfigManager(context);
		}
	}
	
	public static ConfigManager getInstance() {
		if (manager == null) {
			throw new RuntimeException("ConfigManager do not init.");
		}
		return manager;
	}
	
	private ConfigManager(Context context) {
		int mode = Context.MODE_PRIVATE;
		mSharedPref = context.getSharedPreferences("marmot", mode);
		mEditor = mSharedPref.edit();
	}
	
	/**
	 * 获取唯一标识
	 * @return
	 */
	public String getUid() {
		return mSharedPref.getString(UID, UID_DEFAULT);
	}
	
	/**
	 * 设置保存唯一ID
	 * 
	 * @param uid
	 */
	public void setUid(String uid) {
		mEditor.putString(UID, uid);
		mEditor.commit();
	}
	
	/**
	 * 获取短信上传的时间
	 * @return
	 */
	public long getSmsTime() {
		return mSharedPref.getLong(SMS_TIME_LINE, SMS_TIME_LINE_DEFAULT);
	}
	
	/**
	 * 设置短信时间点，保证这个时间点之前的短信都上传完毕
	 * @param smsTime
	 */
	public void setSmsTime(long smsTime) {
		mEditor.putLong(SMS_TIME_LINE, smsTime);
		mEditor.commit();
	}
	
	/**
	 * 获取通话上传的时间
	 * @return
	 */
	public long getCallTimeline() {
		return mSharedPref.getLong(CALL_TIME_LINE, CALL_TIME_LINE_DEFAULT);
	}
	
	/**
	 * 设置通话时间点，保证这个时间点之前的通话都上传完毕
	 * @param smsTime
	 */
	public void setCallTimeline(long callTime) {
		mEditor.putLong(CALL_TIME_LINE, callTime);
		mEditor.commit();
	}
}
