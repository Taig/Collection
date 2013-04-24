package com.taig.util.operations.collection;

public interface ContainsAll
{
	public void containsAllWithNullArgument();

	public void containsAllOnEmptyCollection();

	public void containsAllWithNonExistingElementsOnHealthyCollection();

	public void containsAllWithPartlyExistingElementsOnHealthyCollection();

	public void containsAllWithExitingElementsOnHealthyCollection();
}