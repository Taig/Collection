package com.taig.util;

import java.util.NoSuchElementException;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during
 * iteration, and obtain the iterator's current position in the list. A ListIterator has no current element; its cursor
 * position always lies between the element that would be returned by a call to {@link #previous()} and the element that
 * would be returned by a call to {@link #next()}. In a list of length <code>n</code>, there are <code>n + 1</code>
 * valid index values, from <code>0</code> to <code>n</code>, inclusive.
 * <p/>
 * <pre>
 *          Element(0)   Element(1)   Element(2)   ... Element(n)
 *        ^            ^            ^            ^                ^
 * Index: 0            1            2            3              n + 1
 * </pre>
 * <p/>
 * <p/>
 * Note that the {@link #remove()} and {@link #set(Object)} methods are not defined in terms of the cursor position;
 * they are defined to operate on the last element returned by a call to {@link #next()} or {@link #previous()}.
 */
public abstract class ListIterator<T> extends Iterator<T> implements java.util.ListIterator<T>
{
	protected int index;

	/**
	 * Create a {@link ListIterator} starting with an arbitrary {@link Node}.
	 *
	 * @param node An arbitrary {@link Node} (not necessarily the head or tail node of a collection).
	 */
	public ListIterator( Node<T> node )
	{
		super( node );

		// Calculate the given Node's index.
		Node<T> current = node;

		for( this.index = -1; !current.left.isEmpty(); current = node.left )
		{
			this.index++;
		}
	}

	@Override
	public T next()
	{
		if( hasNext() )
		{
			index++;
		}

		return super.next();
	}

	/**
	 * Returns <code>true</code> if this list iterator has more elements when traversing the list in the reverse direction.
	 * In other words, returns <code>true</code> if {@link #previous()} would return an element rather than throwing an
	 * exception.
	 *
	 * @return <code>true</code> if the list iterator has more elements when traversing the list in the reverse direction.
	 */
	@Override
	public boolean hasPrevious()
	{
		return !current.left.isEmpty();
	}

	/**
	 * Returns the previous element in the list. This method may be called repeatedly to iterate through the list
	 * backwards, or intermixed with calls to {@link #next()} to go back and forth. Note that alternating calls to {@link
	 * #next()} and {@link #previous()} will return the same element repeatedly.
	 *
	 * @return The previous element in the list.
	 * @throws NoSuchElementException If the iteration has no previous element.
	 */
	@Override
	public T previous()
	{
		if( hasPrevious() )
		{
			index--;
			current = current.left;
			return current.payload;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the index of the element that would be returned by a subsequent call to {@link #next()}.
	 *
	 * @return The index of the element that would be returned by a subsequent call to {@link #next()}, or list size if
	 *         list iterator is at end of list.
	 */
	@Override
	public int nextIndex()
	{
		return index + 1;
	}

	/**
	 * Returns the index of the element that would be returned by a subsequent call to {@link #previous()}.
	 *
	 * @return The index of the element that would be returned by a subsequent call to {@link #previous()}, or
	 *         <code>-1</code> if list iterator is at the beginning of list.
	 */
	@Override
	public int previousIndex()
	{
		return Math.max( index - 1, -1 );
	}

	/**
	 * Replaces the last element returned by next or previous with the specified element. This call can be made only if
	 * neither {@link #remove()} nor {@link #add(Object)} have been called after the last call to {@link #next()} or {@link
	 * #previous()}.
	 *
	 * @param element The element with which to replace the last element returned by {@link #next()} or {@link
	 *                #previous()}.
	 * @throws IllegalStateException If neither {@link #next()} nor {@link #previous()} have been called, or {@link
	 *                               #remove()} or {@link #add(Object)} have been called after the last call to {@link
	 *                               #next()} or {@link #previous()}.
	 */
	@Override
	public void set( T element )
	{
		if( current.isValid() )
		{
			current.payload = element;
		}
		else
		{
			throw new IllegalStateException();
		}
	}

	/**
	 * Inserts the specified element into the list. The element is inserted immediately before the next element that would
	 * be returned by {@link #next()}, if any, and after the next element that would be returned by {@link #previous}, if
	 * any. If the list contains no elements, the new element becomes the sole element on the list. The new element is
	 * inserted before the implicit cursor: a subsequent call to {@link #next()} would be unaffected, and a subsequent call
	 * to {@link #previous()} would return the new element. This call increases by one the value that would be returned by
	 * a call to {@link #nextIndex()} or {@link #previousIndex()}.
	 *
	 * @param element The element to insert.
	 */
	@Override
	public void add( T element )
	{
		current = new Node.Empty<T>( add( current, element ), current.right );
		index++;
	}

	protected abstract Node<T> add( Node<T> current, T element );

	@Override
	public void remove()
	{
		super.remove();
		index--;
	}
}