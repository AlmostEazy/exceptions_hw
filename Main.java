import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String mydata = "";
        try (Scanner scanner = new Scanner(System.in, "cp866")) {
            System.out.println("Enter the data"
            + System.lineSeparator() + "(surname, name, patronymic, date of birth, telephone, gender(f/m).): ");
            mydata = scanner.nextLine();
        }
        checkData check = new checkData(mydata);
        if (check.getCorrect()) {
            File file = new File(check.getSurname());
            try (FileWriter writer = new FileWriter(file, true)) {
                String data = check.getSurname() + " " + check.getName() + " " + check.getPatronymic() + " " + check.getDateBirth() + " "
                        + check.getTelephone() + " " + check.getGender() + " \n";
                writer.write(data);
                System.out.println("Successfully written");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}