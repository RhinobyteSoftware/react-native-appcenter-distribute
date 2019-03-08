

#import "RNAppCenterDistribute.h"

#import <React/RCTAssert.h>
#import <React/RCTBridgeModule.h>
#import <React/RCTConvert.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTRootView.h>
#import <React/RCTUtils.h>

//#import "RNAppCenterDistributeUtils.h"
//#import "RNAppCenterDistributeDelegate.h"

#import <AppCenter/MSAppCenter.h>
#import <AppCenterReactNativeShared/AppCenterReactNativeShared.h>

#import "MSDistributeCustomized.h"

@interface RNAppCenterDistribute () <RCTBridgeModule>

@end

@implementation RNAppCenterDistribute


RCT_EXPORT_MODULE();

+ (void)register
{
	[MSDistributeCustomized setEnabledForDebugBuild:false];
	[MSDistributeCustomized setTriggerMode:TriggerModeManual];

	[AppCenterReactNativeShared configureAppCenter];
	if ([MSAppCenter isConfigured]) {
		[MSAppCenter startService:[MSDistributeCustomized class]];
	}
}

+ (void)registerAdvance:(BOOL)enabledForDebugBuild
			triggerMode:(DistributeTriggerMode)triggerMode
	 distributeDelegate:(id<MSDistributeDelegate>)distributeDelegate
{
	[MSDistributeCustomized setEnabledForDebugBuild:enabledForDebugBuild];
	[MSDistributeCustomized setTriggerMode:triggerMode];
	[MSDistributeCustomized setDelegate:distributeDelegate];

	[AppCenterReactNativeShared configureAppCenter];
	if ([MSAppCenter isConfigured]) {
		[MSAppCenter startService:[MSDistributeCustomized class]];
	}
}



RCT_EXPORT_METHOD(checkForUpdate)
{
	[MSDistributeCustomized checkForUpdate];
}

RCT_EXPORT_METHOD(isEnabled:(RCTPromiseResolveBlock)resolve
				  rejecter:(RCTPromiseRejectBlock)reject)
{
	resolve([NSNumber numberWithBool:[MSDistributeCustomized isEnabled]]);
}

RCT_EXPORT_METHOD(setEnabled:(BOOL)shouldEnable
				  resolver:(RCTPromiseResolveBlock)resolve
				  rejecter:(RCTPromiseRejectBlock)reject)
{
	[MSDistributeCustomized setEnabled:shouldEnable];
	resolve(nil);
}

@end
