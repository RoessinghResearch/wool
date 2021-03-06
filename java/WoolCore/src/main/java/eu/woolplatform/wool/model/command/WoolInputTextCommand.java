package eu.woolplatform.wool.model.command;

import eu.woolplatform.utils.exception.LineNumberParseException;
import eu.woolplatform.wool.parser.WoolBodyToken;

import java.util.Map;

public class WoolInputTextCommand extends WoolInputAbstractTextCommand {
	public WoolInputTextCommand(String variableName) {
		super(TYPE_TEXT, variableName);
	}

	public WoolInputTextCommand(WoolInputTextCommand other) {
		super(other);
	}

	@Override
	public WoolInputTextCommand clone() {
		return new WoolInputTextCommand(this);
	}

	public static WoolInputCommand parse(WoolBodyToken cmdStartToken,
			Map<String,WoolBodyToken> attrs) throws LineNumberParseException {
		String variableName = readVariableAttr("value", attrs, cmdStartToken,
				true);
		WoolInputTextCommand command = new WoolInputTextCommand(variableName);
		WoolInputAbstractTextCommand.parseAttributes(command, cmdStartToken,
				attrs);
		return command;
	}
}
