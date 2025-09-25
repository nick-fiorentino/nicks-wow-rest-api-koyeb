package com.nickfiorentino.model.extrapojoitemclasses;



import java.util.List;

public class PreviewItem {

    private Item__1 item;
    private Quality__1 quality;
    private String name;
    private Media__1 media;
    private ItemClass__1 itemClass;
    private ItemSubclass__1 itemSubclass;
    private InventoryType__1 inventoryType;
    private Binding binding;
    private String uniqueEquipped;
    private Weapon weapon;
    private List<Stat> stats;
    private List<Spell> spells;
    private SellPrice sellPrice;
    private Requirements requirements;
    private Durability durability;

    public Item__1 getItem() {
        return item;
    }

    public void setItem(Item__1 item) {
        this.item = item;
    }

    public Quality__1 getQuality() {
        return quality;
    }

    public void setQuality(Quality__1 quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Media__1 getMedia() {
        return media;
    }

    public void setMedia(Media__1 media) {
        this.media = media;
    }

    public ItemClass__1 getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClass__1 itemClass) {
        this.itemClass = itemClass;
    }

    public ItemSubclass__1 getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(ItemSubclass__1 itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public InventoryType__1 getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType__1 inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Binding getBinding() {
        return binding;
    }

    public void setBinding(Binding binding) {
        this.binding = binding;
    }

    public String getUniqueEquipped() {
        return uniqueEquipped;
    }

    public void setUniqueEquipped(String uniqueEquipped) {
        this.uniqueEquipped = uniqueEquipped;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void setSpells(List<Spell> spells) {
        this.spells = spells;
    }

    public SellPrice getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(SellPrice sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public Durability getDurability() {
        return durability;
    }

    public void setDurability(Durability durability) {
        this.durability = durability;
    }

}
