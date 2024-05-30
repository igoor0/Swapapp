package dev.university.project.repository;

import dev.university.project.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    Optional<Transaction> findById(String id);
    List<Transaction> findAllBySellerId(String userId);
}
