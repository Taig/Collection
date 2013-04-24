package com.taig.util.operations.collection;

public interface RemoveAll
{
	public void removeAllWithNullArgument();

	public void removeAllOnEmptyCollection();

	public void removeAllWithNonExistingElementsOnHealthyCollection();

	public void removeAllWithPartlyExistingElementsOnHealthyCollection();

	public void removeAllWithExistingElementsOnHealthyCollection();
}