package eu.woolplatform.utils.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

import org.joda.time.LocalDate;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This deserializer can convert a string in format yyyy-MM-dd to a {@link
 * LocalDate LocalDate}.
 * 
 * @author Dennis Hofs (RRD)
 */
public class SqlDateDeserializer extends JsonDeserializer<LocalDate> {
	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String val = jp.readValueAs(String.class);
		DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd");
		try {
			return parser.parseLocalDate(val);
		} catch (IllegalArgumentException ex) {
			throw new JsonParseException(jp, "Invalid date string: " + val +
					": " + ex.getMessage(), jp.getTokenLocation(), ex);
		}
	}
}
