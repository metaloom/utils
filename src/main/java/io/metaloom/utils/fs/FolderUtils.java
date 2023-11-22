package io.metaloom.utils.fs;

import java.nio.file.Path;

import io.metaloom.utils.hash.SHA512Sum;

public final class FolderUtils {

	private FolderUtils() {
	}

	public static Path segmentPath(Path basePath, SHA512Sum hash) {
		String prefix = hash.toString().substring(0, 4);
		return basePath.resolve(prefix);
	}

}
