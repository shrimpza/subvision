package net.shrimpworks.subvision;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;

import net.shrimpworks.subvision.entities.LogEntry;
import org.xml.sax.SAXException;

public class Main {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
		List<LogEntry> entries = new LogParser("test.xml").entries();
		for (LogEntry entry : entries) {
			System.out.println(entry.author() + " @ " + entry.date() + ": " + entry.message());
		}
	}
}
