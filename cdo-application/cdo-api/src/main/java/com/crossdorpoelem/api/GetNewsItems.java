package com.crossdorpoelem.api;

import com.crossdorpoelem.vocabulary.Author;

public interface GetNewsItems {

	Response get();

	final class Response {

		private Author author;

		private Response(Builder builder) {
			author = builder.author;
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public static final class Builder {

			private Author author;

			private Builder() {
			}

			public Response build() {
				return new Response(this);
			}

			public Builder withAuthor(Author author) {
				this.author = author;
				return this;
			}
		}
	}

}
