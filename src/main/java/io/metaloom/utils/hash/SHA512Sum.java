package io.metaloom.utils.hash;

import java.io.Serializable;

public class SHA512Sum implements Comparable<SHA512Sum>, Serializable {

	private static final long serialVersionUID = -4774581209430311627L;

	private final String hash;

	public SHA512Sum(String hash) {
		this.hash = hash;
	}

	@Override
	public int compareTo(SHA512Sum o) {
		return toString().compareTo(o.toString());
	}

	@Override
	public int hashCode() {
		return hash.hashCode();
	}

	@Override
	public String toString() {
		return hash;
	}

	public static SHA512Sum fromString(String hash) {
		return new SHA512Sum(hash);
	}

	@Override
	public boolean equals(Object obj) {
		return hash.equals(obj);
	}
}
