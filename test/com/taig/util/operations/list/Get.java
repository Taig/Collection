package com.taig.util.operations.list;

public interface Get
{
	public void getOnEmptyCollection();

	public void getWithInvalidIndexOnHealthyCollection();

	public void getWithValidIndexOnHealthyCollection();
}