package com.cronutils.model.field.expression;

import org.apache.commons.lang3.Validate;

/*
 * Copyright 2014 jmrozanec
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.cronutils.model.field.value.IntegerFieldValue;

/**
 * Represents every x time on a cron field.
 */
public class Every extends FieldExpression {

	private static final String EMPTY_STRING = "";

	private FieldExpression expression;
	private IntegerFieldValue period;

	public Every(IntegerFieldValue time) {
		this(new Always(), time);
	}

	public Every(FieldExpression expression, IntegerFieldValue period) {
		this.expression = Validate.notNull(expression, "Expression must not be null");
		this.period = period == null ? new IntegerFieldValue(1) : period;
	}

	public IntegerFieldValue getPeriod() {
		return period;
	}

	public FieldExpression getExpression() {
		return expression;
	}

	@Override
	public String asString() {
		if (period.getValue() == 1) {
			return expression.asString() != null ? expression.asString() : EMPTY_STRING;
		}
		return String.format("%s/%s", expression.asString(), getPeriod());
	}
}
