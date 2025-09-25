package com.nickfiorentino.model.itemsnestedclasses;

 class ItemTest {

    private Links links;
    private Integer id;
    private String name;
    private Quality quality;
    private Integer level;
    private Integer requiredLevel;
    private Media media;
    private ItemClass itemClass;
    private ItemSubclass itemSubclass;
    private InventoryType inventoryType;
    private Integer purchasePrice;
    private Integer sellPrice;
    private Integer maxCount;
    private Boolean isEquippable;
    private Boolean isStackable;
    private PreviewItem previewItem;
    private Integer purchaseQuantity;

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Integer requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public ItemClass getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    public ItemSubclass getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(ItemSubclass itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Boolean getIsEquippable() {
        return isEquippable;
    }

    public void setIsEquippable(Boolean isEquippable) {
        this.isEquippable = isEquippable;
    }

    public Boolean getIsStackable() {
        return isStackable;
    }

    public void setIsStackable(Boolean isStackable) {
        this.isStackable = isStackable;
    }

    public PreviewItem getPreviewItem() {
        return previewItem;
    }

    public void setPreviewItem(PreviewItem previewItem) {
        this.previewItem = previewItem;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

}

 class AttackSpeed {

    private Integer value;
    private String displayString;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

}

 class Damage {

    private Integer minValue;
    private Integer maxValue;
    private String displayString;
    private DamageClass damageClass;

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public DamageClass getDamageClass() {
        return damageClass;
    }

    public void setDamageClass(DamageClass damageClass) {
        this.damageClass = damageClass;
    }

}



 class DamageClass {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

 class DisplayStrings {

    private String header;
    private String gold;
    private String silver;
    private String copper;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public String getSilver() {
        return silver;
    }

    public void setSilver(String silver) {
        this.silver = silver;
    }

    public String getCopper() {
        return copper;
    }

    public void setCopper(String copper) {
        this.copper = copper;
    }

}

 class Dps {

    private Double value;
    private String displayString;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

}

 class Durability {

    private Integer value;
    private String displayString;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

}

 class InventoryType {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

 class InventoryType__1 {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

 class Item {

    private Key__3 key;
    private Integer id;

    public Key__3 getKey() {
        return key;
    }

    public void setKey(Key__3 key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class ItemClass {

    private Key__1 key;
    private String name;
    private Integer id;

    public Key__1 getKey() {
        return key;
    }

    public void setKey(Key__1 key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class ItemClass__1 {

    private Key__5 key;
    private String name;
    private Integer id;

    public Key__5 getKey() {
        return key;
    }

    public void setKey(Key__5 key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class ItemSubclass {

    private Key__2 key;
    private String name;
    private Integer id;

    public Key__2 getKey() {
        return key;
    }

    public void setKey(Key__2 key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class ItemSubclass__1 {

    private Key__6 key;
    private String name;
    private Integer id;

    public Key__6 getKey() {
        return key;
    }

    public void setKey(Key__6 key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class Key {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__1 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__2 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__3 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__4 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__5 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Key__6 {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class Links {

    private Self self;

    public Self getSelf() {
        return self;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

}

 class Media {

    private Key key;
    private Integer id;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class Media__1 {

    private Key__4 key;
    private Integer id;

    public Key__4 getKey() {
        return key;
    }

    public void setKey(Key__4 key) {
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}

 class PreviewItem {

    private Item item;
    private Quality__1 quality;
    private String name;
    private Media__1 media;
    private ItemClass__1 itemClass;
    private ItemSubclass__1 itemSubclass;
    private InventoryType__1 inventoryType;
    private Weapon weapon;
    private SellPrice sellPrice;
    private Durability durability;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public SellPrice getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(SellPrice sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Durability getDurability() {
        return durability;
    }

    public void setDurability(Durability durability) {
        this.durability = durability;
    }

}

 class Quality {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

 class Quality__1 {

    private String type;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

 class Self {

    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}

 class SellPrice {

    private Integer value;
    private DisplayStrings displayStrings;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public DisplayStrings getDisplayStrings() {
        return displayStrings;
    }

    public void setDisplayStrings(DisplayStrings displayStrings) {
        this.displayStrings = displayStrings;
    }

}

 class Weapon {

    private Damage damage;
    private AttackSpeed attackSpeed;
    private Dps dps;

    public Damage getDamage() {
        return damage;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    public AttackSpeed getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(AttackSpeed attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public Dps getDps() {
        return dps;
    }

    public void setDps(Dps dps) {
        this.dps = dps;
    }


}
