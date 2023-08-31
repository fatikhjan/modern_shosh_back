package it.shoshapartment.Entity.Controller;

import it.shoshapartment.Entity.Entity.Blogs;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.BlogDto;
import it.shoshapartment.Entity.Repository.BlogsRepository;
import it.shoshapartment.Entity.Service.BlogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/blogs")
@CrossOrigin
public class BlogController {


    private final BlogsService blogsService;

    private final BlogsRepository blogsRepository;

    @PostMapping
    public HttpEntity<?> addBlog(@RequestBody BlogDto blogDto){
        Integer integer = blogsService.addBlog(blogDto);
        return ResponseEntity.ok(integer);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Blogs> all = blogsRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Blogs blogs = blogsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found blogs or news"));
        return ResponseEntity.ok(blogs);
    }

    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadPhoto(@PathVariable Integer id, @RequestParam(name = "photoId") UUID photoId){
        Blogs blogs = blogsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found blog or news"));
        blogs.setPhotoId(photoId);
        blogsRepository.save(blogs);
        return ResponseEntity.ok(new ApiResponse("saqlandi", true, 200));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editBlog(@PathVariable Integer id, @RequestBody BlogDto blogDto){
        ApiResponse apiResponse = blogsService.editData(id, blogDto);
        return ResponseEntity.status(apiResponse.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteBlogs(@PathVariable Integer id){
        Blogs blogs = blogsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found blog or news"));
        blogsRepository.delete(blogs);
        return ResponseEntity.ok(new ApiResponse("удаленный", true, 200));
    }
}
