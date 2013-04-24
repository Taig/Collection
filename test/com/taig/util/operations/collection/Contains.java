package com.taig.util.operations.collection;

public interface Contains
{
	public void containsOnEmptyCollection();

	public void containsWithNullElement();

	public void containsWithNonExistingElementOnHealthyCollection();

	public void containsWithExistingElementOnHealthyCollection();
}