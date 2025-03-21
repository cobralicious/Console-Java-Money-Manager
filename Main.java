import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Создаем список для хранения расходов
    private static ArrayList<String> expenses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("_____________________________________");
            System.out.println("|1. Добавлять новую трату           |");
            System.out.println("|2. Просматривать все траты         |");
            System.out.println("|3. Вычислять общую сумму расходов  |");
            System.out.println("|4. Фильтровать траты по категории. |");
            System.out.println("|5. Выходить из программы.          |");
            System.out.println("|-----------------------------------|");
            System.out.print("Выбери цифру: ");

            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Expense expense = new Expense();
                    expense.expense(expenses); // Передаем список расходов
                    break;
                case "2":
                    ExpenseManager.showExpenses(expenses); // Выводим список расходов
                    break;
                case "3":
                    TotalAmount.total(expenses); // Выводим сумму
                    break;
                case "4":
                    ExpenseFilter.filterByCategory(expenses);
                    break;
                case "5":
                    System.out.println("Выход из программы.");
                    running = false;
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
            }
        }
    }
}

class Expense { // КНОПКА 1 для добавления
    // Метод для добавления расхода
    public void expense(ArrayList<String> expenses) {
        Scanner sc = new Scanner(System.in);
        System.out.println("_____________________________________");
        System.out.print("Введите категорию: ");
        String category = sc.nextLine();
        System.out.print("Введите сумму: ");
        String sum = sc.nextLine();
        System.out.print("Введите дату (дд.мм.гггг): ");
        String data = sc.nextLine();
        String newExpense = data + " | " + category + " | " + sum + "грн.\n";
        expenses.add(newExpense); // Добавляем новый расход
        System.out.println("Трата добавлена!\n");
    }
}

class ExpenseManager { // КНОПКА 2 для просмотра
    // Метод для отображения списка расходов
    public static void showExpenses(ArrayList<String> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("_____________________________________");
            System.out.println("Список расходов пуст.\n");
        } else {
            System.out.println("_____________________________________");
            System.out.println("Ваши расходы:");
            for (String expense : expenses) {
                System.out.println(expense);
            }
        }
    }
}

class TotalAmount { // КНОПКА 3 для общей суммы
    // Метод для отображения общей суммы расходов
    public static void total(ArrayList<String> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("_____________________________________");
            System.out.println("У вас еще нет расходов.\n\n");
        } else {
            int totalSum = calculateTotal(expenses); // Рассчитываем общую сумму
            System.out.println("_____________________________________");
            System.out.println("Общий расход: " + totalSum + " грн.\n");
        }
    }

    // Метод для вычисления общей суммы расходов
    private static int calculateTotal(ArrayList<String> expenses) {
        int total = 0;
        for (String expense : expenses) {
            // Распознаем сумму из строки "дата | категория | сумма грн."
            try {
                String[] parts = expense.split("\\|"); // Разделяем по "|"
                String sumPart = parts[2].trim(); // Берем часть с суммой
                sumPart = sumPart.replace("грн.", "").trim(); // Убираем "грн."
                int amount = Integer.parseInt(sumPart); // Преобразуем в число
                total += amount;
            } catch (Exception e) {
                System.out.println("_____________________________________");
                System.out.println("Ошибка при обработке строки: " + expense);
            }
        }
        return total;
    }
}

class ExpenseFilter { // КНОПКА 4 для фильтрации по категории
    // Метод для фильтрации расходов по категории
    public static void filterByCategory(ArrayList<String> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("_____________________________________");
            System.out.println("Список расходов пуст.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите категорию для фильтрации: ");
        String categoryFilter = sc.nextLine().trim();

        boolean found = false;
        System.out.println("_____________________________________");
        System.out.println("Траты в категории \"" + categoryFilter + "\":");

        for (String expense : expenses) {
            String[] parts = expense.split("\\|"); // Разделяем строку
            String category = parts[1].trim(); // Получаем категорию

            if (category.equalsIgnoreCase(categoryFilter)) {
                System.out.println(expense);
                found = true;
            }
        }

        if (!found) {
            System.out.println("В категории \"" + categoryFilter + "\" трат не найдено.");
        }
    }
}
