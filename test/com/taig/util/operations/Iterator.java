package com.taig.util.operations;

import com.taig.util.operations.iterator.*;

public interface	Iterator
extends				HasNext,
					Next,
					Remove
{
	public void getIterator();
}