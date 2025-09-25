package com.nickfiorentino.model.mainpojoclasses;

import com.nickfiorentino.model.extrapojoitemclasses.*;


import java.util.List;

public class ItemPojo {

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
    private List<Appearance> appearances;

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

    public List<Appearance> getAppearances() {
        return appearances;
    }

    public void setAppearances(List<Appearance> appearances) {
        this.appearances = appearances;
    }

}
