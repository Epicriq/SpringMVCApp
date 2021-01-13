package ru.asuslov.springcourse.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.asuslov.springcourse.dao.PersonDAO;
import ru.asuslov.springcourse.models.Person;

@Component
public class PersonValidator implements Validator {

    @Autowired
    private PersonDAO personDAO;

    @Override
    public boolean supports(Class<?> validatedClass){
        return Person.class.equals(validatedClass);
    }

    @Override
    public void validate(Object target, Errors errors){
        Person person = (Person) target;
        if (personDAO.isExists(person.getEmail()).booleanValue())
            errors.rejectValue("email", "", "Данное значение уже используется, укажите другое.");
    }
}
