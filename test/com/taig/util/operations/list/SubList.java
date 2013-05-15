package com.taig.util.operations.list;

public interface SubList
{
	public void subListOnEmptyList();

	public void subListWithInvalidIndexesOnHealthyList();

	public void subListWithInvalidStartIndexesOnHealthyList();

	public void subListWithInvalidEndIndexesOnHealthyList();

	public void subListWithValidIndexesOnHealthyList();
}