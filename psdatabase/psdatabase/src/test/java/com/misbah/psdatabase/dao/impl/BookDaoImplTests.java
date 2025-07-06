package com.misbah.psdatabase.dao.impl;

import com.misbah.psdatabase.TestDataUtil;
import com.misbah.psdatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplTests
{
    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql()
    {
        Book book= TestDataUtil.createTestBookA();
        underTest.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn,title,author_id) VALUES (?,?,?)"),
        eq("978-1-2345-6789-0"),
                eq("Chemistry"),
                eq(1L));

    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql()
    {
        underTest.findOne("978-1-2345-6789-0");
        verify(jdbcTemplate).query(
                eq("SELECT isbn,title,author_id from books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-1-2345-6789-0")

        );
    }

    @Test
    public void testThatFindGeneratesCorrectSql()
    {
        underTest.find();
        verify(jdbcTemplate).query(eq("SELECT isbn,title,author_id from books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any());
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql()
    {
        Book bookA=TestDataUtil.createTestBookA();
        underTest.update("978-1-2345-6789-0",bookA);
        verify(jdbcTemplate).update(eq("UPDATE books SET isbn = ?, title = ? ,author_id=? WHERE isbn= ? "),
        eq("978-1-2345-6789-0"),eq("Chemistry"),eq(1L),eq("978-1-2345-6789-0"));
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSql()
    {
        Book bookA=TestDataUtil.createTestBookA();
        underTest.delete("978-1-2345-6789-0");
        verify(jdbcTemplate).update("DELETE FROM books where isbn = ? ","978-1-2345-6789-0");
    }

}
