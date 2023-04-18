package io.metaloom.utils.fs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public final class INodeUtils {

	public static long loadInode(File file) throws IOException {
		return loadInode(file.toPath());
	}

	/**
	 * Load the inode of the file for the provided path. Method may fail on filesystems/os which do not support inodes.
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static long loadInode(Path path) throws IOException {
		try {
			return (long) Files.getAttribute(path, "unix:ino");
		} catch (UnsupportedOperationException | IllegalArgumentException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Load the mtime nanosecond component from the file attributes of the provided file path.
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static long loadModificationNano(Path path) throws IOException {
		try {
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			FileTime ts = attr.lastModifiedTime();
			return ts.toInstant().getNano();
		} catch (UnsupportedOperationException | IllegalArgumentException e) {
			throw new IOException(e);
		}
	}

	public static long loadModificationEpochSecond(Path path) throws IOException {
		try {
			BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
			FileTime ts = attr.lastModifiedTime();
			return ts.toInstant().getEpochSecond();
		} catch (UnsupportedOperationException | IllegalArgumentException e) {
			throw new IOException(e);
		}
	}

}
