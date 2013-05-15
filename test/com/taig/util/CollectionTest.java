package com.taig.util;

import com.taig.util.Container.Empty;
import com.taig.util.Container.Healthy;
import com.taig.util.operations.Collection;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public abstract class CollectionTest<C extends java.util.Collection<T>, T> implements Collection
{
	protected abstract Empty<C, T> empty();

	protected abstract Healthy<C, T> healthy();

	@Test
	public void addWithElementOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.add( data.addable );
		assertEquals( 1, data.collection.size() );
	}

	@Test
	public void addWithElementOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.add( data.addable );
		assertEquals( size + 1, data.collection.size() );
	}

	@Test
	public void addWithNullElement()
	{
		healthy().collection.add( null );
	}

	@Test( expected = NullPointerException.class )
	public void addAllWithNullArgument()
	{
		healthy().collection.addAll( null );
	}

	@Test
	public void addAllOnEmptyCollection()
	{
		Empty<C, T> empty = empty();
		Healthy<C, T> healthy = healthy();
		empty.collection.addAll( healthy.collection );
		assertEquals( empty.collection, healthy.collection );
	}

	@Test
	public void addAllOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.addAll( data.collection );
		assertEquals( size * 2, data.collection.size() );
	}

	@Test
	public void addAllOnCenterOfHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.addAll( Arrays.asList( data.addable, data.missing ) );
	}

	@Test
	public void clearOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.clear();
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void clearOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.clear();
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void containsOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		assertFalse( data.collection.contains( data.addable ) );
	}

	@Test
	public void containsWithNullElement()
	{
		Empty<C, T> data = empty();
		assertFalse( data.collection.contains( null ) );
		data.collection.add( null );
		assertTrue( data.collection.contains( null ) );
	}

	@Test
	public void containsWithNonExistingElementOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		assertFalse( data.collection.contains( data.missing ) );
	}

	@Test
	public void containsWithExistingElementOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		assertTrue( data.collection.contains( data.existing ) );
	}

	@Test( expected = NullPointerException.class )
	public void containsAllWithNullArgument()
	{
		healthy().collection.containsAll( null );
	}

	@Test
	public void containsAllOnEmptyCollection()
	{
		assertFalse( empty().collection.containsAll( healthy().collection ) );
	}

	@Test
	public void containsAllWithNonExistingElementsOnHealthyCollection()
	{
		assertFalse( healthy().collection.containsAll( Arrays.asList( healthy().missing, null ) ) );
	}

	@Test
	public void containsAllWithPartlyExistingElementsOnHealthyCollection()
	{
		assertFalse( healthy().collection.containsAll( Arrays.asList( healthy().existing, healthy().missing ) ) );
	}

	@Test
	public void containsAllWithExitingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.add( data.missing );
		assertTrue( data.collection.containsAll( Arrays.asList( data.existing, data.missing ) ) );
	}

	@Test
	public void hasNextViaIteratorOnEmptyCollection()
	{
		assertFalse( empty().collection.iterator().hasNext() );
	}

	@Test
	public void hasNextViaIteratorOnStartOfHealthyCollection()
	{
		assertTrue( healthy().collection.iterator().hasNext() );
	}

	@Test
	public void hasNextViaIteratorOnEndOfHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();

		for( int i = 0; i < data.collection.size(); i++ )
		{
			iterator.next();
		}

		assertFalse( iterator.hasNext() );
	}

	@Test
	public void isEmptyOnEmptyCollection()
	{
		assertTrue( empty().collection.isEmpty() );
	}

	@Test
	public void isEmptyOnHealthyCollection()
	{
		assertFalse( healthy().collection.isEmpty() );
	}

	@Test
	public void isEmptyAfterElementAddedOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.add( data.addable );
		assertFalse( data.collection.isEmpty() );
	}

	@Test
	public void isEmptyAfterAllElementsRemovedOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();

		while( iterator.hasNext() )
		{
			iterator.next();
			iterator.remove();
		}

		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void isEmptyAfterElementsClearedOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.clear();
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void getIterator()
	{
		assertNotNull( empty().collection.iterator() );
		assertNotNull( healthy().collection.iterator() );
	}

	@Test( expected = NoSuchElementException.class )
	public void nextViaIteratorOnEmptyCollection()
	{
		empty().collection.iterator().next();
	}

	@Test
	public void nextViaIteratorOnHealthyCollection()
	{
		healthy().collection.iterator().next();
	}

	@Test( expected = NoSuchElementException.class )
	public void nextViaIteratorOnEndOfHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();

		for( int i = 0; i < data.collection.size(); i++ )
		{
			iterator.next();
		}

		iterator.next();
	}

	@Test( expected = IllegalStateException.class )
	public void removeOnEmptyCollection()
	{
		empty().collection.iterator().remove();
	}

	@Test( expected = IllegalStateException.class )
	public void removeWithoutNextCallOnHealthyCollection()
	{
		healthy().collection.iterator().remove();
	}

	@Test
	public void removeAfterNextCallOnHealthyCollection()
	{
		Iterator<T> iterator = healthy().collection.iterator();
		iterator.next();
		iterator.remove();
	}

	@Test( expected = IllegalStateException.class )
	public void removeTwiceAfterNextCallOnHealthyCollection()
	{
		Iterator<T> iterator = healthy().collection.iterator();
		iterator.next();
		iterator.remove();
		iterator.remove();
	}

	@Test
	public void removeWithNullObject()
	{
		Healthy<C, T> data = healthy();
		data.collection.add( null );
		int size = data.collection.size();
		data.collection.remove( null );
		assertEquals( size - 1, data.collection.size() );
	}

	@Test
	public void removeWithObjectOnEmptyCollection()
	{
		Empty<C, T> empty = empty();
		empty.collection.remove( empty.addable );
		assertTrue( empty.collection.isEmpty() );
	}

	@Test
	public void removeWithNonExistingObjectOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.remove( data.missing );
		assertEquals( size, data.collection.size() );
	}

	@Test
	public void removeWithExistingObjectOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.remove( data.existing );
		assertEquals( size - 1, data.collection.size() );
	}

	@Test( expected = NullPointerException.class )
	public void removeAllWithNullArgument()
	{
		healthy().collection.removeAll( null );
	}

	@Test
	public void removeAllOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.removeAll( Arrays.asList( data.addable ) );
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void removeAllWithNonExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.removeAll( Arrays.asList( data.addable, data.missing ) );
		assertEquals( size, data.collection.size() );
	}

	@Test
	public void removeAllWithPartlyExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		int size = data.collection.size();
		data.collection.removeAll( Arrays.asList( data.existing, data.addable, data.missing ) );
		assertEquals( size - 1, data.collection.size() );
	}

	@Test
	public void removeAllWithExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.add( data.addable );
		int size = data.collection.size();
		data.collection.removeAll( Arrays.asList( data.existing, data.addable ) );
		assertEquals( size - 2, data.collection.size() );
	}

	@Test( expected = NullPointerException.class )
	public void retainAllWithNullArgument()
	{
		healthy().collection.retainAll( null );
	}

	@Test
	public void retainAllOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.retainAll( Arrays.asList( data.addable ) );
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void retainAllWithNonExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.retainAll( Arrays.asList( data.addable, data.missing ) );
		assertTrue( data.collection.isEmpty() );
	}

	@Test
	public void retainAllWithPartlyExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.retainAll( Arrays.asList( data.existing, data.addable, data.missing ) );
		assertEquals( 1, data.collection.size() );
	}

	@Test
	public void retainAllWithExistingElementsOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		data.collection.add( data.addable );
		data.collection.retainAll( Arrays.asList( data.existing, data.addable ) );
		assertEquals( 2, data.collection.size() );
	}

	@Test
	public void sizeOnEmptyCollection()
	{
		assertEquals( 0, empty().collection.size() );
	}

	@Test
	public void sizeOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();
		int size = 0;

		while( iterator.hasNext() )
		{
			iterator.next();
			size++;
		}

		assertEquals( size, data.collection.size() );
	}

	@Test
	public void sizeAfterElementsAddedOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		data.collection.add( data.addable );
		assertEquals( 1, data.collection.size() );
	}

	@Test
	public void sizeAfterElementsAddedOnHealthyCollection()
	{
		Healthy<C, T> healthy = healthy();
		int size = healthy.collection.size();
		healthy.collection.add( healthy.addable );
		assertEquals( size + 1, healthy.collection.size() );
	}

	@Test
	public void sizeAfterElementsRemovedOnHealthyCollection()
	{
		Healthy<C, T> healthy = healthy();
		int size = healthy.collection.size();
		healthy.collection.remove( healthy.existing );
		assertEquals( size - 1, healthy.collection.size() );
	}

	@Test
	public void sizeAfterElementsClearedOnHealthyCollection()
	{
		Healthy<C, T> healthy = healthy();
		healthy.collection.clear();
		assertEquals( 0, healthy.collection.size() );
	}

	@Test
	public void toArrayOnEmptyCollection()
	{
		assertArrayEquals( new Object[0], empty().collection.toArray() );
	}

	@Test
	public void toArrayOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();
		Object[] array = data.collection.toArray();

		assertEquals( data.collection.size(), array.length );

		for( int i = 0; iterator.hasNext(); i++ )
		{
			assertEquals( iterator.next(), array[i] );
		}
	}

	@Test( expected = NullPointerException.class )
	public void toTypedArrayWithNullTargetArray()
	{
		healthy().collection.toArray( null );
	}

	@Test
	public void toTypedArrayOnEmptyCollection()
	{
		Empty<C, T> data = empty();
		assertArrayEquals( data.createTypedArray( 0 ), data.collection.toArray( data.createTypedArray( 0 ) ) );
	}

	@Test
	public void toTypedArrayOnHealthyCollection()
	{
		Healthy<C, T> data = healthy();
		Iterator<T> iterator = data.collection.iterator();
		T[] array = data.collection.toArray( data.createTypedArray( data.collection.size() ) );

		assertEquals( data.collection.size(), array.length );

		for( int i = 0; iterator.hasNext(); i++ )
		{
			assertEquals( iterator.next(), array[i] );
		}
	}

	@Test( expected = IllegalStateException.class )
	public void removeViaIteratorOnEmptyCollection()
	{
		empty().collection.iterator().remove();
	}

	@Test( expected = IllegalStateException.class )
	public void removeViaIteratorWithoutNextCallOnHealthyCollection()
	{
		healthy().collection.iterator().remove();
	}

	@Test
	public void removeViaIteratorAfterNextCallOnHealthyCollection()
	{
		Iterator<T> iterator = healthy().collection.iterator();
		iterator.next();
		iterator.remove();
	}

	@Test( expected = IllegalStateException.class )
	public void removeTwiceViaIteratorAfterNextCallOnHealthyCollection()
	{
		Iterator<T> iterator = healthy().collection.iterator();
		iterator.next();
		iterator.remove();
		iterator.remove();
	}
}