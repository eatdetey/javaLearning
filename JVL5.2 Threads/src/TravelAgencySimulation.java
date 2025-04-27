import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class TravelAgencySimulation {

    static final int SERVICE_TIME_SELLER = 20;
    static final int SERVICE_TIME_MANAGER = 30;
    static final int CLIENT_PROFIT = 20000;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Semaphore sellerSemaphore = new Semaphore(1);
        Semaphore managerSemaphore = new Semaphore(1);

        int clientsServed = 0;
        int clientsLost = 0;
        int totalProfit = 0;

        double lambda = 2.0 / 60.0;
        double currentTime = 0.0;
        double simulationEnd = 60.0;

        double sellerFreeAt = 0.0;
        double managerFreeAt = 0.0;

        System.out.println("=== СИМУЛЯЦИЯ РАБОТЫ ТУРАГЕНТСТВА ===");
        System.out.printf("Начало работы. Продавец и менеджер свободны.%n%n");

        while (currentTime < simulationEnd) {
            double interArrivalTime = getExponential(random, lambda);
            currentTime += interArrivalTime;

            if (currentTime >= simulationEnd) {
                break;
            }

            if (currentTime >= sellerFreeAt && sellerSemaphore.availablePermits() == 0) {
                sellerSemaphore.release();
            }
            if (currentTime >= managerFreeAt && managerSemaphore.availablePermits() == 0) {
                managerSemaphore.release();
            }

            System.out.printf("[%.2f мин] Поступил новый клиент%n", currentTime);

            if (sellerSemaphore.tryAcquire()) {
                double serviceEnd = currentTime + SERVICE_TIME_SELLER;
                sellerFreeAt = serviceEnd;
                clientsServed++;
                totalProfit += CLIENT_PROFIT;

                System.out.printf("[%.2f мин] Клиент направлен к ПРОДАВЦУ (обслуживание до %.2f мин)%n",
                        currentTime, serviceEnd);

                executor.submit(() -> {
                    try {
                        Thread.sleep((long)(SERVICE_TIME_SELLER * 1000 / 60));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            else if (managerSemaphore.tryAcquire()) {
                double serviceEnd = currentTime + SERVICE_TIME_MANAGER;
                managerFreeAt = serviceEnd;
                clientsServed++;
                totalProfit += CLIENT_PROFIT;

                System.out.printf("[%.2f мин] Клиент направлен к МЕНЕДЖЕРУ (обслуживание до %.2f мин)%n",
                        currentTime, serviceEnd);

                executor.submit(() -> {
                    try {
                        Thread.sleep((long)(SERVICE_TIME_MANAGER * 1000 / 60));
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            else {
                clientsLost++;
                System.out.printf("[%.2f мин] Клиент УШЁЛ БЕЗ ОБСЛУЖИВАНИЯ (все сотрудники заняты)%n", currentTime);
            }

            System.out.printf("Текущая статистика: Обслужено: %d, Упущено: %d%n%n", clientsServed, clientsLost);
        }

        executor.shutdownNow();

        System.out.println("\n=== ИТОГИ РАБОТЫ ЗА 1 ЧАС ===");
        System.out.println("Обслужено клиентов: " + clientsServed);
        System.out.println("Упущено клиентов: " + clientsLost);
        System.out.println("Прибыль: " + totalProfit + " руб.");
    }

    private static double getExponential(Random random, double lambda) {
        return -Math.log(1 - random.nextDouble()) / lambda;
    }
}