/**
 *
 */

#import <Foundation/Foundation.h>

#import <React/RCTEventEmitter.h>

@interface RNAppCenterDistribute : NSObject

+ (void)register;

+ (void)registerAdvance:(BOOL)enabledForDebugBuild
			triggerMode:(DistributeTriggerMode)triggerMode
	 distributeDelegate:(id<MSDistributeDelegate>)distributeDelegate;


@end
