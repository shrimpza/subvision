package net.shrimpworks.subvision;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.shrimpworks.subvision.entities.Author;
import net.shrimpworks.subvision.entities.LogEntry;
import org.xml.sax.SAXException;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogParserTest {

	@Test
	public void testLoadXmlFile() throws URISyntaxException, IOException, SAXException, ParserConfigurationException {
		List<LogEntry> entries = new LogParser(new File(this.getClass().getResource("test.xml").toURI())).entries();
		assertFalse(entries.isEmpty());

		LogEntry entry;

		// verify first log entry against known values
		entry = entries.get(0);
		assertEquals(new Author("bob"), entry.author());
		assertEquals(45756, entry.revision());
		assertFalse(entry.paths().isEmpty());
		assertEquals("Running log message", entry.message());

		// verify oldest log entry against known values
		entry = entries.get(entries.size() - 1);
		assertEquals(new Author("tommy"), entry.author());
		assertEquals(45714, entry.revision());
		assertFalse(entry.paths().isEmpty());
		assertEquals("Rather log message", entry.message());
	}
}