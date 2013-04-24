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
		throw new UnsupportedOperationException();
	}

	@Test
	public void addWithInvalidIndexOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void addWithValidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void addWithInvalidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void getOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void getWithInvalidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void getWithValidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void indexOfWithNullArgument()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void indexOfOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void indexOfWithNonExistingElementOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void indexOfWithExistingElementOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void removeWithIndexOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void removeWithValidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void removeWithInvalidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void setOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void setWithNullArgument()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void setWithInvalidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void setWithValidIndexOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void subListOnEmptyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void subListWithInvalidIndexesOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void subListWithInvalidStartIndexesOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void subListWithInvalidEndIndexesOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}

	@Test
	public void subListWithValidIndexesOnHealthyCollection()
	{
		throw new UnsupportedOperationException();
	}
}