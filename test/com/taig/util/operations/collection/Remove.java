package com.taig.util.operations.collection;

public interface Remove
{
	public void removeElementOnEmptyCollection();

	public void removeNonExistingElementOnHealthyCollection();

	public void removeExistingElementOnHealthyCollection();

	public void removeNullElement();
}