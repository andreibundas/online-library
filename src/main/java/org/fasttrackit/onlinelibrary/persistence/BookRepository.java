package org.fasttrackit.onlinelibrary.persistence;

import org.fasttrackit.onlinelibrary.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {


}
