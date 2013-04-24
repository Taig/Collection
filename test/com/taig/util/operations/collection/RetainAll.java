package com.taig.util.operations.collection;

public interface RetainAll
{
	public void retainAllWithNullArgument();

	public void retainAllOnEmptyCollection();

	public void retainAllWithNonExistingElementsOnHealthyCollection();

	public void retainAllWithPartlyExistingElementsOnHealthyCollection();

	public void retainAllWithExistingElementsOnHealthyCollection();
}