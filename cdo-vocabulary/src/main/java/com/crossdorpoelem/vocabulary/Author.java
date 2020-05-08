package com.crossdorpoelem.vocabulary;

import javax.validation.constraints.NotBlank;

public class Author {

	@NotBlank
	private String name;

	public String getName() {
		return name;
	}

	private Author(Builder builder) {
		name = builder.name;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static final class Builder {
		private @NotBlank String name;

		private Builder() {
		}

		public Builder withName(@NotBlank String name) {
			this.name = name;
			return this;
		}

		public Author build() {
			return new Author(this);
		}
	}
}
