package io.metaloom.utils.fs;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.metaloom.utils.ByteBufferUtils;

public final class XAttrUtils {
	
	public static final Logger log = LoggerFactory.getLogger(XAttrUtils.class);

	private XAttrUtils() {
	}

	public static void clearXAttr(Path path) {
		try {
			UserDefinedFileAttributeView userDefinedFAView = Files
				.getFileAttributeView(path, UserDefinedFileAttributeView.class);
			for (String key : userDefinedFAView.list()) {
				userDefinedFAView.delete(key);
			}
		} catch (Exception e) {
			throw new RuntimeException("Could not clear xattr for path " + path, e);
		}
	}

	public static <T> T readAttr(Path path, String key, Class<T> classOfT) {
		try {
			UserDefinedFileAttributeView userDefinedFAView = Files
				.getFileAttributeView(path, UserDefinedFileAttributeView.class);
			if (!userDefinedFAView.list().contains(key)) {
				return null;
			}
			int size = userDefinedFAView.size(key);
			if (size == -1) {
				return null;
			}
			ByteBuffer buffer = ByteBuffer.allocate(size);
			userDefinedFAView.read(key, buffer);
			return ByteBufferUtils.convertFromByteBuffer(buffer, classOfT);
		} catch (Exception e) {
			throw new RuntimeException("Could not read property " + key, e);
		}
	}

	public static void writeAttr(Path path, String key, Object value) {
		try {
			UserDefinedFileAttributeView userDefinedFAView = Files
				.getFileAttributeView(path, UserDefinedFileAttributeView.class);
			ByteBuffer buffer = ByteBufferUtils.convertToByteBuffer(value);
			userDefinedFAView.write(key, buffer);
		} catch (Exception e) {
			throw new RuntimeException("Failed to write attr {" + key + "} to file {" + path + "}", e);
		}
	}

	public static void writeAttr(Path path, String key, Long value) {
		writeAttr(path, key, String.valueOf(value));
	}

	public static List<String> listAttr(Path path) {
		try {
			UserDefinedFileAttributeView userDefinedFAView = Files
				.getFileAttributeView(path, UserDefinedFileAttributeView.class);
			return userDefinedFAView.list();
		} catch (Exception e) {
			throw new RuntimeException("Failed to list attr for file {" + path + "}", e);
		}
	}

}
