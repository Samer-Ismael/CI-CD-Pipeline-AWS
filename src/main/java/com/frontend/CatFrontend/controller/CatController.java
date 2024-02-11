package com.frontend.CatFrontend.controller;

import com.frontend.CatFrontend.entitiy.Cat;
import com.frontend.CatFrontend.repo.CatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatRepo catRepo;

    public CatController(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @PostMapping("/")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat){
        return ResponseEntity.ok(catRepo.save(cat));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cat>> getAllCats(){
        return ResponseEntity.ok(catRepo.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Cat> getCatByName(@PathVariable String name){

        if (catRepo.findByName(name).isPresent()){
            return ResponseEntity.ok(catRepo.findByName(name).get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cat> updateCat(@PathVariable int id, @RequestBody Cat cat){
        Cat cat1 = catRepo.findById(id).get();
        cat1.setName(cat.getName());
        cat1.setColor(cat.getColor());
        cat1.setAge(cat.getAge());
        return ResponseEntity.ok(catRepo.save(cat1));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCat(@PathVariable int id){
        catRepo.deleteById(id);
        return ResponseEntity.ok("Cat Deleted");
    }
}
