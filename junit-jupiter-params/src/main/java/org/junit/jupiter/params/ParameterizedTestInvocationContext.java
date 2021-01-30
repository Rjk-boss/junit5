/*
 * Copyright 2015-2021 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.params;

import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;

/**
 * @since 5.0
 */
class ParameterizedTestInvocationContext implements TestTemplateInvocationContext {

	private final ParameterizedTestNameFormatter formatter;
	private final ParameterizedTestMethodContext methodContext;
	private final List<Named<Object>> arguments;

	ParameterizedTestInvocationContext(ParameterizedTestNameFormatter formatter,
			ParameterizedTestMethodContext methodContext, List<Named<Object>> arguments) {
		this.formatter = formatter;
		this.methodContext = methodContext;
		this.arguments = arguments;
	}

	@Override
	public String getDisplayName(int invocationIndex) {
		return this.formatter.format(invocationIndex, this.arguments.toArray());
	}

	@Override
	public List<Extension> getAdditionalExtensions() {
		return singletonList(new ParameterizedTestParameterResolver(this.methodContext, this.arguments));
	}

}
