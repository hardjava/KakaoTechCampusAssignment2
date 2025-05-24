package com.kakaotechcampus.schedule_app.Lv3_6.service;

import com.kakaotechcampus.schedule_app.Lv3_6.dto.AuthorResponseDto;
import com.kakaotechcampus.schedule_app.Lv3_6.entity.Author;
import com.kakaotechcampus.schedule_app.Lv3_6.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorResponseDto createAuthor(String email, String name){
        Author author = new Author(email, name);
        Author savedAuthor = authorRepository.createAuthor(author);

        return new AuthorResponseDto(savedAuthor.getId(), savedAuthor.getEmail(), savedAuthor.getName());
    }
}
