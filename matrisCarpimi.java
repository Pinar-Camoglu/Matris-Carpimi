import java.util.Random;

public class matrisCarpimi {

    private static final int SIZE = 5;

    public static void main(String[] args) {
        int[][] A = new int[SIZE][SIZE];
        int[][] B = new int[SIZE][SIZE];
        int[][] C = new int[SIZE][SIZE];

        // Matrisleri rastgele değerlerle doldur
        fillMatrixWithRandomValues(A);
        fillMatrixWithRandomValues(B);

        // Rastgele değerlerle doldurulmuş matrisleri yazdır
        System.out.println("A Matrisi:");
        printMatrix(A);
        System.out.println("\n");
        System.out.println("B Matrisi:");
        printMatrix(B);
        System.out.println("\n");

        // Matris boyutlarını kontrol et
        if (A[0].length != B.length) {
            System.out.println("Matrisler çarpımı için uygun boyutta değiller.");
            return;
        }

        Thread[] threads = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < SIZE; j++) {
                    C[row][j] = 0;
                    for (int k = 0; k < SIZE; k++) {
                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
            threads[i].start();
        }

        // Ana thread, tüm thread'lerin bitmesini bekler
        for (int i = 0; i < SIZE; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Sonucu yazdırma
        System.out.println("C Matrisi:");
        printMatrix(C);
    }

    // Rastgele değerlerle matrisi doldurur
    private static void fillMatrixWithRandomValues(int[][] matrix) {
        Random rand = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = rand.nextInt(10); // 0 ile 9 arasında rastgele sayı
            }
        }
    }

    // Matrisi yazdırır
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
