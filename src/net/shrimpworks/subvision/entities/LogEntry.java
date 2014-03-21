package net.shrimpworks.subvision.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class LogEntry {

	private final int revision;
	private Author author;
	private Date date;
	private String message;
	private Set<SvnPath> paths;

	public LogEntry(int revision) {
		this.revision = revision;
		this.paths = new HashSet<>();
	}

	public int revision() {
		return revision;
	}

	public Author author() {
		return author;
	}

	public Date date() {
		return date;
	}

	public String message() {
		return message;
	}

	public Set<SvnPath> paths() {
		return paths;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPaths(Set<SvnPath> paths) {
		this.paths = paths;
	}
}
