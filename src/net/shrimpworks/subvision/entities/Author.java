package net.shrimpworks.subvision.entities;

public class Author {

	private final String name;

	public Author(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return name.equals(obj);
	}

	@Override
	public String toString() {
		return name;
	}
}
