package com.taig.util.operations.iterator;

public interface Remove
{
	public void removeViaIteratorOnEmptyCollection();

	public void removeViaIteratorWithoutNextCallOnHealthyCollection();

	public void removeViaIteratorAfterNextCallOnHealthyCollection();

	public void removeTwiceViaIteratorAfterNextCallOnHealthyCollection();
}