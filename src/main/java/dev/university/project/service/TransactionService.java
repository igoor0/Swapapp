package dev.university.project.service;

import dev.university.project.model.Product;
import dev.university.project.model.Transaction;
import dev.university.project.model.User;
import dev.university.project.repository.ProductRepository;
import dev.university.project.repository.TransactionRepository;
import dev.university.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public List<Transaction> getTransactions() { return transactionRepository.findAll();}

    public Transaction getTransaction(String id) {return transactionRepository.findById(id).orElse(null);}

//    public Transaction createTransaction(Transaction transaction) {
//        List<Product> product = productRepository.findAllById(transaction.getProductsId());
//        transaction.setSellerId(transaction.getSellerId());
//        transaction.setBuyerId(transaction.getBuyerId());
//        transaction.setProductsId(transaction.getProductsId());
//        transaction.setCreatedAt(LocalDateTime.now());
//        transaction.setTotalPrice((int) product.stream().mapToDouble(Product::getPrice).sum());
//        //userService.updateUserTransactionList(transaction);
//        List<Product> productsExchanged = productRepository.findAllById(transaction.getProductsId());
//        List<String> productIds = productsExchanged.stream().map(Product::getId).toList();
//        User buyer = userService.getUser(transaction.getBuyerId());
//        User seller = userService.getUser(transaction.getSellerId());
//        //buyer.getOwnedProductIdList().addAll(productIds);
//        userRepository.save(buyer);
//        //seller.getOwnedProductIdList().remove(productIds);
//        userRepository.save(seller);
//        return transactionRepository.save(transaction);
//    }

    public Transaction createTransaction(Transaction transaction) {
        List<Product> products = productRepository.findAllById(transaction.getProductsId());
        Transaction newTransaction = new Transaction();
        newTransaction.setTotalPrice((int) products.stream().mapToDouble(Product::getPrice).sum());
        newTransaction.setCreatedAt(LocalDateTime.now());
        newTransaction.setSellerId(transaction.getSellerId());
        newTransaction.setBuyerId(transaction.getBuyerId());
        newTransaction.setProductsId(transaction.getProductsId());
        return transactionRepository.save(newTransaction);
    }


    public List<Transaction> getTransactionsBySellerId(String sellerId) {
        return transactionRepository.findAllBySellerId(sellerId);
    }
    public List<Transaction> getTransactionByBuyerId(String buyerId) {
        return transactionRepository.findAllByBuyerId(buyerId);
    }

    public Boolean deleteTransaction(String transactionId) {
        transactionRepository.deleteById(transactionId);
        return true;
    }

    public List<Transaction> getTransactionsBySellerOrBuyer(String userId) {
        List<Transaction> l1 = getTransactionsBySellerId(userId);
        List<Transaction> l2 = getTransactionByBuyerId(userId);
        List<Transaction> l3 = Stream.concat(l1.stream(), l2.stream()).collect(Collectors.toList());
        return l3;
    }
}
