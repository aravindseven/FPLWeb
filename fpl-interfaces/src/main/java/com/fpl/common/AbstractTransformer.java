package com.fpl.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractTransformer<InputType, OutputType> {

	public abstract OutputType transform(InputType input);
	
	public Collection<OutputType> transform(final Collection<InputType> list) {
		final List<OutputType> outputList = new ArrayList<OutputType>();
		for(final InputType input : list) {
			outputList.add(transform(input));
		}
		return outputList;
	}
}
