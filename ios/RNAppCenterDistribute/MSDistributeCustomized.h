/**
 * Originally based on https://github.com/Microsoft/AppCenter-SDK-Apple/blob/develop/AppCenterDistribute/AppCenterDistribute/MSDistribute.h
 * Raw: https://raw.githubusercontent.com/Microsoft/AppCenter-SDK-Apple/develop/AppCenterDistribute/AppCenterDistribute/MSDistribute.h
 *
 * Their license file: https://github.com/Microsoft/AppCenter-SDK-Apple/blob/develop/LICENSE
 *
 * Visual Studio App Center SDK for Apple platforms
 * Copyright (c) Microsoft Corporation
 *
 * All rights reserved.
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED *AS IS*, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *
 *
 * We need to customize the module in order to allow for the following behavior:
 * 1. Allow the consumer to customize the trigger behavior.
 *     a. Automatic - Same behavior as the normal distribute sdk. It will handle the version checks, download, and update notification automatically.
 *     b. ManuallyTriggerUpdateDialog - Automatically start the distribute workflow. If an update is required, delay calling onReleaseAvailable or showing
 *        the update dialog until the react-native module's checkForUpdate() method is called.
 *     c. Manual - Don't start the distribute workflow until the react-native module's checkForUpdate() method is called.
 *
 * 2. Allow the consumer to enable the distribute feature when a debugger is attached, if they wish.
 */

#import <AppCenterDistribute/MSDistributeDelegate.h>
#import <AppCenterDistribute/MSServiceAbstract.h>

NS_ASSUME_NONNULL_BEGIN

typedef enum DistributeTriggerMode {
	TriggerModeAutomatic,
	TriggerModeManual,
	TriggerModeManuallyTriggerUpdateDialog
}

/**
 * App Center Distribute service.
 */
@interface MSDistributeCustomzied : MSServiceAbstract

/**
 * Set a Distribute delegate
 *
 * @param delegate A Distribute delegate.
 *
 * @discussion If Distribute delegate is set and releaseAvailableWithDetails is returning <code>YES</code>, you must call
 * notifyUpdateAction: with one of update actions to handle a release properly.
 *
 * @see releaseAvailableWithDetails:
 * @see notifyUpdateAction:
 */
+ (void)setDelegate:(id<MSDistributeDelegate>)delegate;

/**
 * Notify SDK with an update action to handle the release.
 */
+ (void)notifyUpdateAction:(MSUpdateAction)action;

/**
 * Change The URL that will be used for generic update related tasks.
 *
 * @param apiUrl The new URL.
 */
+ (void)setApiUrl:(NSString *)apiUrl;

/**
 * Change the base URL that is used to install update.
 *
 * @param installUrl The new URL.
 */
+ (void)setInstallUrl:(NSString *)installUrl;

/**
 * Change the enableForDebugBuild flag.
 * Flag defaults to false.
 *
 * @param enableForDebugBuild Whether or not the distribute feature should be enabled for a debug build or when a debugger is attached.
 */
+ (void)setEnabledForDebugBuild:(BOOL)enabledForDebugBuild;


/**
 * Change the trigger mode that can be used to initiate the update check and display dialogs
 *
 * @param triggerMode The new triggerMode to use.
 */
+ (void)setTriggerMode:(DistributeTriggerMode)triggerMode;


/**
 * Manually check for updates or display the dialogs.
 */
+ (void)checkForUpdate;




/**
 * Process URL request for the service.
 *
 * @param url  The url with parameters.
 *
 * @return `YES` if the URL is intended for App Center Distribute and your application, `NO` otherwise.
 *
 * @discussion Place this method call into your app delegate's openURL method.
 */
+ (BOOL)openURL:(NSURL *)url;

@end

NS_ASSUME_NONNULL_END
