package net.shrimpworks.subvision.entities;

public class Author {

	private final String name;

	public Author(String name) {
		if (name == null) throw new IllegalArgumentException("Name cannot be null");
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
		if (obj == null) return false;
		if (!(obj instanceof Author)) return false;

		Author author = (Author)obj;

		return name.equals(author.name);
	}

	@Override
	public String toString() {
		return name;
	}
}
