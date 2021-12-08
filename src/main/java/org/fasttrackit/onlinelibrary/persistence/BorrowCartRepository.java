package org.fasttrackit.onlinelibrary.persistence;

import org.fasttrackit.onlinelibrary.domain.BorrowCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowCartRepository extends JpaRepository<BorrowCart, Long> {


}
