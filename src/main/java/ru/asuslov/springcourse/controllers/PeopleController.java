package ru.asuslov.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.asuslov.springcourse.dao.PersonDAO;
import ru.asuslov.springcourse.models.Person;
import ru.asuslov.springcourse.utils.PersonValidator;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/people", produces="text/html;charset=UTF-8")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    private PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showPeople(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        else {
            personDAO.save(person);
            return "redirect:/people";
        }
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") Integer id){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") Integer id){
        if (bindingResult.hasErrors())
            return "people/edit";
        else {
            personDAO.update(id, person);
            return "redirect:/people";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@ModelAttribute("person") Person person) {
        personDAO.delete(person);
        return "redirect:/people";
    }
}
