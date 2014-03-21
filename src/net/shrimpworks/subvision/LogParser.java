package net.shrimpworks.subvision;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.shrimpworks.subvision.entities.Author;
import net.shrimpworks.subvision.entities.LogEntry;
import net.shrimpworks.subvision.entities.SvnPath;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LogParser extends DefaultHandler {

	private static final DateFormat LOG_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");

	private final List<LogEntry> entries;

	private LogEntry entry;
	private SvnPath path;
	private String stringVal;

	public LogParser(File logFile) throws SAXException, ParserConfigurationException, IOException {
		entries = new ArrayList<>();

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = spf.newSAXParser();
		sp.parse(logFile, this);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("logentry")) {
			entry = new LogEntry(Integer.parseInt(attributes.getValue("revision")));
		} else if (qName.equals("path")) {
			path = new SvnPath(SvnPath.Kind.get(attributes.getValue("kind")),
							   SvnPath.Action.get(attributes.getValue("action")),
							   attributes.getValue("copyfrom-path"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("logentry")) {
			entries.add(entry);
		} else if (qName.equals("path")) {
			entry.paths().add(path);
		} else if (qName.equals("author")) {
			entry.setAuthor(new Author(stringVal.trim()));
		} else if (qName.equals("msg")) {
			entry.setMessage(stringVal.trim());
		} else if (qName.equals("date")) {
			try {
				entry.setDate(LOG_DATE_FORMAT.parse(stringVal.trim()));
			} catch (ParseException e) {
				throw new SAXException("Unable to parse date: " + stringVal, e);
			}
		} else if (qName.equals("path")) {
			path.setPath(stringVal.trim());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		stringVal = new String(ch, start, length);
	}

	public List<LogEntry> entries() {
		return entries;
	}
}
