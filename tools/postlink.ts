
import rnpmlink from 'appcenter-link-scripts';

// Configure Android first.
// tslint:disable-next-line:no-any
const getFormattedErrorMessage = (errorToFormat: any) => {
	let errorMessage = '';

	if(errorToFormat.stack) {
		// the stack trace will include the message at the top...
		errorMessage = errorToFormat.stack.toString();
	} else {
		// no stack trace so look for a message property
		errorMessage = ((errorToFormat.message || errorToFormat.Message || errorToFormat.error || errorToFormat.Error) || '').toString();
	}

	if(!errorMessage) {
		errorMessage = errorToFormat;
	}

	return errorMessage;
};

const configureAndroidAsync = async () => {
	try {
		if(!rnpmlink.android.checkIfAndroidDirectoryExists()) {
			console.log('No android directory found. Skipping android configuration of react-native-appcenter-distribute.');
			return;
		}

		console.log('Configuring react-native-appcenter-distribute for Android');
		await rnpmlink.android.initAppCenterConfig();

		await rnpmlink.android.removeAndroidDuplicateLinks();
	} catch(asyncError) {
		console.error('\nERROR - Unexpected error configuring react-native-appcenter-distribute in the android project files!');
		console.error(getFormattedErrorMessage(asyncError));
		console.error('\n');
	}
};

const configureIOSAsync = async () => {
	try {
		if(!rnpmlink.ios.checkIfAppDelegateExists()) {
			console.log('No AppDelegate file found. Skipping ios configuration of react-native-appcenter-distribute.');
			return;
		}

		console.log('Configuring react-native-appcenter-distribute for iOS');
		await rnpmlink.ios.initAppCenterConfig();

		const appDelegateFile = await rnpmlink.ios.initInAppDelegate(
			'#import <RNAppCenterDistribute/RNAppCenterDistribute.h>',
			'[RNAppCenterDistribute register]; // Initialize AppCenter Distribute',
			/.*\[RNAppCenterDistribute register.*/g
		);

		console.log(`Added code to initialize RNAppCenterDistribute in ${appDelegateFile}`);

		await rnpmlink.ios.addPodDeps(
			[
				{ pod: 'AppCenter/Distribute', version: '1.13.2' },
				{ pod: 'AppCenterReactNativeShared', version: '1.12.2' } // in case people don't link appcenter (core)
			],
			{ platform: 'ios', version: '9.0' }
		);

	} catch(asyncError) {
		console.error('\nERROR - Unexpected error configuring react-native-appcenter-distribute in the ios project files!');
		console.error(getFormattedErrorMessage(asyncError));
		console.error('\n');
	}
};

const runPostlinkAsync = async () => {
	try {
		await configureAndroidAsync();

		await configureIOSAsync();

	} catch(asyncError) {
		console.error('\nERROR - Unexpected error configuring react-native-appcenter-distribute!');
		console.error(getFormattedErrorMessage(asyncError));
		console.error('\n');
	}
};


runPostlinkAsync()
	.then(() => {
		console.log('\n\nPostlink of react-native-appcenter-distribute has finished!\n');
	})
	.catch((asyncError) => {
		console.error('\nERROR - Unexpected error configuring react-native-appcenter-distribute!');
		console.error(asyncError);
		console.error(' \n');
		return Promise.resolve();
	});
