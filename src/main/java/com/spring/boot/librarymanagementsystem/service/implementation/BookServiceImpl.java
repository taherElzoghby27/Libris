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
import com.spring.boot.librarymanagementsystem.vm.activity.ActivityRequestVm;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestUpdateVm;
import com.spring.boot.librarymanagementsystem.vm.book.BookRequestVm;
import com.spring.boot.librarymanagementsystem.vm.book.BooksResponseVm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
    private final ActivityUserService activityUserService;

    @Transactional
    @Override
    public BookDto addBook(BookRequestVm bookRequestVm) {
        if (Objects.nonNull(bookRequestVm.getId())) {
            throw new BadRequestException("request must be null");
        }
        Book book = BookMapper.INSTANCE.toBook(bookRequestVm);
        // set relations
        setBookRelations(bookRequestVm, book);
        book = bookRepo.save(book);
        //add log
        activityUserService.addActivity(new ActivityRequestVm(
                        "created book",
                        "Book",
                        book.getTitle() + " created by " + book.getCreatedBy()
                )
        );
        return BookMapper.INSTANCE.toBookDto(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    protected void setBookRelations(BookRequestVm bookRequestVm, Book book) {
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
    public BookDto getBookById(Long id) {
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
        BookDto bookDto = getBookById(id);
        bookRepo.deleteById(id);
        //add log
        activityUserService.addActivity(new ActivityRequestVm(
                        "deleted book",
                        "Book",
                        bookDto.getTitle() + " deleted"
                )
        );
    }

    @Transactional
    @Override
    public BookDto updateBook(BookRequestUpdateVm bookRequestUpdateVm) {
        boolean update = false;
        BookDto oldBookDto = getBookById(bookRequestUpdateVm.getId());
        Book oldBook = BookMapper.INSTANCE.toBook(oldBookDto);
        update = updateData(bookRequestUpdateVm, oldBook, update);
        if (update) {
            oldBook = bookRepo.save(oldBook);
            //add log
            activityUserService.addActivity(new ActivityRequestVm(
                            "updated book",
                            "Book",
                            oldBook.getTitle() + " updated"
                    )
            );
            return BookMapper.INSTANCE.toBookDto(oldBook);
        }
        throw new BadRequestException("data must be different");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    protected boolean updateData(BookRequestUpdateVm bookRequestUpdateVm, Book oldBook, boolean update) {
        if (Objects.nonNull(bookRequestUpdateVm.getTitle()) && !oldBook.getTitle().equals(bookRequestUpdateVm.getTitle())) {
            update = true;
            oldBook.setTitle(bookRequestUpdateVm.getTitle());
        }
        if (Objects.nonNull(bookRequestUpdateVm.getSummary()) && !oldBook.getSummary().equals(bookRequestUpdateVm.getSummary())) {
            update = true;
            oldBook.setSummary(bookRequestUpdateVm.getSummary());
        }
        if (Objects.nonNull(bookRequestUpdateVm.getIsbn()) && !oldBook.getIsbn().equals(bookRequestUpdateVm.getIsbn())) {
            update = true;
            oldBook.setIsbn(bookRequestUpdateVm.getIsbn());
        }
        if (Objects.nonNull(bookRequestUpdateVm.getPublicationYear()) && !oldBook.getPublicationYear().equals(bookRequestUpdateVm.getPublicationYear())) {
            update = true;
            oldBook.setPublicationYear(bookRequestUpdateVm.getPublicationYear());
        }
        if (Objects.nonNull(bookRequestUpdateVm.getEdition()) && !oldBook.getEdition().equals(bookRequestUpdateVm.getEdition())) {
            update = true;
            oldBook.setEdition(bookRequestUpdateVm.getEdition());
        }
        if (Objects.nonNull(bookRequestUpdateVm.getCoverImages()) && !bookRequestUpdateVm.getCoverImages().isEmpty()) {
            update = true;
            oldBook.setCoverImages(bookRequestUpdateVm.getCoverImages());
        }
        return update;
    }
}
