import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private String prize;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle, String prize) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
        this.prize = prize;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println();
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada: " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor.");
        System.out.println("S - Savaş");
        System.out.println("K - Kaç");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println();
            System.out.println(this.getName() + " tüm düşmanları yendiniz.");
            return true;
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        int random;
        for (int i = 0; i < obsNumber; i++) {
            random = (int) (Math.random() * 2 + 1);

            if (random == 1) {
                this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
                playerStats();
                obstacleStats(i + 1);

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println();
                    System.out.println("Vur - V");
                    System.out.println("Kaç - K");
                    String selectCombat = input.nextLine().toUpperCase();

                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz.");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else {
                        System.out.println("Savaştan kaçtınız.");
                        return false;
                    }
                }

                if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println();
                    System.out.println((i + 1) + ". Düşmanı yendiniz.");
                    System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                } else {
                    return false;
                }
            } else {
                this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
                playerStats();
                obstacleStats(i + 1);

                while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                    System.out.println();
                    System.out.println("Vur - V");
                    System.out.println("Kaç - K");
                    String selectCombat = input.nextLine().toUpperCase();

                    if (selectCombat.equals("V")) {
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("Canavar size vurdu");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                        System.out.println();
                        System.out.println("Siz vurdunuz.");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    } else {
                        System.out.println("Savaştan kaçtınız.");
                        return false;
                    }
                }

                if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println();
                    System.out.println((i + 1) + ". Düşmanı yendiniz.");
                    System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                } else {
                    return false;
                }
            }
        }

        if (this.getObstacle().getName().equals("Zombi")) {
            this.getPlayer().getInventory().setPrizeCave(this.getPrize());
            System.out.println("Ödülünüz: " + this.getPrize());
        } else if (this.getObstacle().getName().equals("Vampir")) {
            this.getPlayer().getInventory().setPrizeForest(this.getPrize());
            System.out.println("Ödülünüz: " + this.getPrize());
        } else if (this.getObstacle().getName().equals("Ayı")) {
            this.getPlayer().getInventory().setPrizeRiver(this.getPrize());
            System.out.println("Ödülünüz: " + this.getPrize());
        }

        return true;
    }

    public void afterHit() {
        System.out.println();
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı: " + this.getObstacle().getHealth());
    }

    public void playerStats() {
        System.out.println();
        System.out.println("----------------------- Oyuncu Değerleri -----------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getWeapon().getName());
        System.out.println("Hasar: " + (this.getPlayer().getDamage() + this.getPlayer().getInventory().getWeapon().getDamage()));
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int obsNumber) {
        System.out.println();
        System.out.println("----------------------- " + obsNumber + ". " + this.getObstacle().getName() + " Değerleri -----------------------");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
}
