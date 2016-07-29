/**
 * Created by pandeyk on 7/29/2016.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DiamondGame {
    final int cardsInOneSuit = 13;
    Card[] computerCards;
    Card[] playerCards;
    Card[] diamonds;
    float computerMoney = 0;
    float userMoney = 0;

    public DiamondGame() {
        Deck myDeck = new Deck(1, 0);
        computerCards = new Card[cardsInOneSuit];
        playerCards = new Card[cardsInOneSuit];
        diamonds = new Card[cardsInOneSuit];
        for (int i = 0; i < cardsInOneSuit; i++) {
            computerCards[i] = myDeck.cards[i];
            playerCards[i] = myDeck.cards[i + cardsInOneSuit * 2];
        }
        myDeck.shuffle(1);
        for (int i = 0; i < cardsInOneSuit; i++) {
            diamonds[i] = myDeck.cards[i];
            if (diamonds[i].pip == 1) diamonds[i].pip = 14;
        }
    }

    public static void main(String[] args) {
        DiamondGame myDiamondGame = new DiamondGame();
        //System.out.println(myDiamondGame);
        myDiamondGame.play();
    }

    public void play() {
        for(int i = 0; i < diamonds.length; i++){
            System.out.println("Current diamond is:\n" + diamonds[i]);
            System.out.println("What's your bid?:\n");
            Scanner sc = new Scanner(System.in);
            int userBid = sc.nextInt();
            while(isInvalid(userBid)){
                userBid = sc.nextInt();
            }

            int computerBid = getComputerBid(diamonds[i].pip);

            if(userBid < computerBid){
                System.out.println("Sorry, Computer won this bid");
                computerMoney += (float)diamonds[i].pip;
            }
            else if(userBid == computerBid){
                System.out.println("It's a tie; You get half the money");
                computerMoney += (float)diamonds[i].pip / 2;
                userMoney += (float)diamonds[i].pip / 2;
            }
            else{
                System.out.println("Congrats you won this bid");
                userMoney += (float)diamonds[i].pip;
            }
        }
        System.out.println(computerMoney > userMoney ? "You lost" : "Congratulations you won the game");
        System.out.println("Computer has " + computerMoney + " money, and you have " + userMoney);
    }

    public boolean isInvalid(int userBid){
        if(userBid == 1){
            System.out.println("ERROR: Use 14 for A\nWhat's your bid");
            return true;
        }
        if(userBid == 14){
            if(playerCards[0].pip != 0){
                playerCards[0].pip = 0;
                return false;
            }
            System.out.println("ERROR: You don't have this card available\nWhat's your bid");
            return true;
        }
        else if(playerCards[userBid-1].pip != 0){
            playerCards[userBid-1].pip = 0;
            return  false;
        }
        System.out.println("ERROR: You don't have this card available\nWhat's your bid");
        return true;
    }
    public int getComputerBid(int i){
        return i;
    }

    public String toString() {
        String myCards = "Diamonds are: ";
        for (int i = 0; i < cardsInOneSuit; i++) {
            myCards += diamonds[i] + "\n";
        }
        return myCards;
    }
}