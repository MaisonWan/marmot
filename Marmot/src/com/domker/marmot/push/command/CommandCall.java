/**
 * 
 */
package com.domker.marmot.push.command;

import org.json.JSONObject;

/**
 * 
 * @author wanlipeng
 * @date 2017年2月16日 下午6:01:12
 */
public class CommandCall extends CommandData {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.domker.marmot.push.command.CommandData#warp(org.json.JSONObject)
	 */
	@Override
	public CommandData warp(JSONObject json) {
		return this;
	}

}
