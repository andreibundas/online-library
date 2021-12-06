package org.fasttrackit.onlinelibrary.persistence;

import org.fasttrackit.onlinelibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
