package com.taig.util.operations.iterator;

public interface NextIndex
{
	public void nextIndexViaIteratorOnEmptyCollection();

	public void nextIndexViaIteratorOnStartOfHealthyCollection();

	public void nextIndexViaIteratorOnEndOfHealthyCollection();

	public void nextIndexViaIteratorAfterElementAdded();

	public void nextIndexViaIteratorAfterElementRemoved();
}