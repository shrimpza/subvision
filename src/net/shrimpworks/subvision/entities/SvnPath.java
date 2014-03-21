package net.shrimpworks.subvision.entities;

public class SvnPath {

	public enum Kind {
		FILE,
		DIRECTORY,
		UNKNOWN;

		public static Kind get(String kind) {
			if (kind.equalsIgnoreCase("file")) return FILE;
			else if (kind.equalsIgnoreCase("dir")) return DIRECTORY;
			else return UNKNOWN;
		}
	}

	public enum Action {
		ADD,
		MODIFY,
		RENAME,
		DELETE,
		UNKNOWN;

		public static Action get(String action) {
			if (action.equalsIgnoreCase("A")) return ADD;
			else if (action.equalsIgnoreCase("M")) return MODIFY;
			else if (action.equalsIgnoreCase("R")) return RENAME;
			else if (action.equalsIgnoreCase("D")) return DELETE;
			else return UNKNOWN;
		}
	}

	private final Kind kind;
	private final Action action;
	private final String copiedFrom;

	private String path;

	public SvnPath(Kind kind, Action action, String copiedFrom) {
		this.kind = kind;
		this.action = action;
		this.copiedFrom = copiedFrom;
	}

	public Kind kind() {
		return kind;
	}

	public Action action() {
		return action;
	}

	public String copiedFrom() {
		return copiedFrom;
	}

	public String path() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
