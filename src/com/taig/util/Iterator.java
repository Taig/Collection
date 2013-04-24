package com.taig.util;

import java.util.NoSuchElementException;

public abstract class Iterator<T> implements java.util.Iterator<T>
{
	protected Node<T> current;

	public Iterator( Node<T> node )
	{
		this.current = node.left;
	}

	/**
	 * Returns <code>true</code> if the iteration has more elements. In other words, returns <code>true</code> if next
	 * would return an element rather than throwing an exception.
	 *
	 * @return <code>true</code> if the iterator has more elements.
	 */
	@Override
	public boolean hasNext()
	{
		return !current.right.isEmpty();
	}

	/**
	 * Returns the next element in the iteration.
	 *
	 * @return The next element in the iteration.
	 * @throws NoSuchElementException If the iteration has no more elements.
	 */
	@Override
	public T next()
	{
		if( hasNext() )
		{
			current = current.right;
			return current.payload;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	/**
	 * Removes from the underlying collection the last element returned by the iterator (optional operation). This method
	 * can be called only once per call to next. The behavior of an iterator is unspecified if the underlying collection is
	 * modified while the iteration is in progress in any way other than by calling this method.
	 *
	 * @throws IllegalStateException If the next method has not yet been called, or the remove method has already been
	 *                               called after the last call to the next method.
	 */
	@Override
	public void remove()
	{
		Node.Empty<T> empty = new Node.Empty<T>( current.left, current.right );
		remove( current );
		current = empty;
	}

	protected abstract void remove( Node<T> node );
}