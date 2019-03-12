

declare module 'appcenter-link-scripts' {

	class IAndroidHelper {
		checkIfAndroidDirectoryExists(): boolean;
		initAppCenterConfig(): Promise<void>;
		removeAndroidDuplicateLinks(): Promise<void>;
	}


	interface IPodDependency {
		pod: string;
		version: string;
	}

	interface IXcodeOptions {
		platform: string;
		version: string;
	}

	class IIOSHelper {
		addPodDeps(dependencies: IPodDependency[], options: IXcodeOptions): Promise<void>;
		checkIfAppDelegateExists(): boolean;
		initAppCenterConfig(): Promise<void>;
		initInAppDelegate(
			importStatement: string,
			registerStatement: string,
			registerPattern: RegExp
		): Promise<string>;
	}

	namespace rnpmlink {
		const android: IAndroidHelper;
		const ios: IIOSHelper;
	}

	export default rnpmlink;
}
