package com.rhinobyte.reactnative.appcenterdistribute;

import android.util.Log;

class RNAppCenterDistributeUtils {
	private static final String LOG_TAG = "AppCenterDistribute";

	static void logDebug(String message) {
		Log.d(LOG_TAG, message);
	}

	static void logError(String message) {
		Log.e(LOG_TAG, message);
	}

	static void logInfo(String message) {
		Log.i(LOG_TAG, message);
	}

}
