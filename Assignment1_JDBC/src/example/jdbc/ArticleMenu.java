package example.jdbc;

import java.time.LocalDate;
import java.util.Scanner;
import example.jdbc.bean.Article;
import example.jdbc.dao.ArticleDao;

public class ArticleMenu {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Article Table");

        while (true) {
            System.out.println("\n1. Add Article");
            System.out.println("2. View All Articles");
            System.out.println("3. View Article by ID");
            System.out.println("4. Update Article");
            System.out.println("5. Delete Article");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Category (PAINTING/SCULPTURE/ARTIFACT): ");
                        String category = scanner.nextLine();
                        System.out.print("Date Created (YYYY-MM-DD): ");
                        LocalDate dateCreated = LocalDate.parse(scanner.nextLine());
                        System.out.print("Creator Name: ");
                        String creatorName = scanner.nextLine();
                        articleDao.create(new Article(1, name, category, dateCreated, creatorName));
                        break;

                    case 2:
                        articleDao.retrieveAll().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        System.out.println(articleDao.retrieveOne(id));
                        break;

                    case 4:
                        System.out.print("ID to Update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Name: ");
                        name = scanner.nextLine();
                        System.out.print("Category (PAINTING/SCULPTURE/ARTIFACT): ");
                        category = scanner.nextLine();
                        System.out.print("Date Created (YYYY-MM-DD): ");
                        dateCreated = LocalDate.parse(scanner.nextLine());
                        System.out.print("Creator Name: ");
                        creatorName = scanner.nextLine();
                        articleDao.update(new Article(updateId, name, category, dateCreated, creatorName));
                        break;

                    case 5:
                        System.out.print("ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        scanner.nextLine(); 
                        Article article = articleDao.retrieveOne(deleteId);
                        if (article == null) {
                            System.out.println("Article with ID " + deleteId + " not found.");
                        } else {
                            articleDao.delete(deleteId);
                            System.out.println("Article with ID " + deleteId + " has been deleted.");
                        }
                        break;


                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}