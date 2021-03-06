/**
 * 
 */
package com.domker.marmot.device;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Response.Listener;
import com.domker.marmot.config.ConfigManager;
import com.domker.marmot.log.MLog;
import com.domker.marmot.net.ResponseResult;
import com.domker.marmot.net.Urls;
import com.domker.marmot.net.WatcherNet;
import com.domker.marmot.net.WatcherRequest;

/**
 * 设备管理器，初始化一些标识等
 * 
 * @author wanlipeng
 * @date 2017年1月4日 下午7:15:12
 */
public final class DeviceManager {
	public static final int DEFAULT_DELAY_TIME = 60 * 60 * 1000;
	
	private Context mContext = null;
	private ConfigManager mConfigManager = null;
	private DeviceUtils mDeviceInfo = null;
	
	public DeviceManager(Context context) {
		this.mContext = context;
		mDeviceInfo = new DeviceUtils(context);
		mConfigManager = ConfigManager.getInstance();
	}
	
	/**
	 * 初始化检测，决定初始化和是否执行
	 */
	public void checkList() {
		if (checkUid()) {
			// 注册设备信息
			long d = System.currentTimeMillis() - mConfigManager.getDeviceTime();
			if (d >= DEFAULT_DELAY_TIME) {
				deviceRegister();
			}
		}
	}
	
	/**
	 * 检测Uid是否存在，不存在则生成一个
	 * 
	 * @return 如果返回false，则不合法，生成一个新的uid，完成初始化的一系列操作
	 */
	private boolean checkUid() {
		String uid = mConfigManager.getUid();
		if (TextUtils.isEmpty(uid) || !mDeviceInfo.isCorrectUid(uid)) {
			uid = mDeviceInfo.createUid();
			mConfigManager.setUid(uid);
			return mDeviceInfo.isCorrectUid(uid);
		}
		MLog.i("uid = " + uid);
		return true;
	}
	
	private void deviceRegister() {
		DeviceRegister r = mDeviceInfo.createDeviceRegister();
		r.setUid(mConfigManager.getUid());
		Listener<ResponseResult> listener = new Listener<ResponseResult>() {

			@Override
			public void onResponse(ResponseResult result) {
				MLog.i("onResponse", result.toString());
				if (result.isSucceed()) {
					mConfigManager.setDeviceTime(System.currentTimeMillis());
				}
			}
		};
		WatcherRequest request = WatcherRequest.create(Urls.DEVICE_REGISTER, r, listener);
		WatcherNet.getInstance().addRequest(request);
	}
}
