package controllers;

import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    public static final String title = "Slot Machine";
    public static final String description = "This is a Play Framework (JAVA) based Web Application for Object Oriented Programming";
    public static final String keywords = "OOP, Java, Play, Framework, NoSQL, Typescript, Cloud, Azure, Object, Oritented";
    public static final String author = "Minoj Selvarasa";
    public static final String name = "minojselvarasa";
    public static final String password = "123456789";

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
}
