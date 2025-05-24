package com.kakaotechcampus.schedule_app.Lv3_6.repository;

import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;

public interface AuthorRepository {
    Author createAuthor(Author author);
    Author findAuthorByIdOrElseThrow(Long authorId);
}
