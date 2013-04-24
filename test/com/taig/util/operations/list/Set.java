package com.taig.util.operations.list;

public interface Set
{
	public void setOnEmptyCollection();

	public void setWithNullArgument();

	public void setWithInvalidIndexOnHealthyCollection();

	public void setWithValidIndexOnHealthyCollection();
}