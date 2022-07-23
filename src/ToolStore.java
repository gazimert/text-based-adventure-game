public class ToolStore extends NormalLoc{

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("------------ Mağazaya Hoşgeldiniz. ------------");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3){
                System.out.println("Geçersiz Değer Tekrar Giriniz!");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Mağazadan ayrıldınız.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println();
        System.out.println("------------ Silahlar ------------");

        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() + " \tHasar: " + w.getDamage() + " \tPara: " + w.getPrice());
        }

        System.out.println("0 - Çıkış Yap");
        System.out.println("Paranız: " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void buyWeapon(){
        System.out.print("Bir silah seçiniz: ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Geçersiz Değer Tekrar Giriniz!");
            selectWeaponID = input.nextInt();
        }

        if (selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);

            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                    System.out.println();
                }
                else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız.");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }
            }
        }
    }

    public void printArmor(){
        System.out.println();
        System.out.println("------------ Zırhlar ------------");

        for (Armor a:Armor.armors()){
            System.out.println(a.getId() + " - " + a.getName() +
                    " Zırh\tEngelleme: " + a.getBlock() +
                    " Para: " + a.getPrice());
        }

        System.out.println("0 - Çıkış Yap");
        System.out.println("Paranız: " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.println("Geçersiz Değer Tekrar Giriniz!");
            selectArmorID = input.nextInt();
        }

        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorByID(selectArmorID);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > getPlayer().getMoney()){
                    System.out.println("Yeterli paranız bulunamamaktadır");
                    System.out.println();
                }
                else {
                    System.out.println(selectedArmor.getName() + " Zırhı satın aldınız.");
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                }
            }
        }
    }
}
