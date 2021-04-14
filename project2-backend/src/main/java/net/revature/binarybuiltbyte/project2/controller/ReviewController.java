package net.revature.binarybuiltbyte.project2.controller;


import net.revature.binarybuiltbyte.project2.model.Review;
import net.revature.binarybuiltbyte.project2.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("review/test")
public class ReviewController {

    @Autowired
    private ReviewRepository repo;

    @PostMapping
    public Review save(@RequestBody Review review){
        return repo.save(review);
    }
}
