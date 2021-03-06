/*
 * Copyright 2019 Roessingh Research and Development.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a
 * copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package eu.woolplatform.utils.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Instant;

/**
 * This serializer can convert a date/time type to a string in format
 * yyyy-MM-dd'T'HH:mm:ss.SSSZZ. It supports the following types:
 * 
 * <p><ul>
 * <li>{@link Long Long} (timestamp in milliseconds)</li>
 * <li>{@link Date Date}</li>
 * <li>{@link Instant Instant}</li>
 * <li>{@link Calendar Calendar}</li>
 * <li>{@link DateTime DateTime}</li>
 * </ul></p>
 * 
 * <p>The types Long, Date and Instant are translated to the default time zone.
 * Local date/times are not supported, because it may represent a time that
 * doesn't exist in the default time zone.</p>
 * 
 * @author Dennis Hofs (RRD)
 */
public class IsoDateTimeSerializer extends JsonSerializer<Object> {
	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		DateTime dateTime;
		if (value instanceof Long || value instanceof Date ||
				value instanceof Calendar) {
			dateTime = new DateTime(value);
		} else if (value instanceof Instant) {
			dateTime = ((Instant)value).toDateTime();
		} else if (value instanceof DateTime) {
			dateTime = (DateTime)value;
		} else {
			throw new JsonGenerationException(
					"Can't serialize type to ISO date/time: " +
					value.getClass().getName(), jgen);
		}
		jgen.writeString(dateTime.toString("yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
	}
}
