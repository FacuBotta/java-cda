import com.controllers.FilmController;
import com.controllers.UserController;
import com.views.FilmView;
import com.views.UserView;

public class Main {
    public static void main(String[] args) {

        /*System.out.println("Veuillez choisir une option");
        System.out.println("-------------------------\n");
        System.out.println("1 - add -> pour ajouter un compte");
        System.out.println("2 - update -> pour modifier un compte");
        System.out.println("3 - show -> pour afficher un compte");
        System.out.println("4 - delete -> pour supprimer un compte");
        System.out.println("5 - showAll -> pour afficher la liste des comptes");
        System.out.println("6 - stop -> pour arrêter");
        String route = UserView.getScanner().nextLine();

        switch (route){
            case "1":
                UserController.addUser();
                break;
            case "2":
                UserController.updateUser();
                break;
            case "3":
                UserController.showUser();
                break;
            case "4" :
                UserController.deleteUser();
                break;
            case "5":
                UserController.showAllUsers();
            case "6" :
                System.out.println("Arret du programme");
                break;
            default: break;
        }*/
        System.out.println("Veuillez choisir une option");
        System.out.println("-------------------------\n");
        System.out.println("1 - add -> pour ajouter un film");
        System.out.println("2 - update -> pour modifier un film");
        System.out.println("3 - show -> pour afficher un film");
        System.out.println("4 - delete -> pour supprimer un film");
        System.out.println("5 - showAll -> pour afficher la liste des film");
        System.out.println("6 - stop -> pour arrêter");
        String route = FilmView.getScanner().nextLine();

        switch (route){
            case "1":
                FilmController.addFilm();
                break;
            case "2":
                FilmController.updateFilm();
                break;
            case "3":
                FilmController.showFilm();
                break;
            case "4" :
                FilmController.deleteFilm();
                break;
            case "5":
                FilmController.showAllFilms();
            case "6" :
                System.out.println("Arret du programme");
                break;
            default: break;
        }
    }
}
