/*
 * #%L
 * de-metas-common-externalsystem
 * %%
 * Copyright (C) 2021 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

package de.metas.common.externalsystem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

/**
 * Send from metasfresh to indicate that metasfresh wants an external system to do something
 */
@Value
public class JsonExternalSystemRequest
{
	String orgCode;

	JsonExternalSystemName externalSystemName;

	String command;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	Map<String, String> parameters;

	@Builder
	@JsonCreator
	public JsonExternalSystemRequest(
			@JsonProperty("orgCode") @NonNull final String orgCode,
			@JsonProperty("externalSystemName") @NonNull final JsonExternalSystemName externalSystemName,
			@JsonProperty("command") @NonNull final String command,
			@JsonProperty("parameters") @Singular final Map<String, String> parameters)
	{
		this.orgCode = orgCode;
		this.externalSystemName = externalSystemName;
		this.command = command;
		this.parameters = parameters;
	}
}
