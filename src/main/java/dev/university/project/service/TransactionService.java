package dev.university.project.service;

import dev.university.project.model.Transaction;
import dev.university.project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> getTransactions() {return transactionRepository.findAll();}

    public Transaction getTransaction(String id) {return transactionRepository.findById(id).orElse(null);}

    public Transaction createTransaction(Transaction transaction) {return transactionRepository.save(transaction);}

    public Transaction updateTransaction(String id, Transaction transaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null) {
            return null;
        }
        existingTransaction.setBuyerId(transaction.getBuyerId());
        existingTransaction.setSellerId(transaction.getSellerId());
        existingTransaction.setProductsId(transaction.getProductsId());
        existingTransaction.setQuantity(transaction.getQuantity());
        existingTransaction.setTotal(transaction.getTotal());
        existingTransaction.setDate(transaction.getDate());
        return transactionRepository.save(existingTransaction);
    }

    public boolean deleteTransaction(String id) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction == null) {
            return false;
        }
        transactionRepository.delete(existingTransaction);
        return true;
    }

}
