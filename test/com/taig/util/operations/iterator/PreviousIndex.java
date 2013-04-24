package com.taig.util.operations.iterator;

public interface PreviousIndex
{
	public void previousIndexViaIteratorOnEmptyCollection();

	public void previousIndexViaIteratorOnStartOfHealthyCollection();

	public void previousIndexViaIteratorOnEndOfHealthyCollection();

	public void previousIndexViaIteratorAfterElementAdded();

	public void previousIndexViaIteratorAfterElementRemoved();
}