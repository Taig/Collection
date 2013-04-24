package com.taig.util.operations;

import com.taig.util.operations.iterator.HasNext;
import com.taig.util.operations.iterator.Next;
import com.taig.util.operations.iterator.Remove;

public interface Iterator extends HasNext, Next, Remove
{
	public void getIterator();
}