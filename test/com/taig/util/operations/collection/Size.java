package com.taig.util.operations.collection;

public interface Size
{
	public void sizeOnEmptyCollection();

	public void sizeOnHealthyCollection();

	public void sizeAfterElementsAddedOnEmptyCollection();

	public void sizeAfterElementsAddedOnHealthyCollection();

	public void sizeAfterElementsRemovedOnHealthyCollection();

	public void sizeAfterElementsClearedOnHealthyCollection();
}