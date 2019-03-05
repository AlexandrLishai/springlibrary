package ru.javabegin.training.library.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.training.library.dao.impl.AuthorService;
import ru.javabegin.training.library.domain.Author;

import java.util.List;

// автоматически все методы класса будут возвращать JSON
@RestController
@RequestMapping(value = "/rest/author") //  корневой путь
public class RestAuthor {

    @Autowired
    private AuthorService authorService;

    // получить все записи без сортировки (сортировку уже могут сами выбирать на стороне клиента)
    @RequestMapping("/all")
    public List<Author> getAuthors(){
        return authorService.getAll();
    }

    // поиск записей по фио
    @RequestMapping("/search")
    public List<Author> search(@RequestParam("fio") String fio){
        return authorService.search(fio);
    }

    // поиска записей с постраничностью
    @RequestMapping("/searchPage")
    public List<Author> searchPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, @RequestParam("fio") String fio){
        return authorService.search(pageNumber, pageSize, "fio", Sort.Direction.ASC, fio).getContent();
    }
}
