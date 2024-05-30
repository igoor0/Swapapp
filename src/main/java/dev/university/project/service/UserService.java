package dev.university.project.service;

import dev.university.project.model.Transaction;
import dev.university.project.model.User;
import dev.university.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
            return userRepository.findAll();
    }
    public User getUser(String id) {
            return userRepository.findById(id).orElse(null);
    }
    public User getUserByEmailOrId(String emailOrId) {
            return userRepository.findByEmail(emailOrId).orElseGet(() -> getUser(emailOrId));
    }
//    public void updateUserTransactionList(Transaction transaction) {
//        User seller = getUser(transaction.getSellerId());
//        seller.getTransactionIdList().add(transaction.getId());
//        userRepository.save(seller);
//    }
}
