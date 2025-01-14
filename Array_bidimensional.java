public class Array_bidimensional {
    public static void main(String[] args) throws Exception {

        int [][] matrix=new int[4][5];

        matrix[0][0]=15;
        matrix[0][1]=18;
        matrix[0][2]=21;
        matrix[0][3]=9;
        matrix[0][4]=5;

        matrix[1][0]=1;
        matrix[1][1]=20;
        matrix[1][2]=35;
        matrix[1][3]=52;
        matrix[1][4]=48;

        matrix[2][0]=56;
        matrix[2][1]=6;
        matrix[2][2]=18;
        matrix[2][3]=26;
        matrix[2][4]=14;

        matrix[3][0]=15;
        matrix[3][1]=3;
        matrix[3][2]=65;
        matrix[3][3]=49;
        matrix[3][4]=32;

        /*los valores se pueden poner en cualquier oden siempre que 
        esten todos*/

        //System.out.println(matrix[2][3]);

        for (int i=0; i<4;i++ ){ //va a recorrer la 1º dimension,que tiene 4 posiciones x eso j<4
            System.out.println();

            for(int j=0;j<5;j++) { //va a recorrer la 2º dimension que tiene 5 posiciones x eso j<5

                System.out.print(matrix[i][j]+ " ");
            }
        }

        
    }
}
