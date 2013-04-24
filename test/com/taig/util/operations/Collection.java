package com.taig.util.operations;

import com.taig.util.operations.collection.*;
import com.taig.util.operations.collection.IsEmpty;
import com.taig.util.operations.collection.Size;

public interface Collection extends Add, AddAll, Clear, Contains, ContainsAll, IsEmpty, Iterator, Remove, RemoveAll, RetainAll, Size, ToArray, ToTypedArray {}