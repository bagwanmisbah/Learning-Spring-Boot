package com.misbah.psdatabase;

import com.misbah.psdatabase.domain.Author;
import com.misbah.psdatabase.domain.Book;

public final class TestDataUtil {
    private TestDataUtil()
    {

    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Walter White")
                .age(52)
                .build();
    }
    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Saul Goodman")
                .age(44)
                .build();
    }
    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Rick Grimes")
                .age(38)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("Chemistry")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("How to Babysit Holly")
                .authorId(1L)
                .build();
    }
    public static Book createTestBookC() {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("How to say my Name")
                .authorId(1L)
                .build();
    }
}
