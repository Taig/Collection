package com.taig.util.operations.iterator;

public interface HasNext
{
	public void hasNextViaIteratorOnEmptyCollection();

	public void hasNextViaIteratorOnStartOfHealthyCollection();

	public void hasNextViaIteratorOnEndOfHealthyCollection();
}