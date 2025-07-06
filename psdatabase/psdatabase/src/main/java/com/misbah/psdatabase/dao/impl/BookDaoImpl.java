package com.misbah.psdatabase.dao.impl;

import com.misbah.psdatabase.dao.BookDao;
import com.misbah.psdatabase.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDaoImpl implements BookDao
{

    private final JdbcTemplate jdbcTemplate;
    public BookDaoImpl(final JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public void create(Book book)
    {
        jdbcTemplate.update("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId());

    };

    @Override
    public Optional<Book> findOne(String isbn) {
        List<Book> results=jdbcTemplate.query(
                "SELECT isbn,title,author_id from books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query("SELECT isbn,title,author_id from books",
                new BookRowMapper());
    }

    @Override
    public void update(String isbn, Book bookA)
    {
         jdbcTemplate.update("UPDATE books SET isbn = ?, title = ? ,author_id=? WHERE isbn= ? ",
                bookA.getIsbn(),bookA.getTitle(),bookA.getAuthorId(),bookA.getIsbn());

    }

    @Override
    public void delete(String isbn)
    {
        jdbcTemplate.update("DELETE FROM books where isbn = ? ",isbn);

    }

    public static class BookRowMapper implements RowMapper<Book>
    {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getLong("author_id"))
                    .build();
        }
    }
}


