package com.taig.util;

public class Node<T>
{
	public Node<T> left, right;

	public T payload;

	protected Node()
	{
		super();
	}

	public Node( Node<T> left, Node<T> right, T payload )
	{
		if( left == null || right == null )
		{
			throw new IllegalArgumentException( "Nodes may not be null; use Node.Empty instead" );
		}

		left.right = this;
		this.left = left;

		right.left = this;
		this.right = right;

		this.payload = payload;
	}

	public boolean isValid()
	{
		return left.right.equals( this ) && right.left.equals( this );
	}

	public void remove()
	{
		if( isValid() )
		{
			left.right = right;
			right.left = left;
		}
		else
		{
			throw new IllegalStateException( "This Node has already been removed" );
		}
	}

	public boolean isEmpty()
	{
		return false;
	}

	public static class Empty<T> extends Node<T>
	{
		public Empty()
		{
			left = right = this;
		}

		public Empty( Node<T> left, Node<T> right )
		{
			this.left = left;
			this.right = right;
		}

		@Override
		public void remove()
		{
			throw new IllegalStateException( "Empty Nodes cannot be removed" );
		}

		@Override
		public boolean isEmpty()
		{
			return true;
		}
	}
}