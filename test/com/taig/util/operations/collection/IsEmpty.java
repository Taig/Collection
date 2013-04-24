package com.taig.util.operations.collection;

public interface IsEmpty
{
	public void isEmptyOnEmptyCollection();

	public void isEmptyOnHealthyCollection();

	public void isEmptyAfterElementAddedOnEmptyCollection();

	public void isEmptyAfterAllElementsRemovedOnHealthyCollection();

	public void isEmptyAfterElementsClearedOnHealthyCollection();
}