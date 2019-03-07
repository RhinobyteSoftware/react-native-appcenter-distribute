


type Implements<T, TExtends extends T> = T;
type ValueOf<T> = T[keyof T];

interface IDictionary<TObject> {
	[dictionaryKey: string]: TObject;
}

interface IRecursiveDictionary<TObject> {
	[dictionaryKey: string]: TObject | IRecursiveDictionary<TObject>;
}

interface IRecursiveArray<T> extends Array<T | IRecursiveArray<T>> { }
