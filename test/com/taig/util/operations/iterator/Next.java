package com.taig.util.operations.iterator;

public interface Next
{
	public void nextViaIteratorOnEmptyCollection();

	public void nextViaIteratorOnHealthyCollection();

	public void nextViaIteratorOnEndOfHealthyCollection();
}