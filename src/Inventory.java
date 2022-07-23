public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private String prizeCave;
    private String prizeForest;
    private String prizeRiver;

    public Inventory() {
        this.weapon = new Weapon("Yumruk",-1,0,0);
        this.setArmor(new Armor(-1,"Zırhsız",0,0));
        this.prizeCave = "";
        this.prizeForest = "";
        this.prizeRiver = "";
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getPrizeCave() {
        return prizeCave;
    }

    public void setPrizeCave(String prizeCave) {
        this.prizeCave = prizeCave;
    }

    public String getPrizeForest() {
        return prizeForest;
    }

    public void setPrizeForest(String prizeForest) {
        this.prizeForest = prizeForest;
    }

    public String getPrizeRiver() {
        return prizeRiver;
    }

    public void setPrizeRiver(String prizeRiver) {
        this.prizeRiver = prizeRiver;
    }
}
