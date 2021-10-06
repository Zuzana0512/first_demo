package nl.novi.first_demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

public class NameController {


    private static List<String> names = new ArrayList<>();

    public NameController () {
        names.add("Peter");
        names.add("Zuzana");
    }

    @GetMapping(value = "/names")
    public List<String> getNames() {
        return names;
    }


    @GetMapping(value = "/names/{id}")
    public String getNames(@PathVariable int id) {
        return names.get(id);
    }

    @PostMapping(value = "/names")
    public String addNames(@RequestBody String name) {
        names.add(name);
        return "Added name " + name;
    }

    @DeleteMapping(value = "/names/{id}")
    public String removeNames (@PathVariable int id) {
        String name = names.get(id);
        names.remove(id);
        return "Removed name " + name;
    }

    @PutMapping(value = "/names/{id}")
    public String removeNames (@PathVariable int id, @RequestBody String name) {
        names.set(id, name);
        return "Updated name " + name;
    }
}
