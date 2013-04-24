package com.taig.util.operations.list;

public interface SubList
{
	public void subListOnEmptyCollection();

	public void subListWithInvalidIndexesOnHealthyCollection();

	public void subListWithInvalidStartIndexesOnHealthyCollection();

	public void subListWithInvalidEndIndexesOnHealthyCollection();

	public void subListWithValidIndexesOnHealthyCollection();
}