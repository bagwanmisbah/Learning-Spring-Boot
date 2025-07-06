package com.misbah.psdatabase.dao.impl;

import com.misbah.psdatabase.TestDataUtil;
import com.misbah.psdatabase.dao.AuthorDao;
import com.misbah.psdatabase.dao.impl.BookDaoImpl;
import com.misbah.psdatabase.domain.Author;
import com.misbah.psdatabase.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTests {

    private final AuthorDao authorDao;

    private final BookDaoImpl underTest;

    @Autowired

    public BookDaoImplIntegrationTests(BookDaoImpl underTest,AuthorDao authorDao) {
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled()
    {
        Author author=TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book book= TestDataUtil.createTestBookC();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result=underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled()
    {
        Author author=TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book bookA=TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);

        Book bookB=TestDataUtil.createTestBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);

        Book bookC=TestDataUtil.createTestBookC();
        bookC.setAuthorId(author.getId());
        underTest.create(bookC);

        List<Book> result=underTest.find();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookA,bookB,bookC);
//        2:47

    }
    //2:56
    @Test
    public void testThatBookCanBeUpdated()
    {
        Author author=TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA=TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);

        bookA.setTitle("UPDATED");
        underTest.update(bookA.getIsbn(),bookA);

        Optional<Book>result=underTest.findOne(bookA.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);


    }
    @Test
    public void testThatBookCanBeDeleted()
    {
        Author author=TestDataUtil.createTestAuthorA();
        authorDao.create(author);
        Book bookA=TestDataUtil.createTestBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);

        underTest.delete("978-1-2345-6789-0");
        Optional<Book>result=underTest.findOne(bookA.getIsbn());
        assertThat(result).isEmpty();

    }

}
