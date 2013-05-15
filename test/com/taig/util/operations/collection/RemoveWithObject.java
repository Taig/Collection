package com.taig.util.operations.collection;

public interface RemoveWithObject
{
	public void removeWithNullObject();

	public void removeWithObjectOnEmptyCollection();

	public void removeWithNonExistingObjectOnHealthyCollection();

	public void removeWithExistingObjectOnHealthyCollection();
}