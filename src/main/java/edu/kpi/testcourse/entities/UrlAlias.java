package edu.kpi.testcourse.entities;

/**
 * A single shortened URL.
 *
 * @param alias The unique short identifier of this URL. Prepend <tt>https://example.org/r/</tt> to get the short URL.
 */
public record UrlAlias(String alias, String destinationUrl) {
}
