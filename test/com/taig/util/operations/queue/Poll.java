package com.taig.util.operations.queue;

public interface Poll
{
	public void pollOnEmptyQueue();

	public void pollOnHealthyQueue();
}