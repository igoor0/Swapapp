package dev.university.project.service;

import dev.university.project.model.Product;
import dev.university.project.model.Transaction;
import dev.university.project.repository.ProductRepository;
import dev.university.project.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Transaction> getTransactions() { return transactionRepository.findAll();}

    public Transaction getTransaction(String id) {return transactionRepository.findById(id).orElse(null);}

    public Transaction createTransaction(Transaction transaction) {
        List<Product> product = productRepository.findAllById(transaction.getProductsId());
        transaction.setSellerId(transaction.getSellerId());
        transaction.setBuyerId(transaction.getBuyerId());
        transaction.setProductsId(transaction.getProductsId());
        transaction.setDate(new Date());
        transaction.setTotalPrice((int) product.stream().mapToDouble(Product::getPrice).sum());

        return transactionRepository.save(transaction);
    }

}
