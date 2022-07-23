import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera oyununa hoşgeldiniz.");
        System.out.print("Lütfen bir isim giriniz: ");
        //String playerName = input.nextLine();
        System.out.println();
        Player player = new Player("Gazi");
        System.out.println(player.getName() + " Macera Oyununa Hoşgeldin.");
        System.out.println("Lütfen bir karakter seçiniz: ");
        System.out.println();
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("--------------------- Bölgeler ---------------------");
            System.out.println("1 - Güvenli Ev");
            System.out.println("2 - Eşya Dükkanı");
            System.out.println("3 - Mağara --> Ödül: Yemek");
            System.out.println("4 - Orman --> Ödül: Odun");
            System.out.println("5 - Nehir --> Ödül: Su");
            System.out.println("6 - Maden --> Ödül: Para");
            System.out.println("0 - Oyunu Sonlandır");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            int selectLoc = input.nextInt();

            while (selectLoc < 0 || selectLoc > 6){
                System.out.println("Geçersiz Değer Tekrar Giriniz!");
                selectLoc = input.nextInt();
            }


            switch (selectLoc){
                case 0:
                    System.out.println("Oyunu Sonlandırdınız.");
                    System.exit(0);
                    break;
                case 1:
                    if (player.getInventory().getPrizeCave().equals("Yemek") &&
                            player.getInventory().getPrizeForest().equals("Odun") &&
                            player.getInventory().getPrizeRiver().equals("Su")) {

                        System.out.println("Tüm ödülleri topladınız.");
                        System.out.println("Yemek, Odun, Su");
                        System.out.println("Oyunu Kazandınız!");
                        System.exit(0);
                    }
                    else {
                        location = new SafeHouse(player);
                        break;
                    }
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!(player.getInventory().getPrizeCave().equals("Yemek"))){
                        location = new Cave(player);
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Bu bölgenin ödülüne sahipsiniz.");
                        System.out.println("Ödül: " + player.getInventory().getPrizeCave());
                        continue;
                    }
                case 4:
                    if (!(player.getInventory().getPrizeForest().equals("Odun"))){
                        location = new Forest(player);
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Bu bölgenin ödülünü aldınız.");
                        System.out.println("Ödül: " + player.getInventory().getPrizeForest());
                        continue;
                    }
                case 5:
                    if (!(player.getInventory().getPrizeRiver().equals("Su"))){
                        location = new River(player);
                        break;
                    } else {
                        System.out.println();
                        System.out.println("Bu bölgenin ödülünü aldınız.");
                        System.out.println("Ödül: " + player.getInventory().getPrizeRiver());
                        continue;
                    }
                case 6:
                    location = new Mine(player,randomDamage());
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz.");
            }

            if (!location.onLocation()){
                System.out.println("GAME OVER!");
                break;
            }
        }
    }

    public int randomDamage(){
        int damage = (int) (Math.random() * 4 + 3);
        return damage;
    }
}
