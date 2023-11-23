package io.metaloom.utils.fs;

import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class FilterHelper {

	private FilterHelper() {
	}

	public static List<String> getVideoExtensions() {
		return Arrays.asList("avi", "mp4", "mpg", "mpeg", "mkv", "wmv", "qt", "mov", "flv", "vob", "m2ts", "ts", "f4v", "rm", "m4v", "vid");
	}

	public static List<String> getAudioExtensions() {
		return Arrays.asList("wav", "mp3", "oga", "flac");
	}

	public static List<String> getImageExtensions() {
		return Arrays.asList("jpg", "jpeg", "bmp", "tga", "gif", "webp", "png", "raw", "arw");
	}

	public static List<String> getDocumentExtensions() {
		return Arrays.asList("odt", "rtf", "doc", "docx", "txt", "pdf", "md", "adoc");
	}

	public static boolean isDirectory(Path path, LinkOption... options) {
		return path.toFile().isDirectory();
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

	public static boolean isAudio(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return isAudio(name, options);
	}

	public static boolean isMP4(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return name.endsWith(".mp4");
	}

	public static boolean isJPG(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return name.endsWith(".jpg") || name.endsWith("jpeg");
	}

	public static boolean matchesExtension(String filename, List<String> extensions) {
		filename = filename.toLowerCase();
		for (String ext : extensions) {
			if (filename.endsWith("." + ext)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isVideo(String name, LinkOption... options) {
		return matchesExtension(name, FilterHelper.getVideoExtensions());
	}

	public static boolean isVideo(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return isVideo(name, options);
	}

	public static boolean isImage(String name, LinkOption... options) {
		return matchesExtension(name, FilterHelper.getImageExtensions());
	}

	public static boolean isDocument(String name, LinkOption... options) {
		return matchesExtension(name, FilterHelper.getDocumentExtensions());
	}

	public static boolean isDocument(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return isDocument(name, options);
	}

	public static boolean isImage(Path path, LinkOption... options) {
		String name = path.toFile().getName().trim().toLowerCase();
		return isImage(name, options);
	}

	public static boolean notEmpty(Path path, LinkOption... options) {
		return path.toFile().length() != 0;
	}

}
