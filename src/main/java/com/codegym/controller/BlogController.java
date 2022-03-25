package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.BlogForm;
import com.codegym.service.blog.IBlogService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private IBlogService blogService;

    @Value("${file-upload}")//giá trị trong file upload-file.properties
    private String uploadPath;

    @GetMapping("")
    public ModelAndView showAllBlogs(@RequestParam(name = "q") Optional<String> q ) {
        ModelAndView modelAndView = new ModelAndView("blog/list");
        List<Blog> blogs = blogService.findAll();
        if (q.isPresent()){
            blogs = blogService.findByName(q.get());
        }
        modelAndView.addObject("blogs", blogs);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("blog/create");
        modelAndView.addObject("blog", new BlogForm());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createBlog(@ModelAttribute BlogForm blogForm) {
        String fileName = blogForm.getImage().getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;
        try {
            FileCopyUtils.copy(blogForm.getImage().getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Blog blog = new Blog(blogForm.getId(), blogForm.getTittle(), blogForm.getContent(), fileName);
        blogService.save(blog);
        return new ModelAndView("redirect:/blogs");
    }

    @GetMapping("/edit/{id}")
    ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("blog/edit");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editBlog(@PathVariable Long id, @ModelAttribute BlogForm blogForm) {
        MultipartFile img = blogForm.getImage();
        Blog oldBlog = blogService.findById(id);
        if (img.getSize() != 0) {
            String fileName = blogForm.getImage().getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + fileName;
            oldBlog.setImage(fileName);
            try {
                FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        oldBlog.setTittle(blogForm.getTittle());
        oldBlog.setContent(blogForm.getContent());
        blogService.save(oldBlog);
        return new ModelAndView("redirect:/blogs");
    }

    @GetMapping("delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/blog/delete");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }

    @PostMapping("delete/{id}")
    public ModelAndView deleteBlog(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        File file = new File(uploadPath + blog.getImage());
        if (file.exists()) {
            file.delete();
        }
        blogService.remove(id);
        return new ModelAndView("redirect:/blogs");
    }

    @GetMapping ("/{id}")
    public ModelAndView showBlogDetail (@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/view");
        Blog blog = blogService.findById(id);
        modelAndView.addObject("blog", blog);
        return modelAndView;
    }
}
