package com.spring.boot.librarymanagementsystem.service.implementation;

import com.spring.boot.librarymanagementsystem.dto.AuthorDto;
import com.spring.boot.librarymanagementsystem.dto.BookDto;
import com.spring.boot.librarymanagementsystem.dto.CategoryDto;
import com.spring.boot.librarymanagementsystem.dto.PublisherDto;
import com.spring.boot.librarymanagementsystem.entity.Author;
import com.spring.boot.librarymanagementsystem.entity.Book;
import com.spring.boot.librarymanagementsystem.entity.Category;
import com.spring.boot.librarymanagementsystem.entity.Publisher;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.BadRequestException;
import com.spring.boot.librarymanagementsystem.exception.custom_exception.NotFoundResourceException;
import com.spring.boot.librarymanagementsystem.mapper.AuthorMapper;
import com.spring.boot.librarymanagementsystem.mapper.BookMapper;
import com.spring.boot.librarymanagementsystem.mapper.CategoryMapper;
import com.spring.boot.librarymanagementsystem.mapper.PublisherMapper;
import com.spring.boot.librarymanagementsystem.repository.BookRepo;
import com.spring.boot.librarymanagementsystem.service.*;
import com.spring.boot.librarymanagementsystem.vm.BookRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.BooksResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final PublisherService publisherService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Override
    public BookDto addBook(BookRequestVm bookRequestVm) {
        if (Objects.nonNull(bookRequestVm.getId())) {
            throw new BadRequestException("request must be null");
        }
        Book book = BookMapper.INSTANCE.toBook(bookRequestVm);
        // set relations
        setRelationsToBook(bookRequestVm, book);
        book = bookRepo.save(book);
        return BookMapper.INSTANCE.toBookDto(book);
    }

    private void setRelationsToBook(BookRequestVm bookRequestVm, Book book) {
        //get publisher
        PublisherDto publisherDto = publisherService.getPublisher(bookRequestVm.getPublisherId());
        Publisher publisher = PublisherMapper.INSTANCE.toPublisher(publisherDto);
        book.setPublisher(publisher);
        //get category
        CategoryDto categoryDto = categoryService.getCategory(bookRequestVm.getCategoryId());
        Category category = CategoryMapper.INSTANCE.toCategory(categoryDto);
        book.setCategory(category);
        //get authors
        if (bookRequestVm.getAuthorsId().isEmpty()) {
            throw new BadRequestException("authors id must be not empty");
        }
        List<AuthorDto> authorsDto = bookRequestVm.getAuthorsId().stream().map(authorService::getAuthor).toList();
        List<Author> authors = authorsDto.stream().map(AuthorMapper.INSTANCE::toAuthor).toList();
        book.setAuthors(authors);
    }

    @Override
    public BooksResponseVm getAllBooksWithoutData(int page, int size) {
        Pageable pageable = PaginationService.getPageable(page, size);
        Page<Book> result = bookRepo.findAll(pageable);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("books not found");
        }
        return new BooksResponseVm(
                result.map(BookMapper.INSTANCE::toBookResponseVm).getContent(),
                result.getTotalElements()
        );
    }

    @Override
    public BookDto getBook(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        Optional<Book> result = bookRepo.findBook(id);
        if (result.isEmpty()) {
            throw new NotFoundResourceException("book not found");
        }
        return BookMapper.INSTANCE.toBookDto(result.get());
    }

    @Transactional
    @Override
    public void deleteBook(Long id) {
        if (Objects.isNull(id)) {
            throw new BadRequestException("id must be not null");
        }
        // ensure exists
        getBook(id);
        bookRepo.deleteById(id);
    }

    @Override
    public BookDto updateBook(BookRequestUpdateVm bookRequestUpdateVm) {
        boolean update = false;
        if (Objects.isNull(bookRequestUpdateVm.getId())) {
            throw new BadRequestException("id must be not null");
        }
        BookDto oldBookDto = getBook(bookRequestUpdateVm.getId());
        Book oldBook = BookMapper.INSTANCE.toBook(oldBookDto);
        update = updateData(bookRequestUpdateVm, oldBook, update);
        if (update) {
            oldBook = bookRepo.save(oldBook);
            return BookMapper.INSTANCE.toBookDto(oldBook);
        }
        throw new BadRequestException("data must be different");
    }

    private static boolean updateData(BookRequestUpdateVm bookRequestUpdateVm, Book oldBook, boolean update) {
        if (!oldBook.getTitle().equals(bookRequestUpdateVm.getTitle())) {
            update = true;
            oldBook.setTitle(bookRequestUpdateVm.getTitle());
        }
        if (!oldBook.getSummary().equals(bookRequestUpdateVm.getSummary())) {
            update = true;
            oldBook.setSummary(bookRequestUpdateVm.getSummary());
        }
        if (!oldBook.getIsbn().equals(bookRequestUpdateVm.getIsbn())) {
            update = true;
            oldBook.setIsbn(bookRequestUpdateVm.getIsbn());
        }
        if (!oldBook.getPublicationYear().equals(bookRequestUpdateVm.getPublicationYear())) {
            update = true;
            oldBook.setPublicationYear(bookRequestUpdateVm.getPublicationYear());
        }
        if (!oldBook.getEdition().equals(bookRequestUpdateVm.getEdition())) {
            update = true;
            oldBook.setEdition(bookRequestUpdateVm.getEdition());
        }
        if (!bookRequestUpdateVm.getCoverImages().isEmpty()) {
            update = true;
            oldBook.setCoverImages(bookRequestUpdateVm.getCoverImages());
        }
        return update;
    }
}
