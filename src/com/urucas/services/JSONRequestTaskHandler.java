package com.urucas.raddio.services;

/**
* @copyright Urucas
* @license   Copyright (C) 2013. All rights reserved
* @version   Release: 1.0.0
* @link       http://urucas.com
* @developers Bruno Alassia, Pamela Prosperi
*/

import org.json.JSONObject;

public interface JSONRequestTaskHandler {

	public void onSuccess(JSONObject result);
	public void onError(String message);
}
