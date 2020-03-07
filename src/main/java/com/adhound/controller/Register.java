package com.adhound.controller;

import com.adhound.entity.State;
import com.adhound.entity.User;
import com.adhound.entity.UserRole;
import com.adhound.persistence.UserData;
import com.adhound.service.CrudService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@WebServlet(
        urlPatterns = {"/register"}
)

public class Register extends HttpServlet {

    HttpSession session;

    public CrudService crud = new CrudService(State.class);
    //State state = (State)

    Map<String, String> errors = new HashMap<>();

    List <State> states = this.crud.getAll();

    private static Validator validator;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();



        request.setAttribute("message", "Please enter a valid username and password.");

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession();

        UserData userData = new UserData();

        User newUser = new User();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        newUser.setUsername(request.getParameter("emailTextbox").trim());
        newUser.setPassword(request.getParameter("passwordTextbox").trim());
        newUser.setFirstName(request.getParameter("firstNameTextbox").trim());
        newUser.setLastName(request.getParameter("lastNameTextbox").trim());
        newUser.setPhone(request.getParameter("phoneTextbox").trim());
        newUser.setFax(request.getParameter("faxTextbox").trim());
        newUser.setEmail(request.getParameter("emailTextbox").trim());
        newUser.setAddress(request.getParameter("addresssTextbox").trim());
        newUser.setCity(request.getParameter("cityTextbox").trim());
        newUser.setStateId(Integer.parseInt(request.getParameter("stateIdDropdown").trim()));
        newUser.setZipcode(request.getParameter("zipcodeTextbox").trim());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);
//constraintViolations.isEmpty()
// constraintViolations.iterator().next()
        //constraintViolations.iterator().next().getPropertyPath().toString()
        //constraintViolations.iterator().next().getInvalidValue()
        //constraintViolations.iterator().next().getMessage()



        if (constraintViolations.isEmpty()) {
            int newId = (int) userData.crud.insertRecord(newUser);

            newUser = (User) userData.crud.getById(newId);

            UserRole userRole = new UserRole(newUser, newUser.getUsername());
            Serializable userRoleId = userData.crud.insertRecord(userRole);

            User insertedUser = (User) userData.crud.getById(newId);

            if (insertedUser != null) {
                /**
                 * Instantiate a new array list of user data.
                 */
                List <User> user = new ArrayList<>();
                /**
                 * Add the user data to the array list.
                 */
                user.add(insertedUser);
                /**
                 * Pass the array list to the view.
                 */
                request.setAttribute("results", user);

            }
            else {

                request.setAttribute("results", "");
            }
        }
        else {

            Iterator<ConstraintViolation<User>> errorMessages = constraintViolations.iterator();



            while (errorMessages.hasNext()) {

                ConstraintViolation<User> next = errorMessages.next();

                String property = next.getPropertyPath().toString();
                String message = next.getMessage();

                errors.put(property, message);

            }


        }

        request.setAttribute("errormessages", errors);

        request.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);


    }

}
