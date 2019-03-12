package com.rhinobyte.reactnative.appcenterdistribute;

import android.app.Application;
import android.util.Base64;
import android.util.Log;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.reactnative.shared.AppCenterReactNativeShared;
import com.microsoft.appcenter.utils.async.AppCenterConsumer;


@SuppressWarnings("WeakerAccess")
public class RNAppCenterDistributeModule extends BaseJavaModule {

	public RNAppCenterDistributeModule(
		Application application,
		DistributeListener distributeListener,
		boolean enableForDebuggableBuild,
		CustomizedDistributeService.TriggerMode triggerMode
	)
	{
		// The appcenter sdk documentation isn't clear about this but all of the static methods
		// use a singleton instance so it's safe (and necessary) to use these before calling
		// AppCenter.start(..)
		CustomizedDistributeService.setEnabledForDebuggableBuild(enableForDebuggableBuild);
		CustomizedDistributeService.setTriggerMode(triggerMode);

		if(distributeListener != null) {
			CustomizedDistributeService.setListener(distributeListener);
		}

		AppCenterReactNativeShared.configureAppCenter(application);
		if (AppCenter.isConfigured()) {
			AppCenter.start(CustomizedDistributeService.class);
		}
	}


	@Override
	public String getName() {
		return "RNAppCenterDistribute";
	}



	@ReactMethod
	public void checkForUpdate() {
		CustomizedDistributeService.checkForUpdate();
	}

	@ReactMethod
	public void setEnabled(boolean enabled, final Promise promise) {
		CustomizedDistributeService.setEnabled(enabled).thenAccept(new AppCenterConsumer<Void>() {
			@Override
			public void accept(Void result) {
				promise.resolve(result);
			}
		});
	}

	@ReactMethod
	public void isEnabled(final Promise promise) {
		CustomizedDistributeService.isEnabled().thenAccept(new AppCenterConsumer<Boolean>() {

			@Override
			public void accept(Boolean enabled) {
				promise.resolve(enabled);
			}
		});
	}

}
