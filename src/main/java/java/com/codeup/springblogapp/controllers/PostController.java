package java.com.codeup.springblogapp.controllers;

import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    private UserRepository userDao; // this has all the functions of JpaRepository
    private PostRepository postDao; // this has all the functions of JpaRepository

    public PostController(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String showPostsIndexPage(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String showAnIndividualPost(@PathVariable long id, Model model) {
        Post thisPost = postDao.getOne(id); // using JpaRepository.getOne, but for our Post objects
        model.addAttribute("post", thisPost);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() { return "posts/create"; }

    @PostMapping("/posts/create")
    public String submitCreatePost(@RequestParam String title, @RequestParam String body) {
        // before saving a post to the dB, assign a user to that post
        // for our purposes, we'll assign user id 1 manually
        User author = userDao.getOne(1L);
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setBody(body);
        // manually assign user id 1 to this new post
        newPost.setUser(author);
        postDao.save(newPost);
        return "redirect:/posts";
    }
}
