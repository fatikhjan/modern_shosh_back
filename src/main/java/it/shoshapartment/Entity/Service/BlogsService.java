package it.shoshapartment.Entity.Service;

import it.shoshapartment.Entity.Entity.Blogs;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.BlogDto;
import it.shoshapartment.Entity.Repository.BlogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogsService {

    private final BlogsRepository blogsRepository;

    public Integer addBlog(BlogDto blogDto) {
        Blogs build = Blogs.builder()
                .uzAbout(blogDto.uzAbout())
                .ruAbout(blogDto.ruAbout())
                .engAbout(blogDto.engAbout())
                .photoId(blogDto.photoId())
                .build();
        build.setName(blogDto.uzName());
        build.setRuName(blogDto.ruName());
        build.setEngName(blogDto.engName());
        Blogs blogs = blogsRepository.save(build);
        return blogs.getId();
    }

    public ApiResponse editData(Integer id, BlogDto blogDto){
        Blogs blogs = blogsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found blog or news"));
        blogs.setName(blogDto.uzName().length() != 0 ?  blogDto.uzName() : blogs.getName());
        blogs.setRuName(blogDto.ruName().length() != 0 ? blogDto.ruName() : blogs.getRuName());
        blogs.setEngName(blogDto.engName().length() != 0 ? blogDto.engName() : blogs.getEngName());
        blogs.setUzAbout(blogDto.uzAbout().length() != 0 ? blogDto.uzAbout() : blogs.getUzAbout());
        blogs.setRuAbout(blogDto.ruAbout().length() != 0 ? blogDto.ruAbout() : blogs.getRuAbout());
        blogs.setEngAbout(blogDto.engAbout().length() != 0 ? blogDto.engAbout() : blogs.getEngAbout());
        blogsRepository.save(blogs);
        return new ApiResponse("Oтредактировано", true, 200);
    }
}
