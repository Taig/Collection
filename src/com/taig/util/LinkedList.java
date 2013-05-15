/*
 * This program is free software. It comes without any warranty, to
 * the extent permitted by applicable law. You can redistribute it
 * and/or modify it under the terms of the Do What The Fuck You Want
 * To Public License, Version 2, as published by Sam Hocevar. See
 * http://sam.zoy.org/wtfpl/COPYING for more details.
 */
package com.taig.util;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A custom {@link java.util.List} implementation; similar to {@link java.util.LinkedList}.
 *
 * @param <T>
 */
public class LinkedList<T> extends AbstractSequentialList<T> implements List<T>, Queue<T>, Deque<T>
{
	/**
	 * The list's current size.
	 */
	protected int size;

	protected Node<T> head;

	protected Node<T> tail;

	/**
	 * Construct an empty {@link com.taig.util.LinkedList}.
	 */
	public LinkedList()
	{
		clear();
	}

	/**
	 * Construct a {@link com.taig.util.LinkedList} and copy the given {@link java.util.Collection Collection's} items into
	 * this instance.
	 *
	 * @param collection The {@link java.util.Collection} that will be copied into this list.
	 */
	public LinkedList( Collection<? extends T> collection )
	{
		this();
		addAll( collection );
	}

	/**
	 * Returns the first element in this list.
	 *
	 * @return The first element in this list.
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T getFirst()
	{
		return get( 0 );
	}

	/**
	 * Returns the last element in this list.
	 *
	 * @return The last element in this list.
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T getLast()
	{
		return get( size - 1 );
	}

	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return The first element from this list
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T removeFirst()
	{
		return remove( head );
	}

	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return The first element from this list
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T removeLast()
	{
		return remove( tail );
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 *
	 * @param element The element to add.
	 */
	@Override
	public void addFirst( T element )
	{
		prepend( head, element );
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param element The element to add.
	 */
	@Override
	public void addLast( T element )
	{
		append( tail, element );
	}

	/**
	 * Returns <code>true</code> if this list contains the specified element. More formally, returns <code>true</code> if
	 * and only if this list contains at least one element <code>e</code> such that <code>( object == null ? element ==
	 * null : object.equals( element ) )</code>.
	 *
	 * @param object Element whose presence in this list is to be tested.
	 * @return <code>true</code> if this list contains the specified element.
	 */
	@Override
	public boolean contains( Object object )
	{
		for( Node<T> node = head; !node.isEmpty(); node = node.right )
		{
			if( object == null ? node.payload == null : node.payload.equals( object ) )
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return The number of elements in this list.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * @param element Element to be appended to this list.
	 * @return <code>true</code> (as per the general contract of the {@link java.util.Collection#add(Object)} method).
	 */
	@Override
	public boolean add( T element )
	{
		append( tail, element );
		return true;
	}

	/**
	 * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is
	 * unchanged. More formally, removes the element with the lowest index <code>i</code> such that <code>( object == null
	 * ? get( i ) == null : object.equals( get( i ) ) )</code> (if such an element exists).
	 *
	 * @param object Element to be removed from this list, if present.
	 * @return <code>true</code> if this list contained the specified element.
	 */
	@Override
	public boolean remove( Object object )
	{
		for( Node<T> node = head; !node.isEmpty(); node = node.right )
		{
			if( object == null ? node.payload == null : object.equals( node.payload ) )
			{
				remove( node );
				return true;
			}
		}

		return false;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned
	 * by the specified collection's iterator.
	 *
	 * @param collection {@link java.util.Collection} whose elements are to be added to this list.
	 * @return <code>true</code> if this list changed as a result of the call.
	 * @throws NullPointerException If the specified collection is <code>null</code>.
	 * @see #add(Object)
	 */
	@Override
	public boolean addAll( Collection<? extends T> collection )
	{
		return addAll( size, collection );
	}

	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element
	 * currently at that position (if any) and any subsequent elements to the right (increases their indices). The new
	 * elements will appear in this list in the order that they are returned by the specified collection's iterator.
	 *
	 * @param location   Index at which to insert first element from the specified collection.
	 * @param collection Elements to be inserted into this list.
	 * @return <code>true</code> if this list changed as a result of the call.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index > size() )</code>.
	 */
	@Override
	public boolean addAll( int location, Collection<? extends T> collection )
	{
		Node<T> cursor = cursor( location );

		for( T element : collection )
		{
			cursor = append( cursor, element );
		}

		return true;
	}

	/**
	 * Removes all of the elements from this list. This list will be empty after this call returns.
	 */
	@Override
	public void clear()
	{
		head = new Node.Empty<T>();
		tail = new Node.Empty<T>();

		head.right = tail;
		tail.left = head;

		size = 0;
	}

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param location Index of element to return.
	 * @return The element at the specified position in this list.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index >= size() )</code>.
	 */
	@Override
	public T get( int location )
	{
		return selector( location ).payload;
	}

	/**
	 * Replaces the element at the specified position in this list with the specified element.
	 *
	 * @param location Index of element to replace.
	 * @param element  Element to be stored at the specified position.
	 * @return The element previously at the specified position.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index >= size() )</code>.
	 */
	@Override
	public T set( int location, T element )
	{
		Node<T> node = selector( location );
		T previous = node.payload;
		node.payload = element;

		return previous;
	}

	/**
	 * Inserts the specified element at the specified position in this list. Shifts the element currently at that position
	 * (if any) and any subsequent elements to the right (adds one to their indices).
	 *
	 * @param location Index at which the specified element is to be inserted.
	 * @param element  Element to be inserted.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index > size() )</code>.
	 */
	@Override
	public void add( int location, T element )
	{
		prepend( cursor( location ), element );
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts
	 * one from their indices).
	 *
	 * @param location The index of the element to removed.
	 * @return The element previously at the specified position.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index >= size() )</code>.
	 */
	@Override
	public T remove( int location )
	{
		return remove( selector( location ) );
	}

	/**
	 * Returns the index in this list of the first occurrence of the specified element, or <code>-1</code> if this list
	 * does not contain this element. More formally, returns the lowest index <code>i</code> such that <code>( object ==
	 * null ? get( i ) == null : object.equals( get( i ) ) )</code>, or <code>-1</code> if there is no such index.
	 *
	 * @param object Element to search for.
	 * @return The index in this list of the last occurrence of the specified element, or <code>-1</code> if this list does
	 *         not contain this element.
	 */
	@Override
	public int indexOf( Object object )
	{
		int index = 0;

		for( Node<T> node = head; !node.isEmpty(); node = node.right, index++ )
		{
			if( object == null ? node.payload == null : object.equals( node.payload ) )
			{
				return index;
			}
		}

		return -1;
	}

	/**
	 * Returns the index in this list of the last occurrence of the specified element, or <code>-1</code> if this list does
	 * not contain this element. More formally, returns the highest index <code>i</code> such that <code>( object == null ?
	 * get( i ) == null : object.equals( get( i ) ) )</code>, or <code>-1</code> if there is no such index.
	 *
	 * @param object Element to search for.
	 * @return The index in this list of the last occurrence of the specified element, or <code>-1</code> if this list does
	 *         not contain this element.
	 */
	@Override
	public int lastIndexOf( Object object )
	{
		int index = size - 1;

		for( Node<T> node = tail; !node.isEmpty(); node = node.left, index-- )
		{
			if( object == null ? node.payload == null : object.equals( node.payload ) )
			{
				return index;
			}
		}

		return -1;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 *
	 * @return The head of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T peek()
	{
		return head.payload;
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 *
	 * @return The head of this list.
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T element()
	{
		if( head.isEmpty() )
		{
			throw new NoSuchElementException();
		}
		else
		{
			return peek();
		}
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
	 *
	 * @return The head of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T poll()
	{
		return remove( head );
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
	 *
	 * @return The head of this list.
	 * @throws NoSuchElementException If this list is empty.
	 */
	@Override
	public T remove()
	{
		return removeFirst();
	}

	/**
	 * Adds the specified element as the tail (last element) of this list.
	 *
	 * @param element The element to add.
	 * @return <code>true</code> (as specified by {@link Queue#offer(Object)}).
	 */
	@Override
	public boolean offer( T element )
	{
		append( tail, element );
		return true;
	}

	/**
	 * Inserts the specified element at the front of this list.
	 *
	 * @param element The element to add.
	 * @return <code>true</code> (as specified by {@link Deque#offerFirst(Object)})
	 */
	@Override
	public boolean offerFirst( T element )
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Inserts the specified element at the end of this list.
	 *
	 * @param element The element to add.
	 * @return <code>true</code> (as specified by {@link Deque#offerFirst(Object)})
	 */
	@Override
	public boolean offerLast( T element )
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
	 *
	 * @return The first element of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T peekFirst()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieves, but does not remove, the last element of this list, or returns <code>null</code> if this list is empty.
	 *
	 * @return The last element of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T peekLast()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieves and removes the first element of this list, or returns null if this list is empty.
	 *
	 * @return The first element of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T pollFirst()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieves and removes the last element of this list, or returns null if this list is empty.
	 *
	 * @return The last element of this list, or <code>null</code> if this list is empty.
	 */
	@Override
	public T pollLast()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Pushes an element onto the stack represented by this list. In other words, inserts the element at the front of this
	 * list.
	 * <p/>
	 * This method is equivalent to {@link #addFirst(Object)}.
	 *
	 * @param element The element to push.
	 */
	@Override
	public void push( T element )
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Pops an element from the stack represented by this list. In other words, removes and returns the first element of
	 * this list.
	 * <p/>
	 * This method is equivalent to {@link #removeFirst()}.
	 *
	 * @return The element at the front of this list (which is the top of the stack represented by this list).
	 */
	@Override
	public T pop()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the first occurrence of the specified element in this list (when traversing the list from head to tail). If
	 * the list does not contain the element, it is unchanged.
	 *
	 * @param object The element to be removed from this list, if present.
	 * @return <code>true</code> if the list contained the specified element.
	 */
	@Override
	public boolean removeFirstOccurrence( Object object )
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Removes the last occurrence of the specified element in this list (when traversing the list from head to tail). If
	 * the list does not contain the element, it is unchanged.
	 *
	 * @param object The element to be removed from this list, if present.
	 * @return <code>true</code> if the list contained the specified element.
	 */
	@Override
	public boolean removeLastOccurrence( Object object )
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in
	 * this list. The specified index indicates the first element that would be returned by an initial call to the {@link
	 * java.util.ListIterator#next()} method. An initial call to the previous method would return the element with the
	 * specified index minus one.
	 *
	 * @param location Index of first element to be returned from the list iterator (by a call to the {@link
	 *                 java.util.ListIterator#next()} method).
	 * @return A list iterator of the elements in this list (in proper sequence), starting at the specified position in
	 *         this list.
	 * @throws IndexOutOfBoundsException If the index is out of range <code>( index < 0 || index > size() )</code>.
	 */
	@Override
	public ListIterator<T> listIterator( int location )
	{
		return new ListIterator<T>( cursor( location ) )
		{
			@Override
			protected Node<T> add( Node<T> current, T element )
			{
				return prepend( current, element );
			}

			@Override
			protected void remove( Node<T> node )
			{
				LinkedList.this.remove( node );
			}
		};
	}

	/**
	 * Returns an iterator over the elements in this deque in reverse sequential order. The elements will be returned in
	 * order from last (tail) to first (head).
	 *
	 * @return An iterator over the elements in this deque in reverse sequence.
	 */
	@Override
	public Iterator<T> descendingIterator()
	{
		return new ListIterator<T>( tail )
		{
			@Override
			public boolean hasNext()
			{
				return super.hasPrevious();
			}

			@Override
			public int nextIndex()
			{
				return super.previousIndex();
			}

			@Override
			public T next()
			{
				return super.previous();
			}

			@Override
			public boolean hasPrevious()
			{
				return super.hasNext();
			}

			@Override
			public int previousIndex()
			{
				return super.nextIndex();
			}

			@Override
			public T previous()
			{
				return super.next();
			}

			@Override
			protected Node<T> add( Node<T> current, T element )
			{
				return append( current, element );
			}

			@Override
			protected void remove( Node<T> node )
			{
				LinkedList.this.remove( node );
			}
		};
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the
	 * {@link java.util.Collection#toArray()} method.
	 *
	 * @return An array containing all of the elements in this list in proper sequence.
	 * @see java.util.Arrays#asList(Object[])
	 */
	@Override
	public Object[] toArray()
	{
		return toArray( new Object[size] );
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned
	 * array is that of the specified array. Obeys the general contract of the {@link java.util.Collection#toArray()}
	 * method.
	 *
	 * @param array The array into which the elements of this list are to be stored, if it is big enough; otherwise, a new
	 *              array of the same runtime type is allocated for this purpose.
	 * @return An array containing the elements of this list.
	 * @throws ArrayStoreException  If the runtime type of the specified array is not a supertype of the runtime type of
	 *                              every element in this list.
	 * @throws NullPointerException If the specified array is <code>null</code>.
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public <A> A[] toArray( A[] array )
	{
		if( array.length < size )
		{
			array = (A[]) Array.newInstance( array.getClass().getComponentType(), size );
		}

		java.util.ListIterator<T> iterator = listIterator();

		while( iterator.hasNext() )
		{
			array[iterator.nextIndex()] = (A) iterator.next();
		}

		return array;
	}

	protected Node<T> cursor( int location )
	{
		if( location < 0 || location > size )
		{
			throw new IndexOutOfBoundsException();
		}

		return move( location );
	}

	protected Node<T> selector( int location )
	{
		if( location < 0 || location >= size )
		{
			throw new IndexOutOfBoundsException();
		}

		return move( location );
	}

	protected Node<T> move( int location )
	{
		Node<T> current = head;

		for( int i = 0; i < location; i++ )
		{
			current = current.right;
		}

		return current;
	}

	protected Node<T> append( Node<T> cursor, T element )
	{
		return insert( cursor, cursor.right, element );
	}

	protected Node<T> prepend( Node<T> cursor, T element )
	{
		return insert( cursor.left, cursor, element );
	}

	protected Node<T> insert( Node<T> left, Node<T> right, T element )
	{
		Node<T> node = new Node<T>( left, right, element );

		if( left.isEmpty() )
		{
			head = node;
		}

		if( right.isEmpty() )
		{
			tail = node;
		}

		size++;

		return node;
	}

	protected T remove( Node<T> cursor )
	{
		cursor.remove();

		if( cursor.left.isEmpty() )
		{
			head = cursor.right;
		}

		if( cursor.right.isEmpty() )
		{
			tail = cursor.left;
		}

		size--;

		return cursor.payload;
	}
}