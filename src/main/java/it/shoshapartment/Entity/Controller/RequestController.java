package it.shoshapartment.Entity.Controller;

import it.shoshapartment.Entity.Entity.Request;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.RequestDto;
import it.shoshapartment.Entity.Repository.RequestRepository;
import it.shoshapartment.Entity.Service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/request")
@RequiredArgsConstructor
@CrossOrigin
public class RequestController {

    private final RequestRepository requestRepository;

    private final TelegramService telegramService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Request> all = requestRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public HttpEntity<?> requesting(@RequestBody RequestDto requestDto){
        Request build = Request.builder().phoneNumber(requestDto.phoneNumber()).build();
        build.setName(requestDto.name());
        requestRepository.save(build);
        telegramService.sendMessage("Sizda yangi so'rovnoma mavjud\n" + "Bog'lanish uchun: " + requestDto.phoneNumber() + "\nIsmi: " + requestDto.name());
        return ResponseEntity.ok(new ApiResponse("saqlandi", true, 200));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRequest(@PathVariable Integer id){
        Request notFoundRequest = requestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found request"));
        requestRepository.delete(notFoundRequest);
        return ResponseEntity.ok(new ApiResponse("olib tashlandi", true, 200));
    }
}
