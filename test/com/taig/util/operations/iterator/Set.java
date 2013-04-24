package com.taig.util.operations.iterator;

public interface Set
{
	public void setViaIteratorOnEmptyCollection();

	public void setViaIteratorAfterNextCall();

	public void setViaIteratorAfterPreviousCall();

	public void setViaIteratorAfterAddCall();

	public void setViaIteratorAfterRemoveCall();
}