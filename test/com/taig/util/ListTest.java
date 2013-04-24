package com.taig.util;

import com.taig.util.operations.List;

import static org.junit.Assert.*;

import org.junit.Test;
import com.taig.util.Container.*;

import java.util.ListIterator;

public abstract class ListTest<L extends java.util.List<T>, T> extends CollectionTest<L, T> implements List
{
	@Test
	public void hasPreviousViaIteratorOnEmptyCollection()
	{
		assertFalse( empty().collection.listIterator().hasPrevious() );
	}

	@Test
	public void hasPreviousViaIteratorOnStartOfHealthyCollection()
	{
		assertFalse( healthy().collection.listIterator().hasPrevious() );
	}

	@Test
	public void hasPreviousViaIteratorOnEndOfHealthyCollection()
	{
		ListIterator<T> iterator = healthy().collection.listIterator();

		while( iterator.hasNext() )
		{
			iterator.next();
		}

		assertTrue( iterator.hasPrevious() );
	}

	@Test
	public void previousIndexViaIteratorOnEmptyCollection()
	{
		assertEquals( -1, empty().collection.listIterator().previousIndex() );
	}

	@Test
	public void previousIndexViaIteratorOnStartOfHealthyCollection()
	{
		assertEquals( -1, healthy().collection.listIterator().previousIndex() );
	}

	@Test
	public void addViaIteratorWithNullArgument()
	{
		Empty<L, T> data = empty();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.add( null );

		assertNull( data.collection.iterator().next() );
	}

	@Test
	public void addViaIteratorOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.add( data.addable );

		assertEquals( 1, data.collection.size() );
		assertEquals( data.addable, data.collection.iterator().next() );
	}

	@Test
	public void addViaIteratorOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		int size = data.collection.size();

		iterator.next();
		iterator.add( data.addable );

		assertEquals( size + 1, data.collection.size() );
	}

	@Test
	public void nextIndexViaIteratorOnEmptyCollection()
	{
		assertEquals( 0, empty().collection.listIterator().nextIndex() );
	}

	@Test
	public void nextIndexViaIteratorOnStartOfHealthyCollection()
	{
		assertEquals( 0, healthy().collection.listIterator().nextIndex() );
	}

	@Test
	public void nextIndexViaIteratorOnEndOfHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		while( iterator.hasNext() )
		{
			iterator.next();
		}

		assertEquals( data.collection.size(), iterator.nextIndex() );
	}

	@Test
	public void nextIndexViaIteratorAfterElementAdded()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		int index = iterator.nextIndex();
		iterator.add( data.addable );

		assertEquals( index + 1, iterator.nextIndex() );
	}

	@Test
	public void nextIndexViaIteratorAfterElementRemoved()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.next();
		int index = iterator.nextIndex();
		iterator.remove();

		assertEquals( index - 1, iterator.nextIndex() );
	}

	@Test( expected = IllegalStateException.class )
	public void setViaIteratorOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		data.collection.listIterator().set( data.addable );
	}

	@Test
	public void setViaIteratorAfterNextCall()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.set( data.addable );

		assertEquals( data.addable, data.collection.iterator().next() );
	}

	@Test
	public void setViaIteratorAfterPreviousCall()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.next();
		iterator.previous();
		iterator.set( data.addable );

		assertEquals( data.addable, data.collection.iterator().next() );
	}

	@Test( expected = IllegalStateException.class )
	public void setViaIteratorAfterAddCall()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.add( data.addable );
		iterator.set( data.missing );
	}

	@Test( expected = IllegalStateException.class )
	public void setViaIteratorAfterRemoveCall()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.remove();
		iterator.set( data.missing );
	}

	@Test
	public void previousIndexViaIteratorOnEndOfHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		while( iterator.hasNext() )
		{
			iterator.next();
		}

		assertEquals( data.collection.size() - 2, iterator.previousIndex() );
	}

	@Test
	public void previousIndexViaIteratorAfterElementAdded()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		int index = iterator.previousIndex();
		iterator.add( data.addable );
		assertEquals( index + 1, iterator.previousIndex() );
	}

	@Test
	public void previousIndexViaIteratorAfterElementRemoved()
	{
		Healthy<L, T> data = healthy();
		ListIterator<T> iterator = data.collection.listIterator();

		iterator.next();
		iterator.next();
		int index = iterator.previousIndex();
		iterator.remove();
		assertEquals( index - 1, iterator.previousIndex() );
	}

	@Test
	public void addWithValidIndexOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		data.collection.add( 0, data.addable );
		assertEquals( 1, data.collection.size() );
		assertEquals( data.addable, data.collection.get( 0 ) );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void addWithInvalidIndexOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		data.collection.add( 1, data.addable );
	}

	@Test
	public void addWithValidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		int size = data.collection.size();
		data.collection.add( 2, data.addable );
		assertEquals( data.addable, data.collection.get( 2 ) );
		assertEquals( size + 1, data.collection.size() );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void addWithInvalidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.add( data.collection.size() + 10, data.addable );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void getWithIndexOnEmptyCollection()
	{
		empty().collection.get( 0 );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void getWithInvalidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.get( -1 );
	}

	@Test
	public void getWithValidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.add( 3, data.addable );
		assertEquals( data.addable, data.collection.get( 3 ) );
	}

	@Test
	public void indexOfWithNullArgument()
	{
		assertEquals( -1, empty().collection.indexOf( null ) );
	}

	@Test
	public void indexOfOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		assertEquals( -1, data.collection.indexOf( data.addable ) );
	}

	@Test
	public void indexOfWithNonExistingElementOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		assertEquals( -1, data.collection.indexOf( data.missing ) );
	}

	@Test
	public void indexOfWithExistingElementOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.add( 3, data.addable );
		assertEquals( 3, data.collection.indexOf( data.addable ) );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void removeWithIndexOnEmptyCollection()
	{
		empty().collection.remove( 0 );
	}

	@Test
	public void removeWithValidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		int size = data.collection.size();
		data.collection.remove( 2 );
		assertEquals( size - 1, data.collection.size() );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void removeWithInvalidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.remove( data.collection.size() + 10 );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void setOnEmptyCollection()
	{
		Empty<L, T> data = empty();
		data.collection.set( 0, data.addable );
	}

	@Test
	public void setWithNullArgument()
	{
		Healthy<L, T> data = healthy();
		data.collection.set( 1, null );
		assertNull( data.collection.get( 1 ) );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void setWithInvalidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.set( data.collection.size() + 10, data.addable );
	}

	@Test
	public void setWithValidIndexOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.set( 2, data.addable );
		assertEquals( data.addable, data.collection.get( 2 ) );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void subListOnEmptyCollection()
	{
		empty().collection.subList( 0, 0 );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void subListWithInvalidIndexesOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.subList( -1, data.collection.size() + 10 );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void subListWithInvalidStartIndexesOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.subList( -1, data.collection.size() - 1 );
	}

	@Test( expected = IndexOutOfBoundsException.class )
	public void subListWithInvalidEndIndexesOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		data.collection.subList( 0, data.collection.size() + 10 );
	}

	@Test
	public void subListWithValidIndexesOnHealthyCollection()
	{
		Healthy<L, T> data = healthy();
		java.util.List<T> subList = data.collection.subList( 0, 2 );
		assertEquals( 2, subList.size() );
		assertEquals( data.collection.get( 0 ), subList.get( 0 ) );
	}
}