/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fifteensolver;

/**
 *
 * @author Kuszc
 */
public class Greedy {

}
// 0=UP
// ma zwracac liste ruchow gdzie L-Left; R-right; U; D ;+ ilość ruchów długość znalezionego rozwiązania;
//liczbę stanów odwiedzonych;
//liczbę stanów przetworzonych;
//maksymalną osiągniętą głębokość rekursji;
//czas trwania procesu obliczeniowego.
public OUT_Info Greedy( Tiles_array)
{
private String moveList; 
    private int Min = 100;
    Calculate_Avg_dist;
    private int Prev_move = 4;
    private int Best_move = 4;
    private int Avg = 0;
    private int totalMoves = 0;
private int checkedMoves = 0;
    float T = System.currentTimeMillis();
    do
    {
        for (int i =0; i<4;i++)
        {
            if(Is_move_legal)
            {
                if(i!=Prev_move)
                {
checkedMoves++;
                Avg = Calculate_Avg_dist;
                    if(Avg<Min)
                    {
                    Best_Move = i;
                    Min = Avg;
                    }           
                }
            }

        }
totalMoves++;
switch (Best_move) {
            case 0:  moveList = moveList +  "U ";
                     break;
            case 1:  moveList = moveList +  "R ";
                     break;
            case 2:  moveList = moveList + "D ";
                     break;
            case 3:  moveList = moveList +  "L ";
                     break;
           

    Prev_move =Best_move;
    Min = 100;
    

    }
    while( Not Solved)

    T= System.currentTimeMillis() - T;

    return OUT_Info(checkedMoves, T, totalMoves)
}
