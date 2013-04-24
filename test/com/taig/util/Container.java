package com.taig.util;

import java.util.Collection;

public class Container
{
	public static abstract class Empty<C extends Collection<T>, T>
	{
		public C collection;

		public T addable;

		public Empty( C collection, T addable )
		{
			this.collection = collection;
			this.addable = addable;
		}

		public abstract T[] createTypedArray( int size );
	}

	public static abstract class Healthy<C extends Collection<T>, T> extends Empty<C, T>
	{
		public T existing;

		public T missing;

		public Healthy( C collection, T addable, T existing, T missing )
		{
			super( collection, addable );
			this.existing = existing;
			this.missing = missing;
		}
	}
}