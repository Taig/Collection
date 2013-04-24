package com.taig.util.operations.list;

public interface Get
{
	public void getWithIndexOnEmptyCollection();

	public void getWithInvalidIndexOnHealthyCollection();

	public void getWithValidIndexOnHealthyCollection();
}