package io.metaloom.utils;

import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class FilterHelper {

	private FilterHelper() {
	}

	public static List<String> getVideoExtensions() {
		return Arrays.asList("avi", "mp4", "mpg", "mpeg", "mkv", "wmv", "qt", "mov", "flv", "vob", "m2ts", "ts", "f4v", "rm", "m4v");
	}

	public static List<String> getAudioExtensions() {
		return Arrays.asList("wav", "mp3", "oga", "flac");
	}

	public static boolean isDirectory(Path path, LinkOption... options) {
		return path.toFile().isDirectory();
	}

	public static boolean isVideo(String name, LinkOption... options) {
		name = name.toLowerCase();
		for (String ext : FilterHelper.getVideoExtensions()) {
			if (name.endsWith("." + ext)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isAudio(String name, LinkOption... options) {
		name = name.toLowerCase();
		for (String ext : FilterHelper.getAudioExtensions()) {
			if (name.endsWith("." + ext)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isMP4(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return name.endsWith(".mp4");
	}

	public static boolean isVideo(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return isVideo(name, options);
	}

	public static boolean notEmpty(Path path, LinkOption... options) {
		return path.toFile().length() != 0;
	}

}
