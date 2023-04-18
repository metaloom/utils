package io.metaloom.utils.fs;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.directory.InvalidAttributesException;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FilenameUtils;

public final class FileUtils {

	private FileUtils() {
	}

	/**
	 * Move the file to the destination and ensure that all xattr will be moved too when the file crosses fs boundaries.
	 * 
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void moveFile(File sourceFile, File targetFile) throws IOException {
		File parentFolder = targetFile.getParentFile();
		if (!parentFolder.exists()) {
			if (!parentFolder.mkdirs()) {
				throw new RuntimeException("Could not create dir {" + parentFolder.getAbsolutePath() + "}");
			}
		}
		if (!sourceFile.isFile()) {
			throw new RuntimeException("The provided file " + sourceFile + " is not a regular file");
		}
		if (targetFile.exists()) {
			throw new FileExistsException(targetFile);
		}

		UserDefinedFileAttributeView sourceView = Files
			.getFileAttributeView(sourceFile.toPath(), UserDefinedFileAttributeView.class);

		Map<String, ByteBuffer> data = new HashMap<>();
		try {
			for (String key : sourceView.list()) {
				int size = sourceView.size(key);
				if (size == -1) {
					throw new InvalidAttributesException("Could not read size of {" + key + "}");
				}
				ByteBuffer buffer = ByteBuffer.allocate(size);
				sourceView.read(key, buffer.duplicate());
				data.put(key, buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		org.apache.commons.io.FileUtils.moveFile(sourceFile, targetFile);

		try {
			UserDefinedFileAttributeView targetView = Files
				.getFileAttributeView(targetFile.toPath(), UserDefinedFileAttributeView.class);
			for (Entry<String, ByteBuffer> entry : data.entrySet()) {
				targetView.write(entry.getKey(), entry.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate an auto-rotated file that avoids filename collisions with existing files when potentially moving source to the target folder.
	 * 
	 * @param source
	 *            Source file from which the filename will be taken
	 * @param targetFolder
	 * @return Auto-rotated target file for the target folder
	 */
	public static File autoRotate(File source, File targetFolder) {
		String filename = source.getName();
		File file = new File(targetFolder, filename);
		if (!file.exists()) {
			return file;
		} else {
			String ext = FilenameUtils.getExtension(filename);
			String base = FilenameUtils.getBaseName(filename);
			if (!ext.isEmpty()) {
				ext = "." + ext;
			}
			for (int i = 1; i < 1024; i++) {
				File newDest = new File(targetFolder, base + "_" + i + ext);
				if (!newDest.exists()) {
					return newDest;
				}
			}
			throw new RuntimeException("Unable to find new destination file for " + file.getAbsolutePath());
		}
	}

}
