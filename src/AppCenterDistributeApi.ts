


// EXTERNAL MODULE IMPORTS
import { NativeModules } from 'react-native';



// INTERNAL MODULE IMPORTS



// MODULE CONSTANTS AND TYPES
const { RNAppCenterDistribute } = NativeModules;

interface IAppCenterDistributeApi {
	checkForUpdateAsync(): Promise<void>;
	isEnabledAsync(): Promise<boolean>;
	setEnabledAsync(enabled: boolean): Promise<void>;
}



// EXPORTS
export const AppCenterDistributeApi: IAppCenterDistributeApi = {
	checkForUpdateAsync(): Promise<void> {
		return RNAppCenterDistribute.checkForUpdate();
	},

	isEnabledAsync(): Promise<boolean> {
		return RNAppCenterDistribute.isEnabled();
	},

	setEnabledAsync(enabled: boolean): Promise<void> {
		return RNAppCenterDistribute.setEnabled(enabled);
	}
};
