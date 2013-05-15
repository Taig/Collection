package com.taig.util;

import java.util.Arrays;
import java.util.List;

import com.taig.util.Container.*;
import com.taig.util.operations.Queue;
import com.taig.util.operations.Deque;

public class LinkedListTest extends ListTest<List<Integer>, Integer> implements Queue, Deque
{
	@Override
	protected Empty<List<Integer>, Integer> empty()
	{
		return new Empty<List<Integer>, Integer>( new LinkedList<Integer>(), 1 )
		{
			@Override
			public Integer[] createTypedArray( int size )
			{
				return new Integer[size];
			}
		};
	}

	@Override
	protected Healthy<List<Integer>, Integer> healthy()
	{
		return new Healthy<List<Integer>, Integer>( new LinkedList<Integer>( Arrays.asList( 1, 2, 3 ) ), 0, 1, 4 )
		{
			@Override
			public Integer[] createTypedArray( int size )
			{
				return new Integer[size];
			}
		};
	}

	@Override
	public void addOnFullQueue()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void addFirstOnEmptyDequeue()
	{
		throw new UnsupportedOperationException();
	}
}