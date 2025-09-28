public class lab1 {
    public static void main(String[] args) {
        try {
            int num = 23;
            int c5 = num % 5,
                    c7 = num % 7,
                    c11 = num % 11;
            System.out.println("c5 = " + c5);
            System.out.println("c7 = " + c7);
            System.out.println("c11 = " + c11);

            short[][] A = {
                    { 3, 2, 0 },
                    { 4, 6, 1 },
                    { 2, 8, 9 }
            };

            short[][] B = {
                    { 0, 0, 5 },
                    { 8, 0, 3 },
                    { 0, 2, 9 }
            };

            short[][] C = new short[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    C[i][j] = (short) (A[i][j] ^ B[i][j]);
                }
            }

            System.out.println("C = ");

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(C[i][j] + "  ");
                }
                System.out.println();
            }
            short sum = 0;
            for (int i = 0; i < 3; i++) {
                short min = C[i][0];
                for (int j = 0; j < 3; j++) {
                    if (C[i][j] < min) {
                        min = C[i][j];
                    }

                }
                sum = (short) (sum + min);
            }
            System.out.println(sum);
        } catch (Exception e) {
            System.out.println("Виникла помилка під час виконання програми: " + e.getMessage());
        }

    }
}
