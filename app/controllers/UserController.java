package controllers;

import models.Login;
import models.SignUp;

import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import play.Logger;

import views.html.Slot.*;

import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
public class UserController extends Controller {

    @Inject
    FormFactory formFactory;

    public static int credits = 0;
    public static int wins = 0;
    public static int loss = 0;
    public static int betAmt = 0;
    private String user = null;

    /**
     * Render the Index Page if User is Logged In.
     * If the User has not logged in yet, render the Login Page
     * @return
     */
    public Result index() {
        if(!session().isEmpty()) {
            user = session("connected");
        }
        if(user == null) {
            return create();
        }

        return ok(index.render(user,HomeController.title,HomeController.description,HomeController.keywords,HomeController.author,String.valueOf(betAmt)));
    }

    public Result statistics() {

        if(!session().isEmpty()) {
            user = session("connected");
        }
        if(user == null) {
            return create();
        }

        return ok(statistics.render(user,HomeController.title,HomeController.description,HomeController.keywords,HomeController.author,String.valueOf(betAmt)));
    }

    /**
     * Create the Form and Pass the Form to Login Page to display the Form
     * Return the result after rendering the Login Page
     * @return
     */
    public Result create() {

        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(login.render(loginForm, HomeController.title,HomeController.description,HomeController.keywords,HomeController.author));
    }

    /**
     * If the User has input a valid Username and a valid Password, set loggedIn to 1 and userName to the user's username
     * and redirect to the user to the respective page.
     * If the User has input an invalid username or password, redirect the user again to the login page
     * @return
     */
    public Result login() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();

        // Validate Form Inputs
        if(loginForm.hasErrors()) {
            Logger.error("error");
        } else {
            Login userLogin = loginForm.get();

            try {
                session("connected", userLogin.getEmail());
                session("key", userLogin.getPassword());
                user = session("connected");
                Logger.info("Came here 76");
            } catch(NullPointerException e) {
                Logger.error("Form Content Null",e);
                return internalServerError(login.render(loginForm, HomeController.title, HomeController.description, HomeController.keywords, HomeController.author));
            }
        }

        return redirect(routes.UserController.index());
    }

    public Result logout() {
        if(user != null) {
            session().clear();
            user = null;
            session().clear();
        }

        return redirect(routes.UserController.index());
    }

    /**
     * When User wants to create am account, render the Sign Up page
     */
    public Result signup() {
        Form<SignUp> signupForm = formFactory.form(SignUp.class);

        if(user == null) {
            return ok(signup.render(signupForm, HomeController.title,HomeController.description,HomeController.keywords,HomeController.author));
        } else {
            return redirect(routes.UserController.index());
        }
    }

    public Result signupForm() {
        Form<SignUp> signUpForm = formFactory.form(SignUp.class).bindFromRequest();

        System.out.println(signUpForm.get().getEmail());
        System.out.println(signUpForm.get().getFullName());
        System.out.println(signUpForm.get().getPassword());

        SignUp userLogin = signUpForm.get();

        // Validate Form Inputs

        try {
            if (!signUpForm.hasErrors()) {
                session("connected", userLogin.getEmail());
            } else {
                return badRequest(signup.render(signUpForm, HomeController.title,HomeController.description,HomeController.keywords,HomeController.author));
            }
        } catch(Exception e) {
            Logger.error("Unexpected Error : "+e);
        }

        return redirect(routes.UserController.index());
    }


    public boolean dbCompare(String username, String password) {
        return true;
    }

    public int getCredits() {
        credits = 3;
        return credits;
    }

    public void setCredits(int credit) {
        credits = credit;
    }

    public void setValues(boolean win) {
        if(win) {
            wins += 1;
        } else {
            loss += 1;
        }
    }

    public void setBetAmt(int betAmnt) {
        betAmt = betAmnt;
    }
}
